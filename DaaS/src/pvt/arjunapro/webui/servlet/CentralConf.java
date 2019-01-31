package pvt.arjunapro.webui.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.arjunapro.testauto.console.Console;
import com.arjunapro.testauto.interfaces.Value;
import com.google.gson.JsonObject;

import pvt.arjunapro.web.utils.CentralDB;
import pvt.arjunapro.web.utils.HTMLUtils;
import pvt.unitee.cli.UniteeCLI;

public class CentralConf extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println("hhh" + path);
		String retContent =  null;
		switch (path){
		case "/":
			retContent = main(request, response);
			break;
		case "/overview":
			retContent = overview(request, response);
			break;
		case "/arjuna":
			retContent = arjunaSettings(request, response);
			break;
		case "/arjuna/view":
			retContent = viewArjunaSettings(request, response);
			break;
		case "/arjuna/edit":
			retContent = editArjunaSettings(request, response);
			break;
		case "/uo":
			retContent = userConf(request,response);
			break;
		case "/tv":
			retContent = utv(request,response);
			break;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
	
	private String main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/allconf.html");
		retHtml = retHtml.replace("{{CONF_URI}}", "/centralconf");
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String overview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confoverview.html");
		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String arjunaSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettings.html")
				.replace("{{VIEW_LINK}}", "/centralconf/arjuna/view")
				.replace("{{EDIT_LINK}}", "/centralconf/arjuna/edit")
				.replace("{{SAVE_LINK}}", "/centralconf/arjuna/save");	

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String viewArjunaSettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettingsView.html");
		try{
			HashMap<String, Value> vals = CentralDB.INSTANCE.getCentralArjunaConfig();
			for(String k: vals.keySet()){
				body = body.replace(String.format("{{%s}}", k), vals.get(k).asString());
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String editArjunaSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/arjunaSettingsEdit.html");
		try{
			body = body.replace("{{JSON_CONF}}", CentralDB.INSTANCE.getCentralArjunaConfigAsJsonString());
		} catch (Exception e){
			e.printStackTrace();
		}

		response.setStatus(HttpStatus.OK_200);
		return body;
	}
	
	private String userConf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confFormTemplate.html")
				.replace("{{CONF_TYPE}}", "uo")
				.replace("{{CONF_TITLE}}", "User Options")
				.replace("{{CONF_SAVE_LINK}}", "/centralconf/uo/save");		
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String utv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/conf/confFormTemplate.html")
				.replace("{{CONF_TYPE}}", "tv")
				.replace("{{CONF_TITLE}}", "Test Variables")
				.replace("{{CONF_SAVE_LINK}}", "/centralconf/tv/save");	
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		String retContent =  null;
		
		JsonObject jObj = HTMLUtils.getJson(request.getInputStream());
		System.out.println(jObj.toString());
		
		switch (path){
		case "/arjuna/save":
			response.sendRedirect("/centralconf/arjuna");
			return;
		case "/uo/save":
			response.sendRedirect("/centralconf/userConf");
			return;
		case "/tv/save":
			response.sendRedirect("/centralconf/utv");
			return;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
	
}


