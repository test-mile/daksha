package arjuna.tpi.guiauto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiDriverExtendedConfig{
	private Map<String, Object> capabilities = new HashMap<String, Object>(); 
	private List<String> browserArgs = new ArrayList<String>();
	private Map<String, Object> browserPreferences = new HashMap<String, Object>(); 
	private List<String> browserExtensions = new ArrayList<String>(); 
	
	public GuiDriverExtendedConfig capability(String name, Object value) {
		this.capabilities.put(name,  value);
		return this;
	}
	
	public GuiDriverExtendedConfig browserArg(String arg) {
		this.browserArgs.add(arg);
		return this;
	}
	
	public GuiDriverExtendedConfig browserPreference(String name, Object value) {
		this.browserPreferences.put(name,  value);
		return this;
	}	
	
	public GuiDriverExtendedConfig browserExtension(String path) {
		this.browserExtensions.add(path);
		return this;
	}
	
	public Map<String, Object> asMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("capabilities", capabilities);
		map.put("browserArgs", browserArgs);
		map.put("browserPreferences", browserPreferences);
		map.put("browserExtensions", browserExtensions);
		return map;
	}
}