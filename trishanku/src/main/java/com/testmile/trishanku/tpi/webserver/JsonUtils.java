package com.testmile.trishanku.tpi.webserver;

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
    	System.out.println(theString);
    	return parser.parse(theString).getAsJsonObject();
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
