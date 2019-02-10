package com.testmile.setu.agent.guiauto.core.handler.automator;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public interface ElementFinder {

	GuiMultiElement findAll(String by, String value) throws Exception;

	GuiElement find(String by, String value) throws Exception;

}