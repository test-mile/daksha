package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public class GuiElementHandler {
	private Map<String, GuiElement> elementMap = new HashMap<String, GuiElement>();

	public void registerElement(String uuid, GuiElement element) {
		this.elementMap.put(uuid, element);
	}

	public String takeElementAction(String uuid, String jsonStr) throws Exception {
		ElementAction action = new ElementAction(jsonStr);
		switch(action.getActionType()) {
		case CLICK:
			this.elementMap.get(uuid).getBasicActionsHandler().click();
			break;
		case CLEAR_TEXT:
			this.elementMap.get(uuid).getBasicActionsHandler().clearText();
			break;
		case ENTER_TEXT:
			this.elementMap.get(uuid).getBasicActionsHandler().enterText(action.getArgs().get("text").asString());
			break;
		case WAIT_UNTIL_CLICKABLE:
			this.elementMap.get(uuid).getStateHandler().waitUntilClickable();
			break;
		default:
			return String.format("Unrecognized element action: %s", action.getActionType());
		}
		return "Success";
	}

}
