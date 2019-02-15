package com.testmile.setu.agent.guiauto.tpi.automator;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public interface ElementFinder {

	GuiElement find(String by, String value) throws Exception;
	
	GuiMultiElement findAll(String by, String value) throws Exception;

}