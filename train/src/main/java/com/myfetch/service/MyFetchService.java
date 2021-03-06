package com.myfetch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.webservice.client.WebClient;

import com.myfetch.myfetch.dao.MyFetchDao;
import com.myfetch.service.http.util.HttpHtmlService;
import com.myfetch.service.parse.ParseHtml;
import com.myfetch.util.DedePublisherUtils;
import com.myfetch.util.FileWriterReaderUtils;
import com.myfetch.util.ServiceUtils;
import com.myfetch.util.XMLUtils;

public class MyFetchService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyFetchService.class);
	MyFetchDao dao = null;
	public static String bootPath = "D:\\xiaoshuoimg";
	public static String contentImgPath;
	public static String contentTxtPath;
	public final static String[] bookstatus = new String[] { "连载,", "完成,w", "完结,w" };

	public MyFetchDao getDao() {
		return dao;
	}

	public void setDao(MyFetchDao dao) {
		this.dao = dao;
	}

	/**
	 * 未采集完成列表 staus==0表示未完成 status==1表示完成 badbook =1的书籍不要采集，可能是收费书籍
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List unFetchList() {
		List list = this.dao.getUrlList();
		List retList = new ArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("0".equals(ObjectUtils.toString(map.get("badfetch")))) {
					if ("0".equals(ObjectUtils.toString(map.get("isfetch"))) || "".equals(ObjectUtils.toString(map.get("isfetch")))) {
						retList.add(map);
					}
				}
			}
		}
		return retList;
	}

	/**
	 * 采集完成列表 staus==0表示未完成 status==1表示完成
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List fetchList() {
		List list = this.dao.getUrlList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("1".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	public List getUnConverList() {
		List list = this.dao.getCoverList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("0".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	public List getConverList() {
		List list = this.dao.getCoverList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("1".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	public void disposeBookList(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseSiteList(XMLUtils.getDocumentXml(filename));
			String[] burls = StringUtils.split(map.get("burl"), "|");
			for (int i = 0; i < burls.length; i++) {
				String html = HttpHtmlService.getHtmlContent(burls[i]);
				List<Map<String, String>> bMap = ParseHtml.parseSiteInfo(html, map);
				for (Map<String, String> map2 : bMap) {
					if (this.dao.getFetchurlsUrl(map2.get("bookurl")) == 0) {
						int booknameCount = this.dao.getFetchUrlsByBookName(map2.get("bookname"));
						if (booknameCount > 0) {
							String type = this.dao.getBookTypeByName(map2.get("bookname"));
							if ("".equals(type)) {
								type = ObjectUtils.toString(map2.get("type"));
							}
							if ("".equals(type)) {
								type = this.dao.getBookTypeByName("空");
							}
							this.dao.saveBookUrl(map2.get("bookurl"), map2.get("status"), type, filename, map2.get("lastarc"), map2.get("bookname"), "1");
							logger.info("有重名的书籍存在请手工确认是否需要继续采集" + map2.get("bookname") + " /////  " + map2.get("bookurl"));
						} else {
							String type = this.dao.getBookTypeByName(map2.get("bookname"));
							if ("".equals(type)) {
								type = ObjectUtils.toString(map2.get("type"));
							}
							if ("".equals(type)) {
								type = this.dao.getBookTypeByName("空");
							}
							this.dao.saveBookUrl(map2.get("bookurl"), map2.get("status"), type, filename, map2.get("lastarc"), map2.get("bookname"), "0");
						}
					} else {
						logger.info("该书的地址已经存在" + map2.get("bookurl"));
					}
				}
			}
		}
	}

	/**
	 * 采集书籍类型分类关系表，，针对分类混乱的采集站使用 分类已"爱书者"的分类为准
	 * 
	 * @param files
	 */
	public void disposeBookMap(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseBookTypeList(XMLUtils.getDocumentXml(filename));
			Map<String, String> typeMap = XMLUtils.parseSiteList(XMLUtils.getDocumentXml(filename));
			String[] splitstr = null;
			if (StringUtils.indexOf(map.get("url"), ",") != -1) {
				splitstr = StringUtils.split(map.get("url"), ",");
				splitstr[0] = StringUtils.substring(splitstr[0], (StringUtils.lastIndexOf(splitstr[0], "/") + 1));
				splitstr[1] = StringUtils.substring(splitstr[1], 0, (StringUtils.indexOf(splitstr[1], ".")));
			}
			String[] burls = new String[NumberUtils.toInt(splitstr[1])];// StringUtils.split(map.get("burl"),"|");
			String preStr = StringUtils.substring(map.get("url"), 0, StringUtils.lastIndexOf(map.get("url"), "/") + 1);
			for (int i = 0; i < burls.length; i++) {
				burls[i] = preStr + (i + 1) + ".htm";
				typeMap.remove("burl");
				typeMap.put("burl", burls[i]);
			}
			for (int i = 0; i < burls.length; i++) {
				String html = HttpHtmlService.getHtmlContent(burls[i]);
				List<Map<String, String>> bMap = ParseHtml.parseSiteInfo(html, typeMap);
				for (Map<String, String> map2 : bMap) {
					this.dao.saveBookType(map2.get("bookname"), map2.get("type"), map2.get("bookurl"));
				}
			}
		}
	}

	/**
	 * file，name bookconversite 参考aishuzhe.xml 加上直接采集书籍URL 不建议直接采集书籍封面 这样会采集不到是否连载，最后更新章节等 实在要使用封面直接采集建议可以在数据库配置
	 * 
	 * @param files
	 */
	@SuppressWarnings("unchecked")
	public void disposeBookConver(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseConverXml(XMLUtils.getDocumentXml(filename));
			// 这种抓取方式还未完善不推荐使用
			if (!"".equals(ObjectUtils.toString(map.get("urls")))) {
				String[] urls = StringUtils.split(map.get("urls"), "|");
				for (int i = 0; i < urls.length; i++) {
					int id = this.dao.saveBookUrl(urls[i], null, null, filename, null, null, "0");
					String html = HttpHtmlService.getHtmlContent(urls[i]);
					logger.info("开始抓取封面地址为：" + urls[i]);
					List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
					for (Map<String, String> map2 : converMap) {
						if (!"".equals(map2.get("chapterlisturl")) && this.dao.getFetchbookconverUrl(map2.get("chapterlisturl")) == 0) {
							this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2.get("author")),
									ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")), id);
							// this.dao.updateFetchUrlsType(NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))),map2.get("type"));
						} else {
							logger.info("该封面地址列表地址已存在:;" + map2.get("chapterlisturl"));
						}
					}
				}
			} else {
				List bList = unFetchList();
				for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
					Map bMap = (Map) iterator.next();
					String bookUrl = ObjectUtils.toString(bMap.get("url"));
					if (!bookUrl.contains(files)) {
						continue;
					}
					if (!"".equals(bookUrl) && StringUtils.contains(bookUrl, files)) {
						String html = HttpHtmlService.getHtmlContent(bookUrl);
						logger.info("开始抓取封面地址为：" + bMap.get("url"));
						List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
						for (Map<String, String> map2 : converMap) {
							if (!"".equals(map2.get("chapterlisturl")) && this.dao.getFetchbookconverUrl(map2.get("chapterlisturl")) == 0) {
								this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2.get("author")),
										ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")), NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))));
								if (!"".equals(ObjectUtils.toString(map2.get("type")))) {
									this.dao.updateFetchUrlsType(NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))), map2.get("type"));
								}
							} else {
								logger.info("该封面地址列表地址已存在:;" + map2.get("chapterlisturl"));
							}
						}
					}
				}
			}

		}
	}

	/**
	 * 书籍章节列表
	 * 查重方式
	 * 1、URL查重
	 * 2、章节名称查重
	 * 	a)去掉除汉字之外的字符，如：空格、【】，[]，“”，‘’，"",'',以空格分隔的单独字"图"字,如果相同就重复
	 * 	b)以空格分隔章节名称，判断是否含有卷字，是否含有章字，章节名称，图,如：卷[0]|章[1]|实际章节名称[2]|图[3]
	 * 	  如果0+1相同就重复
	 *   如果1+2相同就重复
	 *   
	 *  	
	 * @param files
	 */
	@SuppressWarnings("unchecked")
	public void disposeBookChapter(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseChapterXml(XMLUtils.getDocumentXml(filename));
			List bList = this.getUnConverList();
			for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
				Map bMap = (Map) iterator.next();
				String bookListUrl = ObjectUtils.toString(bMap.get("booklisturl"));
				if (!bookListUrl.contains(files)) {
					continue;
				}
				if (!"".equals(bookListUrl) && StringUtils.contains(bookListUrl, files)) {
					String html = HttpHtmlService.getHtmlContent(bookListUrl);
					logger.info("开始抓取书籍章节列表地址为：" + bMap.get("booklisturl"));
					// 如果已经抓取过得处理
					// TODO 这里更行抓取有问题
					List<Map<String, String>> chapterurls = ParseHtml.parseChapterList(html, map, bookListUrl);
					if (CollectionUtils.isNotEmpty(this.dao.getFetchchapterurlsByBookid(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")))))) {

						List lastList = this.dao.getFetchchapterurlsListByBookid(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
						String lastarc = (String) ((Map) lastList.get(0)).get("chapterurl");
						boolean flag = false;
						Integer columnId = -1;
						Integer bookid = -1;
						Map<String, Integer> mapColumnId = new HashMap<String, Integer>();
						for (Map<String, String> map2 : chapterurls) {
							if (!StringUtils.equals("", ObjectUtils.toString(map2.get("columnname")))) {
								columnId = this.dao.saveFetchBookColumn(map2.get("columnname"), NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
								mapColumnId.put(map2.get("uuid"), columnId);
							}
							String curls = ObjectUtils.toString(map2.get("chaptercontenturl"));
							if ("".equals(curls) && StringUtils.equals("", ObjectUtils.toString(map2.get("columnname")))) {
								continue;
							}
							if (StringUtils.equals(curls, lastarc)) {
								flag = true;
								continue;
							}
							if (!flag && curls.contains("###")) {
								if (StringUtils.equals(curls.split("###")[0], lastarc) || StringUtils.equals(curls.split("###")[1], lastarc)) {
									flag = true;
									continue;
								}
							}
							if (flag) {
								logger.info("新增书籍章节地址:;" + map2.get("chaptercontenturl"));
								if (StringUtils.equals("", ObjectUtils.toString(map2.get("columnname")))) {
									Integer columnid = 0;
									if (columnId > 0) {
										columnid = mapColumnId.get(map2.get("uuid"));
										bookid = NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
									} else {
										bookid = NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
										columnid = -1;
									}
									this.dao.saveChapterInfo(map2.get("chaptername"), map2.get("chaptercontenturl"), bookid, columnid);
								}
							} else {
								// logger.info("该列表地址书籍章节已存在:;" + map2.get("chaptercontenturl"));
							}
						}
					} else {
						Integer columnId = -1;
						Integer bookid = -1;
						Map<String, Integer> mapColumnId = new HashMap<String, Integer>();
						for (Map<String, String> map2 : chapterurls) {
							// TODO 涉及到更新章节的时候这里要注意查重
							if ("".equals(ObjectUtils.toString(map2.get("chaptercontenturl"))) && StringUtils.equals("", ObjectUtils.toString(map2.get("columnname")))) {
								continue;
							}
							if (!StringUtils.equals("", ObjectUtils.toString(map2.get("columnname")))) {
								columnId = this.dao.saveFetchBookColumn(map2.get("columnname"), NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
								mapColumnId.put(map2.get("uuid"), columnId);
							} else {
								Integer columnid = 0;
								if (columnId > 0) {
									columnid = mapColumnId.get(map2.get("uuid"));
									bookid = NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
								} else {
									bookid = NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
									columnid = -1;
								}
								this.dao.saveChapterInfo(map2.get("chaptername"), map2.get("chaptercontenturl"), bookid, columnid);
							}
						}
					}
				}
			}

		}
	}

	/**
	 * 支持抓取图片
	 * @param files
	 * @throws IOException 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void disposeChapterContent(String files) throws IOException {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseContentXml(XMLUtils.getDocumentXml(filename));
			List bList = this.dao.getLimitContentList("0", "0", files, 100);
			while (CollectionUtils.isNotEmpty(bList)) {
				for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
					Map bMap = (Map) iterator.next();
					String chapterurl = ObjectUtils.toString(bMap.get("chapterurl"));
					if (!chapterurl.contains(files)) {
						continue;
					}
					if (!"".equals(chapterurl) && StringUtils.contains(chapterurl, files)) {
						String contentUrl = chapterurl;
						String fetchURL = contentUrl;
						if (contentUrl.contains("###")) {
							fetchURL = contentUrl.split("###")[0];
						}
						String html = HttpHtmlService.getHtmlContent(fetchURL);
						if (StringUtils.isBlank(html) && contentUrl.contains("###")) {
							fetchURL = contentUrl.split("###")[1];
							html = HttpHtmlService.getHtmlContent(fetchURL);
						}
						List<Map<String, String>> contentList = ParseHtml.parseChapterContent(html, map,contentTxtPath, ObjectUtils.toString(bMap.get("id")), ObjectUtils.toString(bMap.get("bookid")));
						// logger.info("开始抓取内容地址为：" + fetchURL);
						for (Map<String, String> map2 : contentList) {
//							this.dao.saveContentInfo(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))), ObjectUtils.toString(bMap.get("id")), map2.get("contentbody"), map2.get("title"));
							FileWriterReaderUtils.writeBodyToTxt(contentTxtPath, ObjectUtils.toString(bMap.get("id")), ObjectUtils.toString(bMap.get("bookid")), map2.get("contentbody"));
							this.dao.updateIsFetchFetchChapterUrls(NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))));
						}
					}
				}
				bList = this.dao.getLimitContentList("0", "0", files, 100);
			}
		}
	}

	/**
	 * 数据转移到dedecms数据库
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void saveDataToDedecms(String sitePattern) {
		int minArcId = -1;
		Map<String, String> typeMap = new HashMap<String, String>();
		Map<String, String> bookMap = new HashMap<String, String>();
		List arcList = new ArrayList();
		try {
			List list = this.dao.getSiteData();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map bookBasic = (Map) iterator.next();
				if (!StringUtils.contains(ObjectUtils.toString(bookBasic.get("url")), sitePattern)) {
					continue;
				}
				Integer bookid = NumberUtils.toInt(ObjectUtils.toString(bookBasic.get("id")));

				Integer typeid = this.dao.getTypeidByfetchId(ObjectUtils.toString(bookBasic.get("type")));
				if (typeid.intValue() == -1) {
					continue;
				}

				// 保存小说基本信息
				Map map = this.dao.getBookInfoById(bookid);
				if (map == null || "".equals(ObjectUtils.toString(map.get("bookname")))) {
					continue;
				}
				logger.info("开始转移抓取数据，书名为：：" + ObjectUtils.toString(map.get("bookname")));

				// 是否有需要更新的章节
				int retPublish = this.dao.getIntPublishBook(bookid);
				Integer parentArcId = 0;
				if (retPublish == 0) {
					FileWriterReaderUtils.imageDownload(map);
					parentArcId = this.dao.saveConverForDede(typeid, bookid, map);
				} else {
					if ("".equals(ObjectUtils.toString(bookBasic.get("bookname")))) {
						logger.info("要求注意的地方" + ObjectUtils.toString(bookBasic.get("url")) + "发布失败!!!!");
						continue;
					}
					parentArcId = this.dao.getPublishBookIdByBookId(ObjectUtils.toString(map.get("bookname")), typeid);
					if (parentArcId < 1) {
						logger.info("要求注意的地方" + ObjectUtils.toString(bookBasic.get("url")) + "发布失败!!!!");
						continue;
					}
				}

				// 保存章节 抓取的章节列表

				arcList = this.dao.getFetchArcListByBookId(bookid);
				int bookCount = this.dao.getFetchArcCountByBookId(bookid);
				int i = 0;
				while (CollectionUtils.isNotEmpty(arcList)) {
					for (Iterator iterator2 = arcList.iterator(); iterator2.hasNext();) {
						Map arcMap = (Map) iterator2.next();
						i++;
						boolean isAddLastId = false;
						if (bookCount == i) {
							isAddLastId = true;
						}
						Integer arcId = this.dao.saveArticleForDede(typeid, bookid, map, arcMap, parentArcId, isAddLastId);
						// 保存章节内容 发布信息（只保存最小值）
						if (minArcId == -1) {
							minArcId = arcId;
						} else if (arcId < minArcId) {
							minArcId = arcId;
						}
						// 保存目录、封面发布信息
						if (parentArcId > 0 && !bookMap.containsKey(ObjectUtils.toString(parentArcId))) {
							bookMap.put(ObjectUtils.toString(parentArcId), ObjectUtils.toString(parentArcId));
						}
						// 保存发布TYPEID
						if (!typeMap.containsKey(ObjectUtils.toString(typeid))) {
							typeMap.put(ObjectUtils.toString(typeid), ObjectUtils.toString(typeid));
						}
						this.dao.updateIsMoveFetchChapterUrls((Integer) arcMap.get("id"));
					}
					arcList = this.dao.getFetchArcListByBookId(bookid);
				}
				// 更新完结书籍的抓取状态
				boolean isOver = false;
				String status = ObjectUtils.toString(bookBasic.get("status"));
				if (!"".equals(status)) {
					for (int j = 0; j < MyFetchService.bookstatus.length; j++) {
						String[] st = StringUtils.split(MyFetchService.bookstatus[j], ",");
						if (st.length == 2) {
							if (StringUtils.equals(status.trim(), st[0])) {
								if (!"".equals(st[1]) && "w".equals(st[1])) {
									isOver = true;
									break;
								}
							}
						}
					}
				}
				if (isOver) {
					this.dao.updateFetchUrls(bookid);
				}
				arcList = null;
				System.gc();
			}
			// 把需要发布的内容写入文件

		} catch (Exception e) {
			logger.error("转移数据失败：" + e.getMessage());
			e.printStackTrace();
		}
		ServiceUtils.writePublish(typeMap, bookMap, minArcId);
	}



	public Boolean isHaveLastUpdate(String lastarcName, String url) {
		int i = this.dao.getFetchurlsLastUpdate(lastarcName, url);
		return i > 0 ? false : true;
	}

	public void testDao() {
	}

	/**
	 * 同时自动发布采集数据 发布顺序为:1、更新缓存 2、文档部署 3、目录部署 4、封面部署 5、列表部署 6、首页部署
	 * 
	 * @param pro
	 */
	public void dedePublisher(Properties pro) {
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisher.properties");
			String typeIds = prop.getProperty("typeids");
			String bookIds = prop.getProperty("bookids");
			String minArcId = prop.getProperty("minarcid").trim();
			WebClient client = new WebClient();
			DedePublisherUtils.login(client, pro);
			DedePublisherUtils.updateDedeCache(client, pro);
			String[] typeid = StringUtils.split(typeIds, ",");
			String[] bookid = StringUtils.split(bookIds, ",");
			if (!"".equals(minArcId) && !"-1".equals(minArcId)) {
				DedePublisherUtils.publishArchives(client, pro, minArcId, "0");
			}
			for (int i = 0; i < bookid.length; i++) {
				if (!"".equals(bookid[i])) {
					Map map = this.dao.getDedeArchivesPreIdByBookId(bookid[i], minArcId);
					if (MapUtils.isNotEmpty(map) && !"".equals(ObjectUtils.toString(map.get("id")))) {
						DedePublisherUtils.publishArchives(client, pro, ObjectUtils.toString(map.get("id")), ObjectUtils.toString(map.get("id")));
					}
				}
			}
			for (int i = 0; i < bookid.length; i++) {
				if (!"".equals(bookid[i])) {
					DedePublisherUtils.publishTable(client, pro, bookid[i]);
				}
			}
			for (int i = 0; i < bookid.length; i++) {
				if (!"".equals(bookid[i])) {
					DedePublisherUtils.publishPage(client, pro, bookid[i]);
				}
			}
			for (int i = 0; i < typeid.length; i++) {
				if (!"".equals(typeid[i])) {
					DedePublisherUtils.publishList(client, pro, typeid[i]);
				}
			}
			DedePublisherUtils.publishHomePage(client, pro);
		} catch (IOException e) {
			logger.error("发布数据失败:" + e.getMessage());
		}

	}

	public void disposeFetchId(String sitename) {
		List<String> list = XMLUtils.getXmlFileName(sitename);
		for (String filename : list) {
			XMLUtils.genFetchId(java.util.UUID.randomUUID().toString(), filename);
		}
	}
	@SuppressWarnings("unchecked")
	public void moveDataForTxt(String filePath) throws IOException{
		List list=this.dao.getDedeArchives(0,100);
		int i=100;
		while(CollectionUtils.isNotEmpty(list)){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				FileWriterReaderUtils.writeBodyToTxt(filePath,ObjectUtils.toString(map.get("id")),ObjectUtils.toString(map.get("isbookpage")),ObjectUtils.toString(map.get("body")));
			}
			list=this.dao.getDedeArchives(i,i+=100);
		}
	}


}
