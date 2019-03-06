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

package com.testmile.setu.actor.core.websvc.guiauto.helpers;

public enum AutomatorActionType {
	GO_TO_URL,
	FIND_MULTIELEMENT, 
	FIND_ELEMENT,
	
	// Window Actions
	GET_CURRENT_WINDOW_HANDLE,
	GET_WINDOW_TITLE,
	MAXIMIZE_WINDOW,
	GET_CURRENT_WINDOW_SIZE,
	SET_WINDOW_SIZE,
	GET_ALL_WINDOW_HANDLES,
	SWITCH_TO_WINDOW,
	CLOSE_CURRENT_WINDOW, 
	
	EXECUTE_JAVASCRIPT, 

	IS_ALERT_PRESENT,
    CONFIRM_ALERT,
    DISMISS_ALERT,
    SEND_TEXT_TO_ALERT,
    GET_TEXT_FROM_ALERT,
    
    GET_CURRENT_VIEW_CONTEXT,
    GET_ALL_VIEW_CONTEXTS,
    SWITCH_TO_VIEW_CONTEXT,
    
    JUMP_TO_FRAME,
    JUMP_TO_PARENT_FRAME,
    JUMP_TO_HTML_ROOT
}
