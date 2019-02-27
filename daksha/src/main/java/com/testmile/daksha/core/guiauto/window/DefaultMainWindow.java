package com.testmile.daksha.core.guiauto.window;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.MainWindow;

public class DefaultMainWindow extends AbstractBasicWindow implements MainWindow {

	public DefaultMainWindow(DefaultGuiAutomator automator, String elemSetuId) {
		super(automator, elemSetuId);
	}
	
	@Override
	public void maximize() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.MAXIMIZE);
		this.getSetuClient().post(this.getBaseActionUri(), action);
	}
}
