package com.testmile.daksha.core.guiauto.maker.appium;

import java.util.Map;

import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.maker.GuiAutomatorBuilder;

public class AppiumBuilder extends GuiAutomatorBuilder{
	
	public AppiumBuilder(TestContext testContext) throws Exception{
		super(testContext);
	}
	
	public void appPath(String path){
	}
	
	public AppiumBuilder appPackage(String pkg) {
		return this;
	}
	
	public AppiumBuilder appActivity(String pkg) {
		return this;
	}
	
	public void platformName(String name){
	}
	
	
	public void platformVersion(String version){
	}
	
	
	public void deviceName(String name){
	}
	
	
	public void udid(String id){
	}
	
	
	public void capabilities(Map<String, String> caps){
	}
	
	
	public SetuClientGuiAutomator build() throws Exception{
		return null;
	}
	
	public void proxy(String proxyString) {
	}

	public void sslProxy(String proxyString) {
	}
	
}
