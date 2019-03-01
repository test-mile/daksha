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
import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.Frame;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.guiauto.With;
import com.testmile.daksha.tpi.test.TestConfig;

public class Ex7Frame {

	public static void main(String[] args) throws Exception {
		TestConfig config = Daksha.init();
		GuiAutomator automator = new DefaultGuiAutomator(config);
		WPLoginLogout.login(automator);
		
		automator.element(With.LINK_TEXT,"Posts").click();
		automator.element(With.LINK_TEXT,"Add New").click();
		
		automator.element(With.ID, "title").setText("Sample");
		
		// Frame by identifier and jump to root
		Frame frame = automator.frame(With.ID, "content_ifr");
		frame.jump();
		automator.element(With.ID,"tinymce").setText("This is a test - frame by name.");
		frame.jumpToRoot();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		// Frame by index
		frame = automator.frame(With.INDEX, "0");
		frame.jump();
		automator.element(With.ID,"tinymce").setText("This is a test - frame by index.");
		frame.jumpToRoot();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		// jump to parent
		frame = automator.frame(With.XPATH, "//iframe");
		frame.jump();
		automator.element(With.ID,"tinymce").setText("This is a test - jumping to parent after this.");
		frame.jumpToParent();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		WPLoginLogout.logout(automator);
	}

}
