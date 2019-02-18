	package setuexp;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
	private static Gson gson = new Gson();
	private ResponseCode result;
	private String emessage;
	private String etrace;
	private Map<String,Object> responseData = null;
	
	public ResponseCode getResult() {
		return result;
	}
	public void setResult(ResponseCode code) {
		this.result = code;
	}
	public String getMessage() {
		return emessage;
	}
	public void setMessage(String message) {
		this.emessage = message;
	}
	public String getTrace() {
		return etrace;
	}
	public void setTrace(String trace) {
		this.etrace = trace;
	}
	
	public Map<String,Object> getData(){
		return this.responseData;
	}
	
	public void addDataItem(String name, Object value) {
		if (this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		this.responseData.put(name, value);
	}
	
	public static Response fromJsonStr(String json) {
		return gson.fromJson(json, Response.class);
	}

}
