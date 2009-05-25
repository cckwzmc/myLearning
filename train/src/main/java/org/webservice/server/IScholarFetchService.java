package org.webservice.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "fetchService")
public interface IScholarFetchService {
	/**
	 * sCode类型:字符串
含义：标识用户使用的加密字符串，防止不明用户随意使用本接口，类似登录密码的一个概念，格式详见”4 数据结构”
ins_id类型：整型
含义：用户，即单位,对应数据库的ins_id，也就是单位的主键，来源于institution表。
	 * @param sCode
	 * @param ins_id
	 * @return
	 */
	@WebResult(name = "getSearchInfo") 
	public String getSearchInfo(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id);

	@WebResult(name = "getRecordsNum")
	public int getRecordsNum(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "year") int iYear, @WebParam(name = "dbid") int iDbid);

	@WebResult(name = "getRecords")
	public String getRecords(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "year") int iYear, @WebParam(name = "dbid") int iDbid);

	@WebResult(name = "writeData")
	public boolean WriteData(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "data") String sData);

	@WebResult(name = "upLoadOver")
	public void UpLoadOver(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "year") int iYear, @WebParam(name = "dbid") int iDbid);

	@WebResult(name = "errorInfo")
	public void WriteErrorInfo(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "year") int iYear, @WebParam(name = "dbid") int iDbid, @WebParam(name = "errorStr") String sErr);

	@WebResult(name = "getSearchStr")
	public String getSearchStr(@WebParam(name = "scode") String sCode, @WebParam(name = "insId") int ins_id, @WebParam(name = "year") int iYear, @WebParam(name = "dbid") int iDbid);
}
