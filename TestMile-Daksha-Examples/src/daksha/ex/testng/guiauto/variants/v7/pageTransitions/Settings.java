package daksha.ex.testng.guiauto.variants.v7.pageTransitions;

import static org.testng.Assert.assertTrue;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.guiauto.element.GuiElement;

public class Settings extends BaseNestedGui{

	public Settings(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	public void playWithSettings() throws Exception{
		GuiElement blogNameTextBox = this.element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		this.element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		GuiElement roleDropDown = this.element("ROLE").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");		
	}

}
