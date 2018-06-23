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

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.tpi.cleanup.gui.BaseGui;
import daksha.tpi.cleanup.gui.DefaultGui;
import daksha.tpi.cleanup.gui.Gui;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class PageFactory {
	
	public static Gui createAppFromDir(String name, GuiAutomatorProxy automator, String appMapsRootDir) throws Exception{
		String consideredPath = appMapsRootDir;
		if (!FileSystemUtils.isDir(consideredPath)){
			consideredPath = FileSystemUtils.getCanonicalPath(automator.getTestContext().getConfig().value(DakshaOption.GUIAUTO_MAPS_DIR).asString() + "/" + consideredPath);
			if (!FileSystemUtils.isDir(consideredPath)){
				throw new Exception(String.format("Provided root map path dir is not a directory: %s" ,  consideredPath));			
			} 
		}
		Gui app = new DefaultGui(name, automator, appMapsRootDir + File.separator + "home.ini");
		File d = new File(consideredPath + File.separator + "pages");
		if (FileSystemUtils.isDir(d)){
			for (File path: d.listFiles()){
				app.registerPage(FilenameUtils.getBaseName(path.getAbsolutePath()), path.getAbsolutePath());
			}
		}
		return app;
	}

	public static Gui getPage(GuiAutomatorProxy automator, String mapPath) throws Exception {
		return new DefaultGui(FileSystemUtils.getFileName(mapPath), automator, mapPath);
	}
}
