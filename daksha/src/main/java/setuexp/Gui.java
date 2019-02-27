package setuexp;

public interface Gui extends AppAutomator{

	void addChild(String label, Gui gui);

	GuiElement element(String name) throws Exception;

	GuiMultiElement multiElement(String name) throws Exception;
	
	DropDown dropdown(String name) throws Exception;

	RadioGroup radioGroup(String name) throws Exception;

	Frame frame(String name) throws Exception;

	ChildWindow childWindow(String name) throws Exception;
	
	

}
