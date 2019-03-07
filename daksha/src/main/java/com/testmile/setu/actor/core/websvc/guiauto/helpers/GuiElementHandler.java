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
	
	private String takeSingleElementAction(GuiElement element, String actionStr, Map<String,Object> args) throws Exception {
		String retContent = null;
		ElementActionType actionType = ElementActionType.valueOf(actionStr);
		switch(actionType) {
		case FIND_ELEMENT:
			GuiElement foundElement = element.getElementFinder().find(
					(String) args.get("withType"),
					(String) args.get("withValue")
			);
			String setu_id = (String) args.get("elementSetuId");
			this.registerElement(setu_id,  foundElement);
			retContent = Response.createSuccessResponseString();
			break;
		case FIND_MULTIELEMENT:
			GuiMultiElement mElement = element.getElementFinder().findAll(
					(String) args.get("withType"),
					(String) args.get("withValue")
			);
			this.registerMultiElement((String) args.get("elementSetuId"), mElement);
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
			element.getBasicActionsHandler().sendText((String) args.get("text"));
			retContent = Response.createSuccessResponseString();
			break;
		case IS_SELECTED:
			boolean isSelected = element.getStateHandler().isSelected();
			retContent = Response.createSuccessResponseString("result", isSelected);
			break;
		case IS_VISIBLE:
			boolean isVisible = element.getStateHandler().isVisible();
			retContent = Response.createSuccessResponseString("result", isVisible);
			break;
		case IS_CLICKABLE:
			boolean isClickable = element.getStateHandler().isClickable();
			retContent = Response.createSuccessResponseString("result", isClickable);
			break;
		case GET_TAG_NAME:
			String tagName = element.getInquirer().getTagName();
			retContent = Response.createSuccessResponseString("result", tagName);
			break;
		case GET_ATTR_VALUE:
			String attrValue = element.getInquirer().getAttribute((String) args.get("attr"));
			retContent = Response.createSuccessResponseString("result", attrValue);
			break;
		case GET_TEXT_CONTENT:
			String text = element.getInquirer().getTextContent();
			retContent = Response.createSuccessResponseString("result", text);
			break;
		default:
			throw new Exception(String.format("Unrecognized element action: %s", actionType));
		}
		System.out.println(retContent);
		return retContent;		
	}

	public String takeElementAction(String elemSetuId, String actionStr, Map<String,Object> args) throws Exception {
		GuiElement element;
		Object instanceAction = args.get("isInstanceAction");
		if ((instanceAction != null) && ((boolean) instanceAction)){
			GuiMultiElement me = getMultiElementForSetuId(elemSetuId);
			element = me.getInstanceAtIndex(((Number) args.get("instanceIndex")).intValue());
		} else {
			element = getElementForSetuId(elemSetuId);
		}
		return takeSingleElementAction(element, actionStr, args);
	}

}
