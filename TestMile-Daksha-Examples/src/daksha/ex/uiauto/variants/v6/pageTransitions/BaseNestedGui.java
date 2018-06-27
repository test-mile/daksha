package daksha.ex.uiauto.variants.v6.pageTransitions;

import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.ex.config.AppConfig;

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
		this.goTo(AppConfig.WP_LOGOUT_URL);
	}
}