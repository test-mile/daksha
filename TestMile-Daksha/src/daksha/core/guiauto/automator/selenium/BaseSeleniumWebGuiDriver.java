/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.guiauto.automator.selenium;

import java.awt.Toolkit;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.batteries.config.Configuration;
import daksha.core.guiauto.automator.AbstractGuiAutomator;
import daksha.core.guiauto.enums.Direction;
import daksha.core.guiauto.enums.GuiDriverEngine;
import daksha.core.guiauto.enums.GuiElementLoaderType;
import daksha.core.guiauto.notifier.selenium.SeleniumListener;
import daksha.core.value.StringValue;
import daksha.tpi.TestContext;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public abstract class BaseSeleniumWebGuiDriver<D,E> extends AbstractGuiAutomator<D,E>{
	
	private D driver = null;
	private WebDriverWait waiter = null;
	private Browser browser = null;
	protected Capabilities capabilities = null;
	public String screenShotDir = null;
	private int scrollPixels;
	
	public BaseSeleniumWebGuiDriver(TestContext testContext, GuiDriverEngine engine, GuiElementLoaderType loaderType) throws Exception{
		super(testContext, GuiDriverEngine.WEBDRIVER, loaderType);
		screenShotDir = this.getTestContext().getScreenshotsDir();
		File sDir = new File(screenShotDir);
		if (sDir.isFile()) {
			throw new Exception(String.format("Screenshots directory: %s is not a directory. It is an existing file.", screenShotDir));
		} else if (!sDir.isDirectory()) {
			FileUtils.forceMkdir(new File(screenShotDir));
		}
		scrollPixels = this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_SCROLL_PIXELS).asInt();
	}
	
	@Override
	public void init() throws Exception{
		//this.setBrowser(Browser.valueOf(this.getTestContext().getConfig().value(DakshaOption.BROWSER_PC_DEFAULT).asString().toUpperCase()));
		this.setWaitTime(this.getTestContext().getConfig().value(DakshaOption.GUIAUTO_MAX_WAIT).asInt());
		this.setGuiTestEngineName(GuiDriverEngine.WEBDRIVER);		
	}
	
	@Override
	public void setCapabilities(Map<String,?> caps){
		this.capabilities = new MutableCapabilities(caps);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void load() throws Exception{
		EventFiringWebDriver driver = null;
		String browserBinPath = this.getTestContext().getBrowserBinaryPath();
		switch (this.getBrowser()){
		case FIREFOX:
			FirefoxOptions fOptions = new FirefoxOptions(capabilities);
			if (StringValue.isSet(browserBinPath)) {
				fOptions.setBinary(browserBinPath);
			}
			driver = new EventFiringWebDriver(new FirefoxDriver());
			break;
		case CHROME:
			ChromeOptions coptions = new ChromeOptions();
			if (StringValue.isSet(browserBinPath)) {
				coptions.setBinary(browserBinPath);
			}
			coptions.merge(capabilities);
			driver = new EventFiringWebDriver(new ChromeDriver(coptions));
			break;
		case SAFARI:
			SafariOptions sOptions = new SafariOptions();
			sOptions.merge(capabilities);
			driver = new EventFiringWebDriver(new SafariDriver(sOptions));
			break;
		case HTML_UNIT:
			driver = new EventFiringWebDriver(new HtmlUnitDriver(capabilities));
			break; 
		}
		driver.register(new SeleniumListener());
		
		this.setDriver((D) driver);
		initWait();
		resizeWindow();
	}
	
	private void resizeWindow() throws Exception {
		// Resize window
		Configuration config = this.getTestContext().getConfig();
		Value browserWidth = config.value(DakshaOption.BROWSER_DIM_WIDTH);
		Value browserHeight = config.value(DakshaOption.BROWSER_DIM_HEIGHT);
		boolean maxWindow = config.value(DakshaOption.BROWSER_MAXIMIZE).asBoolean();
		
		if (browserWidth.isNotSet() && browserHeight.isNotSet()) {
			if (maxWindow) {
				maximizeWindow();
			}
		} else {
			int width;
			int height;
			
			Dimension currentSize = this.getUnderlyingEngine().manage().window().getSize();
			
			if (!browserWidth.isNotSet()) {
				width = browserWidth.asInt();
			} else {
				width = currentSize.getWidth();
			}
			
			if (!browserHeight.isNotSet()) {
				height = browserHeight.asInt();
			} else {
				height = currentSize.getHeight();
			}
			
			this.getUnderlyingEngine().manage().window().setSize(new Dimension(width, height));
		}
	}
	
	protected void setDriver(D driver) {
		this.driver = driver;
	}

	public void maximizeWindow(){
		// Check for some property here. To override this default.
		try{
			getUnderlyingEngine().manage().window().maximize();
		} catch (WebDriverException e){
			java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			// This dimension is webdriver Dimension
			getUnderlyingEngine().manage().window().setSize(new Dimension((int)d.getWidth(), (int) d.getHeight()));
		}
	}

	public void initWait() {
		this.setWaiter(new WebDriverWait(this.getUnderlyingEngine(), getWaitTime()));
		if(this.getBrowser() != Browser.SAFARI){
			getUnderlyingEngine().manage().timeouts().pageLoadTimeout(getWaitTime(), TimeUnit.SECONDS);
		}	
	}

	public WebDriverWait getWaiter() {
		return this.waiter;
	}

	public void setWaiter(WebDriverWait waiter) {
		this.waiter = waiter;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public Browser getBrowser() {
		return this.browser;
	}

	@Override
	public D getGuiDriverEngine() {
		return this.driver;
	}

	private WebDriver getUnderlyingEngine() {
		return (WebDriver) getGuiDriverEngine();
	}

	public void setCapabilities(MutableCapabilities capabilities) throws Exception {
		if (this.getTestContext().getConfig().value(DakshaOption.BROWSER_PC_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = this.getTestContext().getConfig().value(DakshaOption.BROWSER_PC_PROXY_HOST).asString() + ":" + this.getTestContext().getConfig().value(DakshaOption.BROWSER_PC_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			capabilities.setCapability("proxy", proxy);
		}
	}

	public void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}

	public void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}

	/**********************************************************************************/

	public TakesScreenshot getScreenshotAugmentedDriver() {
		return (TakesScreenshot) (new Augmenter().augment(getUnderlyingEngine()));
	}
	
	/**********************************************************************************/
	/*					AUTOMATOR API												*/
	/**********************************************************************************/
	
	public void goTo(String url) throws Exception {
		getUnderlyingEngine().get(url);
		waitForBody();
	}
	
	public void waitForBody() throws Exception {
		this.getIdentifier().elementWithTagName("body").waitUntilPresent();
	}
	
	public void refresh() throws Exception {
		getUnderlyingEngine().navigate().refresh();
	}
	
	public void back() throws Exception {
		getUnderlyingEngine().navigate().back();
	}
	
	public void forward() throws Exception {
		getUnderlyingEngine().navigate().forward();
	}

	public void close(){
		getUnderlyingEngine().quit();
	}

	public void confirmAlertIfPresent() {
		WebDriver d = getUnderlyingEngine();
		try{
			Alert alert = d.switchTo().alert();
			alert.accept();
			d.switchTo().defaultContent();
		} catch (Exception e){ // ignore
		}
	}
	
	// Windows related
	@Override
	public String getPageTitle() {
		return getUnderlyingEngine().getTitle();
	}
	
	public String getCurrentWindow() {
		return getUnderlyingEngine().getWindowHandle();
	}
	
	public void switchToWindow(String windowHandle){
		getUnderlyingEngine().switchTo().window(windowHandle); 		
	}
	
	public void switchToNewWindow() {
		WebDriver driver = getUnderlyingEngine();
		String parentHandle = getCurrentWindow();
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentHandle)) {
				switchToWindow(winHandle); // switch focus of WebDriver to the next found window handle
			}
		}
	}
	
	public void closeCurrentWindow(){
		getUnderlyingEngine().close();
	}
	
	public void switchToFrame(int index) throws Exception {
		this.getUnderlyingEngine().switchTo().frame(index);
	}

	public void switchToFrame(String name) throws Exception {
		this.getUnderlyingEngine().switchTo().frame(name);
	}
	
	public void switchToDefaultFrame() throws Exception {
		this.getUnderlyingEngine().switchTo().defaultContent();
	}
	
	@Override
	public File takeScreenshot() throws Exception {
		TakesScreenshot augDriver = getScreenshotAugmentedDriver();
        File srcFile = augDriver.getScreenshotAs(OutputType.FILE);
        return FileSystemUtils.moveFiletoDir(srcFile, screenShotDir);
	}
	
	public void focusOnApp() throws Exception{
		
	}
	
	public Actions getActionChain(){
		return new Actions(getUnderlyingEngine());
	}		
	
	protected void validateScrollSupport() throws Exception {
		// Web apps support scrolling
		return;
	}
	
	private void scroll(Direction direction, int count) throws Exception {
		validateScrollSupport();
		JavascriptExecutor jse = (JavascriptExecutor) this.getGuiDriverEngine();
		int spc = 0;
		if (direction == Direction.DOWN) {
			spc = scrollPixels;
		} else if (direction == Direction.UP) {
			spc = -scrollPixels;
		} else {
			throw new Exception("Scroll left/right is not yet supported.");
		}
		for (int i = 0; i < count; i++) {
			jse.executeScript(String.format("window.scrollBy(0, %d)", spc), "");
		}
	}
	
	@Override
	public void scrollDown() throws Exception {
		scroll(Direction.DOWN, 1);
	}

	@Override
	public void scrollDown(int count) throws Exception {
		scroll(Direction.DOWN, count);
	}

	@Override
	public void scrollUp() throws Exception {
		scroll(Direction.UP, 1);
	}

	@Override
	public void scrollUp(int count) throws Exception {
		scroll(Direction.UP, count);
	}
	
	protected boolean isWebView() {
		return true;
	}
	
	public void validatePageLoad() {
		if ((GuiAutomationContext.isAnyWebContext(this.getAutomatorContext()) || isWebView())) {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((String) ((JavascriptExecutor) driver).executeScript("return document.readyState"))
							.equals("complete");
				}
			};
			this.getWaiter().until(pageLoadCondition);
		}
	}
	
}
