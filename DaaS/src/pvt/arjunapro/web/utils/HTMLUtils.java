package pvt.arjunapro.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HTMLUtils {
	private static Gson gson;
	
	public static String getFileContent(String path) throws IOException{
		return IOUtils.toString((BufferedInputStream) HTMLUtils.class.getResource(path).getContent(), "UTF-8");
	}

	public static String notFoundPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
		String base = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/skel.html");	
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/404.html").replace("{{%%URI}}", path);

		String retHtml = base.replace("{{%%BODY}}", body);
		response.setStatus(HttpStatus.NOT_FOUND_404);
		return retHtml;		
	}
	
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
}
