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

package arjuna.ex.selenium.using.automator;

import com.testmile.arjuna.tpi.Arjuna;
import com.testmile.arjuna.tpi.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.guiauto.component.Alert;
import com.testmile.arjuna.tpi.test.TestConfig;

public class Ex3Alerts {

	public static void main(String[] args) throws Exception {
		TestConfig config = Arjuna.init();
		GuiAutomator automator = Arjuna.createGuiAutomator(config);
		
		WPLoginLogout.login(automator);
		
		automator.executeJavaScript("alert('dummy')");
		automator.alert().confirm();
		automator.executeJavaScript("alert('dummy')");
		automator.alert().dismiss();
		
		automator.executeJavaScript("alert('Sample')");
		Alert alert = automator.alert();
		assert alert.getText() == "Sample";
		alert.confirm();
		
		automator.executeJavaScript("prompt('Are You Sure?')");
		alert = automator.alert();
		alert.sendText("Yes");	
		alert.confirm();
		
		WPLoginLogout.logout(automator);
	}

}