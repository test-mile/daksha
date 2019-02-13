package com.testmile.setu.agent.core.websvc.guiauto.helpers;

public enum ElementActionType {
	// Single element actions
	CLICK,
	CLEAR_TEXT,
	SEND_TEXT,
	FIND_MULTIELEMENT,
	FIND_ELEMENT,
	
	// Single and Multi-element actions
	WAIT_UNTIL_CLICKABLE,
	WAIT_UNTIL_VISIBLE,
	IS_SELECTED,
	GET_TAG_NAME,
	GET_ATTR_VALUE,
	GET_TEXT_CONTENT
}
