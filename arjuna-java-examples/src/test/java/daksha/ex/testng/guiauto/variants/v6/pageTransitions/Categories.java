package daksha.ex.testng.guiauto.variants.v6.pageTransitions;

import arjuna.tpi.tpi.guiauto.automator.SetuClientGuiAutomator;
import arjuna.tpi.tpi.guiauto.element.SetuClientGuiElement;
import arjuna.tpi.tpi.guiauto.element.SetuClientGuiMultiElement;

public class Categories extends BaseNestedGui{

	public Categories(SetuClientGuiAutomator automator) throws Exception {
		super(automator);
	}

	public void playWithCats() throws Exception {
		SetuClientGuiMultiElement tags = this.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.IndexedElement(1).uncheck();
			
		for (SetuClientGuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}		
	}
}
