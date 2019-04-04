package arjex.s02guiauto.ep04compositepom;

import arjuna.tpi.guiauto.GuiAutomator;

public abstract class WPFullPage extends WPBasePage {
	private TopMenuBar topMenu = null;
	private LeftNavSideBar leftNav = null;
	
	public WPFullPage(GuiAutomator automator) throws Exception {
		super(automator);
		this.setTopMenu(new TopMenuBar(this.getAutomator(), this));
		this.setLeftNav(new LeftNavSideBar(this.getAutomator(), this));
	}

	public TopMenuBar getTopMenu() {
		return topMenu;
	}

	private void setTopMenu(TopMenuBar topMenu) {
		this.topMenu = topMenu;
	}

	public LeftNavSideBar getLeftNav() {
		return leftNav;
	}

	private void setLeftNav(LeftNavSideBar leftNav) {
		this.leftNav = leftNav;
	}

}
