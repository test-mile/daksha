package daksha.core.leaping.element;

import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.pageobject.Page;

public abstract class BaseManagedConcreteGuiElement<D,E,P> {
	private GuiAutomator<D,E> automator = null;
	private String automatorName = null;
	private P proxy = null;
	private Page page = null;
	
	public BaseManagedConcreteGuiElement(GuiAutomator<D,E> automator, P proxy) {
		this.automator = automator;
		this.proxy = proxy;
	}
	
	public BaseManagedConcreteGuiElement(Page page, GuiAutomator<D,E> automator, P proxy) {
		this(automator, proxy);
		this.page = page;
	}
	
	protected Page getPage() {
		return this.page;
	}
	
	protected GuiAutomator<D,E> getAutomator() {
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
