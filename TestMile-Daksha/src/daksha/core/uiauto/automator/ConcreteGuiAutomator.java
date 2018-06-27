package daksha.core.uiauto.automator;

import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.identifier.GuiElementIdentifier;
import daksha.tpi.uiauto.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getGuiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy proxy);
	GuiAutomatorProxy getProxy();
	void validatePageLoad();

}
