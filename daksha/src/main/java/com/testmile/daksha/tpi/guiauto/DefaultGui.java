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

package com.testmile.daksha.tpi.guiauto;

import java.util.HashMap;
import java.util.Map;

import com.testmile.setu.requester.config.SetuActionType;
import com.testmile.setu.requester.connector.SetuArg;
import com.testmile.setu.requester.connector.SetuResponse;
import com.testmile.setu.requester.guiauto.With;
import com.testmile.setu.requester.guiauto.automator.AbstractAppAutomator;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.setu.requester.guiauto.component.DomRoot;
import com.testmile.setu.requester.guiauto.component.DropDown;
import com.testmile.setu.requester.guiauto.component.GuiElement;
import com.testmile.setu.requester.guiauto.component.GuiMultiElement;
import com.testmile.setu.requester.guiauto.component.MainWindow;
import com.testmile.setu.requester.guiauto.component.RadioGroup;
import com.testmile.setu.requester.guiauto.gui.Gui;
import com.testmile.trishanku.core.problem.ErrorType;
import com.testmile.trishanku.core.problem.Problem;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public class DefaultGui extends AbstractAppAutomator implements Gui{
	private GuiAutomator automator;
	private Map<String, Gui> children = new HashMap<String, Gui>();
	private GuiAutomationContext autoContext;
	
	public DefaultGui(GuiAutomator automator) throws Exception {
		this.setConfig(automator.getConfig());
		this.automator = automator;
		this.autoContext = automator.getAutomationContext();
		
		SetuResponse response = this.sendRequest(SetuActionType.GUIAUTO_GUIMGR_CREATE_GUI);
		this.setSetuId(response.getGuiSetuId());
		this.setSelfSetuIdArg("guiSetuId");
		load();
	}
	
	public DefaultGui(Gui parent, String label, GuiAutomator automator) throws Exception {
		this.automator = automator;
		this.autoContext = automator.getAutomationContext();
		SetuResponse response = this.sendRequest(
				SetuActionType.GUIAUTO_GUIMGR_CREATE_GUI,
				SetuArg.arg("childGuiLabel", label),
				SetuArg.arg("childGuiName", this.getClass().getSimpleName()),
				SetuArg.arg("childGuiQualifiedName", this.getClass().getName())
		);
		this.setSetuId(response.getGuiSetuId());
		parent.addChild(label, this);
		this.setSelfSetuIdArg("guiSetuId");
		load();
		load();
	}
	
	protected String getQualifiedName() {
		return this.getClass().getName();
	}
	
	protected void throwUndefinedUiException(String method, String label) throws Exception{
		throw new Problem(
			"gui",
			this.getQualifiedName(),
			method,
			ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NONEXISTING_LABEL, label, this.getQualifiedName())
		);
	}
	
	protected void throwNullUiException(String method) throws Exception{
		throw new Problem(
			"gui",
			this.getQualifiedName(),
			method,
			ErrorType.COMPOSITE_GUI_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_GUI_NULL_LABEL, this.getQualifiedName())
		);
	}
	
	public void addChild(String label, Gui gui) {
		children.put(label.toLowerCase(), gui);
	}
	
	public Gui gui(String label) throws Exception {
		if (label != null){
			if (children.containsKey(label.toLowerCase())){
				return children.get(label.toLowerCase());
			} else{
				throwUndefinedUiException("gui", label);
			}
		} else {
			throwNullUiException("gui");
		}
		
		return null;
	}
	
	protected Object throwDefaultUiException(String action, String code, String message) throws Exception {
		throw new Problem(
				"GUI Automator",
				this.getQualifiedName(),
				action,
				code,
				message
				);		
	}

	public Object throwNullAutomatorException(String methodName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.GUI_NULL_AUTOMATOR,
				String.format(
						ErrorType.GUI_NULL_AUTOMATOR,
						this.autoContext.toString()
						)
				);
	}

	public Object throwUndefinedElementException(String methodName, String elementName) throws Exception {
		return throwDefaultUiException(
				methodName,
				ErrorType.GUI_UNDEFINED_ELEMENT,
				String.format(
						ErrorType.GUI_UNDEFINED_ELEMENT,
						elementName,
						this.autoContext.toString()
						)
				);
	}
	
	protected void goTo() throws Exception{
		// Child classes can override this and write any necessary loading instructions.
	}
	
	protected void validateReadiness() throws Throwable{
	}
	
	public final void load() throws Exception {
		try {
			this.validateReadiness();
			this.goTo();
		} catch (Throwable e) {
			throw new Exception(String.format("UI [%s] with SetuId [%s] did not load as expected. Error: %s.", this.getClass().getName(), this.getSetuId(), e.getMessage()));
		}
	}

	public GuiAutomator getAutomator() throws Exception{
		return this.automator;
	}
	
	@Override
	public GuiElement element(String name) throws Exception {
		return super.element(With.ASSIGNED_NAME, name);
	}

	@Override
	public GuiMultiElement multiElement(String name) throws Exception {
		return super.multiElement(With.ASSIGNED_NAME, name);
	}

	@Override
	public DropDown dropdown(String name) throws Exception {
		return super.dropdown(With.ASSIGNED_NAME, name);
	}

	@Override
	public RadioGroup radioGroup(String name) throws Exception {
		return super.radioGroup(With.ASSIGNED_NAME, name);
	}
	
	public MainWindow mainWindow() throws Exception {
		return this.automator.mainWindow();
	}
	
	public DomRoot domRoot() {
		return this.automator.domRoot();
	}
}

