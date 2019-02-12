package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.agent.guiauto.tpi.builder.GuiAutomatorFactory;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public class GuiAutomatorHandler {
	private GuiAutomator automator = null;
	private String id = null;
	private GuiElementHandler elementHandler = new GuiElementHandler();

	public GuiAutomatorHandler(String jsonStr, String uuid) throws Exception {
		id = uuid;
		automator = GuiAutomatorFactory.createAutomator(jsonStr);
	}
	
	public String getSetuId() {
		return this.id;
	}

	private GuiElementHandler getElementHandler() {
		return this.elementHandler;
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
		case FIND_ELEMENT:
			GuiElement element = this.automator.getElementFinder().find(
					action.getArgs().get("byType").asString(),
					action.getArgs().get("byValue").asString()
			);
			getElementHandler().registerElement(action.getArgs().get("uuid").asString(), element);
		}
		return "Success";
	}

	public String takeElementAction(String uuid, String jsonStr) throws Exception {
		return this.getElementHandler().takeElementAction(uuid, jsonStr);
	}

}

class Action{
	String action;
	String args;
}
