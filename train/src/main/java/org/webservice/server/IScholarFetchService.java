package org.webservice.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "fetchService")
public interface IScholarFetchService {
	/**
	 * sCode����:�ַ���
���壺��ʶ�û�ʹ�õļ����ַ�������ֹ�����û�����ʹ�ñ��ӿڣ����Ƶ�¼�����һ�������ʽ�����4 ���ݽṹ��
ins_id���ͣ�����
���壺�û�������λ,��Ӧ���ݿ��ins_id��Ҳ���ǵ�λ����������Դ��institution��
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
