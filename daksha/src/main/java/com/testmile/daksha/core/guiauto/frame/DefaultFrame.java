package com.testmile.daksha.core.guiauto.frame;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.tpi.guiauto.Frame;

public class DefaultFrame extends DefaultSetuObject implements Frame {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/frame/action";

	public DefaultFrame(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public void jump() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToParent() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_PARENT);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToRoot() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_ROOT);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
