package com.testmile.setu.agent.guiauto.core.handler.automator.sikuli;

import org.sikuli.script.Screen;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;

public class SikuliScroller extends AbstractAutomatorHandler implements Scroller{
	private int defaultMouseCount = 5;
	
	public SikuliScroller(SetuAgentConfig config) throws Exception {
		super(config);
	}
	
	@Override
	public void scrollDown(int times)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(defaultMouseCount* times);
	}
	

	@Override
	public void scrollUp(int times)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(defaultMouseCount* times);
	}

}
