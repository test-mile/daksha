package pvt.arjunapro.webui.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.arjunapro.testauto.console.Console;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import pvt.arjunapro.web.utils.HTMLUtils;
import pvt.unitee.cli.UniteeCLI;

public class Projects extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println(path);
		String retContent =  null;
		switch (path){
		case "/":
			retContent = main(request, response);
			break;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}

	private String main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/gotoproject.html");
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println(path);
		JsonObject jObj = HTMLUtils.getJson(request.getInputStream());
		String retContent =  null;
		switch (path){
		case "/create":
			// Here we need to create the DB entry and dir structure in projects directory.
			//Then redirect to project/<project_name>
			response.sendRedirect(String.format("/project/%s/home/new", jObj.get("newProjName").getAsString()));
			return;
		default:
			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
		
}


