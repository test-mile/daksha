package com.testmile.daksha.core.guiauto.window;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.tpi.guiauto.ChildWindow;

public class DefaultChildWindow extends AbstractBasicWindow implements ChildWindow {

	public DefaultChildWindow(AppAutomator automator, String elemSetuId) {
		super(automator, elemSetuId);
	}

	@Override
	public void close() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.CLOSE);
		this.getSetuClient().post(this.getBaseActionUri(), action);
	}
}
