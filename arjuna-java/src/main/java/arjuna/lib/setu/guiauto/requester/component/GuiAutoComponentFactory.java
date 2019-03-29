/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package arjuna.lib.setu.guiauto.requester.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import arjuna.lib.setu.core.requester.connector.SetuArg;
import arjuna.lib.setu.core.requester.connector.SetuResponse;
import arjuna.lib.setu.guiauto.requester.automator.AppAutomator;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.Alert;
import arjuna.tpi.guiauto.component.Browser;
import arjuna.tpi.guiauto.component.ChildWindow;
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.GuiMultiElement;
import arjuna.tpi.guiauto.component.MainWindow;
import arjuna.tpi.guiauto.component.RadioGroup;

public class GuiAutoComponentFactory {

	public static GuiElement Element(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultGuiElement(session, automator, setuId);
	}

	public static GuiMultiElement MultiElement(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultGuiMultiElement(session, automator, setuId);
	}

	public static DropDown DropDown(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultDropDown(session, automator, setuId);
	}

	public static RadioGroup RadioGroup(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultRadioGroup(session, automator, setuId);
	}

	public static Frame Frame(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultFrame(session, automator, setuId);
	}

	public static Alert Alert(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultAlert(session, automator, setuId);
	}

	public static MainWindow MainWindow(TestSession session, AppAutomator automator, String setuId) {
		return new DefaultMainWindow(session, automator, setuId);
	}

	public static DomRoot DomRoot(TestSession session, AppAutomator automator) {
		return new DefaultDomRoot(session, automator);
	}
	
	public static Browser Browser(TestSession session, AppAutomator automator) {
		return new DefaultBrowser(session, automator);
	}
	
	private static class BaseComponent extends BaseSetuObject{
		private AppAutomator automator;
		private TestSession testSession;

		public BaseComponent(TestSession session, AppAutomator automator) {
			this.testSession = session;
			this.automator = automator;
			if (automator.isGui()) {
				this.setGuiSetuIdArg(automator.getSetuId());
			} else {
				this.setAutomatorSetuIdArg(automator.getSetuId());	
			}
			this.setTestSessionSetuIdArg(testSession.getSetuId());
		}

		protected AppAutomator getAutomator() {
			return this.automator;
		}
		
		protected TestSession getTestSession() {
			return this.testSession;
		}

	}

	private static class BaseElement extends BaseComponent{

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

	private static class DefaultGuiElement extends BaseElement implements GuiElement {

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

	private static class DefaultGuiMultiElement extends BaseElement implements GuiMultiElement {

		public DefaultGuiMultiElement(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
		}

		@Override
		public GuiElement IndexedElement(int index) {
			return new DefaultGuiElement(this.getTestSession(), this.getAutomator(), this.getSetuId(), index);
		}

	}

	private static class DefaultDropDown extends BaseElement implements DropDown {

		public DefaultDropDown(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
		}
		
		public boolean hasValueSelected(String value) throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_HAS_VALUE_SELECTED, SetuArg.valueArg(value));
			return response.getValueForCheckResult();
		}

