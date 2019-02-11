package com.testmile.setu.agent;

import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.agent.guiauto.tpi.builder.GuiAutomatorFactory;

public class GuiAutomatorHandler {
	private GuiAutomator automator = null;
	private String id = null;

	public GuiAutomatorHandler(String jsonStr, String uuid) throws Exception {
		id = uuid;
		automator = GuiAutomatorFactory.createAutomator(jsonStr);
	}
	
	public String SetId() {
		return this.id;
	}

	public void quit() throws Exception {
		this.automator.quit();
	}

	public String takeAction(String jsonStr) throws Exception {
		AutomatorAction action = new AutomatorAction(jsonStr);
		switch(action.getActionType()) {
		case GO_TO_URL:
			System.out.println(action.getArgs().get("url").asString());
			this.automator.getBrowserHandler().goTo(action.getArgs().get("url").asString());
			break;
		}
		return "Success";
	}

}

class Action{
	String action;
	String args;
}
