package com.testmile.setu.requester.guiauto;

import com.testmile.setu.requester.config.SetuActionType;
import com.testmile.setu.requester.connector.BaseSetuObject;
import com.testmile.setu.requester.connector.SetuArg;
import com.testmile.setu.requester.connector.SetuResponse;
import com.testmile.setu.requester.guiauto.automator.AppAutomator;
import com.testmile.setu.requester.guiauto.automator.DefaultGuiAutomator;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.setu.requester.guiauto.component.Alert;
import com.testmile.setu.requester.guiauto.component.Browser;
import com.testmile.setu.requester.guiauto.component.ChildWindow;
import com.testmile.setu.requester.guiauto.component.DomRoot;
import com.testmile.setu.requester.guiauto.component.DropDown;
import com.testmile.setu.requester.guiauto.component.Frame;
import com.testmile.setu.requester.guiauto.component.GuiElement;
import com.testmile.setu.requester.guiauto.component.GuiMultiElement;
import com.testmile.setu.requester.guiauto.component.MainWindow;
import com.testmile.setu.requester.guiauto.component.RadioGroup;
import com.testmile.setu.requester.testsession.TestSession;

public class GuiAutoComponentFactory {

	public static GuiElement createGuiElement(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static GuiMultiElement createGuiMultiElement(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DropDown createGuiDropDown(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static RadioGroup createRadioGroup(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Frame createFrame(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Alert createAlert(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ChildWindow createChildWindow(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static MainWindow createMainWindow(TestSession session, AppAutomator automator, String setuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DomRoot createDomRoot(TestSession session, AppAutomator automator) {
		return new DefaultDomRoot(session, automator);
	}
	
	public static Browser createBrowser(TestSession session, AppAutomator automator) {
		return new DefaultBrowser(session, automator);
	}

}

class BaseComponent extends BaseSetuObject{
	private AppAutomator automator;
	private TestSession testSession;

	public BaseComponent(TestSession session, AppAutomator automator) {
		this.testSession = session;
		this.automator = automator;
		this.setAutomatorSetuIdArg(automator.getSetuId());
		this.setTestSessionSetuIdArg(testSession.getSetuId());
	}
	
	protected AppAutomator getAutomator() {
		return this.automator;
	}
	
	protected TestSession getTestSession() {
		return this.testSession;
	}

}



class BaseElement extends BaseComponent{

	public BaseElement(TestSession session, AppAutomator automator, String elemSetuId) {
		super(session, automator);
		this.setSetuId(elemSetuId);
		this.setSelfSetuIdArg("elementSetuId");
	}
	
	public BaseElement(TestSession testSession, AppAutomator automator, String elemSetuId, int index) {
		this(testSession, automator, elemSetuId);
		this.addArgs(
				SetuArg.arg("isInstanceAction", true),
				SetuArg.arg("instanceIndex", index)
		);
	}

}

class DefaultGuiElement extends BaseElement implements GuiElement {

	public DefaultGuiElement(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}
	
	public DefaultGuiElement(TestSession session, AppAutomator automator, String setuId, int index) {
		super(session, automator, setuId, index);
	}
	
	@Override
	public void enterText(String text) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_ENTER_TEXT, SetuArg.textArg(text));
	}
	
	@Override
	public void setText(String text) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_SET_TEXT, SetuArg.textArg(text));
	}

	@Override
	public void click() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_CLICK);
	}

	@Override
	public void waitUntilClickable() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_WAIT_UNTIL_CLICKABLE);
	}

	@Override
	public void check() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_CHECK);
	}

	@Override
	public void uncheck() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_ELEMENT_UNCHECK);
	}

}

class DefaultGuiMultiElement extends BaseElement implements GuiMultiElement {

	public DefaultGuiMultiElement(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) {
		return new DefaultGuiElement(this.getTestSession(), this.getAutomator(), this.getSetuId(), index);
	}

}

class SelectableElementGroup extends BaseElement {

	public SelectableElementGroup(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

	public boolean hasValueSelected(String value) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_SELECTABLE_ELEMENTGROUP_HAS_VALUE_SELECTED, SetuArg.valueArg(value));
		return response.getValueForCheckResult();
	}

	public boolean hasIndexSelected(int index) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_SELECTABLE_ELEMENTGROUP_HAS_INDEX_SELECTED, SetuArg.indexArg(index));
		return response.getValueForCheckResult();
	}

	public void selectByValue(String value) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_SELECTABLE_ELEMENTGROUP_SELECT_BY_VALUE, SetuArg.valueArg(value));
	}

	public void selectByIndex(int index) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_SELECTABLE_ELEMENTGROUP_SELECT_BY_INDEX, SetuArg.indexArg(index));
	}

	public String getFirstSelectedOptionValue() throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_SELECTABLE_ELEMENTGROUP_GET_FIRST_SELECTED_OPTION_VALUE);
		return response.getValueForValueAttr();
	}
}

