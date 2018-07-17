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
package daksha.core.guiauto.namestore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import daksha.tpi.guiauto.enums.GuiAutomationContext;
import daksha.tpi.sysauto.file.FileLineReader;

public class NamespaceFileLoader extends BaseGuiNamespaceLoader{
	private File nsFile= null;
	private String nsPath = null;
	private Pattern namePattern = Pattern.compile("\\[\\s*(.*?)\\s*\\]");
	private Pattern platformPattern = Pattern.compile("\\s*#\\s*(.*?)\\s*");
	private Pattern idPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*");
	private boolean headerFound = false;
	private String lastHeader = null;
	private List<GuiAutomationContext> lastContexts;
	private Map<String, Map<GuiAutomationContext,List<StringNVPair>>> ns = new HashMap<String, Map<GuiAutomationContext,List<StringNVPair>>>();
	private FileLineReader reader = null;

	public NamespaceFileLoader(String nsFilePath) throws Exception {
		super("Default GUI Namespace Loader");
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
		reader = new FileLineReader(this.nsPath);
	}

	String delimiter = null;
	
	private boolean matchHeader(String input) throws Exception{
        Matcher matcher = namePattern.matcher(input);
        boolean matches = matcher.matches();	
        if (matches == true){
        	String currentHeader = matcher.group(1);
        	if (lastHeader == null) {
        		lastHeader = matcher.group(1);
        	} else if (lastHeader.equalsIgnoreCase(currentHeader)) {
        		throw new Exception("Found duplicate namespace definition for " + lastHeader);
        	} else {
        		if (ns.get(lastHeader).keySet().isEmpty()) {
        			throw new Exception("Found empty namespace definition for " + lastHeader);
        		} else {
        			for (GuiAutomationContext context: ns.get(lastHeader).keySet()) {
        				if (ns.get(lastHeader).get(context).isEmpty()) {
        					throw new Exception(String.format("Found empty namespace definition for %s context for %s", context.toString(), lastHeader));		
        				}
        			}
        		}
        		lastHeader = currentHeader;
        	}
        	
        	this.lastContexts = null;
        	ns.put(lastHeader, new HashMap<GuiAutomationContext,List<StringNVPair>>());
        	return true;
        } else {
        	return false;
        }
	}
	
	private boolean matchContexts(String input) throws Exception {
        Matcher matcher = platformPattern.matcher(input);
        boolean matches = matcher.matches();	
        if (matches == true){
        	String[] contexts = matcher.group(1).split(",");
        	GuiAutomationContext contextEnum = null;
        	List<GuiAutomationContext> contextEnums = new ArrayList<GuiAutomationContext>();
        	for (String context: contexts) {
        		try {
        			contextEnum = GuiAutomationContext.valueOf(context.toUpperCase());
        			contextEnums.add(contextEnum);
        		} catch (Exception e) {
        			throw new Exception("Invalid automation context in namespace file: " + context);
        		}
        		if (ns.get(lastHeader).containsKey(contextEnum)) {
        			throw new Exception(String.format("Found duplicate automation context %s in %s namespace definition", context, lastHeader));
        		} else {
        			ns.get(lastHeader).put(contextEnum, new ArrayList<StringNVPair>());
        		}
        	}
        	
        	lastContexts = contextEnums;
        	return true;
        } else {
        	return false;
        }
	}
	
	private boolean matchId(String input) throws Exception {
        Matcher matcher = idPattern.matcher(input);
        boolean matches = matcher.matches();	
        if (matches == true){
        	String type = matcher.group(1);
        	String value = matcher.group(2);
        	StringNVPair locator = new StringNVPair(type, value);
        	if (this.lastContexts == null) {
        		throw new Exception("Locators must be preceded with context information as #context1, context2 construct. Current line: " + input);
        	}
        	for (GuiAutomationContext context: this.lastContexts) {
        		ns.get(lastHeader).get(context).add(locator);
        	}
        	return true;
        } else {
        	return false;
        }
	}

	public void load() throws Exception{

		for (String line: reader.all()) {
	        if (matchHeader(line)) {
	        	headerFound = true;
	        	continue;
	        } else {
	        	if (!headerFound){
	        		throw new Exception("Namespace contents must be container inside a [name] header.");
	        	}
	        	if (matchContexts(line)) {
	        		continue;
	        	} else if (matchId(line)) {
	        		continue;
	        	} else {
	        		throw new Exception("Unexpected namespace file entry. Namspace content can either be plaforms or identification definition: " + line);
	        	}
	        }
		}
		
		reader.close();
		
		
		
		for (String ename: ns.keySet()) {
			for (GuiAutomationContext context: ns.get(ename).keySet()) {
				this.addElementMetaData(ename,  context, ns.get(ename).get(context));
			}
		}
	}

}
