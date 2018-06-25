/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package daksha.core.cleanup.automator.sikuli;

import org.sikuli.script.App;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.AbstractUiAutomator;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.OSType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.tpi.cleanup.enums.UiAutomationContext;

public class SikuliScreenUiDriver extends AbstractUiAutomator<Screen,Match>{
	
	public SikuliScreenUiDriver(TestContext testContext, ElementLoaderType loaderType) throws Exception {
		super(testContext, UiDriverEngine.SIKULI, UiAutomationContext.SCREEN, loaderType);
	}
	
	public SikuliScreenUiDriver(TestContext testContext) throws Exception{
		this(testContext, ElementLoaderType.AUTOMATOR);
	}

	@Override
	public void focusOnApp() throws Exception{
		App app = new App(this.getAppTitle());
		app.focus();
	}


	@Override
	public void scrollDown(int downCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseDown(downCount);
	}
	
	

	@Override
	public void scrollDown() throws Exception{
		scrollDown(5);
	}
	

	@Override
	public void scrollUp(int upCount)  throws Exception{
		Screen screen = new Screen();
		screen.mouseUp(upCount);
	}
	
	@Override
	public void scrollUp() throws Exception{
		scrollUp(5);
	}

	@Override
	public void sendKeysToScreen(String text) throws Exception{
		Screen screen = new Screen();
		screen.type(text);
	}
	
	/*
	 * Exceptions
	 */
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWaitTime(int waitTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOSType(OSType platformType) {
		// TODO Auto-generated method stub
		
	}

}
