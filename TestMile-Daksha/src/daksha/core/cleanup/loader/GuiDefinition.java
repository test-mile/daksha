package daksha.core.cleanup.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.core.cleanup.picker.DefaultGuiElementMetaData;
import daksha.core.cleanup.picker.GuiElementMetaData;

public class GuiDefinition {
	private Map<String, GuiElementMetaData> guiDef = new HashMap<String, GuiElementMetaData>();
	
	public void addElementMetaData(String name, Map<String,String> map) throws Exception {
		GuiElementMetaData emd = new DefaultGuiElementMetaData(map);
		emd.process();
		this.guiDef.put(name.toLowerCase(), emd);
	}
	
	public synchronized GuiElementMetaData getMetaData(String name) {
		return this.guiDef.get(name.toLowerCase());
	}

	public boolean has(String name) {
		return this.guiDef.containsKey(name.toLowerCase());
	}

}
