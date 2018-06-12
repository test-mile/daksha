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
package daksha.tpi.leaping.factories;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.factories.PageMapperFactory;
import daksha.tpi.leaping.interfaces.App;
import daksha.tpi.leaping.interfaces.Page;
import daksha.tpi.leaping.interfaces.PageDefLoader;
import daksha.tpi.leaping.interfaces.GuiAutomator;
import daksha.tpi.leaping.pageobject.BaseApp;
import daksha.tpi.leaping.pageobject.BasePage;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class PageFactory {

//	public static Page getPage(String label, UiDriver uiDriver, PageMapper mapper) throws Exception{
//		Page page = new BasePage(label, uiDriver);
//		page.populate(mapper);
//		return page;
//	}

	private static Page getPage(GuiAutomator uiDriver, PageDefLoader mapper) throws Exception{
		Page page = new BasePage(uiDriver);
		page.populate(mapper);
		return page;
	}
	
	public static App getSimpleApp(String name, GuiAutomator uiDriver, String appMapsRootDir) throws Exception{
		App app = new BaseApp(name);
		String consideredPath = appMapsRootDir;
		if (!FileSystemUtils.isDir(consideredPath)){
			consideredPath = FileSystemUtils.getCanonicalPath(Batteries.value(UiAutomatorPropertyType.DIRECTORY_PROJECT_UI_MAPS).asString() + "/" + consideredPath);
			if (!FileSystemUtils.isDir(consideredPath)){
				throw new Problem(
						"UI Automator", 
						"Page Mapper", 
						"getFileMapper", 
						Daksha.problem.APP_MAP_DIR_NOT_A_DIR, 
						Batteries.getProblemText(Daksha.problem.APP_MAP_DIR_NOT_A_DIR, consideredPath)
					);				
			} 
		}
		File d = new File(consideredPath);
		for (File path: d.listFiles()){
			app.registerPage(FilenameUtils.getBaseName(path.getAbsolutePath()), uiDriver,
					path.getAbsolutePath());
		}
		return app;
	}

	public static Page getPage(GuiAutomator uiDriver, String mapPath) throws Exception {
		return getPage(uiDriver, PageMapperFactory.getFileMapper(mapPath));
	}
}
