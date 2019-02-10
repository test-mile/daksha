package com.testmile.daksha.core.guiauto.namestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.setu.guiauto.core.identifier.GuiElementMetaData;
import com.testmile.trishanku.setu.guiauto.core.identifier.SetuGuiElementMetaData;

public class GuiNamespace {
	private Map<String, Map<GuiAutomationContext,GuiElementMetaData>> ns = new HashMap<String, Map<GuiAutomationContext,GuiElementMetaData>>();
	
	public void addElementMetaData(String name, GuiAutomationContext context, List<StringNVPair> locators) throws Exception {
		GuiElementMetaData emd = new SetuGuiElementMetaData(locators);
		if (!ns.containsKey(name.toLowerCase())){
			ns.put(name.toLowerCase(), new HashMap<GuiAutomationContext,GuiElementMetaData>());
		} 
		this.ns.get(name.toLowerCase()).put(context, emd);
	}
	
	public synchronized GuiElementMetaData getMetaData(String name, GuiAutomationContext context) throws Exception {
		if (!has(name)) {
			throw new Exception("GuiNamespace does not have an element with name: " + name);
		} else if (!has(name, context)) {
			throw new Exception(String.format("GuiNamespace does not have an element with name:%s for automation context:%s", name, context.toString()));
		} else {
			return this.ns.get(name.toLowerCase()).get(context);
		}
	}

	public boolean has(String name) {
		return this.ns.containsKey(name.toLowerCase());
	}
	
	public boolean has(String name, GuiAutomationContext context) {
		if (has(name)) {
			return this.ns.get(name.toLowerCase()).containsKey(context);
		} else {
			return false;
		}
	}

}
