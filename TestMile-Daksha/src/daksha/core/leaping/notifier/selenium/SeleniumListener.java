package daksha.core.leaping.notifier.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class SeleniumListener implements WebDriverEventListener{

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		System.out.println("Would now accert the alert");
	}
	
	@Override
	public void afterAlertAccept(WebDriver arg0) {
		System.out.println("Alert accepted");
	}


	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		System.out.println("Would now dismiss alert");
	}
	
	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		System.out.println("Alert dismissed");
	}
	
	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		System.out.println(String.format("Value of %s would be changed to %s", arg0, arg2));
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		System.out.println(String.format("Value of %s changed to %s", arg0, arg2));
	}
	
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		System.out.println(String.format("Would now click %s", arg0));
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		System.out.println(String.format("Clicked %s", arg0));
	}
	
	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		System.out.println(String.format("Would find element by: %s", arg0));
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		System.out.println(String.format("Found element by: %s", arg0));
	}
	
	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		System.out.println("Would now take screenshot");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		System.out.println("Took screenshot");
	}
	
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		System.out.println("Would navigate back");
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		System.out.println("Navigated back");
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		System.out.println("Would navigate forward");
	}
	
	@Override
	public void afterNavigateForward(WebDriver arg0) {
		System.out.println("Navigated forward");
	}
	
	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		System.out.println("Would  refresh");
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		System.out.println("Refreshed");
	}
	
	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		System.out.println("Would navigate to: " + arg0);
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		System.out.println("Navigated to: " + arg0);
	}
	
	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		System.out.println("Would run script: " + arg0);
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		System.out.println("Ran script: " + arg0);
	}

	
	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		System.out.println("Would now swtich to window: " + arg0);
	}
	
	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		System.out.println("Swtiched to window: " + arg0);
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		System.out.println("Exception occured");
		System.out.println(arg0.toString());
		arg0.printStackTrace();
	}

}
