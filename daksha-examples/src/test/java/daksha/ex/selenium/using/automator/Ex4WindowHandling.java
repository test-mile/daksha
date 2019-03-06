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

package daksha.ex.selenium.using.automator;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.setu.requester.guiauto.component.ChildWindow;
import com.testmile.setu.requester.guiauto.component.MainWindow;

public class Ex4WindowHandling {

	public static void main(String[] args) throws Exception {
		DakshaTestConfig config = Daksha.init();
		GuiAutomator automator = Daksha.createGuiAutomator(config);
		
		WPLoginLogout.login(automator);
		
		MainWindow mainWin = automator.mainWindow();
		mainWin.maximize();
		System.out.println(mainWin.getTitle());
		automator.browser().executeJavaScript("window.open('/abc')");
		ChildWindow win = automator.mainWindow().latestChildWindow();
		win.focus();
		System.out.println(win.getTitle());
		win.close();
		automator.browser().executeJavaScript("window.open('/def')");
		automator.browser().executeJavaScript("window.open('/xyz')");
		automator.mainWindow().closeAllChildWindows();
		System.out.println(mainWin.getTitle());
		
		WPLoginLogout.logout(automator);
	}

}
