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
package com.testmile.daksha.tpi.guiauto.element;

import com.testmile.daksha.core.guiauto.actions.element.AttributesInquirer;
import com.testmile.daksha.core.guiauto.actions.element.BasicActionHandler;
import com.testmile.daksha.core.guiauto.actions.element.ChainActionHandler;
import com.testmile.daksha.core.guiauto.actions.element.CheckBoxActionHandler;
import com.testmile.daksha.core.guiauto.actions.element.DropdownActionsHandler;
import com.testmile.daksha.core.guiauto.actions.element.ElementIdentification;
import com.testmile.daksha.core.guiauto.actions.element.ImageBasedActionHandler;
import com.testmile.daksha.core.guiauto.actions.element.State;
import com.testmile.daksha.core.guiauto.actions.element.WebActionHandler;
import com.testmile.daksha.core.guiauto.element.ManagedSingleGuiElement;

public interface GuiElement extends 		ManagedSingleGuiElement,
											ImageBasedActionHandler,
											BasicActionHandler,
											AttributesInquirer,
											ChainActionHandler,
											CheckBoxActionHandler,
											DropdownActionsHandler,
											WebActionHandler,
											ElementIdentification,
											State{

	Object throwUnsupportedActionException(String action) throws Exception;

	Object throwUnsupportedSelectActionException(String action)
			throws Exception;

}
