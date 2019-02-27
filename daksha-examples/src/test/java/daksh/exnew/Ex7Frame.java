package daksh.exnew;

import setuexp.DefaultGuiAutomator;
import setuexp.DropDown;
import setuexp.Frame;
import setuexp.GuiAutomator;
import setuexp.GuiElement;
import setuexp.RadioGroup;
import setuexp.With;

public class Ex7Frame {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		automator.element(With.LINK_TEXT,"Posts").click();
		automator.element(With.LINK_TEXT,"Add New").click();
		
		automator.element(With.ID, "title").setText("Sample");
		
		// Frame by identifier and jump to root
		Frame frame = automator.frame(With.ID, "content_ifr");
		frame.jump();
		GuiElement tiny_mce = automator.element(With.ID,"tinymce");
		tiny_mce.setText("This is a test - frame by name.");
		frame.jumpToRoot();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		// Frame by index
		frame = automator.frame(With.INDEX, "0");
		frame.jump();
		tiny_mce = automator.element(With.ID,"tinymce");
		tiny_mce.setText("This is a test - frame by index.");
		frame.jumpToRoot();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		// jump to parent
		frame = automator.frame(With.XPATH, "//iframe");
		frame.jump();
		tiny_mce = automator.element(With.ID,"tinymce");
		tiny_mce.setText("This is a test - jumping to parent after this.");
		frame.jumpToParent();
		automator.element(With.ID,"publish").click();
		Thread.sleep(5000);
		
		WPLoginLogout.logout(automator);
	}

}
