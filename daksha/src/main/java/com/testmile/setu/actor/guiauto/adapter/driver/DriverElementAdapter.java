package com.testmile.setu.actor.guiauto.adapter.driver;

import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.actor.guiauto.commander.driver.DriverElementCommandUtils;
import com.testmile.setu.actor.guiauto.commander.driver.DriverElementContainer;
import com.testmile.setu.actor.guiauto.core.GuiElementActionType;
import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.setu.actor.ActorAction;

public class DriverElementAdapter<T,E> extends AbstractDriverTranslator{
	private Map<String, DriverElementContainer<T,E>> elementMap = new HashMap<String, DriverElementContainer<T,E>>();
	private Map<String, GuiMultiElement<T,E>> melementMap = new HashMap<String, GuiMultiElement<T,E>>();

	public void registerElement(String uuid, DriverElementContainer<T,E> element) {
		System.out.println("registering: " + uuid);
		this.elementMap.put(uuid, element);
	}
	
	public void registerMultiElement(String uuid, GuiMultiElement<T,E> element) {
		this.melementMap.put(uuid, element);
	}

	public void deregisterMultiElementForSetuId(String uuid) {
		this.melementMap.remove(uuid);
	}
	
	public DriverElementContainer<T,E> getElementForSetuId(String uuid) throws Exception {
		System.out.println("retrieving: " + uuid);
		if (!this.elementMap.containsKey(uuid)) {
			throw new Exception(String.format("Element with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.elementMap.get(uuid);
	}
	
	public GuiMultiElement<T,E> getMultiElementForSetuId(String uuid) throws Exception {
		if (!this.melementMap.containsKey(uuid)) {
			throw new Exception(String.format("MultiElement with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.melementMap.get(uuid);
	}
	
	private String takeSingleElementAction(DriverElementContainer<T,E> element, ActorAction action) throws Exception {
		String retContent = null;
		boolean result = false;
		String textResult = null;
		switch(GuiElementActionType.valueOf(action.getActionString())) {
		case FIND_ELEMENT:
			DriverElementContainer<T,E> foundElement = element.findElement(action.strValue("withType"), action.strValue("withValue"));
			String setuId = action.strValue("childElementSetuId");
			registerElement(setuId,  foundElement);
			return success();
		case FIND_MULTIELEMENT:
			GuiMultiElement<T,E> mElement = element.findElements(action.strValue("withType"), action.strValue("withValue"));
			String mSetuId = action.strValue("childElementSetuId");
			registerMultiElement(mSetuId,  mElement);
			return this.success("instanceCount", mElement.getInstanceCount());
		case CLICK:
			DriverElementCommandUtils.click(element.asWebElement());
			return success();
		case CLEAR_TEXT:
			DriverElementCommandUtils.clearText(element.asWebElement());
			return success();
		case SEND_TEXT:
			DriverElementCommandUtils.sendText(element.asWebElement(), action.strValue("text"));
			return success();
		case IS_SELECTED:
			result = DriverElementCommandUtils.isSelected(element.asWebElement());
			return successResult(result);
		case IS_VISIBLE:
			result = DriverElementCommandUtils.isVisible(element.asWebElement());
			return successResult(result);
		case IS_CLICKABLE:
			result = DriverElementCommandUtils.isClickable(element.asWebElement());
			return successResult(result);
		case GET_TAG_NAME:
			textResult = DriverElementCommandUtils.getTagName(element.asWebElement());
			return successResult(textResult);
		case GET_ATTR_VALUE:
			textResult = DriverElementCommandUtils.getAttribute(element.asWebElement(), action.strValue("attr"));
			return successResult(textResult);
		case GET_TEXT_CONTENT:
			textResult = DriverElementCommandUtils.getTextContent(element.asWebElement());
			return successResult(textResult);
		default:
			throw new Exception(String.format("Unrecognized element action: %s", action.getActionString()));
		}	
	}

	public String takeElementAction(String elemSetuId, ActorAction action) throws Exception {
		DriverElementContainer<T,E> element;
		if (action.isInstanceAction()) {
			GuiMultiElement<T,E> me = getMultiElementForSetuId(elemSetuId);
			element = me.getInstanceAtIndex(action.intValue("instanceIndex"));
		} else {
			element = getElementForSetuId(elemSetuId);
		}
		return takeSingleElementAction(element, action);
	}

}
