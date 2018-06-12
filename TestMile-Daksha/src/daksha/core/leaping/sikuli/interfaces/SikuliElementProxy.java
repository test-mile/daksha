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
package daksha.core.leaping.sikuli.interfaces;

import java.io.File;
import java.util.List;

import org.sikuli.script.Match;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.interfaces.UiElementProxy;
import daksha.tpi.leaping.interfaces.SikuliGuiAutomator;
import daksha.tpi.leaping.interfaces.GuiElement;

public interface SikuliElementProxy extends UiElementProxy{

	String getImagePath();

	void setImagePath(String imagePath) throws Exception;

	SikuliGuiAutomator getSikuliUiDriver();

	void setSikuliUiDriver(SikuliGuiAutomator uiDriver);

	Match getToolElement() throws Exception;

	Match getToolElementWithRetry() throws Exception;

	void setToolElement(Match element);

	void setToolElements(List<Match> elements);

	List<Match> getToolElements();

	void setRawToolElement(Object toolElementObject);

	void setRawToolElements(Object toolElementsObject);

	Object getToolFindersQueueObject();

	boolean isCompositeElementIdentified() throws Exception;

	boolean isSingularElementIdentified() throws Exception;

	int getElementCountForCompositeElement() throws Exception;

	void enterText(String text) throws Exception;

	void setText(String text) throws Exception;

	void clearText() throws Exception;

	boolean isPresent() throws Exception;

	void click() throws Exception;

	void hoverAndClick() throws Exception;

	void rightClick() throws Exception;

	int getWaitTime() throws Exception;

	File takeScreenshot() throws Exception;

	GuiElement getUiElementWrapperForToolElement(Match toolElement) throws Exception;

	// While returning element from a Composite UI Element, this method is used to create the wrapper
	GuiElement getElementWrapper(UiElementIdentifier elementMetaData, Match toolElement, ElementLoaderType type)
			throws Exception;

	void setElementForUiElement(Match toolElement) throws Exception;

	void setElementsForUiElement(List<Match> toolElements) throws Exception;

	void setElementForChildUiElement(GuiElement childUiElement, Match toolElement) throws Exception;

	void setElementsForChildUiElement(GuiElement childUiElement, List<Match> toolElements) throws Exception;

	void identify() throws Exception;

	void identifyAll() throws Exception;

	void assignElementAtIndexFromMatches(int index) throws Exception;

	void identifyAtIndex(int index) throws Exception;

	GuiElement getInstanceAtIndex(int index) throws Exception;

	List<GuiElement> getAllInstances() throws Exception;

	void hoverAndClickElement(GuiElement uiElement) throws Exception;

	void rightClickAndClickElement(GuiElement uiElement) throws Exception;

}
