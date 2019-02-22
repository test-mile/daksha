package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.awt.image.renderable.RenderableImage;
import java.awt.image.renderable.RenderableImageOp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.SystemPropertyUtils;

import com.testmile.setu.agent.SetuAgent;
import com.testmile.setu.agent.core.websvc.guiauto.SetuWebService;
import com.testmile.setu.agent.core.websvc.guiauto.helpers.AutomatorActionType;
import com.testmile.setu.agent.core.websvc.guiauto.helpers.GuiAutomatorHandler;

import utils.CFG;
import utils.CFG.ResponseJson;
import utils.CFG.TempJSON;
import utils.UUIDProvider;

class AutomatorTests {

	private GuiAutomatorHandler handler;
	private String setuid;

	@BeforeEach
	public void launchAutomationHandler() throws Exception {
		setuid = UUIDProvider.genUUID();
		handler = new GuiAutomatorHandler(CFG.jsonConfAsStr(), setuid);
		assertEquals(setuid, handler.getSetuId(), "Setu id should be same as the uuid passed to the handler");
		handler.getSetuId();
	}

	@Test
	void getSetuId() throws Exception {
		String setuid = UUIDProvider.genUUID();
		GuiAutomatorHandler handler = new GuiAutomatorHandler(CFG.jsonConfAsStr(), setuid);
		assertEquals(setuid, handler.getSetuId(), "Setu id should be same as the uuid passed to the handler");
		handler.quit();
	}

	@Test
	void navigateToURL() throws Exception {
		TempJSON data = CFG.tempJson();
		URL url = getClass().getClassLoader().getResource("simple_html/index.html");
		data.addArgs("url", url.toString());
		data.addAction(AutomatorActionType.GO_TO_URL.toString());
		String response = handler.takeAction(data.str());
		assertTrue(ResponseJson.create(response).isSuccess(), "Result should be Success");
		System.out.println(response);
		// handler.takeAction(JSONConfig.)
		// String screenshotBytes = handler.takeScreeshot();
		// OutputStream output = new FileOutputStream(new
		// File(JSONConfig.SCREENSHOTS_DIR+ File.pathSeparatorChar+"sample.jpg"));
		//// BufferedImage outFile = ImageIO.read(new File(JSONConfig.SCREENSHOTS_DIR+
		// File.pathSeparatorChar+"sample.jpg"));
		// BufferedImage img = ImageIO.read(new
		// ByteArrayInputStream(screenshotBytes.getBytes()));
		// ImageIO.write(img, "jpg", output );
		// handler.getSetuId();
	}

	@Test
	void fetchTitle() throws Exception {
		TempJSON data = CFG.tempJson();
		URL url = getClass().getClassLoader().getResource("simple_html/index.html");
		System.out.println(url.getFile());
		data.addArgs("url", url.toString());
		data.addAction(AutomatorActionType.GO_TO_URL.toString());
		String response = handler.takeAction(data.str());
		assertTrue(ResponseJson.create(response).isSuccess(), "Result should be Success");
		String output = handler
				.takeAction(CFG.tempJson().addAction(AutomatorActionType.GET_WINDOW_TITLE.toString()).str());
		assertTrue(ResponseJson.create(output).isSuccess(), "Result should be Success");
		assertTrue(ResponseJson.create(output).isDataEquals("title", "App title"), "Should get the Browser Title");
	}

