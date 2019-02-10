package com.testmile.trishanku.tpi.guiauto.automator;

import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public abstract class BaseGuiAutomator {
	private TestContext testContext = null;
	private OSType os = null;
	GuiAutomatorName engineName;
	private GuiAutomationContext automatorContext = null;
	String appTitle = null;
	private GuiElementLoaderType loaderType = GuiElementLoaderType.AUTOMATOR;
	private int waitTime = 10;
	
	public BaseGuiAutomator(TestContext testContext, GuiAutomatorName name, GuiElementLoaderType loaderType) throws Exception{
		this.testContext = testContext;
		this.setGuiTestEngineName(name);
		this.setElementLoaderType(loaderType);
	}
	
	public String getName() {
		return engineName.toString();
	}
	
	public TestContext getTestContext() {
		return this.testContext;
	}
	
	protected OSType getOSType() {
		return os;
	}

	public void setOSType(OSType os) {
		this.os = os;
	}
	
	public int getWaitTime() {
		return this.waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public GuiAutomatorName getGuiDriverEngineName(){
		return this.engineName;
	}
	
	public void setGuiTestEngineName(GuiAutomatorName name){
		this.engineName = name;
	}
	
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getAppTitle() {
		return this.appTitle;
	}

	public GuiAutomationContext getAutomatorContext() {
		return automatorContext;
	}

	public void setAutomatorContext(GuiAutomationContext context) {
		this.automatorContext = context;
	}
	
	public GuiElementLoaderType getElementLoaderType() {
		return loaderType;
	}

	public void setElementLoaderType(GuiElementLoaderType loaderType) {
		this.loaderType = loaderType;
	}

}
