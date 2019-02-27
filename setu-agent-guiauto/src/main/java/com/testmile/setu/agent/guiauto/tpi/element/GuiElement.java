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

package com.testmile.setu.agent.guiauto.tpi.element;

import com.testmile.setu.agent.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;

public interface GuiElement {

	BasicActionsHandler getBasicActionsHandler() throws Exception;

	ElementInquirer getInquirer() throws Exception;

	ElementStateHandler getStateHandler() throws Exception;

	ElementFinder getElementFinder() throws Exception;

	void switchToFrame() throws Exception;

}