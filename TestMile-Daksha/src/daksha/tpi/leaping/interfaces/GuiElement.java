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
package daksha.tpi.leaping.interfaces;

import daksha.core.leaping.interfaces.ManagedElement;
import daksha.core.leaping.interfaces.actions.AttributesInquirer;
import daksha.core.leaping.interfaces.actions.BasicActionHandler;
import daksha.core.leaping.interfaces.actions.ChainActionHandler;
import daksha.core.leaping.interfaces.actions.CheckBoxActionHandler;
import daksha.core.leaping.interfaces.actions.ElementIdentifier;
import daksha.core.leaping.interfaces.actions.ElementNestedActionHandler;
import daksha.core.leaping.interfaces.actions.ImageBasedActionHandler;
import daksha.core.leaping.interfaces.actions.InstanceGetter;
import daksha.core.leaping.interfaces.actions.SelectAndRadioActionHandler;
import daksha.core.leaping.interfaces.actions.WebActionHandler;

public interface GuiElement extends 		ManagedElement,
											ImageBasedActionHandler,
											BasicActionHandler,
											AttributesInquirer,
											ChainActionHandler,
											CheckBoxActionHandler,
											SelectAndRadioActionHandler,
											WebActionHandler,
											ElementIdentifier,
											InstanceGetter,
											ElementNestedActionHandler{

	Object throwUnsupportedActionException(String action) throws Exception;

	Object throwZeroOrdinalException(String action) throws Exception;

	Object throwNegativeIndexException(String action) throws Exception;

	Object throwEmptyElementQueueException(String action) throws Exception;

	Object throwUnsupportedSelectActionException(String action)
			throws Exception;

}
