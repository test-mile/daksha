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
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.Frame;

public class Ex08Frame {
	
	@Test
	public void test() throws Exception{
		Arjuna.init();
		GuiAutomator automator = Arjuna.createGuiAutomator();
		
		WPLoginLogout.login(automator);
		
		automator.Element(With.linkText("Posts")).click();
		automator.Element(With.linkText("Add New")).click();
		
		automator.Element(With.id("title")).setText("Sample");
		
		With tinymce = With.id("tinymce");
		With publish = With.id("publish");
		
		// Frame by identifier and jump to root
		automator.Frame(With.id("content_ifr")).focus();
		automator.Element(tinymce).setText("This is a test - frame by name.");
		automator.DomRoot().focus();
		automator.Element(publish).click();
		
		// Frame by index
		automator.Frame(With.index(0)).focus();
		automator.Element(tinymce).setText("This is a test - frame by index.");
		// Focusing on root from frame itself
		automator.DomRoot().focus();
		automator.Element(publish).click();
		
		// jump to parent
		Frame frame = automator.Frame(With.xpath("//iframe"));
		frame.focus();
		automator.Element(tinymce).setText("This is a test - jumping to parent after this.");
		frame.ParentFrame().focus();
		automator.Element(publish).click();
		
		WPLoginLogout.logout(automator);
	}

}
