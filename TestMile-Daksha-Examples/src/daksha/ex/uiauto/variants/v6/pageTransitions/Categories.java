package daksha.ex.uiauto.variants.v6.pageTransitions;

import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.uiauto.element.GuiElement;
import daksha.tpi.uiauto.element.GuiMultiElement;

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
