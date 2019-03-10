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

package com.testmile.setu.actor.guiauto.core;


public enum GuiAutomatorActionType {
	QUIT,
	
	GO_TO_URL,
	FIND_MULTIELEMENT, 
	FIND_ELEMENT,
	
	// Window Actions
	GET_CURRENT_WINDOW_HANDLE,
	GET_CURRENT_WINDOW_TITLE,
	MAXIMIZE_CURRENT_WINDOW,
	GET_CURRENT_WINDOW_SIZE,
	SET_CURRENT_WINDOW_SIZE,
	GET_ALL_WINDOW_HANDLES,
	FOCUS_ON_WINDOW,
	CLOSE_CURRENT_WINDOW, 
	
	EXECUTE_JAVASCRIPT, 

	IS_WEB_ALERT_PRESENT,
    CONFIRM_WEB_ALERT,
    DISMISS_WEB_ALERT,
    SEND_TEXT_TO_WEB_ALERT,
    GET_TEXT_FROM_WEB_ALERT,
    
    GET_CURRENT_MOBILE_VIEW_CONTEXT,
    GET_ALL_MOBILE_VIEW_CONTEXTS,
    FOCUS_ON_MOBILE_VIEW_CONTEXT,
    
    FOCUS_ON_FRAME,
    FOCUS_ON_PARENT_FRAME,
    FOCUS_ON_DOM_ROOT
}
