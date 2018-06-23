package daksha.core.cleanup.automator;

import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.core.cleanup.picker.GuiElementIdentifier;
import daksha.tpi.cleanup.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getUiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy guiAutomatorProxy);
	GuiAutomatorProxy getProxy();

}
