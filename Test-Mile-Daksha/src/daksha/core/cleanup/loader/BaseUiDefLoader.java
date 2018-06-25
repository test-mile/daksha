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
import daksha.tpi.cleanup.constructor.loader.UiDefLoader;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BaseUiDefLoader implements UiDefLoader{
	private String name;
	private UiDefinition uiDef;
	
	public BaseUiDefLoader(String name) {
		this.name = name;
		this.uiDef = new UiDefinition();
	}
	
	public String getName() {
		return this.name;
	}
	
	protected synchronized void addElementMetaData(String name, Map<String,String> map) throws Exception {
		this.uiDef.addElementMetaData(name, map);
	}
	
	@Override
	public synchronized UiDefinition getUiDef() {
		return this.uiDef;
	}
	
	protected Object throwGenericUiDefLoaderException(
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
		return throwGenericUiDefLoaderException(
				methodName,
				ErrorType.UIDEFFILE_NOTAFILE,
				String.format(
						ErrorType.UIDEFFILE_NOTAFILE,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwFileNotFoundException(String methodName, String filePath) throws Exception{
		return throwGenericUiDefLoaderException(
				methodName,
				ErrorType.UIDEFFILE_NOT_FOUND,
				String.format(
						ErrorType.UIDEFFILE_NOT_FOUND,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwRelativePathException(String methodName, String filePath) throws Exception{
		return throwGenericUiDefLoaderException(
				methodName,
				ErrorType.UIDEFFILE_RELATIVE_PATH,
				String.format(
						ErrorType.UIDEFFILE_RELATIVE_PATH,
						filePath
				)
		);
	}
}
