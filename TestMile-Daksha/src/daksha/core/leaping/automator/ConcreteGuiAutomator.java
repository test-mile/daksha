package daksha.core.leaping.automator;

import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.core.leaping.identifier.GuiElementIdentifier;
import daksha.tpi.leaping.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getUiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy guiAutomatorProxy);
	GuiAutomatorProxy getProxy();

}
