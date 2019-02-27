package daksh.exnew;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.Frame;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.guiauto.With;

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
