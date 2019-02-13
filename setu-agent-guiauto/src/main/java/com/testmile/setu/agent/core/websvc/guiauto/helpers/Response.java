package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.gson.Gson;

public class Response {
	private static Gson gson = new Gson();
	private ResponseCode result;
	private String emessage;
	private String etrace;
	private Map<String,Object> data = null;
	
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
		return this.data;
	}
	
	public void addDataItem(String name, Object value) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.put(name, value);
	}
	
	public static String createSuccessResponseString() {
		Response res = new Response();
		res.setResult(ResponseCode.SUCCESS);
		return gson.toJson(res, Response.class);		
	}
	public static String createErrorResponseString(Exception e) {
		Response res = new Response();
		res.setResult(ResponseCode.ERROR);
		res.setMessage(e.getMessage());
		res.setTrace(ExceptionUtils.getStackTrace(e));
		return gson.toJson(res, Response.class);
	}
	public static String createSuccessResponseString(String name, Object value) {
		Response res = new Response();
		res.setResult(ResponseCode.SUCCESS);
		res.addDataItem(name, value);
		System.out.println(res.getData());
		return gson.toJson(res, Response.class);
	}

}
