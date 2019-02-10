package com.testmile.setu.agent.guiauto.core.handler.automator.sikuli;

import org.sikuli.script.Screen;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;

public class SikuliScroller extends AbstractAutomatorHandler implements Scroller{
	private int defaultUpcount = 5;
	
	public SikuliScroller(SetuAgentConfig config) throws Exception {
		super(config);
	}
	
	@Override
	public void scrollDown(int downCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(downCount);
	}

	@Override
	public void scrollDown() throws Exception{
		scrollDown(defaultUpcount);
	}
	

	@Override
	public void scrollUp(int upCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(upCount);
	}
	
	@Override
	public void scrollUp() throws Exception{
		scrollUp(defaultUpcount);
	}
}
