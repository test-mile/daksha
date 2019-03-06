/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testmile.setu.actor.core.websvc.guiauto.helpers;

import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.actor.guiauto.tpi.element.GuiElement;
import com.testmile.setu.actor.guiauto.tpi.element.GuiMultiElement;

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
		case FIND_ELEMENT:
			GuiElement foundElement = element.getElementFinder().find(
					action.getArgs().get("byType").asString(),
					action.getArgs().get("byValue").asString()
			);
			String setu_id = action.getArgs().get("uuid").asString();
			this.registerElement(setu_id,  foundElement);
			retContent = Response.createSuccessResponseString();
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
		case GET_TEXT_CONTENT:
			String text = element.getInquirer().getTextContent();
			retContent = Response.createSuccessResponseString("attrValue", text);
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
