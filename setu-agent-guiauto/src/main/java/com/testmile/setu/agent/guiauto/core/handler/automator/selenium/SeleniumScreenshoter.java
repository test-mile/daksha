package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.sysauto.utils.FileSystemUtils;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Screenshoter;

public class SeleniumScreenshoter extends AbstractWDHandler implements Screenshoter{
	private TakesScreenshot screenshotDriver;
	
	public SeleniumScreenshoter(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.screenshotDriver = (TakesScreenshot) driver;
		File sDir = new File(getScreenshotsDir());
		if (sDir.isFile()) {
			throw new Exception(String.format("Screenshots directory: %s is not a directory. It is an existing file.", this.getScreenshotDriver()));
		} else if (!sDir.isDirectory()) {
			FileUtils.forceMkdir(new File(this.getScreenshotsDir()));
		}
	}
	
	private TakesScreenshot getScreenshotDriver() {
		return this.screenshotDriver;
	}
	
	private String getScreenshotsDir() throws Exception {
		return this.getConfig().value(DakshaOption.SCREENSHOTS_DIR).asString();
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.Screenshoter#takeScreenshot()
	 */
	@Override
	public File takeScreenshot() throws Exception {
        File srcFile = getScreenshotDriver().getScreenshotAs(OutputType.FILE);
        return FileSystemUtils.moveFiletoDir(srcFile, this.getScreenshotsDir());
	}
}