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
package daksha.tpi.cleanup.constructor;

import org.apache.log4j.Logger;

import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.loader.IniUiDefLoader;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.constructor.loader.UiDefLoader;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.enums.FileFormat;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class UiDefLoaderFactory {
	private static Logger sLogger = Logger.getLogger("daksha");
	
	public static UiDefLoader createUiDefLoader(TestContext testContext, String defPath) throws Exception{
		String ext = FileSystemUtils.getExtension(defPath).toUpperCase();
		FileFormat format = null;
		String consideredPath = defPath;
		try{
			format = FileFormat.valueOf(ext);
		} catch (Exception e){
			throw new Problem(
					"UI Automator", 
					"UI Loader", 
					"createUiDefLoader", 
					ErrorType.UIDEFFILE_UNSUPPORTED_FORMAT, 
					String.format(ErrorType.UIDEFFILE_UNSUPPORTED_FORMAT, ext)
				);			
		}
		
		if (!FileSystemUtils.isFile(consideredPath)){
			consideredPath = FileSystemUtils.getCanonicalPath(testContext.getConfig().value(DakshaOption.UIAUTO_DEF_DIR).asString() + "/" + consideredPath);
			if (FileSystemUtils.isDir(consideredPath)){
				throw new Problem(
						"UI Automator", 
						"UI Loader", 
						"createUiDefLoader",  
						ErrorType.UIDEFFILE_NOTAFILE, 
						String.format(ErrorType.UIDEFFILE_NOTAFILE, consideredPath)
					);				
			} else if (!FileSystemUtils.isFile(consideredPath)){
				throw new Problem(
						"UI Automator", 
						"UI Loader", 
						"createUiDefLoader", 
						ErrorType.UIDEFFILE_NOT_FOUND, 
						String.format(ErrorType.UIDEFFILE_NOT_FOUND, consideredPath)
					);				
			}
		}

		switch(format){
		case INI : return new IniUiDefLoader(consideredPath);
		default: return null;
		}
	}
}
