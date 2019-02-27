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

package com.testmile.daksha.core.guiauto.automator;

public enum GuiAppAutomatorActionType {
	
	NAVIGATE_BROWSER, 
	
	CREATE_ELEMENT,
	CREATE_MULTIELEMENT,
	CREATE_DROPDOWN,
	CREATE_RADIOGROUP,
	CREATE_FRAME,
	CREATE_CHILD_WINDOW,
	CREATE_NEW_CHILD_WINDOW,
	CREATE_ALERT,
	
	EXECUTE_JAVASCRIPT,

	GET_MAIN_WINDOW,
	CLOSE_ALL_CHILD_WINDOWS,
}
