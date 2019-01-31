package daksha.ex.testng.guiauto.variants.v7.pageTransitions;

import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.tpi.guiauto.element.GuiElement;
import com.testmile.daksha.tpi.guiauto.element.GuiMultiElement;

public class Categories extends BaseNestedGui{

	public Categories(GuiAutomatorProxy automator) throws Exception {
		super(automator);
	}

	public void playWithCats() throws Exception {
		GuiMultiElement tags = this.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}		
	}
}
