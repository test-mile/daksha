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

import daksha.core.cleanup.enums.MobileNativePickBy;
import daksha.core.cleanup.enums.MobileWebPickBy;
import daksha.core.cleanup.enums.NativePickBy;
import daksha.core.cleanup.enums.PickBy;
import daksha.core.cleanup.enums.VisualPickBy;
import daksha.core.cleanup.enums.WebPickBy;

public class UiElementLocator {
	private PickBy by = null;
	private String value = null;
	
	public UiElementLocator(PickBy by, String value){
		this.by = by;
		this.value = value;
	}
	
	public PickBy asPickBy() {
		return this.by;
	}
	
	public WebPickBy asWebPickBy() {
		return WebPickBy.valueOf(by.toString());
	}
	
	public MobileWebPickBy asMobileWebLocateBy() {
		return MobileWebPickBy.valueOf(by.toString());
	}
	
	public MobileNativePickBy asMobileNativeLocateBy() {
		return MobileNativePickBy.valueOf(by.toString());
	}
	
	public NativePickBy asNativeLocateBy() {
		return NativePickBy.valueOf(by.toString());
	}
	
	public VisualPickBy asVisualLocateBy() {
		return VisualPickBy.valueOf(by.toString());
	}
	
	public String getValue() {
		return value;
	}
}
