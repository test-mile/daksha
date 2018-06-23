package daksha.core.leaping.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.core.leaping.identifier.DefaultGuiElementMetaData;
import daksha.core.leaping.identifier.GuiElementMetaData;

public class PageDefinition {
	private Map<String, GuiElementMetaData> pageDef = new HashMap<String, GuiElementMetaData>();
	
	public void addElementMetaData(String name, Map<String,String> map) throws Exception {
		GuiElementMetaData emd = new DefaultGuiElementMetaData(map);
		emd.process();
		this.pageDef.put(name.toLowerCase(), emd);
	}
	
	public synchronized GuiElementMetaData getMetaData(String name) {
		return this.pageDef.get(name.toLowerCase());
	}

	public boolean has(String name) {
		return this.pageDef.containsKey(name.toLowerCase());
	}

}
