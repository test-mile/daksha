package daksha.core.cleanup.automator;

import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.core.cleanup.picker.GuiElementPicker;
import daksha.tpi.cleanup.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementPicker<D,E> getPicker();
	D getUiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy guiAutomatorProxy);
	GuiAutomatorProxy getProxy();
	void validatePageLoad();

}
