package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.sikuli.SikuliBasicActionsHandler;

public class SikuliGuiElement extends AbstractGuiElement{
	public SikuliGuiElement(String idImagePath, SetuAgentConfig config) throws Exception {
		setBasicActionsHandler(new SikuliBasicActionsHandler(idImagePath, config));
	}

}
