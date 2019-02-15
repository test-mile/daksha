package com.testmile.setu.agent.core.websvc.guiauto.helpers;

public enum AutomatorActionType {
	GO_TO_URL,
	FIND_MULTIELEMENT, 
	FIND_ELEMENT,
	SWITCH_TO_FRAME,
	
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
    SWITCH_TO_VIEW_CONTEXT
}
