package com.iris.scholar.system.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iris.scholar.server.ws.dao.ScholarFetchDao;

public class WriteDataSOAPTask implements Runnable {
	private static final Log log = LogFactory.getLog(WriteDataSOAPTask.class);
	private ScholarFetchDao fetchDao = null;
	private static ScholarFetchDao fetchDaoStatic = null;
	private SOAPMessage soapMsg;
	private JdbcTemplate jdbcTemplate;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/spring/applicationContext.xml","classpath:/spring/applicationContext-database.xml","classpath:/spring/applicationContext-dao.xml"});
		fetchDaoStatic=(ScholarFetchDao) context.getBean("scholarFetchDao");
	}
	public WriteDataSOAPTask() {
	}

	public WriteDataSOAPTask(ScholarFetchDao dao) {
		this.fetchDao = dao;
	}

	public WriteDataSOAPTask(SOAPMessage msg) {
		this.soapMsg = msg;
	}

	public void call(SOAPMessage message) throws Exception {
		if (soapMsg != null) {
			Lock lock = soapMsg.getLock();
			try {
				if (lock.tryLock()) {
					wrireData(soapMsg);
				} else {
					lock.lock();
					lock.unlock();
				}
			} catch (Exception e) {
				synchronized (WriteDataSOAPService.MSG_COUNT_MAP) {
					String[] msgs = soapMsg.getMessageId().split("#");
					if (msgs.length == 2) {
						// WriteDataSOAPService.MSG_COUNT_MAP.add(new
						// SOAPMessageResult(msg.getMessageId(), retInt));
						if (WriteDataSOAPService.MSG_COUNT_MAP.containsKey(msgs[0])) {
							if (!"".equals(ObjectUtils.toString(WriteDataSOAPService.MSG_COUNT_MAP.get(msgs[0]))) && WriteDataSOAPService.MSG_COUNT_MAP.get(msgs[0]) > 0) {
								int value = WriteDataSOAPService.MSG_COUNT_MAP.remove(msgs[0]);
								value = value - 1;
								WriteDataSOAPService.MSG_COUNT_MAP.put(msgs[0], value);
								if(MapUtils.isEmpty(WriteDataSOAPService.RESULT_MAP)){
									String infoValue=0+"|"+0+"|"+0+"|0";
									WriteDataSOAPService.RESULT_MAP.put(msgs[0], infoValue);
								}else if(WriteDataSOAPService.RESULT_MAP.containsKey(msgs[0])){
									String valueStr=WriteDataSOAPService.RESULT_MAP.remove(msgs[0]);
									String[] infoValue=StringUtils.split(valueStr,"|");
									valueStr=(NumberUtils.toInt(infoValue[0])+0)+"|"+(NumberUtils.toInt(infoValue[1])+0)+"|"+(NumberUtils.toInt(infoValue[2])+0)+"|"+(NumberUtils.toInt(infoValue[3])+1);
									WriteDataSOAPService.RESULT_MAP.put(msgs[0], valueStr);
								}
							} else {

							}
						}
					}
				}
				e.printStackTrace();
				lock.lock();
				lock.unlock();
			}
		}
	}

	private int wrireData(SOAPMessage msg) {
		int retInt = 0;
		int dupInt = 0;
		int insertInt = 0;
		// int resultInsertInt[];
		if (this.getFetchDao() == null) {
			log.info("fetchDao ======================= is null");
			fetchDao=fetchDaoStatic;
			if(fetchDaoStatic==null){
				log.info("fetchDaoStatic ======================= is null");
			}
		}
		String data = msg.getXmlData();
		List<Map> list = new ArrayList<Map>();
		int ins_id = msg.getInsId(), dbid = msg.getDbid(), year = msg.getYear();
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(data)) {
				Document doc = DocumentHelper.parseText(data);
				List listNode = doc.selectNodes("//scholarWorks/data");
				if(listNode == null|| listNode.size()==0)
				{
					log.info("~~~~~~~~~~本次上传xml:" + data);
				}
				log.info("~~~~~~~~~~本次上传条数:" + ObjectUtils.toString(listNode != null ? listNode.size() : 0));
				for (Iterator iterator = listNode.iterator(); iterator.hasNext();) {
					Map<String, Object> map = new HashMap<String, Object>();
					Node e = ((Node) iterator.next());
					Element ePub = (Element) (e.selectSingleNode("publication"));
					for (Iterator it = ePub.attributeIterator(); it.hasNext();) {
						Attribute attribute = (Attribute) it.next();
						map.put(!"".equals(ObjectUtils.toString(attribute.getName())) ? attribute.getName().toLowerCase() : "", attribute.getValue());
					}
					String dataXml = e.asXML();
					if (log.isDebugEnabled()) {
						// log.info("~~  "+dataXml);
					}
					if (map != null && !map.isEmpty()) {
						// List listId=null;
						// if(fetchDao==null)
						// {
						// this.getFetchDao().getSearchInfoId(ins_id, year,
						// dbid);
						// }else{
						//							
						// }
						List listId = this.getFetchDao().getSearchInfoId(ins_id, year, dbid);
						if (CollectionUtils.isEmpty(listId)) {
							continue;
						}
						flag = this.getFetchDao().isDupDbcache(ins_id, map, dbid);
						if (!flag) {
							insertInt+=this.getFetchDao().saveDbcacheXml(dataXml,ins_id,map,ObjectUtils.toString(((Map)listId.get(0)).get("ID")),dbid,year);
							//this.createDbcacheBacth(dataXml,ins_id,map,ObjectUtils.toString(((Map)listId.get(0)).get("ID")),dbid,year));
							map.put("dataXml", dataXml);
							map.put("searchInfoId", ObjectUtils.toString(((Map) listId.get(0)).get("ID")));
							list.add(map);
							retInt++;
						} else {
							retInt++;
							log.info("有重复记录 ctitle==" + ObjectUtils.toString(map.get("ctitle")) + "有重复记录 etitle==" + ObjectUtils.toString(map.get("etitle")));
							dupInt++;
						}
					}
				}
//				retInt += this.getFetchDao().saveDbcacheXml(ins_id, list, dbid, year);
				// for (int i = 0; i < resultInsertInt.length; i++) {
				// retInt+=resultInsertInt[i];
				// }
				log.info("~~~~~~~~本次插入成功条数:" + insertInt);
				log.info("~~~~~~~~本次重复条数:" + dupInt);
				log.info("~~~~~~~~本次返回条数:" + retInt);
			} else {
			}
		} catch (DocumentException e) {
			log.error(e);
		}
		synchronized (WriteDataSOAPService.MSG_COUNT_MAP) {
			String[] msgs = msg.getMessageId().split("#");
			if (msgs.length == 2) {
				// WriteDataSOAPService.MSG_COUNT_MAP.add(new
				// SOAPMessageResult(msg.getMessageId(), retInt));
				if (WriteDataSOAPService.MSG_COUNT_MAP.containsKey(msgs[0])) {
					if (!"".equals(ObjectUtils.toString(WriteDataSOAPService.MSG_COUNT_MAP.get(msgs[0]))) && WriteDataSOAPService.MSG_COUNT_MAP.get(msgs[0]) > 0) {
						int value = WriteDataSOAPService.MSG_COUNT_MAP.remove(msgs[0]);
						value = value - 1;
						WriteDataSOAPService.MSG_COUNT_MAP.put(msgs[0], value);
						if(MapUtils.isEmpty(WriteDataSOAPService.RESULT_MAP)||!WriteDataSOAPService.RESULT_MAP.containsKey(msgs[0])){
							String infoValue=retInt+"|"+dupInt+"|"+insertInt+"|0";
							WriteDataSOAPService.RESULT_MAP.put(msgs[0], infoValue);
						}else if(WriteDataSOAPService.RESULT_MAP.containsKey(msgs[0])){
							String valueStr=WriteDataSOAPService.RESULT_MAP.remove(msgs[0]);
							String[] infoValue=StringUtils.split(valueStr,"|");
							valueStr=(NumberUtils.toInt(infoValue[0])+retInt)+"|"+(NumberUtils.toInt(infoValue[1])+dupInt)+"|"+(NumberUtils.toInt(infoValue[2])+insertInt)+"|"+(NumberUtils.toInt(infoValue[3])+0);
							WriteDataSOAPService.RESULT_MAP.put(msgs[0], valueStr);
						}
					} else {

					}
				}
			}
		}
		return retInt;
	}

	public ScholarFetchDao getFetchDao() {
		return fetchDao;
	}

	public void setFetchDao(ScholarFetchDao fetchDao) {
		this.fetchDao = fetchDao;
	}

	@Override
	public void run() {
		try {
			call(getSoapMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List getSearchInfoId(Integer ins_id, Integer year, Integer dbid) {
		String sql = "select t.id from dbcache_searchinfo t where t.year=? and t.ins_id = ? and t.dbid =?";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { year, ins_id, dbid });
		return list;
	}

	/**
	 * 写数据的时候查重
	 * 
	 * @param ins_id
	 * @param map
	 * @return
	 */
	public boolean isDupDbcache(Integer ins_id, Map<String, Object> map, Integer dbid) {
		String title = "".equals(ObjectUtils.toString(map.get("ctitle")).trim()) ? ObjectUtils.toString(map.get("etitle")) : ObjectUtils.toString(map.get("ctitle"));
		String original = ObjectUtils.toString(map.get("original")).trim();
		String sql = "select count(*) from dbcache t where t.ins_id=? and t.dbid=? and nvl(ctitle,etitle)=? and t.original=?";
		int i = this.getJdbcTemplate().queryForInt(sql, new Object[] { ins_id, dbid, title, original });
		return i > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public int saveDbcacheXml(Integer ins_id, List<Map> list, Integer dbid, Integer year) {
		try {
			final Integer f_dbid = dbid;
			final Integer pubyear = year;
			final Integer ins_id_final = ins_id;
			final List<Map> listFinal = list;
			String saveSql = "insert into dbcache (XML_ID,ins_id,xml_data,pubyear,dbid,etitle,ctitle,author_names,authors_names_spec,ekeywords,ckeywords,source_url,searchinfoid,fulltext_url,organizer,pub_type,original)"
					+ " values (SEQ_DBCACHE.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter batchPstm = new BatchPreparedStatementSetter() {
				public int getBatchSize() {
					return listFinal.size();
				}

				public void setValues(PreparedStatement psmt, int index) {
					String etitle = ObjectUtils.toString(listFinal.get(index).get("etitle"));
					String ctitle = ObjectUtils.toString(listFinal.get(index).get("ctitle"));
					String author_names = ObjectUtils.toString(listFinal.get(index).get("author_names"));
					String author_names_spec = ObjectUtils.toString(listFinal.get(index).get("authors_names_spec"));
					String ekeywords = ObjectUtils.toString(listFinal.get(index).get("ekeywords"));
					String ckeywords = ObjectUtils.toString(listFinal.get(index).get("ckeywords"));
					String source_url = ObjectUtils.toString(listFinal.get(index).get("source_url"));
					String s_id = ObjectUtils.toString(listFinal.get(index).get("searchInfoId"));
					String fulltext_url = ObjectUtils.toString(listFinal.get(index).get("fulltext_url"));
					Integer pub_type = "".equals(ObjectUtils.toString(listFinal.get(index).get("pub_type")).trim()) ? null : NumberUtils.toInt(ObjectUtils.toString(listFinal.get(index).get("pub_type")).trim());
					String organizer = ObjectUtils.toString(listFinal.get(index).get("organizer"));
					String original = ObjectUtils.toString(listFinal.get(index).get("original"));
					String xml_final = ObjectUtils.toString(listFinal.get(index).get("dataXml"));
					try {
						psmt.setInt(1, ins_id_final);
						psmt.setString(2, xml_final);
						psmt.setObject(3, pubyear);
						psmt.setObject(4, f_dbid);
						psmt.setObject(5, etitle);
						psmt.setObject(6, ctitle);
						psmt.setObject(7, author_names);
						psmt.setObject(8, author_names_spec);
						psmt.setObject(9, ekeywords);
						psmt.setObject(10, ckeywords);
						psmt.setObject(11, source_url);
						psmt.setObject(12, s_id);
						psmt.setObject(13, fulltext_url);
						psmt.setObject(14, organizer);
						psmt.setObject(15, pub_type);
						psmt.setObject(16, original);
					} catch (SQLException e) {
						log.error(e);
					}
				}
			};
			this.getJdbcTemplate().batchUpdate(saveSql, batchPstm);
			return list.size();
		} catch (Exception e) {
			log.error("error  info ==========" + e);
			return 0;
		}
	}

	public SOAPMessage getSoapMsg() {
		return soapMsg;
	}

	public void setSoapMsg(SOAPMessage soapMsg) {
		this.soapMsg = soapMsg;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
