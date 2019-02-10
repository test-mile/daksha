package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.sikuli.SikuliBasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.InquirableElement;

public class SikuliGuiElement extends AbstractGuiElement implements InquirableElement{
	public SikuliGuiElement(String idImagePath, SetuAgentConfig config) throws Exception {
		setBasicActionsHandler(new SikuliBasicActionsHandler(idImagePath, config));
	}

}
