package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Swiper;
import com.testmile.trishanku.tpi.guiauto.enums.Direction;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AppiumSwiper extends SeleniumJSExecutor implements Swiper{
	private float swipeTop;
	private float swipeBottom;
	private int swipeMaxWait;
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumSwiper(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
		swipeTop = getConfig().value(DakshaOption.GUIAUTO_SWIPE_TOP).asFloat();
		swipeBottom = getConfig().value(DakshaOption.GUIAUTO_SWIPE_BOTTOM).asFloat();
		swipeMaxWait = getConfig().value(DakshaOption.GUIAUTO_SWIPE_MAX_WAIT).asInt();
	}
	
	private AppiumDriver<MobileElement> getAppiumDriver(){
		return this.appiumDriver;
	}
	
	private void validateSwipeSupport() throws Exception {
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("Swipe actions are not supported for Web View.");
		}
	}

	private String swipeUsingADB(int startx, int starty, int endx, int endy, int duration) {
        return executeAsString("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy+" "+duration);
    }

    private String executeAsString(String command) {
        try {
            Process pr = execute(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                if (!line.isEmpty()) {
                    sb.append(line);
                }
            }
            input.close();
            pr.destroy();
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Execution error while executing command" + command, e);
        }
    }  
    
    private Process execute(String command) throws Exception {
        List<String> commandP = new ArrayList<String>();
        String[] com = command.split(" ");
        for (int i = 0; i < com.length; i++) {
            commandP.add(com[i]);
        }
        ProcessBuilder prb = new ProcessBuilder(commandP);
        Process pr = prb.start();
        pr.waitFor(10, TimeUnit.SECONDS);
        return pr;
    }

	
	private void swipe(Direction direction, int count, float startFraction, float endFraction) throws Exception {
		validateSwipeSupport();
		Dimension size = this.getAppiumDriver().manage().window().getSize();
		int x1 = (int) (0.5 * size.width);
		int y1 = (int) (size.height * startFraction);
		int x2 = (int) (0.5 * size.width);
		int y2 = (int) (size.height * endFraction);

		if (this.getConfig().getOSType() == OSType.ANDROID) {
			new AndroidTouchAction((AndroidDriver<MobileElement>) this.getAppiumDriver())
			.press(PointOption.point(x1,  y1))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeMaxWait)))
			.moveTo(PointOption.point(x2, y2)).release().perform();
		} else {
			Map<String, Double> swipeElement = new HashMap<String, Double>();
			swipeElement.put("startX", (double) x1);
			swipeElement.put("startY", (double) y1);
			swipeElement.put("endX", (double) x2);
			swipeElement.put("endY", (double) y2);
			swipeElement.put("duration", (double) swipeMaxWait);
			this.executeScript("mobile: swipe", swipeElement);
		}
		
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.Swiper#swipeUp(int)
	 */
	@Override
	public void swipeUp(int count) throws Exception {
		swipe(Direction.UP, count, swipeBottom, swipeTop);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.Swiper#swipeUp()
	 */
	@Override
	public void swipeUp() throws Exception {
		swipeUp(1);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.Swiper#swipeDown(int)
	 */
	@Override
	public void swipeDown(int count) throws Exception {
		swipe(Direction.DOWN, count, swipeTop, swipeBottom);
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.appium.Swiper#swipeDown()
	 */
	@Override
	public void swipeDown() throws Exception {
		swipeDown(1);
	}
	
}