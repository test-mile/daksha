package daksha.core.cleanup.element;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.tpi.cleanup.ui.UI;

public abstract class BaseManagedConcreteUiElement<D,E,P> {
	private ConcreteUiAutomator<D,E> automator = null;
	private String automatorName = null;
	private P proxy = null;
	private UI ui = null;
	
	public BaseManagedConcreteUiElement(ConcreteUiAutomator<D,E> automator, P proxy) {
		this.automator = automator;
		this.proxy = proxy;
	}
	
	public BaseManagedConcreteUiElement(UI ui, ConcreteUiAutomator<D,E> automator, P proxy) {
		this(automator, proxy);
		this.ui = ui;
	}
	
	protected UI getUI() {
		return this.ui;
	}
	
	protected ConcreteUiAutomator<D,E> getAutomator() {
		return this.automator;
	}

	public P getUiElementProxy() {
		return this.proxy;
	}

	public void setUiElementProxy(P proxy) {
		this.proxy = proxy;
	}

	public String getAutomatorName() {
		return this.automatorName;
	}

	public void setAutomatorName(String name) {
		this.automatorName = name;
	}
	
	public abstract boolean isIdentified() throws Exception;

}
