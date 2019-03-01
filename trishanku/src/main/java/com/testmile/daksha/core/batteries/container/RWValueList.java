package com.testmile.daksha.core.batteries.container;

import java.util.List;
import java.util.Map;

import com.testmile.daksha.tpi.batteries.container.Value;

public interface RWValueList extends ROValueList{
	void addAll(List<Value> map);
	
	void addAll(ValueList map);
	
	void add(Value value);
	
	void addObject(Object o);
}
