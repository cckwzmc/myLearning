package com.lyxmq.blog.publisher.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.LobHandler;


/**
 * HibernateBaseDao基类
 * 
 * @author 
 * 
 */

public class JdbcBaseDao extends JdbcDaoSupport {

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	public JdbcBaseDao() {
		// entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	private SimpleJdbcTemplate simpleJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	private LobHandler lobHandler = null;


	/**
	 * 存储过程返回分页，存储过程编写的时候注意，参数顺序如下： 1.变量（可选，入参） 2.排序（必选，入参）
	 * 3.页码（必选，出入参），当page=0的时候，返回所有结果不分页 4.每页行数（必选，出入参） 5.总行数（必选，出参）
	 * 6.结果集游标（必选，出参）
	 * 
	 * @param storeSql
	 * @param pramList
	 * @param page
	 * @param objectsPerPage
	 * @return
	 */
	// public PaginatedList QueryTableByStore(String storeSql, Object[]
	// pramList,
	// Integer page, Integer objectsPerPage)
	// {
	// PaginatedListHelper recordSet = new PaginatedListHelper();
	// /*
	// //排序字段
	// ArrayList arrayList = (ArrayList)ThreadUtil.get("orderArray");
	//    	
	// Object[] order = arrayList.toArray();
	// String orderString = "";
	// if(order.length > 0)
	// {
	//    	    
	// for (int i = 1; i < order.length; i++)
	// {
	// if (order[i] != null && !"".equalsIgnoreCase(order[i].toString()))
	// {
	// if (!"".equalsIgnoreCase(orderString))
	// {
	// orderString = orderString + ",";
	// }
	// orderString = orderString + order[i] + " " + order[0].toString();
	// }
	// }
	// }
	// */
	// //看是不是要打印到excel,如果是就全部输出
	// //Boolean isExcel = (Boolean)ThreadUtil.get("isExcel");
	// //if (isExcel.booleanValue() == true)
	// //{
	// // page=0;//表示查询所有
	// //}
	//
	// LinkedHashMap<String, Object> inOutParamList=new LinkedHashMap<String,
	// Object>();
	// inOutParamList.put("page", page);
	// inOutParamList.put("objectsPerPage", objectsPerPage);
	//		
	// LinkedHashMap<String, Integer> outParamList=new LinkedHashMap<String,
	// Integer>();
	// outParamList.put("rs_count", Types.INTEGER);
	//		
	// // Map results=executeByStore(storeSql,new
	// Object[]{pramList,orderString},inOutParamList,outParamList,new
	// Object[]{"RS_REF_CURSOR"});
	// Map
	// results=executeByStore(storeSql,pramList,inOutParamList,outParamList,new
	// Object[]{"RS_REF_CURSOR"});
	//		
	// recordSet.setFullListSize(Integer.valueOf(results.get("rs_count")+""));
	// recordSet.setList((ArrayList)results.get("RS_REF_CURSOR"));
	// recordSet.setPageNumber(Integer.valueOf(results.get("page")+""));
	// recordSet.setObjectsPerPage(objectsPerPage.intValue());
	//
	// return recordSet;
	// }
	/**
	 * 
	 * @param count总记录数
	 * @param objectsPerPage每页显示记录数
	 * @return pageCount总页数
	 */
	public int pageCount(int count, int objectsPerPage) {

		int intPageCount = (count) / objectsPerPage;
		if (count > intPageCount * objectsPerPage)
			intPageCount = intPageCount + 1;
		if (intPageCount <= 0)
			intPageCount = 1;
		return intPageCount;
	}

	/**
	 * @return lobHandler
	 */
	public LobHandler getLobHandler() {
		return lobHandler;
	}

	/**
	 * @param lobHandler
	 *            要设置的 lobHandler
	 */
	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	//
	// public JdbcTemplate getJdbcTemplate() {
	// // this.jdbcTemplate = new JdbcTemplate();
	// // this.jdbcTemplate.setDataSource( super.getHibernateTemplate().);
	// return jdbcTemplate;
	// }
	//
	// public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		if (simpleJdbcTemplate == null) {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
		}
		return simpleJdbcTemplate;
	}

	/**
	 * 单纯调用存储过程，不需要特别返回值
	 * 
	 * @param storeSql
	 * @param pramList
	 */
	// public void updateByStore(String storeSql,Object[] pramList)
	// {
	// executeByStore(storeSql,pramList,null,null,null);
	// }
	/**
	 * 返回只有一个出参的结果
	 * 
	 * @param storeSql
	 * @param pramList
	 * @param outParamName
	 * @param outParamType
	 * @return
	 */
	// public Object queryForObjectByStore(String storeSql,Object[]
	// pramList,String outParamName,int outParamType)
	// {
	// StoreTemplate st = new StoreTemplate();
	// st.setDataSource(jdbcTemplate.getDataSource());
	// st.setSql(storeSql);
	// //处理入参
	// if(pramList!=null)
	// {
	// for(int i=0;i<pramList.length;i++)
	// {
	// st.setParamValue(String.valueOf(i), pramList[i] );
	// }
	// }
	// st.setOutParam(outParamName, outParamType);
	//		
	// //执行
	// Map results = st.execute();
	// // 输出返回值
	// return results.get(outParamName);
	// }
	/**
	 * 输入一个出入参，返回只有一个出参的结果
	 * 
	 * @param storeSql
	 * @param pramList
	 * @param outParamName
	 * @param outParamType
	 * @return
	 */
	// public Object queryForObjectByStore(String storeSql,Object[]
	// pramList,String inOutParamName,Object value)
	// {
	// StoreTemplate st = new StoreTemplate();
	// st.setDataSource(jdbcTemplate.getDataSource());
	// st.setSql(storeSql);
	// //处理入参
	// if(pramList!=null)
	// {
	// for(int i=0;i<pramList.length;i++)
	// {
	// st.setParamValue(String.valueOf(i), pramList[i] );
	// }
	// }
	// st.setInOutParam(inOutParamName, value);
	//		
	// //执行
	// Map results = st.execute();
	// // 输出返回值
	// return results.get(inOutParamName);
	// }
	/**
	 * 调用此方法进行存储过程操作的时候，要注意存储过程定义的变量类型需要依照以下顺序执行：入参，出入参，出参，出游标结果集
	 * 
	 * @param storeSql
	 *            存储过程名称
	 * @param pramList
	 *            入参数
	 * @param inOutPramMap
	 *            出入参数
	 * @param outPramMap
	 *            出参数
	 * @param outCurList
	 *            出游标返回结果集
	 * @return
	 */
	// public Map executeByStore(String storeSql,Object[]
	// pramList,LinkedHashMap<String, Object> inOutPramMap,LinkedHashMap<String,
	// Integer> outPramMap,Object[] outCurList) {
	//
	// Iterator myEnumerator=null;
	// String paramName=null;
	//		
	// StoreTemplate st = new StoreTemplate();
	// st.setDataSource(jdbcTemplate.getDataSource());
	// st.setSql(storeSql);
	// //处理入参
	// if(pramList!=null)
	// {
	// for(int i=0;i<pramList.length;i++)
	// {
	// st.setParamValue(String.valueOf(i), pramList[i] );
	// }
	// }
	// //处理入参出参
	// if(inOutPramMap!=null)
	// {
	// myEnumerator = inOutPramMap.keySet().iterator();
	// while (myEnumerator.hasNext()) {
	// paramName = myEnumerator.next().toString();
	// st.setInOutParam(paramName, inOutPramMap.get(paramName));
	// }
	// }
	// //处理出参
	// if(outPramMap!=null)
	// {
	// myEnumerator = outPramMap.keySet().iterator();
	// while (myEnumerator.hasNext()) {
	// paramName = myEnumerator.next().toString();
	// st.setOutParam(paramName, Integer.valueOf(outPramMap.get(paramName)+""));
	// }
	// }
	//		
	// //处理游标
	// if(outCurList!=null)
	// {
	// for(int i=0;i<outCurList.length;i++)
	// {
	// st.setOutOracleCursorParam(outCurList[i].toString(), new RowMapper() {
	// public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	//		
	// int count = rs.getMetaData().getColumnCount();
	// String[] header = new String[count];
	// for(int i=0;i<count;i++)
	// {
	// header[i] = rs.getMetaData().getColumnName(i+1);
	// }
	//					      
	// HashMap<String,Object> row = new HashMap<String,Object>(count + 3);
	// for(int i=0;i<count;i++)
	// {
	// row.put(header[i],rs.getObject(i+1));
	// }
	//		
	// return row;
	// }
	//		
	// });
	//			
	// }
	// }
	//		
	// //执行
	// Map results = st.execute();
	// // 输出返回值
	// return results;
	// }
	//	
	protected StringBuilder fromArrayToString(String[] ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < ids.length - 1; i++) {
			sb.append(ids[i] + ",");
		}
		if (ids.length > 0)
			sb.append(ids[ids.length - 1] + ")");
		else
			sb.append(")");
		return sb;
	}

}
