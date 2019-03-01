package com.testmile.daksha.core.batteries.container;

import java.util.Map;

import com.testmile.daksha.tpi.batteries.container.Value;

public interface RWValueMap<T> extends ROValueMap<T>{
	
	void add(T k, Value value);
	
	void addObject(T k, Object o);
	
	void addAll(Map<T, Value> map);
	
	void addAllObjects(Map<T, Object> map);
	
	void addAll(ValueMap<T> map) throws Exception;
}
