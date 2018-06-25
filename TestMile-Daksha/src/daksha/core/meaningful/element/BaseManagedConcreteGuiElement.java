package daksha.core.meaningful.element;

import daksha.core.meaningful.automator.ConcreteGuiAutomator;
import daksha.tpi.meaningful.gui.Gui;

public abstract class BaseManagedConcreteGuiElement<D,E,P> {
	private ConcreteGuiAutomator<D,E> automator = null;
	private String automatorName = null;
	private P proxy = null;
	private Gui gui = null;
	
	public BaseManagedConcreteGuiElement(ConcreteGuiAutomator<D,E> automator, P proxy) {
		this.automator = automator;
		this.proxy = proxy;
	}
	
	public BaseManagedConcreteGuiElement(Gui gui, ConcreteGuiAutomator<D,E> automator, P proxy) {
		this(automator, proxy);
		this.gui = gui;
	}
	
	protected Gui getGui() {
		return this.gui;
	}
	
	protected ConcreteGuiAutomator<D,E> getAutomator() {
		return this.automator;
	}

	public P getGuiElementProxy() {
		return this.proxy;
	}

	public void setGuiElementProxy(P proxy) {
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
