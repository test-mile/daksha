package arjuna.lib.core.value;

import java.util.List;

import arjuna.tpi.value.Value;

public interface ValueList {

	List<Value> values() throws Exception;

	List<Value> values(List<Integer> indices) throws Exception;

	List<String> strings() throws Exception;

	List<String> strings(List<Integer> indices) throws Exception;

	Value value(int index) throws Exception;

	String strValue(int index) throws Exception;
	
	Object object(int index) throws Exception;
	
	boolean hasIndex(int index)  throws Exception;

	boolean isEmpty() throws Exception;
}