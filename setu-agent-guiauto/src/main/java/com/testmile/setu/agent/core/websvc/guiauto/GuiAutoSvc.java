package com.testmile.setu.agent.core.websvc.guiauto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
import com.testmile.setu.agent.core.websvc.guiauto.helpers.GuiAutomatorHandler;
import com.testmile.setu.agent.core.websvc.guiauto.helpers.Response;
import com.testmile.trishanku.tpi.webserver.JsonUtils;

public class GuiAutoSvc extends HttpServlet {
	private static GuiAutomatorHandler guiAutoHandler = null;
	private static Gson gson = new Gson();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372756574410648998L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String retContent =  null;
		GuiAutoRequest req = new GuiAutoRequest(request);
		String path = req.getTarget() + req.getCommand();
		try {
			switch (path){
			case "/automator/quit":
				try {
					guiAutoHandler.quit();
					retContent = "Success";
				} catch (Exception e) {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
					retContent = e.getMessage();
				}
				break;
			default:
				throw new Exception(String.format("Requested link: %s is invalid.", request.getRequestURI()));
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			retContent = Response.createErrorResponseString(e);
		}
		
		response.getWriter().println(retContent);
		response.flushBuffer();	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String retContent =  null;
		GuiAutoRequest req = new GuiAutoRequest(request);
		String path = req.getTarget() + req.getCommand();
		try {
			switch (path){
			case "/automator/launch":
				guiAutoHandler = new GuiAutomatorHandler(req.getJsonStr(), req.getUuid());
				retContent = Response.createSuccessResponseString();
				break;
			case "/automator/action":
				retContent = guiAutoHandler.takeAction(req.getJsonStr());
				break;
			case "/element/action":
				retContent = guiAutoHandler.takeElementAction(req.getUuid(), req.getJsonStr());
				break;
			case "/multielement/action":
				retContent = guiAutoHandler.takeMultiElementAction(req.getUuid(), req.getJsonStr());
				break;
			default:
				throw new Exception(String.format("Requested link: %s is invalid.", request.getRequestURI()));
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			retContent = Response.createErrorResponseString(e);
		}
		
		response.getWriter().println(retContent);
		response.flushBuffer();	
	}
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String fullPath = request.getPathInfo();
//		System.out.println(fullPath);
//		String patternString1 = "(/.*)";
//        Pattern pattern = Pattern.compile(patternString1);
//        Matcher matcher = pattern.matcher(fullPath);
//        matcher.matches();
//        //String projName = matcher.group(1);
//        String path = matcher.group(1);
//		String retContent =  null;
//		System.out.println(path);
//		System.out.println(path.equals("/hello"));
//		switch (path){
//		case "/launch":
//			System.out.println("here");
//			retContent = "Hello";
//			response.setStatus(HttpStatus.OK_200);
//			break;
//		case "/home":
//			retContent = home(projName, request, response);
//			break;
//		case "/menu":
//			retContent = menu(projName, request, response);
//			break;
//		case "/home/new":
//			retContent = homeNew(projName, request, response);
//			break;
//		case "/settings":
//			retContent = projSettings(projName, request, response);
//			break;
//		case "/settings/panel":
//			retContent = projSettingsPanel(projName, request, response);
//			break;
//		case "/settings/view":
//			retContent = viewProjSettings(projName, request, response);
//			break;
//		case "/settings/edit":
//			retContent = editProjSettings(projName, request, response);
//			break;
//		case "/configure":
//			retContent = configure(projName, request, response);
//			break;
//		case "/conf/overview":
//			retContent = overview(projName, request, response);
//			break;
//		case "/conf/arjuna":
//			retContent = arjunaSettings(projName, request, response);
//			break;
//		case "/conf/arjuna/view":
//			retContent = viewArjunaSettings(projName, request, response);
//			break;
//		case "/conf/arjuna/edit":
//			retContent = editArjunaSettings(projName, request, response);
//			break;
//		case "/conf/uo":
//			retContent = userConf(projName, request, response);
//			break;
//		case "/conf/tv":
//			retContent = utv(projName, request, response);
//			break;
//		case "/execute":
//			retContent = execute(projName, request, response);
//			break;
//		default:
//			retContent = String.format("Requested link: <font color='red'>%s</font> is invalid.", request.getRequestURI());
//		}
//		
//		response.getWriter().println(retContent);
//		// This is very important, else JavaFX keeps waiting with zero progress.
//		response.flushBuffer();	
//	}
	
//	private String execute(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjForm.html");
//		String common = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjFormCommon.html");
//		String runid = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/runid.html");
//		String simple = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/execProjFormCommonSimple.html");
//		String session = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/session.html");
//		String picker = "";
//		
//		String retHtml = body
//				.replace("{{COMMON_EXEC_FORM}}", common)
//				.replace("{{COMMON_SIMPLE_EXEC_FORM}}", simple)
//				.replace("{{RUNID_FORM}}", runid)
//				.replace("{{SESSION_FORM}}", session)
//				.replace("{{PICKER_FORM}}", "")
//				.replace("{{PROJECT_NAME}}", projName)
//				.replace("{{SOURCE_CONFIG_TYPE}}", "Session");
//		response.setStatus(HttpStatus.OK_200);
//		return retHtml;
//	}
	
//	private String execute(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjForm.html");
//		String common = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjFormCommon.html");
//		String runid = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/runid.html");
//		String simple = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/execProjFormCommonSimple.html");
//		//String session = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/sessionm.html");;
//		String pickers = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickers.html");
//		String pickSingle = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickersingle.html");
//		String pickMulti = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/exec/pickermulti.html");
//					
//		String retHtml = body;
//				.replace("{{COMMON_EXEC_FORM}}", common)
//				.replace("{{COMMON_SIMPLE_EXEC_FORM}}", simple)
//				.replace("{{RUNID_FORM}}", runid)
//				//.replace("{{SESSION_FORM}}", session)
//				.replace("{{SESSION_FORM}}", "")
//				.replace("{{PICKER_FORM}}", pickers)
//				.replace("{{PICKER_PACKAGE_NAME}}", pickSingle.replace("{{OBJECT}}", "Package"))
//				.replace("{{PICKER_MULTI_PACKAGE}}", pickMulti.replace("{{OBJECT}}", "Package").replace("{{OBJECTS}}", "Packages"))
//				.replace("{{PICKER_CLASS_NAME}}", pickSingle.replace("{{OBJECT}}", "Class"))
//				.replace("{{PICKER_MULTI_CLASS}}", pickMulti.replace("{{OBJECT}}", "Class").replace("{{OBJECTS}}", "Classes"))
//				.replace("{{PICKER_METHODS}}", pickMulti.replace("{{OBJECT}}", "Method").replace("{{OBJECTS}}", "Methods"))
//				.replace("{{PROJECT_NAME}}", projName)
//				.replace("{{SOURCE_CONFIG_TYPE}}", "Project");
//		response.setStatus(HttpStatus.OK_200);
//		return "";
//	}
	
//	private String home(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String body = HTMLUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projectSection.html");
//
//		String retHtml = body
//				.replace("{{PROJECT_NAME}}", projName);
//		response.setStatus(HttpStatus.OK_200);
//		return retHtml;
//	}
	
//	private String menu(String projName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String body = JsonUtils.getFileContent("/com/arjunapro/pvt/content/html/project/projectMenu.html");
//
//		String retHtml = body
//				.replace("{{PROJECT_NAME}}", projName);
//		response.setStatus(HttpStatus.OK_200);
//		return retHtml;
//	}	
		
}

class GuiAutoRequest{
	private String target;
	private String uuid;
	private String command;
	private String jsonStr;
	
	public GuiAutoRequest(HttpServletRequest request) throws IOException {
		String fullPath = request.getPathInfo();
		System.out.println(fullPath);
		String patternString1 = "(/.*?)/(.*?)(/.*?)";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(fullPath);
        matcher.matches();
        target = matcher.group(1);
        uuid = matcher.group(2);
        command = matcher.group(3);
        System.out.println(target);
        System.out.println(uuid);
        System.out.println(command);		
		jsonStr = JsonUtils.asJsonString(request.getInputStream());
		System.out.println("JsonInput:" + jsonStr);		
	}
	
	public String getTarget() {
		return target;
	}

	public String getUuid() {
		return uuid;
	}

	public String getCommand() {
		return command;
	}

	public String getJsonStr() {
		return jsonStr;
	}	
}
