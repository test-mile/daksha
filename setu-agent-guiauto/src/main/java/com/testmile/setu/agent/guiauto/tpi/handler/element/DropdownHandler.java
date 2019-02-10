package com.testmile.setu.agent.guiauto.tpi.handler.element;

import java.util.List;

public interface DropdownHandler {

	void select(String text) throws Exception;

	void selectText(String text) throws Exception;

	void selectValue(String value) throws Exception;

	void selectAtIndex(int index) throws Exception;

	boolean hasSelectedText(String text) throws Exception;

	boolean hasSelectedValue(String value) throws Exception;

	boolean hasSelectedIndex(int index) throws Exception;

	List<String> getAllOptions() throws Exception;

	List<String> getAllValues() throws Exception;

	int getOptionCount() throws Exception;

}