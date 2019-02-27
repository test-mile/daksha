package setuexp;

public interface GuiMultiElement extends SetuManagedObject{

	AppAutomator getAutomator() throws Exception;

	GuiElement getInstanceAtIndex(int index);

}