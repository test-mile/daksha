package setuexp;

import java.util.HashMap;
import java.util.Map;

public class GuiActionRequest {
	private String action;
	private String setuId;
	private Map<String,Object> args = null;
	
	public void addArg(String name, Object value) {
		if (this.args == null) {
			this.args = new HashMap<String, Object>();
		}
		this.args.put(name, value);
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public String getSetuId() {
		return setuId;
	}

	public void setSetuId(String setuId) {
		this.setuId = setuId;
	}

}
