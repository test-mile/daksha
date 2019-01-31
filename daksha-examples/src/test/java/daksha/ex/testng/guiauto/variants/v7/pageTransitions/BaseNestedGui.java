package daksha.ex.testng.guiauto.variants.v7.pageTransitions;

import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;

public abstract class BaseNestedGui extends AbstractWordPressGui{
	private LeftNavigation leftNav = null;
	
	public BaseNestedGui(GuiAutomatorProxy automator) throws Exception {
		super(automator);
		leftNav = new LeftNavigation(automator);
	}
	
	private class LeftNavigation extends AbstractWordPressGui{

		public LeftNavigation(GuiAutomatorProxy automator) throws Exception {
			super(automator);
		}

	}
	
	public Categories goToCategries() throws Exception {
		leftNav.element("POSTS").hover();
		leftNav.element("CATEGORIES").click();	
		leftNav.waitForBody();
		return new Categories(this.getAutomator());
	}

	public Settings goToSettings() throws Exception {
		leftNav.element("SETTINGS").click();
		return new Settings(this.getAutomator());
	}
	
	public void logout() throws Exception{
		this.goTo(this.getTestContext().getValue("wp.logout.url").asString());
	}
}