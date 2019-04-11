/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package arjuna.lib.setu.actor;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	private static Gson gson;
	
//	public static String getFileContent(String path) throws IOException{
//		return IOUtils.toString((BufferedInputStream) HTMLUtils.class.getResource(path).getContent(), "UTF-8");
//	}
	
    public static Gson getGson() {
        if (gson == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
        }
        return gson;
    }
    
    public static JsonObject getJson(ServletInputStream stream) throws IOException{
    	JsonParser parser = new JsonParser();
    	StringWriter writer = new StringWriter();
    	IOUtils.copy(stream, writer, "UTF-8");
    	String theString = writer.toString();
    	return parser.parse(theString).getAsJsonObject();
    }
    
    public static String asJsonString(ServletInputStream stream) throws IOException{
    	StringWriter writer = new StringWriter();
    	IOUtils.copy(stream, writer, "UTF-8");
    	String theString = writer.toString();
    	return theString;
    }
    
    public static String readMavenResource(String path) throws Exception{
    	String result = "";
		
    	ClassLoader classLoader = JsonUtils.class.getClassLoader();
    	try {
    	    result = IOUtils.resourceToString(path, Charset.forName("UTF-8"), classLoader);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
}
