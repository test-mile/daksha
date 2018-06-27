package daksha.core.uiauto.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.core.uiauto.identifier.DefaultGuiElementMetaData;
import daksha.core.uiauto.identifier.GuiElementMetaData;

public class GuiNamespace {
	private Map<String, GuiElementMetaData> ns = new HashMap<String, GuiElementMetaData>();
	
	public void addElementMetaData(String name, Map<String,String> map) throws Exception {
		GuiElementMetaData emd = new DefaultGuiElementMetaData(map);
		emd.process();
		this.ns.put(name.toLowerCase(), emd);
	}
	
	public synchronized GuiElementMetaData getMetaData(String name) {
		return this.ns.get(name.toLowerCase());
	}

	public boolean has(String name) {
		return this.ns.containsKey(name.toLowerCase());
	}

}
