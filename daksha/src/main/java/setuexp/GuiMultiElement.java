package setuexp;

public interface GuiMultiElement extends SetuManagedObject{

	GuiAutomator getAutomator() throws Exception;

	GuiElement getInstanceAtIndex(int index);

	RadioGroup asRadioGroup() throws Exception;

}