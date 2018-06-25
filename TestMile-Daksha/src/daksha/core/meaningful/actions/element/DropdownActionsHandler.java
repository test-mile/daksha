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
package daksha.core.meaningful.actions.element;

import java.util.List;

public interface DropdownActionsHandler {
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
