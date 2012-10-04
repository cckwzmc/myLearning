package com.toney.mvc.web.utils;

/**
 * @ClassName: JsonpPackageWrapper
 * @Description: JSON统一输出格式.
 * @author toney.li
 * @date 2012-3-22 下午3:47:42
 * <ul>
 * 	<li>success是否成功，默认为成功.</li>
 * 	<li>scode如果失败则返回失败码.</li>
 * 	<li>smsg如果失败则返回失败信息.</li>
 * 	<li>data返回数据.</li>
 * </ul>
 */
public class JsonPackageWrapper {
	private Boolean success=true;
    private String scode;
    private String smsg;

    private Object data;

    public JsonPackageWrapper() {
    	scode="";
        smsg = "";
        data = null;
    }

    public JsonPackageWrapper(String scode) {
        this.scode = scode;
        this.smsg = "";
        this.data = null;
    }

    public JsonPackageWrapper(String scode, String smessage) {
        this.scode = scode;
        this.smsg = smessage;
        this.data = null;
    }
    public JsonPackageWrapper(boolean success,String smessage) {
    	this.success=success;
    	this.scode = "";
    	this.smsg = smessage;
    	this.data = null;
    }
    public JsonPackageWrapper(boolean success,String scode, String smessage) {
    	this.success=success;
    	this.scode = scode;
    	this.smsg = smessage;
    	this.data = null;
    }

    public JsonPackageWrapper(String scode, Object data) {
        this.scode = scode;
        this.smsg = "";
        this.data = data;
    }

    public JsonPackageWrapper(String scode, String smessage, Object data) {
        this.scode = scode;
        this.smsg = smessage;
        this.data = data;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSmsg() {
        return smsg;
    }

    public void setSmsg(String smessage) {
        this.smsg = smessage;
    }

    public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
