package com.testmile.setu.agent.guiauto.tpi.automator;

import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public interface ElementFinder {

	GuiMultiElement findAll(String by, String value) throws Exception;

}