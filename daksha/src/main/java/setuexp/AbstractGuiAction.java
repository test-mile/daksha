package setuexp;

import com.google.gson.Gson;

public abstract class AbstractGuiAction implements GuiAction {
	private static Gson gson = new Gson();
	private GuiActionRequest actionRequest;
	
	public AbstractGuiAction() {
		this.actionRequest = new GuiActionRequest();
	}
	
	public void addArg(String name, Object value) {
		this.getActionRequest().addArg(name, value);
	}
	
	@Override
	public String asJsonString() {
		return gson.toJson(this.getActionRequest(), GuiActionRequest.class);
	}

	protected GuiActionRequest getActionRequest() {
		return actionRequest;
	}
	
}
