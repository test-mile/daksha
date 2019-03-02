package com.testmile.trishanku.tpi.value;

import java.util.List;

public interface ValueList {

	List<Value> values() throws Exception;

	List<Value> values(List<Integer> indices) throws Exception;

	List<String> strings() throws Exception;

	List<String> strings(List<Integer> indices) throws Exception;

	Value valueAt(int index) throws Exception;

	String stringAt(int index) throws Exception;
	
	Object objectAt(int index) throws Exception;
	
	boolean hasIndex(int index)  throws Exception;
}