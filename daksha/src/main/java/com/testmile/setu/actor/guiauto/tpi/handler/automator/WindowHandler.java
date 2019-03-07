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

package com.testmile.setu.actor.guiauto.tpi.handler.automator;

import java.util.Map;
import java.util.Set;

public interface WindowHandler {
	
	Map<String, Integer> getCurrentWindowSize() throws Exception;
	
	Set<String> getAllWindowHandles() throws Exception;

	void setWindowSize(int width, int height) throws Exception;

	void maximizeWindow();

	String getCurrentWindowHandle();

	void focusOnWindow(String windowHandle);

	void closeCurrentWindow();

	String getTitle();

}