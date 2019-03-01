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

package com.testmile.daksha.core.guiauto.radiogroup;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.GenericElement;
import com.testmile.daksha.core.guiauto.setu.SelectableMultiElement;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.daksha.tpi.guiauto.DropDown;
import com.testmile.daksha.tpi.guiauto.RadioGroup;

public class DefaultRadioGroup extends SelectableMultiElement implements RadioGroup {

	public DefaultRadioGroup(AppAutomator automator, String setuId) {
		super(automator, setuId, "/radiogroup/action");
	}

}
