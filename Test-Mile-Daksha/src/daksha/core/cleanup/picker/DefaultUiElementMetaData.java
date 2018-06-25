/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.cleanup.picker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.Daksha;
import daksha.core.cleanup.enums.PickBy;
import daksha.tpi.cleanup.enums.UiElementType;

public class DefaultUiElementMetaData implements UiElementMetaData {
	private Map<String, String> metaData = new HashMap<String, String>();
	List<UiElementLocator> locators = new ArrayList<UiElementLocator>();
	
	public DefaultUiElementMetaData(Map<String, String> map) {
		for (String key: map.keySet()){
			set(key, map.get(key));
		}
	}

	@Override
	public void set(String propName, String value) {
		metaData.put(propName.toUpperCase(), value);
	}


	@Override
	public String get(String propName) {
		return metaData.get(propName.toUpperCase());
	}

	@Override
	public Map<String, String> getAllProperties() {
		return metaData;
	}

	private List<String> getAllowedPickByStrings() throws Exception{
		return Daksha.getAllowedPickByStrings();
	}
	
	public void process() throws Exception{
		for (String property: getAllProperties().keySet()){
			String upProperty = property.toUpperCase();
			String value = get(property);
			if(getAllowedPickByStrings().contains(upProperty)){
				PickBy locateBy = PickBy.valueOf(upProperty);
				switch(locateBy){
				case ID: addLocator(locateBy, value);break;
				case NAME: addLocator(locateBy, value);break;
				case CLASS: addLocator(locateBy, value);break;
				case LINK_TEXT: addLocator(locateBy, value);break;
				case PARTIAL_LINK_TEXT: addLocator(locateBy, value);break;
				case XPATH: addLocator(locateBy, value);break;
				case CSS: addLocator(locateBy, value); break;
				case TAG: addLocator(locateBy, value);break;
				case IMAGE: addLocator(locateBy, value);break;
				case X_TEXT:
					addLocator(PickBy.XPATH, String.format("//*[text()='%s']", value));
					break;
				case X_PARTIAL_TEXT: 
					addLocator(PickBy.XPATH, String.format("//*[contains(text(),'%s')]", value));
					break;
				case X_VALUE:
					addLocator(PickBy.XPATH, String.format("//*[@value='%s']", value));
					break;					
				case X_TITLE: 
					addLocator(PickBy.XPATH, String.format("//*[@title='%s']", value));
					break;
				case X_IMAGE_SRC:
					addLocator(PickBy.XPATH, String.format("//img[@src='%s']", value));
					break;
				case X_TYPE:
					String upValue = value.toUpperCase();
					if (Daksha.getAllAllowedUiElementTypes().contains(upValue)){
						UiElementType elementType = UiElementType.valueOf(upValue);
						switch(elementType){
						case TEXTBOX:
							addLocator(PickBy.XPATH, String.format("//input[@type='text']", value));
							break;	
						case PASSWORD:
							addLocator(PickBy.XPATH, String.format("//input[@type='password']", value));
							break;	
						case LINK: 
							addLocator(PickBy.XPATH, String.format("//a", value));
							break;	
						case BUTTON: 
							addLocator(PickBy.XPATH, String.format("//input[@type='button']", value));
							break;	
						case SUBMIT_BUTTON: 
							addLocator(PickBy.XPATH, String.format("//input[@type='submit']", value));
							break;	
						case DROPDOWN: 
							addLocator(PickBy.XPATH, String.format("//select", value));
							break;	
						case CHECKBOX: 
							addLocator(PickBy.XPATH, String.format("//input[@type='checkbox']", value));
							break;	
						case RADIO: 
							addLocator(PickBy.XPATH, String.format("//input[@type='radio']", value));
							break;
						case IMAGE: 
							addLocator(PickBy.XPATH, String.format("//img", value));
							break;	
						}
					} else {
				// Do nothing. BECAUSE a single definition is used across multiple automators.
					} 
				}
			}else {
				// Opportunity for meta properties.
			}
			
		}

	}

	@Override
	public List<UiElementLocator> getLocators(){
		return locators;
	}

	@Override
	public void addLocator(PickBy key, String value) {
		locators.add(new UiElementLocator(key, value));
	}
	
}