		public boolean hasIndexSelected(int index) throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_HAS_INDEX_SELECTED, SetuArg.indexArg(index));
			return response.getValueForCheckResult();
		}

		public void selectByValue(String value) throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_SELECT_BY_VALUE, SetuArg.valueArg(value));
		}

		public void selectByIndex(int index) throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_SELECT_BY_INDEX, SetuArg.indexArg(index));
		}

		public String getFirstSelectedOptionValue() throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_DROPDOWN_GET_FIRST_SELECTED_OPTION_VALUE);
			return response.getValueForValueAttr();
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

	private static class DefaultRadioGroup extends BaseElement implements RadioGroup {

		public DefaultRadioGroup(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
		}
		
		public boolean hasValueSelected(String value) throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_RADIOGROUP_HAS_VALUE_SELECTED, SetuArg.valueArg(value));
			return response.getValueForCheckResult();
		}

		public boolean hasIndexSelected(int index) throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_RADIOGROUP_HAS_INDEX_SELECTED, SetuArg.indexArg(index));
			return response.getValueForCheckResult();
		}

		public void selectByValue(String value) throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_RADIOGROUP_SELECT_BY_VALUE, SetuArg.valueArg(value));
		}

		public void selectByIndex(int index) throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_RADIOGROUP_SELECT_BY_INDEX, SetuArg.indexArg(index));
		}

		public String getFirstSelectedOptionValue() throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_RADIOGROUP_GET_FIRST_SELECTED_OPTION_VALUE);
			return response.getValueForValueAttr();
		}
	}

	private static class DefaultAlert extends BaseElement implements Alert {

		public DefaultAlert(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
		}

		@Override
		public void confirm() throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_ALERT_CONFIRM);
		}

		@Override
		public void dismiss() throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_ALERT_DISMISS);
		}

		@Override
		public String getText() throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_ALERT_GET_TEXT);
			return response.getValueForText();
		}

		@Override
		public void sendText(String text) throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_ALERT_SEND_TEXT, SetuArg.textArg(text));
		}

	}

	private static class DefaultBrowser extends BaseComponent implements Browser {

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
	}

	private static class DefaultFrame extends BaseElement implements Frame {

		public DefaultFrame(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
			this.setSelfSetuIdArg("elementSetuId");
		}

		@Override
		public void focus() throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_FRAME_FOCUS);
		}
		
		@Override
		public Frame Frame(With... locators) throws Exception {
			List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				arg.add(locator.asMap());
			}
			SetuResponse response = this.sendRequest(
					SetuActionType.GUIAUTO_FRAME_CREATE_FRAME,
					SetuArg.arg("locators", arg)
			);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
		
		@Override
		public Frame ParentFrame() throws Exception {
			SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_FRAME_GET_PARENT);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
	}

	private static class DefaultDomRoot extends BaseComponent implements DomRoot{
		
		public DefaultDomRoot(TestSession session, AppAutomator automator) {
			super(session, automator);
		}

		@Override
		public void focus() throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_DOMROOT_FOCUS);
		}

		@Override
		public DefaultFrame Frame(With... locators) throws Exception {
			List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				arg.add(locator.asMap());
			}
			SetuResponse response = this.sendRequest(
					SetuActionType.GUIAUTO_DOMROOT_CREATE_FRAME,
					SetuArg.arg("locators", arg)
			);
			return new DefaultFrame(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}

		@Override
		public Frame ParentFrame() throws Exception {
			throw new Exception("DOM root does not have a parent frame.");
		}	
	}

	private static class AbstractBasicWindow extends BaseElement {

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

	private static class DefaultChildWindow extends AbstractBasicWindow implements ChildWindow {

		public DefaultChildWindow(TestSession session, AppAutomator automator, String setuId) {
			super(session, automator, setuId);
		}

		@Override
		public void close() throws Exception {
			this.sendRequest(SetuActionType.GUIAUTO_CHILD_WINDOW_CLOSE);
		}
		
		@Override
		public MainWindow mainWindow() throws Exception {
			return this.getAutomator().MainWindow();
		}
	}

	private static class DefaultMainWindow extends AbstractBasicWindow implements MainWindow {

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
		public ChildWindow childWindow(With... locators) throws Exception {
			List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
			for(With locator: locators) {
				arg.add(locator.asMap());
			}
			SetuResponse response = this.sendRequest(
					SetuActionType.GUIAUTO_MAIN_WINDOW_CREATE_CHILD_WINDOW,
					SetuArg.arg("locators", arg)
			);
			return new DefaultChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}
		
		@Override
		public ChildWindow latestChildWindow() throws Exception {
			SetuResponse response = sendRequest(SetuActionType.GUIAUTO_MAIN_WINDOW_GET_LATEST_CHILD_WINDOW);
			return new DefaultChildWindow(this.getTestSession(), this.getAutomator(), response.getValueForElementSetuId());
		}

		@Override
		public void closeAllChildWindows() throws Exception {
			sendRequest(SetuActionType.GUIAUTO_MAIN_WINDOW_CLOSE_ALL_CHILD_WINDOWS);
		}
	}

}


