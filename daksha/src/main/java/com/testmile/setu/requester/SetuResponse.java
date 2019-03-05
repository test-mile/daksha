package com.testmile.setu.requester;

import java.util.Map;

import com.testmile.trishanku.tpi.value.Value;

public interface SetuResponse {

	ResponseCode getResult();

	String getMessage();

	String getTrace();

	Map<String, Object> getData();
	
	Value getValueForKey(String keyName) throws Exception;
	
	Value getValue() throws Exception;
	
	String getValueForValueAttr() throws Exception;
	
	String getValueForText() throws Exception;
	
	boolean getValueForCheckResult() throws Exception;

	String getValueForElementSetuId() throws Exception;

	String getValueForTestSessionSetuId() throws Exception;
	
	String getValueForConfigSetuId() throws Exception;
	
	String getValueForGuiAutomatorSetuId() throws Exception;

	String getGuiSetuId() throws Exception;

	String getDataSourceSetuId() throws Exception;

}