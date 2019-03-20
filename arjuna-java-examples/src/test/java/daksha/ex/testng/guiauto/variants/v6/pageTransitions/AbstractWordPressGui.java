package daksha.ex.testng.guiauto.variants.v6.pageTransitions;

import com.testmile.arjuna.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.arjuna.tpi.guiauto.gui.SetuClientDefaultGui;

public class AbstractWordPressGui extends SetuClientDefaultGui{

	public AbstractWordPressGui(SetuClientGuiAutomator automator) throws Exception {
		super(automator);
	}
	
	protected String getDefPath() throws Exception {
		return String.format("wordpress/%s.gns", this.getClass().getSimpleName());
	}

}
