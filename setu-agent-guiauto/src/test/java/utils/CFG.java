package utils;

import java.io.File;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CFG {
	public static final String ROOT_DIR = new File("").getAbsolutePath();
	public static final String LOG_DIR = ROOT_DIR+ File.separatorChar+ "log";
	public static final String SCREENSHOTS_DIR = ROOT_DIR+ File.separatorChar+ "screenshots";
	public static final String DRIVERS_DIR = ROOT_DIR+ File.separatorChar+ "drivers";
	private static JsonObject jobj = new JsonObject();
	
	
	private CFG() {
	
	}
	
	public static TempJSON tempJson() {
		return TempJSON.create();
	}
	
	private static JsonObject defaults() {
		jobj.addProperty(JSONKEYS.ROOT_DIR.toString(), ROOT_DIR); 
		jobj.addProperty(JSONKEYS.LOG_DIR.toString(), LOG_DIR); 
		jobj.addProperty(JSONKEYS.SCREENSHOTS_DIR.toString(), SCREENSHOTS_DIR); 
		jobj.addProperty(JSONKEYS.TESTRUN_ENVIRONMENT.toString(), "TEST"); 
		jobj.addProperty(JSONKEYS.TESTRUN_TARGET_PLATFORM.toString(), "windows"); 
		jobj.addProperty(JSONKEYS.TESTRUN_TARGET_PLATFORM_VERSION.toString(), "NOT_SET");
		jobj.addProperty(JSONKEYS.GUI_AUTOMATOR_NAME.toString(), "SELENIUM"); 
		jobj.addProperty(JSONKEYS.BROWSER_NAME.toString(), "chrome"); 
		jobj.addProperty(JSONKEYS.BROWSER_VERSION.toString(), ""); 
		jobj.addProperty(JSONKEYS.BROWSER_MAXIMIZE.toString(), false); 
		jobj.addProperty(JSONKEYS.BROWSER_DIM_HEIGHT.toString(), "NOT_SET"); 
		jobj.addProperty(JSONKEYS.BROWSER_DIM_WIDTH.toString(), "NOT_SET"); 
		jobj.addProperty(JSONKEYS.BROWSER_BIN_PATH.toString(), "NOT_SET"); 
		jobj.addProperty(JSONKEYS.BROWSER_PROXY_ON.toString(), false); 
		jobj.addProperty(JSONKEYS.GUIAUTO_CONTEXT.toString(), "WEB"); 
		jobj.addProperty(JSONKEYS.GUIAUTO_SCROLL_PIXELS.toString(), "100"); 
		jobj.addProperty(JSONKEYS.GUIAUTO_SWIPE_TOP.toString(), "0.1"); 
		jobj.addProperty(JSONKEYS.GUIAUTO_SWIPE_BOTTOM.toString(), "0.5"); 
		jobj.addProperty(JSONKEYS.GUIAUTO_SWIPE_MAX_WAIT.toString(), "5"); 
		jobj.addProperty(JSONKEYS.GUIAUTO_MAX_WAIT.toString(), "60"); 
		jobj.addProperty(JSONKEYS.MOBILE_DEVICE_NAME.toString(), "Android Emulator"); 
		jobj.addProperty(JSONKEYS.MOBILE_DEVICE_UDID.toString(), "NOT_SET"); 
		jobj.addProperty(JSONKEYS.MOBILE_APP_FILE_PATH.toString(), "NOT_SET"); 
		jobj.addProperty(JSONKEYS.SELENIUM_DRIVER_PROP.toString(), "webdriver.chrome.driver"); 
		jobj.addProperty(JSONKEYS.SELENIUM_DRIVERS_DIR.toString(), DRIVERS_DIR); 
		jobj.addProperty(JSONKEYS.SELENIUM_DRIVER_PATH.toString(), DRIVERS_DIR+ File.separatorChar+ "chromedriver.exe"); 
		jobj.addProperty(JSONKEYS.APPIUM_HUB_URL.toString(), "http://127.0.0.1:4723/wd/hub"); 
		jobj.addProperty(JSONKEYS.APPIUM_AUTO_LAUNCH.toString(), "false"); 
		jobj.addProperty(JSONKEYS.IMAGE_COMPARISON_MIN_SCORE.toString(), "0.7");
		return jobj;
	}
	
	public static String jsonConfAsStr() {
		CFG.defaults();
		System.out.println(jobj);
		return jobj.toString();
	}
	
	public static class TempJSON {
		private final JsonObject TEMP_JSON = new JsonObject();
		private TempJSON() {
			
		}
		
		public static TempJSON create() {
			return new TempJSON();
		}
		
		public TempJSON add(String k, String v) {
			TEMP_JSON.addProperty(k, v);
			return this;
		}
		
		public TempJSON addAction(String v) {
			TEMP_JSON.addProperty("action", v);
			return this;
		}
		public TempJSON addArgs(String k, String v) {
			if(!TEMP_JSON.has("args")) {
				TEMP_JSON.add("args", new JsonObject());
			}
			TEMP_JSON.get("args").getAsJsonObject().addProperty(k, v);
			return this;
		}
		
		public String str() {
			return TEMP_JSON.toString();
		}
	}
	
	public static class ResponseJson {
		private final JsonObject TEMP_JSON ;
		
		private ResponseJson(String data) {
			TEMP_JSON = new GsonBuilder().create().fromJson(data, JsonObject.class);
		}
		
		public static ResponseJson create(String data) {
			return new ResponseJson(data);
		}
		
		public String getResult() {
			return TEMP_JSON.get("result").getAsString();
		}
		
		public boolean isSuccess() {
			return getResult().equalsIgnoreCase("success");
		}
		
		public JsonObject getData() {
			return TEMP_JSON.get("data").getAsJsonObject();
		}
		
		public String getData(String action) {
			return getData().get(action).getAsString();
		}
		
		public int getWidth() {
			return getData().get("size").getAsJsonObject().get("width").getAsInt();
		}
		
		public int getHeight() {
			return getData().get("size").getAsJsonObject().get("height").getAsInt();
		}
		
		@SuppressWarnings("unchecked")
		public List<String> getHandlers() {
			return new Gson().fromJson(getData().get("handles"), List.class);
		}
		
		public boolean isWidthPresent() {
			return getData().get("size").getAsJsonObject().has("width");
		}
		
		public boolean isHeightPresent() {
			return getData().get("size").getAsJsonObject().has("height");
		}
		
		public boolean isHandlersPresent() {
			return getData().has("handles");
		}
		
		public boolean isDataEquals(String action, String expected) {
			return getData(action).equalsIgnoreCase(expected);
		}
		
		@Override
		public String toString() {
			return TEMP_JSON.toString();
		}
	}
	

}

