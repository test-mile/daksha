package arjex.s05basicfixtures.ep03testlevel;

import arjuna.tpi.guiauto.GuiAutomator;

public enum Global {
	INSTANCE;
	
	private GuiAutomator automator = null;
	
	public void setAutomator(GuiAutomator automator){
		this.automator = automator;
	}

	public GuiAutomator getAutomator(){
		return this.automator;
	}
	
	public void destroyAutomator(){
		this.automator = null;
	}
}
