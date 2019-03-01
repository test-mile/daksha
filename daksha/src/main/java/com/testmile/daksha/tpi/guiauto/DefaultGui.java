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

import com.testmile.daksha.core.guiauto.automator.AbstractAppAutomator;
import com.testmile.daksha.core.guiauto.gui.GuiAction;
import com.testmile.daksha.core.guiauto.gui.GuiActionType;
import com.testmile.daksha.core.guiauto.gui.GuiMgrAction;
import com.testmile.daksha.core.guiauto.gui.GuiMgrActionType;
import com.testmile.daksha.core.problem.ErrorType;
import com.testmile.daksha.core.problem.Problem;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.sysauto.utils.DataUtils;
import com.testmile.trishanku.Trishanku;

public class DefaultGui extends AbstractAppAutomator implements Gui{
	private String pagemgrUri = "/guimgr/action";
	private String pagUri;
	private GuiAutomator automator;
	private Map<String, Gui> children = new HashMap<String, Gui>();
	private GuiAutomationContext autoContext;
	
	public DefaultGui(GuiAutomator automator) throws Exception {
		super("/gui/");
		this.setConfig(automator.getConfig());
		this.pagUri = "/gui/";
		this.automator = automator;
		this.autoContext = automator.getAutomationContext();
		GuiMgrAction action = new GuiMgrAction(this, GuiMgrActionType.CREATE_GUI);
		Response response = this.setuClient.post(pagemgrUri, action);
		this.setSetuId((String) response.getData().get("guiSetuId"));
		load();
	}
	
	public DefaultGui(Gui parent, String label, GuiAutomator automator) throws Exception {
		super("/gui/");
		this.pagUri = "/gui/";
		this.automator = automator;
		this.autoContext = automator.getAutomationContext();
		GuiAction action = new GuiAction(parent, GuiActionType.CREATE_GUI);
		action.addArg("childGuiLabel", label);
		action.addArg("childGuiName", this.getClass().getSimpleName());
		action.addArg("childGuiQualifiedName", this.getClass().getName());
		Response response = this.getSetuClient().post(this.pagUri + "/action", action);
		this.setSetuId((String) response.getData().get("guiSetuId"));
		parent.addChild(label, this);
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
						Trishanku.getAutomationContextName(this.autoContext)
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
						DataUtils.toTitleCase(this.autoContext.toString())
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
	
	private Response takeAction(GuiAction action) throws Exception {
		return this.setuClient.post(pagUri + "action", action);
	}
	
	private String takeElementFindingAction(GuiAction action) throws Exception{
		Response response = takeAction(action);
		return (String) response.getData().get("elementSetuId");			
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
	
	@Override
	public Frame frame(String name) throws Exception {
		return super.frame(With.ASSIGNED_NAME, name);
	}
	
	@Override
	public ChildWindow childWindow(String name) throws Exception {
		return super.childWindow(With.ASSIGNED_NAME, name);
	}
}
