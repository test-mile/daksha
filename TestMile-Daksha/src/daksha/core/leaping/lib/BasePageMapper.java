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
package daksha.core.leaping.lib;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.UiAutomator;
import daksha.tpi.leaping.interfaces.PageMapper;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BasePageMapper implements PageMapper{
	private String mapName = null;
	
	public abstract String getName();
	
	protected Object throwGenericUiMapperException(
			String action,
			String code,
			String message
			) throws Exception{
				throw new Problem(
						Batteries.getConfiguredName("COMPONENT_NAMES", "UI_AUTOMATOR"),
				this.getName(),
				action,
				code,
				message
				);		
	}
	
	public Object throwNotAFileException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				UiAutomator.problem.MAPFILE_NOTAFILE,
				Batteries.getProblemText(
						UiAutomator.problem.MAPFILE_NOTAFILE,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwFileNotFoundException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				UiAutomator.problem.MAPFILE_NOT_FOUND,
				Batteries.getProblemText(
						UiAutomator.problem.MAPFILE_NOT_FOUND,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwRelativePathException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				UiAutomator.problem.MAPFILE_RELATIVE_PATH,
				Batteries.getProblemText(
						UiAutomator.problem.MAPFILE_RELATIVE_PATH,
						filePath
				)
		);
	}
}
