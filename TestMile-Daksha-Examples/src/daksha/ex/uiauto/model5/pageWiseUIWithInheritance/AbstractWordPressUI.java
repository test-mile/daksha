package daksha.ex.uiauto.model5.pageWiseUIWithInheritance;

import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.tpi.cleanup.ui.DefaultUI;

public class AbstractWordPressUI extends DefaultUI{

	public AbstractWordPressUI(UiAutomatorProxy automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.ini", this.getClass().getSimpleName());
	}

}
