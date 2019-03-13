/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package com.testmile.setu.actor.guiauto.commander.sikuli;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.testmile.setu.actor.guiauto.adapter.driver.SetuDriverConfig;

public class SikuliBasicActionsHandler{
	private String idImagePath;
	private Pattern pattern;
	
	public SikuliBasicActionsHandler(String idImagePath, SetuDriverConfig config) throws Exception {
		this.idImagePath = idImagePath;
		this.pattern = new Pattern(this.idImagePath);
	}
	
	private Screen getCurrentScreen() {
		return new Screen();
	}

	private Pattern getPattern() {
		return pattern;
	}
	

	public void click() throws Exception{
		getCurrentScreen().click(this.getPattern());
	}


	public void sendText(String text)  throws Exception{
		getCurrentScreen().type(this.getPattern(), text);
	}
	

	public void clearText() throws Exception {
		getCurrentScreen().paste(this.getPattern(), "");
	}


	public void submit() throws Exception {
		throw new Exception("Not supported for Sikuli.");
	}

}
