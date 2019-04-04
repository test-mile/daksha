package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.component.DropDown;

public class SettingsPage extends WPFullPage{

	public SettingsPage(GuiAutomator automator) throws Exception {
		super(automator);
	}

	public SettingsPage tweakSettings() throws Exception {		
		DropDown roleSelect = this.DropDown("role");
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		roleSelect.selectByValue("editor");
		roleSelect.selectByVisibleText("Subscriber");
		roleSelect.selectByIndex(4);
		return this;
	}
}
