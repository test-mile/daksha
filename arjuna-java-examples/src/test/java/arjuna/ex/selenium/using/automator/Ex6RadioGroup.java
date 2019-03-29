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

import arjex.s02guiauto.ep01selenium.WPLoginLogout;
import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.test.TestConfig;

public class Ex6RadioGroup {

	public static void main(String[] args) throws Exception {
		TestConfig config = Arjuna.init();
		GuiAutomator automator = Arjuna.createGuiAutomator(config);
		
		WPLoginLogout.login(automator);
		
		automator.enableSlowMotion(true);
		automator.Element(With.linkText("Settings")).click();
		RadioGroup dateFormat = automator.radioGroup(With.name("date_format"));
		System.out.println(dateFormat.hasValueSelected("Y-m-d"));
		System.out.println(dateFormat.hasIndexSelected(1));
		System.out.println(dateFormat.getFirstSelectedOptionValue());
		dateFormat.selectByValue("\\c\\u\\s\\t\\o\\m");
		dateFormat.selectByIndex(2);
		
		WPLoginLogout.logout(automator);
	}

}
