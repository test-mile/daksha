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

import java.io.File;

abstract public class FileBasedGuiNamespaceLoader extends BaseGuiNamespaceLoader{
	private File nsFile= null;
	private String nsPath = null;

	public FileBasedGuiNamespaceLoader(String name, String nsFilePath) throws Exception {
		super(name);
		this.nsPath = nsFilePath;
		this.nsFile = new File(nsFilePath);
		if (!this.nsFile.isAbsolute()){
			this.throwRelativePathException("constructor", nsFilePath);
		} 
		
		if (!this.nsFile.exists()){
			this.throwFileNotFoundException("constructor", nsFilePath);
		} 
		
		if (!this.nsFile.isFile()){
			this.throwNotAFileException("constructor", nsFilePath);
		}
	}

	protected String getnsFilePath(){
		return nsPath;
	}
}
