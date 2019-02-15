package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public class GuiElementHandler {
	private Map<String, GuiElement> elementMap = new HashMap<String, GuiElement>();
	private Map<String, GuiMultiElement> melementMap = new HashMap<String, GuiMultiElement>();

	public void registerElement(String uuid, GuiElement element) {
		System.out.println("registering: " + uuid);
		this.elementMap.put(uuid, element);
	}
	
	public void registerMultiElement(String uuid, GuiMultiElement element) {
		this.melementMap.put(uuid, element);
	}

	public void deregisterMultiElementForSetuId(String uuid) {
		this.melementMap.remove(uuid);
	}
	
	public GuiElement getElementForSetuId(String uuid) throws Exception {
		System.out.println("retrieving: " + uuid);
		if (!this.elementMap.containsKey(uuid)) {
			throw new Exception(String.format("Element with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.elementMap.get(uuid);
	}
	
	public GuiMultiElement getMultiElementForSetuId(String uuid) throws Exception {
		if (!this.melementMap.containsKey(uuid)) {
			throw new Exception(String.format("MultiElement with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.melementMap.get(uuid);
	}
	
	private String takeSingleElementAction(GuiElement element, ElementAction action) throws Exception {
		String retContent = null;
		switch(action.getActionType()) {
		case FIND_MULTIELEMENT:
			GuiMultiElement mElement = element.getElementFinder().findAll(
					action.getArgs().get("byType").asString(),
					action.getArgs().get("byValue").asString()
			);
			this.registerMultiElement(action.getArgs().get("uuid").asString(), mElement);
			retContent = Response.createSuccessResponseString("instanceCount", mElement.getInstanceCount());
			break;
		case CLICK:
			element.getBasicActionsHandler().click();
			retContent = Response.createSuccessResponseString();
			break;
		case CLEAR_TEXT:
			element.getBasicActionsHandler().clearText();
			retContent = Response.createSuccessResponseString();
			break;
		case SEND_TEXT:
			element.getBasicActionsHandler().sendText(action.getArgs().get("text").asString());
			retContent = Response.createSuccessResponseString();
			break;
		case IS_SELECTED:
			boolean isSelected = element.getStateHandler().isSelected();
			retContent = Response.createSuccessResponseString("checkResult", isSelected);
			break;
		case IS_VISIBLE:
			boolean isVisible = element.getStateHandler().isVisible();
			retContent = Response.createSuccessResponseString("checkResult", isVisible);
			break;
		case IS_CLICKABLE:
			boolean isClickable = element.getStateHandler().isClickable();
			retContent = Response.createSuccessResponseString("checkResult", isClickable);
			break;
		case GET_TAG_NAME:
			String tagName = element.getInquirer().getTagName();
			retContent = Response.createSuccessResponseString("attrValue", tagName);
			break;
		case GET_ATTR_VALUE:
			String attrValue = element.getInquirer().getAttribute(action.getArgs().get("attr").asString());
			retContent = Response.createSuccessResponseString("attrValue", attrValue);
			break;
		default:
			throw new Exception(String.format("Unrecognized element action: %s", action.getActionType()));
		}
		System.out.println(retContent);
		return retContent;		
	}

	public String takeElementAction(String uuid, String jsonStr) throws Exception {
		GuiElement element = getElementForSetuId(uuid);
		ElementAction action = new ElementAction(jsonStr);
		return takeSingleElementAction(element, action);
	}
	
	public String takeMultiElementAction(String uuid, String jsonStr) throws Exception {
		GuiMultiElement me = getMultiElementForSetuId(uuid);
		ElementAction action = new ElementAction(jsonStr);
		return this.takeSingleElementAction(me.getInstanceAtIndex(action.getInstanceIndex()), action);
	}

}
