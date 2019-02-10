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

package com.testmile.setu.agent.guiauto.ex.gettingstarted;

import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.agent.guiauto.tpi.builder.GuiAutomatorFactory;
import com.testmile.trishanku.tpi.webserver.JsonUtils;

public class Basic1WithCentralTestContext{
	
	public static void main (String args[]) throws Exception {
		// Create Selenium automator with central context options
		System.out.println();
		GuiAutomator automator = GuiAutomatorFactory.createAutomator(JsonUtils.readMavenResource("basicSetu.json"));

		automator.getBrowserHandler().goTo("https://www.google.com");
		System.out.println(automator.getWindowHandler().getTitle());
		automator.quit();
	}

}
