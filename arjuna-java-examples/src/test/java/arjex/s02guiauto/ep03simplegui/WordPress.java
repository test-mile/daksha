package arjex.s02guiauto.ep03simplegui;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.SimpleBaseGui;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.DropDown;

public class WordPress extends SimpleBaseGui{

	public WordPress(GuiAutomator automator) throws Exception {
		super(automator, "simpleapp");
	}

	public void login() throws Exception {
		this.Browser().goToUrl(this.getAutomator().getConfig().getUserOptionValue("wp.login.url").asString());
		this.Element(With.id("user_login")).setText("user");
		this.Element(With.id("user_pass")).setText("bitnami");
		this.Element(With.id("wp-submit")).click();
		this.Element(With.className("welcome-view-site")).waitUntilClickable();
	}
	
	public void tweakSettings() throws Exception {
		// Tweak Settings
		this.Element("settings").click();
		
		DropDown roleSelect = this.DropDown("role");
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		roleSelect.selectByValue("editor");
		roleSelect.selectByVisibleText("Subscriber");
		roleSelect.selectByIndex(4);
	}
	
	public void logout() throws Exception {
		this.Browser().goToUrl(this.getAutomator().getConfig().getUserOptionValue("wp.logout.url").asString());
		this.getAutomator().quit();	
	}
}
