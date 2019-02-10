package com.testmile.daksha.core.setu.client.guiauto.proxy.common;

import com.testmile.daksha.core.setu.client.guiauto.enums.MatchType;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;

public class GuiElementFindMetaData {
	
	private GuiElementLoaderType elementLoaderType;
	private String finderId;
	private LocatorType locatorType;
	private String locatorValue;
	private MatchType matchType;
	
	public GuiElementLoaderType getElementLoaderType() {
		return elementLoaderType;
	}

	public void setElementLoaderType(GuiElementLoaderType elementLoaderType) {
		this.elementLoaderType = elementLoaderType;
	}
	
	public String getFinderId() {
		return finderId;
	}
	
	public void setFinderId(String finderId) {
		this.finderId = finderId;
	}
	
	public LocatorType getLocatorType() {
		return locatorType;
	}
	
	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}
	
	public String getLocatorValue() {
		return locatorValue;
	}
	
	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public MatchType getMatchType(MatchType first) {
		return this.matchType;
	}
	
	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

}
