package com.testmile.trishanku.tpi.value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testmile.daksha.core.value.AnyRefValue;

public abstract class AbstractValueMap<T> implements RWValueMap<T>{
	
	private Map<T, Value> map = null;
	
	public AbstractValueMap() {
		this.map = new HashMap<T, Value>();
	}
	
	protected T formatKey(T key) {
		return key;
	}
	
	protected abstract String formatKeyAsStr(T key);

	@Override
	public Map<T, Value> items() throws Exception {
		return this.map;
	}

	@Override
	public Map<T, Value> items(List<T> filterKeys) throws Exception {
		Map<T, Value> outMap = new HashMap<T,Value>();
		for (T key: map.keySet()) {
			if (filterKeys.contains(key)) {
				outMap.put(key, map.get(key));
			}
		}
		
		return outMap;
	}
	
	private Map<String, String> convertToStringMap(Map<T, Value> inMap){
		Map<String, String> outMap = new HashMap<String, String>();
		for (T key: inMap.keySet()) {
			outMap.put(this.formatKeyAsStr(key), map.get(key).toString());
		}
		
		return outMap;		
	}

	@Override
	public Map<String, String> strItems() throws Exception {
		return this.convertToStringMap(this.map); 
	}

	@Override
	public Map<String, String> strItems(List<T> filterKeys) throws Exception {
		Map<T, Value> inMap = this.items(filterKeys);
		return this.convertToStringMap(inMap);
	}

	@Override
	public Value value(T key) throws Exception {
		return this.map.get(this.formatKey(key));
	}

	@Override
	public String string(T key) throws Exception {
		return value(key).toString();
	}

	@Override
	public boolean hasKey(T key) throws Exception {
		return this.map.containsKey(this.formatKey(key));
	}
	
	@Override
	public void add(T k, Value value) {
		this.map.put(this.formatKey(k), value);
	}

	@Override
	public void addObject(T k, Object obj) {
		this.map.put(this.formatKey(k), new AnyRefValue(obj));
	}

	@Override
	public void addAll(Map<T, Value> map) {
		for (T key: map.keySet()) {
			this.add(key, map.get(key));
		}
	}
	
	@Override
	public void addAllObjects(Map<T, Object> map) {
		for (T key: map.keySet()) {
			this.addObject(key, map.get(key));
		}
	}

	@Override
	public void addAll(ValueMap<T> valueMap) throws Exception {
		this.addAll(valueMap.items());
	}

}
