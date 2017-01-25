package com.digitalchina.common.data;


public class RtnData {

    private String code = Constants.RTN_CODE_SUCCESS;
    private String message = "success";
    private Object result;
    private String status = Constants.RTN_STATUS_SUCCESS;

    public static RtnData ok(Object result){ 
    	RtnData rtnData = new RtnData();
    	rtnData.setCode(Constants.RTN_CODE_SUCCESS);
    	rtnData.setStatus(Constants.RTN_STATUS_SUCCESS);
    	rtnData.setResult(result);
    	return rtnData;
    }
    
    public static RtnData fail(Object result){ 
		return fail(result, null);
    }

	public static RtnData fail(Object result, String message){
		return fail(result, Constants.RTN_CODE_FAIL, message);
	}
    
    public static RtnData fail(Object result, String code, String message){ 
    	RtnData rtnData = new RtnData();
    	rtnData.setCode(code);
    	rtnData.setMessage(message);
    	rtnData.setStatus(Constants.RTN_STATUS_ERROR);
    	rtnData.setResult(result);
    	return rtnData;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
