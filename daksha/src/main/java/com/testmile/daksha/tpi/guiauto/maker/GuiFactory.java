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
package com.testmile.daksha.tpi.guiauto.maker;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.gui.SetuClientDefaultGui;
import com.testmile.daksha.tpi.guiauto.gui.SetuClientGui;
import com.testmile.daksha.tpi.sysauto.utils.FileSystemUtils;

public class GuiFactory {
	
	public static SetuClientGui createAppFromDir(String name, SetuClientGuiAutomator automator, String appDefDir) throws Exception{
		String consideredPath = appDefDir;
		if (!FileSystemUtils.isDir(consideredPath)){
			consideredPath = FileSystemUtils.getCanonicalPath(automator.getTestContext().getConfig().value(DakshaOption.GUIAUTO_NAMESPACE_DIR).asString() + "/" + consideredPath);
			if (!FileSystemUtils.isDir(consideredPath)){
				throw new Exception(String.format("Provided root definition path is not a directory: %s" ,  consideredPath));			
			} 
		}
		SetuClientGui app = new SetuClientDefaultGui(name, automator, appDefDir + File.separator + "Home.gns");
		File d = new File(consideredPath + File.separator + "children");
		if (FileSystemUtils.isDir(d)){
			for (File path: d.listFiles()){
				app.addChild(FilenameUtils.getBaseName(path.getAbsolutePath()), path.getAbsolutePath());
			}
		}
		return app;
	}

	public static SetuClientGui createGui(SetuClientGuiAutomator automator, String defPath) throws Exception {
		return new SetuClientDefaultGui(FileSystemUtils.getFileName(defPath), automator, defPath);
	}
}
