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

package com.testmile.setu.requester.config;

public enum SetuActionType {
	TESTSESSION_INIT,
	TESTSESSION_FINISH,
	
	TESTSESSION_LOAD_PROJECT_CONF,
	TESTSESSION_REGISTER_CONFIG,
	
	TESTSESSION_CREATE_FILE_DATA_SOURCE,
	
	TESTSESSION_LAUNCH_GUIAUTOMATOR,
	TESTSESSION_QUIT_GUIAUTOMATOR,
	
	CONFIGURATOR_GET_SETU_OPTION_VALUE,
	CONFIGURATOR_GET_USER_OPTION_VALUE,
	
	DATASOURCE_GET_NEXT_RECORD,
	DATASOURCE_GET_ALL_RECORDS,
	DATASOURCE_RESET,
	
	/* -----------------------------------
	 * BEGIN Actions for Automator and Gui 
	 * ----------------------------------- */
	
	GUIAUTO_BROWSER_GO_TO_URL,
	GUIAUTO_BROWSER_GO_BACK,
	GUIAUTO_BROWSER_GO_FORWARD,
	GUIAUTO_BROWSER_REFRESH,
	GUIAUTO_BROWSER_EXECUTE_JAVASCRIPT,
	
	GUIAUTO_CREATE_ELEMENT,
	GUIAUTO_CREATE_MULTIELEMENT,
	GUIAUTO_CREATE_DROPDOWN,
	GUIAUTO_CREATE_RADIOGROUP,
	GUIAUTO_CREATE_ALERT,

	GUIAUTO_GET_MAIN_WINDOW,
	GUIAUTO_GET_DOM_ROOT,
	GUIAUTO_SET_SLOMO,
	
	/* ---
	 * END
	 * --- */
	
	GUIAUTO_GUIMGR_CREATE_GUI,
	GUIAUTO_GUI_CREATE_GUI,
	
	GUIAUTO_ELEMENT_ENTER_TEXT,
	GUIAUTO_ELEMENT_SET_TEXT,
	GUIAUTO_ELEMENT_CLEAR_TEXT,
	
	GUIAUTO_ELEMENT_CLICK, 
	
	GUIAUTO_ELEMENT_WAIT_UNTIL_CLICKABLE, 
	
	GUIAUTO_ELEMENT_CHECK,
	GUIAUTO_ELEMENT_UNCHECK,
	
	GUIAUTO_DROPDOWN_HAS_VALUE_SELECTED,
	GUIAUTO_DROPDOWN_HAS_INDEX_SELECTED,
	GUIAUTO_DROPDOWN_SELECT_BY_VALUE,
	GUIAUTO_DROPDOWN_SELECT_BY_INDEX, 
	GUIAUTO_DROPDOWN_GET_FIRST_SELECTED_OPTION_VALUE,
	GUIAUTO_DROPDOWN_HAS_VISIBLE_TEXT_SELECTED,
	GUIAUTO_DROPDOWN_GET_FIRST_SELECTED_OPTION_TEXT,
	GUIAUTO_DROPDOWN_SELECT_BY_VISIBLE_TEXT,
	
	GUIAUTO_RADIOGROUP_HAS_VALUE_SELECTED,
	GUIAUTO_RADIOGROUP_HAS_INDEX_SELECTED,
	GUIAUTO_RADIOGROUP_SELECT_BY_VALUE,
	GUIAUTO_RADIOGROUP_SELECT_BY_INDEX, 
	GUIAUTO_RADIOGROUP_GET_FIRST_SELECTED_OPTION_VALUE,

	GUIAUTO_DOMROOT_FOCUS,
	GUIAUTO_DOMROOT_CREATE_FRAME,
	
	GUIAUTO_WEB_ALERT_CONFIRM,
	GUIAUTO_WEB_ALERT_DISMISS,
	GUIAUTO_WEB_ALERT_GET_TEXT,
	GUIAUTO_WEB_ALERT_SEND_TEXT,
	
	GUIAUTO_FRAME_FOCUS,
	GUIAUTO_FRAME_CREATE_FRAME,
	GUIAUTO_FRAME_GET_PARENT,
	
	GUIAUTO_WINDOW_FOCUS,
	GUIAUTO_WINDOW_GET_TITLE, 
	
	GUIAUTO_MAIN_WINDOW_MAXIMIZE,
	GUIAUTO_MAIN_WINDOW_CREATE_CHILD_WINDOW,
	GUIAUTO_MAIN_WINDOW_GET_LATEST_CHILD_WINDOW,
	GUIAUTO_MAIN_WINDOW_CLOSE_ALL_CHILD_WINDOWS,
	
	GUIAUTO_CHILD_WINDOW_CLOSE,
}
