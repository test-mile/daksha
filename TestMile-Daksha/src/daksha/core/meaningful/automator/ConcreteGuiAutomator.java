package daksha.core.meaningful.automator;

import daksha.core.meaningful.automator.proxy.GuiAutomatorProxy;
import daksha.core.meaningful.identifier.GuiElementIdentifier;
import daksha.tpi.meaningful.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getGuiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy proxy);
	GuiAutomatorProxy getProxy();
	void validatePageLoad();

}