enum JSONKEYS {
	ROOT_DIR, 
	LOG_DIR, 
	SCREENSHOTS_DIR, 
	TESTRUN_ENVIRONMENT, 
	TESTRUN_TARGET_PLATFORM, 
	TESTRUN_TARGET_PLATFORM_VERSION,
	GUI_AUTOMATOR_NAME, 
	BROWSER_NAME, 
	BROWSER_VERSION, 
	BROWSER_MAXIMIZE, 
	BROWSER_DIM_HEIGHT, 
	BROWSER_DIM_WIDTH, 
	BROWSER_BIN_PATH, 
	BROWSER_PROXY_ON, 
	GUIAUTO_CONTEXT, 
	GUIAUTO_SCROLL_PIXELS, 
	GUIAUTO_SWIPE_TOP, 
	GUIAUTO_SWIPE_BOTTOM, 
	GUIAUTO_SWIPE_MAX_WAIT, 
	GUIAUTO_MAX_WAIT, 
	MOBILE_DEVICE_NAME, 
	MOBILE_DEVICE_UDID, 
	MOBILE_APP_FILE_PATH, 
	SELENIUM_DRIVER_PROP, 
	SELENIUM_DRIVERS_DIR, 
	SELENIUM_DRIVER_PATH, 
	APPIUM_HUB_URL, 
	APPIUM_AUTO_LAUNCH, 
	IMAGE_COMPARISON_MIN_SCORE,
}

