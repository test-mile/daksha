package daksha.core.cleanup.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.core.cleanup.picker.DefaultUiElementMetaData;
import daksha.core.cleanup.picker.UiElementMetaData;

public class UiDefinition {
	private Map<String, UiElementMetaData> uiDef = new HashMap<String, UiElementMetaData>();
	
	public void addElementMetaData(String name, Map<String,String> map) throws Exception {
		UiElementMetaData emd = new DefaultUiElementMetaData(map);
		emd.process();
		this.uiDef.put(name.toLowerCase(), emd);
	}
	
	public synchronized UiElementMetaData getMetaData(String name) {
		return this.uiDef.get(name.toLowerCase());
	}

	public boolean has(String name) {
		return this.uiDef.containsKey(name.toLowerCase());
	}

}
