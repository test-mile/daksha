package daksha.ex.testng.guiauto.variants.v5.pageWiseUIWithInheritance;

import arjuna.tpi.tpi.guiauto.automator.SetuClientGuiAutomator;
import arjuna.tpi.tpi.guiauto.gui.SetuClientDefaultGui;

public class AbstractWordPressGui extends SetuClientDefaultGui{

	public AbstractWordPressGui(SetuClientGuiAutomator automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.gns", this.getClass().getSimpleName());
	}

}
