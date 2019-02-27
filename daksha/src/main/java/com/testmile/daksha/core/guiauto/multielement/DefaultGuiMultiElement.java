package com.testmile.daksha.core.guiauto.multielement;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.element.DefaultGuiElement;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.tpi.guiauto.GuiElement;
import com.testmile.daksha.tpi.guiauto.GuiMultiElement;

public class DefaultGuiMultiElement extends DefaultSetuObject implements GuiMultiElement {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private final static String baseActionUri = "/multielement/action";

	public DefaultGuiMultiElement(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	public AppAutomator getAutomator() {
		return this.automator;
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) {
		return new DefaultGuiElement(this.automator, this.getSetuId(), index, baseActionUri);
	}

}
