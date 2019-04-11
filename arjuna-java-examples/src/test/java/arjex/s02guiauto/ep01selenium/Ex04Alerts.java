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

package arjex.s02guiauto.ep01selenium;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.component.Alert;

public class Ex04Alerts {
	
	@Test
	public void test() throws Exception{
		Arjuna.init();
		GuiAutomator automator = Arjuna.createGuiAutomator();
		
		WPLoginLogout.login(automator);
		
		automator.executeJavaScript("alert('dummy')");
		automator.Alert().confirm();
		automator.executeJavaScript("alert('dummy')");
		automator.Alert().dismiss();
		
		automator.executeJavaScript("alert('Sample')");
		Alert alert = automator.Alert();
		assert alert.getText().equals("Sample");
		alert.confirm();
		
		automator.executeJavaScript("prompt('Are You Sure?')");
		alert = automator.Alert();
		alert.sendText("Yes");	
		alert.confirm();
		
		WPLoginLogout.logout(automator);
	}

}
