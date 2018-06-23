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
package daksha.core.cleanup.loader;

import java.util.Map;

import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.constructor.loader.GuiDefLoader;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BaseGuiDefLoader implements GuiDefLoader{
	private String name;
	private GuiDefinition guiDef;
	
	public BaseGuiDefLoader(String name) {
		this.name = name;
		this.guiDef = new GuiDefinition();
	}
	
	public String getName() {
		return this.name;
	}
	
	protected synchronized void addElementMetaData(String name, Map<String,String> map) throws Exception {
		this.guiDef.addElementMetaData(name, map);
	}
	
	@Override
	public synchronized GuiDefinition getGuiDef() {
		return this.guiDef;
	}
	
	protected Object throwGenericUiMapperException(
			String action,
			String code,
			String message
			) throws Exception{
				throw new Problem(
						"Loader",
				this.getName(),
				action,
				code,
				message
				);		
	}
	
	public Object throwNotAFileException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				ErrorType.GUIDEFFILE_NOTAFILE,
				String.format(
						ErrorType.GUIDEFFILE_NOTAFILE,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwFileNotFoundException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				ErrorType.GUIDEFFILE_NOT_FOUND,
				String.format(
						ErrorType.GUIDEFFILE_NOT_FOUND,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwRelativePathException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				ErrorType.GUIDEFFILE_RELATIVE_PATH,
				String.format(
						ErrorType.GUIDEFFILE_RELATIVE_PATH,
						filePath
				)
		);
	}
}
