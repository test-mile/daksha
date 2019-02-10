package com.testmile.daksha.core.setu.client.guiauto.proxy.page;

import java.util.List;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;

public class GuiMetaData {
	private String id;
	private String testContextId;
	private String automatorId;
	private GuiAutomationContext automationContext;
	private String label;
	private String name;
	private String parentContextId;
	private GuiElementLoaderType elementLoaderType;
	private String defPath;
	private List<String> childrenIds;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestContextId() {
		return testContextId;
	}
	public void setTestContextId(String testContextId) {
		this.testContextId = testContextId;
	}
	public String getAutomatorId() {
		return automatorId;
	}
	public void setAutomatorId(String automatorId) {
		this.automatorId = automatorId;
	}
	public GuiAutomationContext getAutomationContext() {
		return automationContext;
	}
	public void setAutomationContext(GuiAutomationContext automationContext) {
		this.automationContext = automationContext;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentContextId() {
		return parentContextId;
	}
	public void setParentContextId(String parentContextId) {
		this.parentContextId = parentContextId;
	}
	public GuiElementLoaderType getElementLoaderType() {
		return elementLoaderType;
	}
	public void setElementLoaderType(GuiElementLoaderType loaderType) {
		this.elementLoaderType = loaderType;
	}
	public String getDefPath() {
		return defPath;
	}
	public void setDefPath(String defPath) {
		this.defPath = defPath;
	}
	public List<String> getChildrenIds() {
		return childrenIds;
	}
	public void setChildrenIds(List<String> childrenIds) {
		this.childrenIds = childrenIds;
	}
}
