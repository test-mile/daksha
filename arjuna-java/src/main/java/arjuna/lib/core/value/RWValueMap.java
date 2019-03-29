package arjuna.lib.core.value;

import java.util.Map;

import arjuna.tpi.value.Value;

public interface RWValueMap<T> extends ROValueMap<T>{
	
	void add(T k, Value value);
	
	void addObject(T k, Object o);
	
	void addAll(Map<T, Value> map);
	
	void addAllObjects(Map<T, Object> map);
	
	void addAll(ValueMap<T> map) throws Exception;
}
