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
import com.testmile.setu.requester.guiauto.With;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.setu.requester.guiauto.component.Frame;

public class Ex7Frame {

	public static void main(String[] args) throws Exception {
		DakshaTestConfig config = Daksha.init();
		GuiAutomator automator = Daksha.createGuiAutomator(config);
		
		WPLoginLogout.login(automator);
		
		automator.element(With.LINK_TEXT,"Posts").click();
		automator.element(With.LINK_TEXT,"Add New").click();
		
		automator.element(With.ID, "title").setText("Sample");
		
		// Frame by identifier and jump to root
		Frame frame = automator.domRoot().frame(With.ID, "content_ifr");
		frame.focus();
		automator.element(With.ID,"tinymce").setText("This is a test - frame by name.");
		automator.domRoot().focus();
		automator.element(With.ID,"publish").click();
		
		// Frame by index
		frame = automator.domRoot().frame(With.INDEX, "0");
		frame.focus();
		automator.element(With.ID,"tinymce").setText("This is a test - frame by index.");
		// Focusing on root from frame itself
		automator.domRoot().focus();
		automator.element(With.ID,"publish").click();
		
		// jump to parent
		frame = automator.domRoot().frame(With.XPATH, "//iframe");
		frame.focus();
		automator.element(With.ID,"tinymce").setText("This is a test - jumping to parent after this.");
		frame.getParent().focus();
		automator.element(With.ID,"publish").click();
		
		WPLoginLogout.logout(automator);
	}

}