	@Test
	void getWindowSize() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_SIZE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		assertTrue(resp.isHeightPresent(), "Result should have height");
		assertTrue(resp.isWidthPresent(), "Result should have width");
	}

	@Test
	void setWindowSize() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_SIZE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		int height = resp.getHeight() + 40;
		int width = resp.getWidth() + 240;

		data = CFG.tempJson();
		data.addAction(AutomatorActionType.SET_WINDOW_SIZE.toString());
		data.addArgs("height", Integer.valueOf(height).toString());
		data.addArgs("width", Integer.valueOf(width).toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_SIZE.toString());
		;
		resp = ResponseJson.create(handler.takeAction(data.str()));
		assertEquals(height, resp.getHeight(), "Should be updated to new height");
		assertEquals(width, resp.getWidth(), "Should be updated to new Width");
	}

	@Test
	void maximizeWindow() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_SIZE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		int height = resp.getHeight();
		int width = resp.getWidth();

		data = CFG.tempJson();
		data.addAction(AutomatorActionType.MAXIMIZE_WINDOW.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_SIZE.toString());
		;
		resp = ResponseJson.create(handler.takeAction(data.str()));
		assertNotEquals(height, resp.getHeight(), "Should be updated to new height");
		assertNotEquals(width, resp.getWidth(), "Should be updated to new Width");
	}

	@Test
	void getWindowHandles() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_ALL_WINDOW_HANDLES.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		assertTrue(resp.getHandlers().size() == 1, "One window handle should be present");
	}

	@Test
	void getCurrentWindowHandle() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_HANDLE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp.getData("handle"));
		assertNotNull(resp.getData("handle"), "Current Window Handle should not be null");
	}

	@Test
	void executeJS() throws Exception {
		TempJSON data = CFG.tempJson();
		data.addArgs("script", "window.open()");
		data.addAction(AutomatorActionType.EXECUTE_JAVASCRIPT.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);
		assertTrue(resp.isSuccess(), "Result should be Success");
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_ALL_WINDOW_HANDLES.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);
		assertTrue(resp.getHandlers().size() == 2, "Two window handles should be present");
	}

	@Test
	void switchToWindow() throws Exception {

		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_HANDLE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		String mainWindow = resp.getData("handle");

		data = CFG.tempJson();
		data.addArgs("script", "window.open()");
		data.addAction(AutomatorActionType.EXECUTE_JAVASCRIPT.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);

		assertTrue(resp.isSuccess(), "Result should be Success");
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_ALL_WINDOW_HANDLES.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);

		assertTrue(resp.getHandlers().size() == 2, "Two window handles should be present");
		List<String> handles = resp.getHandlers();
		handles.remove(mainWindow);
		String currWindow = handles.iterator().next();
		System.out.println(currWindow);

		data = CFG.tempJson();
		data.addAction(AutomatorActionType.SWITCH_TO_WINDOW.toString());
		resp = ResponseJson.create(handler.takeAction(data.addArgs("handle", currWindow).str()));
		System.out.println(resp);
		Thread.sleep(5000);
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_HANDLE.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		assertNotEquals(resp.getData("handle"), mainWindow);
		assertEquals(resp.getData("handle"), currWindow);
	}
	
	@Test
	void closeWindow() throws Exception {

		TempJSON data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_HANDLE.toString());
		ResponseJson resp = ResponseJson.create(handler.takeAction(data.str()));
		String mainWindow = resp.getData("handle");

		data = CFG.tempJson();
		data.addArgs("script", "window.open()");
		data.addAction(AutomatorActionType.EXECUTE_JAVASCRIPT.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);

		assertTrue(resp.isSuccess(), "Result should be Success");
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_ALL_WINDOW_HANDLES.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);

		assertTrue(resp.getHandlers().size() == 2, "Two window handles should be present");
		List<String> handles = resp.getHandlers();
		handles.remove(mainWindow);
		String currWindow = handles.iterator().next();
		System.out.println(currWindow);

		data = CFG.tempJson();
		data.addAction(AutomatorActionType.SWITCH_TO_WINDOW.toString());
		resp = ResponseJson.create(handler.takeAction(data.addArgs("handle", currWindow).str()));
		System.out.println(resp);
		Thread.sleep(5000);
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.GET_CURRENT_WINDOW_HANDLE.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		assertNotEquals(resp.getData("handle"), mainWindow);
		assertEquals(resp.getData("handle"), currWindow);
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.CLOSE_CURRENT_WINDOW.toString());
		resp = ResponseJson.create(handler.takeAction(data.str()));
		System.out.println(resp);
		data = CFG.tempJson();
		data.addAction(AutomatorActionType.SWITCH_TO_WINDOW.toString());
		resp = ResponseJson.create(handler.takeAction(data.addArgs("handle", mainWindow).str()));
		System.out.println(resp);
		Thread.sleep(5000);
		
	}

	@AfterEach
	public void closeHandler() throws Exception {
		handler.quit();
	}

}
