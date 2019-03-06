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

import com.testmile.setu.requester.guiauto.With;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;

public class WPLoginLogout {
	
	public static void login(GuiAutomator automator) throws Exception {
		automator.launch();
		automator.goToUrl("http://192.168.56.103/wp-admin");
		automator.element(With.ID, "user_login").setText("user");
		automator.element(With.ID, "user_pass").setText("bitnami");
		automator.element(With.ID, "wp-submit").click();
		automator.element(With.CLASS_NAME, "welcome-view-site").waitUntilClickable();
	}
	
	public static void logout(GuiAutomator automator) throws Exception {
		automator.goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();		
	}

}
