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
package daksha.core.batteries.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import daksha.tpi.sysauto.utils.DataUtils;

public class FileWriter {
	private BufferedWriter writer = null;

	public void flush() throws IOException {
		this.writer.flush();
	}

	private void prepareFile(String filePath) throws FileNotFoundException {
		File resultFile = new File(filePath);
		FileOutputStream is = new FileOutputStream(resultFile);
		OutputStreamWriter osw = new OutputStreamWriter(is);
		this.writer = new BufferedWriter(osw);
	}

	public void setPath(String filePath) throws FileNotFoundException {
		prepareFile(filePath);
	}

	public FileWriter() throws FileNotFoundException {
	}

	public FileWriter(String filePath) throws FileNotFoundException {
		prepareFile(filePath);
	}

	public void write(String data) throws IOException {
		this.writer.write(data);
		this.flush();
	}

	public void writeLine(String line) throws IOException {
		this.writer.write(line + "\r\n");
		this.flush();
	}

	public void writeArrayAsLine(String[] lineParts, String delimiter) throws IOException {
		this.writer.write(DataUtils.join(lineParts, delimiter) + "\r\n");
	}

	public void close() throws IOException {
		this.writer.close();
	}
}
