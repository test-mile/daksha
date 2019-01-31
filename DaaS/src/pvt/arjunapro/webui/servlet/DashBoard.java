package pvt.arjunapro.webui.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.arjunapro.testauto.console.Console;

import pvt.arjunapro.web.utils.HTMLUtils;
import pvt.unitee.cli.UniteeCLI;

public class DashBoard extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2148735714508044601L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		System.out.println(path);
		String retContent =  null;
		switch (path){
		case "/":
			retContent = skeleton(request, response);
			break;
		case "/widgets":
			retContent = widgets(request, response);
			break;
		default:
			retContent = HTMLUtils.notFoundPage(request, response, request.getRequestURI());
		}
		
		response.getWriter().println(retContent);
		// This is very important, else JavaFX keeps waiting with zero progress.
		response.flushBuffer();	
	}
	
	private String skeleton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/skel.html");	
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
	
	private String widgets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String retHtml = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/dash.html");
		response.setStatus(HttpStatus.OK_200);
		return retHtml;
	}
}


