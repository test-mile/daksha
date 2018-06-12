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
package daksha.tpi.leaping.pageobject;

import daksha.tpi.leaping.interfaces.Page;
import daksha.tpi.leaping.interfaces.PageSection;
import daksha.tpi.leaping.interfaces.UiDriver;

public class BasePageSection extends BasePage implements PageSection{

	public BasePageSection(
			String uiLabel, 
			Page parent, 
			UiDriver automator) throws Exception {
		super(uiLabel, parent, automator);
	}
	
	public BasePageSection(
			String uiLabel, 
			Page parent,
			UiDriver automator, 
			String mapPath) throws Exception {
		super(uiLabel, parent, automator, mapPath);
	}
	
}
