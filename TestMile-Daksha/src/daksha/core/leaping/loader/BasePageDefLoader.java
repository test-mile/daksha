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
package daksha.core.leaping.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.Daksha;
import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.identifier.DefaultElementMetaData;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.loader.PageDefLoader;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BasePageDefLoader implements PageDefLoader{
	private String name;
	private PageDefinition pageDef;
	
	public BasePageDefLoader(String name) {
		this.name = name;
		this.pageDef = new PageDefinition();
	}
	
	public String getName() {
		return this.name;
	}
	
	protected synchronized void addElementMetaData(String name, Map<String,String> map) throws Exception {
		this.pageDef.addElementMetaData(name, map);
	}
	
	@Override
	public synchronized PageDefinition getPageDef() {
		return this.pageDef;
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
				Daksha.problem.MAPFILE_NOTAFILE,
				String.format(
						Daksha.problem.MAPFILE_NOTAFILE,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwFileNotFoundException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				Daksha.problem.MAPFILE_NOT_FOUND,
				String.format(
						Daksha.problem.MAPFILE_NOT_FOUND,
						FileSystemUtils.getCanonicalPath(filePath)
				)
		);
	}
	
	public Object throwRelativePathException(String methodName, String filePath) throws Exception{
		return throwGenericUiMapperException(
				methodName,
				Daksha.problem.MAPFILE_RELATIVE_PATH,
				String.format(
						Daksha.problem.MAPFILE_RELATIVE_PATH,
						filePath
				)
		);
	}
}
