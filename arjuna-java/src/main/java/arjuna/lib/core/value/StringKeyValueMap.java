package arjuna.lib.core.value;

import java.util.List;
import java.util.Map;

public class StringKeyValueMap extends AbstractValueMap<String>{
	
	public StringKeyValueMap() {
		super();
	}
	
	public StringKeyValueMap(List<String> names, List<Object> objects){
		super(names, objects);		
	}
	
	public StringKeyValueMap(String[] headers, List<Object> objList) {
		super(headers, objList);	
	}

	public StringKeyValueMap(String[] names, Object[] objects){
		super(names, objects);	
	}
	
	public StringKeyValueMap(Map<String, Object> nvMap){
		super(nvMap);	
	}
	
	protected String formatKey(String key) {
		return key.toLowerCase().trim();
	}

	protected String formatKeyAsStr(String key) {
		return key;
	}

}
