package daksha.core.cleanup.automator;

import daksha.core.cleanup.automator.proxy.UiAutomatorProxy;
import daksha.core.cleanup.picker.UiElementPicker;
import daksha.tpi.cleanup.automator.UiAutomator;

public interface ConcreteUiAutomator<D,E> extends UiAutomator{
	
	UiElementPicker<D,E> getPicker();
	D getUiDriverEngine()  throws Exception;
	void setProxy(UiAutomatorProxy proxy);
	UiAutomatorProxy getProxy();
	void validatePageLoad();

}
