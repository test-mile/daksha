package com.testmile.trishanku.tpi.value;

import java.util.List;
import java.util.Map;

public interface RWValueList extends ROValueList{
	
	void add(Value value);
	
	void addObject(Object o);

	void addAll(List<Value> map);
	
	void addAll(ValueList map) throws Exception;
	
	void addAll(Object[] objects);
}
