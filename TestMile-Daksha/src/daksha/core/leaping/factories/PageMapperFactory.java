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
package daksha.core.leaping.factories;

import org.apache.log4j.Logger;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.lib.IniPageDefLoader;
import daksha.tpi.enums.FileFormat;
import daksha.tpi.leaping.interfaces.PageDefLoader;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class PageMapperFactory {
	private static Logger sLogger = Logger.getLogger(Batteries.getCentralLogName());
	
	public static PageDefLoader getFileMapper(String mapPath) throws Exception{
		String ext = FileSystemUtils.getExtension(mapPath).toUpperCase();
		FileFormat format = null;
		String consideredPath = mapPath;
		try{
			format = FileFormat.valueOf(ext);
		} catch (Exception e){
			throw new Problem(
					"UI Automator", 
					"Page Mapper", 
					"getFileMapper", 
					Daksha.problem.UNSUPPORTED_MAP_FILE_FORMAT, 
					Batteries.getProblemText(Daksha.problem.UNSUPPORTED_MAP_FILE_FORMAT, ext)
				);			
		}
		
		if (!FileSystemUtils.isFile(consideredPath)){
			consideredPath = FileSystemUtils.getCanonicalPath(Batteries.value(UiAutomatorPropertyType.DIRECTORY_PROJECT_UI_MAPS).asString() + "/" + consideredPath);
			if (FileSystemUtils.isDir(consideredPath)){
				throw new Problem(
						"UI Automator", 
						"Page Mapper", 
						"getFileMapper", 
						Daksha.problem.MAPFILE_NOTAFILE, 
						Batteries.getProblemText(Daksha.problem.MAPFILE_NOTAFILE, consideredPath)
					);				
			} else if (!FileSystemUtils.isFile(consideredPath)){
				throw new Problem(
						"UI Automator", 
						"Page Mapper", 
						"getFileMapper", 
						Daksha.problem.MAPFILE_NOT_FOUND, 
						Batteries.getProblemText(Daksha.problem.MAPFILE_NOT_FOUND, consideredPath)
					);				
			}
		}

		switch(format){
		case INI : return new IniPageDefLoader(consideredPath);
		default: return null;
		}
	}
}
