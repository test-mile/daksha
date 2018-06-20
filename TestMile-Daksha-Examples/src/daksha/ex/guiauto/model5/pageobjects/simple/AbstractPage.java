package daksha.ex.guiauto.model5.pageobjects.simple;

import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.leaping.pageobject.BasePage;

public class AbstractPage extends BasePage{

	public AbstractPage(GuiAutomatorProxy automator) throws Exception {
		super(automator);
		String name = this.getClass().getSimpleName();
		this.setLabel(name);
		this.loadPageDef(String.format("wordpress/%s.ini", name));
	}

}
