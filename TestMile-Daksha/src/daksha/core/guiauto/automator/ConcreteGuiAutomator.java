package daksha.core.guiauto.automator;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.identifier.GuiElementIdentifier;
import daksha.tpi.guiauto.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getGuiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy proxy);
	GuiAutomatorProxy getProxy();
	void validatePageLoad();

}
