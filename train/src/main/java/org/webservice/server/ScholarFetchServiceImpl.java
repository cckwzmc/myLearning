package org.webservice.server;

import javax.jws.WebService;

@WebService(endpointInterface = "org.webservice.server.IScholarFetchService",
serviceName = "fetchService")
public class ScholarFetchServiceImpl implements IScholarFetchService {

	@Override
	public String getRecords(String code, int ins_id, int year, int dbid) {
		return null;
	}

	@Override
	public int getRecordsNum(String code, int ins_id, int year, int dbid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSearchInfo(String code, int ins_id) {
		
		return null;
	}

	@Override
	public String getSearchStr(String code, int ins_id, int year, int dbid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpLoadOver(String code, int ins_id, int year, int dbid) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean WriteData(String code, int ins_id, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void WriteErrorInfo(String code, int ins_id, int year, int dbid, String err) {
		// TODO Auto-generated method stub

	}

}
