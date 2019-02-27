package daksh.exnew;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.ChildWindow;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.guiauto.MainWindow;

public class Ex4WindowHandling {

	public static void main(String[] args) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator();
		WPLoginLogout.login(automator);
		
		MainWindow mainWin = automator.mainWindow();
		mainWin.maximize();
		System.out.println(mainWin.getTitle());
		automator.executeJavaScript("window.open('/abc')");
		ChildWindow win = automator.newChildWindow();
		win.jump();
		System.out.println(win.getTitle());
		win.close();
		automator.executeJavaScript("window.open('/def')");
		automator.executeJavaScript("window.open('/xyz')");
		automator.closeAllChildWindows();
		System.out.println(mainWin.getTitle());
		
		WPLoginLogout.logout(automator);
	}

}
