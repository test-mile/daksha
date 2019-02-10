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
package com.testmile.trishanku.setu.guiauto.core.identifier;

import com.testmile.trishanku.setu.guiauto.core.enums.SetuMobileNativeLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuMobileWebLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuNativeLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuVisualLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuWebLocateBy;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;

public class GuiElementLocator {
	private LocatorType by = null;
	private String value = null;
	
	public GuiElementLocator(LocatorType by, String value){
		this.by = by;
		this.value = value;
	}
	
	public LocatorType asLocateBy() {
		return this.by;
	}
	
	public SetuWebLocateBy asWebLocateBy() {
		return SetuWebLocateBy.valueOf(by.toString());
	}
	
	public SetuMobileWebLocateBy asMobileWebLocateBy() {
		return SetuMobileWebLocateBy.valueOf(by.toString());
	}
	
	public SetuMobileNativeLocateBy asMobileNativeLocateBy() {
		return SetuMobileNativeLocateBy.valueOf(by.toString());
	}
	
	public SetuNativeLocateBy asNativeLocateBy() {
		return SetuNativeLocateBy.valueOf(by.toString());
	}
	
	public SetuVisualLocateBy asVisualLocateBy() {
		return SetuVisualLocateBy.valueOf(by.toString());
	}
	
	public String getValue() {
		return value;
	}
}