class DefaultDropDown extends SelectableElementGroup implements DropDown {

	public DefaultDropDown(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}
	
	public String getFirstSelectedOptionText() throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_GET_FIRST_SELECTED_OPTION_TEXT);
		return response.getValueForText();
	}

	@Override
	public boolean hasVisibleTextSelected(String text) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_HAS_VISIBLE_TEXT_SELECTED, SetuArg.textArg(text));
		return response.getValueForCheckResult();
	}

	@Override
	public void selectByVisibleText(String text) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_SELECT_BY_VISIBLE_TEXT, SetuArg.textArg(text));
	}
}

class DefaultRadioGroup extends SelectableElementGroup implements RadioGroup {

	public DefaultRadioGroup(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

}

class DefaultAlert extends BaseElement implements Alert {

	public DefaultAlert(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

	@Override
	public void confirm() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_CONFIRM_ALERT);
	}

	@Override
	public void dismiss() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_DISMISS_ALERT);
	}

	@Override
	public String getText() throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_GET_TEXT_FROM_ALERT);
		return response.getValueForText();
	}

	@Override
	public void sendText(String text) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_SEND_TEXT_TO_ALERT, SetuArg.textArg(text));
	}

}

class DefaultBrowser extends BaseComponent implements Browser {

	public DefaultBrowser(TestSession session, AppAutomator automator) {
		super(session, automator);
	}

	@Override
	public void goToUrl(String url) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_GO_TO_URL, SetuArg.arg("url", url));	
	}

	@Override
	public void goBack() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_GO_BACK);
	}

	@Override
	public void goForward() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_GO_FORWARD);
	}

	@Override
	public void refresh() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_REFRESH);
	}
	
	@Override
	public void executeJavaScript(String script) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_EXECUTE_JAVASCRIPT, SetuArg.arg("script", script));
	}
}


class DefaultDomRoot extends BaseComponent implements DomRoot{
	
	public DefaultDomRoot(TestSession session, AppAutomator automator) {
		super(session, automator);
	}

	@Override
	public void focus() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_DOMROOT_FOCUS);
	}

	@Override
	public DefaultFrame createFrame(With with, String value) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DOMROOT_CREATE_FRAME);
		return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
	}	
}

class DefaultFrame extends BaseElement implements Frame {

	public DefaultFrame(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

	@Override
	public void focus() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_FRAME_FOCUS);
	}
	
	@Override
	public Frame createFrame(With with, String value) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_FRAME_CREATE_FRAME);
		return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
	}
	
	@Override
	public Frame getParent() throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_FRAME_GET_PARENT);
		return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
	}
	
	@Override
	public DomRoot domRoot() throws Exception {
		return this.getAutomator().domRoot();
	}

}

class AbstractBasicWindow extends BaseElement {

	public AbstractBasicWindow(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}
	
	public String getTitle() throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_WINDOW_GET_TITLE);
		return response.getValueForKey("title").asString();
	}
	
	public void focus() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_WINDOW_FOCUS);
	}
}

class DefaultChildWindow extends AbstractBasicWindow implements ChildWindow {

	public DefaultChildWindow(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}

	@Override
	public void close() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_CHILD_WINDOW_CLOSE);
	}
	
	@Override
	public MainWindow mainWindow() throws Exception {
		return this.getAutomator().mainWindow();
	}
}

class DefaultMainWindow extends AbstractBasicWindow implements MainWindow {

	public DefaultMainWindow(TestSession session, AppAutomator automator, String setuId) {
		super(session, automator, setuId);
	}
	
	@Override
	public void maximize() throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_MAIN_WINDOW_MAXIMIZE);
	}
	
	protected String takeElementFindingAction(SetuActionType actionType, SetuArg... args) throws Exception {
		SetuResponse response = this.sendRequest(actionType, args);
		return response.getValueForElementSetuId();		
	}
	
	@Override
	public ChildWindow childWindow(With with, String value) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.GUIAUTO_MAIN_WINDOW_CREATE_CHILD_WINDOW,
				SetuArg.with(with, value)
		);
		return GuiAutoComponentFactory.createChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
	}
	
	@Override
	public ChildWindow newChildWindow() throws Exception {
		SetuResponse response = sendRequest(SetuActionType.GUIAUTO_MAIN_WINDOW_GET_NEWLY_LAUNCHED_CHILD_WINDOW);
		return GuiAutoComponentFactory.createChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
	}

	@Override
	public void closeAllChildWindows() throws Exception {
		sendRequest(SetuActionType.GUIAUTO_MAIN_WINDOW_CLOSE_ALL_CHILD_WINDOWS);
	}
}


