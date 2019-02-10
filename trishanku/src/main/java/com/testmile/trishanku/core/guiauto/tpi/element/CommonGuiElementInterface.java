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
package com.testmile.trishanku.core.guiauto.tpi.element;

import java.util.List;

public interface CommonGuiElementInterface {
	
	// Attr inquiry
	String getText() throws Exception;
	String getValue() throws Exception;
	String getAttribute(String attr) throws Exception;
	
	// Basic Element Actions
	void enterText(String text) throws Exception;
	void setText(String text) throws Exception;
	void clearText() throws Exception;

	void focus() throws Exception;
	void click() throws Exception;
	void doubleClick() throws Exception;
	
	void submit() throws Exception;
	
	// Chain Actions
	void hover() throws Exception;
	void hoverAndClick() throws Exception;
	void rightClick()  throws Exception;
//	
//	void hoverAndClickElement(String name) throws Exception;
//	void rightClickAndClickElement(String name)  throws Exception;
//	void dragAndDrop(String name) throws Exception;
	
	// Checkbox actions
	void check() throws Exception;
	void uncheck() throws Exception;
	void toggle() throws Exception;
	
	// Drop Down actions
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
	
	// State
	boolean isPresent() throws Exception;
    boolean isAbsent()  throws Exception;
    boolean isVisible()  throws Exception;
    boolean isInvisible()  throws Exception;
	boolean isClickable() throws Exception;
    void waitUntilPresent() throws Exception;
    void waitUntilAbsent()  throws Exception;
    void waitUntilVisible()  throws Exception;
    void waitUntilInvisible()  throws Exception;
	void waitUntilClickable() throws Exception;
	boolean isChecked() throws Exception;
	boolean isSelected() throws Exception;
	
	// Image based identification
	String getImagePath() throws Exception;
	void setImagePath(String imagePath) throws Exception;

	// Web Actions
	void switchToFrame() throws Exception;
}
