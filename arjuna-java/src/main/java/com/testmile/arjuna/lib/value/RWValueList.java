package com.testmile.arjuna.lib.value;

import java.util.List;

import com.testmile.arjuna.tpi.value.Value;

public interface RWValueList extends ROValueList{
	
	void add(Value value);
	
	void addObject(Object o);

	void addAll(List<Value> map);
	
	void addAll(ValueList map) throws Exception;
	
	void addAllObjects(Object[] objects);

	void addAllObjects(List<Object> objects);
}
