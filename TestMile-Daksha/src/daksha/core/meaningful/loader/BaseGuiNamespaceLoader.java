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
package daksha.core.meaningful.loader;

import java.util.Map;

import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.meaningful.maker.namespace.GuiNamespaceLoader;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BaseGuiNamespaceLoader implements GuiNamespaceLoader{
	private String name;
	private GuiNamespace namespace;
	
	public BaseGuiNamespaceLoader(String name) {
		this.name = name;
		this.namespace = new GuiNamespace();
	}
	
	public String getName() {
		return this.name;
	}
	
	protected synchronized void addElementMetaData(String name, Map<String,String> map) throws Exception {
		this.namespace.addElementMetaData(name, map);
	}
	
	@Override
	public synchronized GuiNamespace getNamespace() {
		return this.namespace;
	}
	
	protected Object throwNamespaceLoaderException(
			String action,
			String code,
			String message
			) throws Exception{
				throw new Problem(
						"Namespace Loader",
				this.getName(),
				action,
				code,
				message
				);		
	}
	
	public Object throwNotAFileException(String methodName, String filePath) throws Exception{
		return throwNamespaceLoaderException(
				methodName,
				ErrorType.GUI_NAMESPACE_FILE_NOTAFILE,
				String.format(
						ErrorType.GUI_NAMESPACE_FILE_NOTAFILE,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwFileNotFoundException(String methodName, String filePath) throws Exception{
		return throwNamespaceLoaderException(
				methodName,
				ErrorType.GUI_NAMESPACE_FILE_NOT_FOUND,
				String.format(
						ErrorType.GUI_NAMESPACE_FILE_NOT_FOUND,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwRelativePathException(String methodName, String filePath) throws Exception{
		return throwNamespaceLoaderException(
				methodName,
				ErrorType.GUI_NAMESPACE_FILE_RELATIVE_PATH,
				String.format(
						ErrorType.GUI_NAMESPACE_FILE_RELATIVE_PATH,
						filePath
				)
		);
	}
}
