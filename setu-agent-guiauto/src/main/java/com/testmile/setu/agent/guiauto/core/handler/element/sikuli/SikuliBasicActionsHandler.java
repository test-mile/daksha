package com.testmile.setu.agent.guiauto.core.handler.element.sikuli;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;

public class SikuliBasicActionsHandler extends AbstractAutomatorHandler implements BasicActionsHandler {
	private String idImagePath;
	private Pattern pattern;
	
	public SikuliBasicActionsHandler(String idImagePath, SetuAgentConfig config) throws Exception {
		super(config);
		this.idImagePath = idImagePath;
		this.pattern = new Pattern(this.idImagePath);
	}
	
	private Screen getCurrentScreen() {
		return new Screen();
	}

	private Pattern getPattern() {
		return pattern;
	}
	
	@Override
	public void click() throws Exception{
		getCurrentScreen().click(this.getPattern());
	}

	@Override
	public void sendText(String text)  throws Exception{
		getCurrentScreen().type(this.getPattern(), text);
	}
	
	@Override
	public void clearText() throws Exception {
		getCurrentScreen().paste(this.getPattern(), "");
	}

	@Override
	public void submit() throws Exception {
		throw new Exception("Not supported for Sikuli.");
	}

}
