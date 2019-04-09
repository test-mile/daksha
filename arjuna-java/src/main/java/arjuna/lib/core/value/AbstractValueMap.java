package arjuna.lib.core.value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arjuna.tpi.value.Value;

public abstract class AbstractValueMap<T> implements RWValueMap<T>{
	
	private Map<T, Value> map = null;
	
	public AbstractValueMap() {
		this.map = new HashMap<T, Value>();
	}
	
	public AbstractValueMap(List<T> names, List<Object> objects){
		this();
		for (int i = 0; i < names.size(); i++) {
			this.addObject(names.get(i), objects.get(i));
		}		
	}
	
	public AbstractValueMap(T[] headers, List<Object> objList) {
		this();
		for (int i = 0; i < headers.length; i++) {
			this.addObject(headers[i], objList.get(i));
		}
	}

	public AbstractValueMap(T[] names, Object[] values){
		this();
		for (int i = 0; i < names.length; i++) {
			this.addObject(names[i], values[i]);
		}	
	}
	
	public AbstractValueMap(Map<T, Object> nvMap){
		this();
		for (T name : nvMap.keySet()) {
			this.addObject(name, nvMap.get(name));
		}
	}
	
	protected T formatKey(T key) {
		return key;
	}
	
	protected abstract String formatKeyAsStr(T key);

	@Override
	public Map<T, Value> items() throws Exception {
		return this.map;
	}
	
	private void validateKey(T key) throws Exception {
		if (map.size() == 0){
			throw new EmptyValueMapLookUpException(this.formatKeyAsStr(key));			
		} else {
			if (!this.hasKey(key)) {
				throw new ValueMapLookUpException(this.formatKeyAsStr(key));
			}
		}
	}

	private void validateKeys(List<T> keys) throws Exception {
		for (T key: keys) {
			validateKey(key);
		}
	}

	@Override
	public Map<T, Value> items(List<T> filterKeys) throws Exception {
		validateKeys(filterKeys);
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
		validateKeys(filterKeys);
		Map<T, Value> inMap = this.items(filterKeys);
		return this.convertToStringMap(inMap);
	}

	@Override
	public Value value(T key) throws Exception {
		validateKey(key);
		return this.map.get(this.formatKey(key));
	}

	@Override
	public String strValue(T key) throws Exception {
		validateKey(key);
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
	
	@Override
	public boolean isEmpty() throws Exception {
		return this.map.size() == 0;
	}

}
