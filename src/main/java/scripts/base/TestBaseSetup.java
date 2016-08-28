package scripts.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.selenium.util.DriverUtil;

/**
 * @class TestBaseSetup
 * 
 * @author HangNT
 * @since 2016/06/09
 *
 */
public class TestBaseSetup {
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String baseURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(baseURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(baseURL);
			break;
		case "ie":
			driver = initIEDriver(baseURL);
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(baseURL);
		}
	}

	private static WebDriver initChromeDriver(String baseURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", DriverUtil.getChromeDriver());
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String baseURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	private static WebDriver initIEDriver(String baseURL) {
		System.out.println("Launching Internet Explorer browser..");
		System.setProperty("webdriver.ie.driver",DriverUtil.getIeDriver());
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		return driver;
	}

	@Parameters({ "browserType", "baseURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String baseURL) {
		try {
			;
			// browserType =
			// context.getCurrentXmlTest().getParameter("selenium.browser");
			// baseURL=context.getCurrentXmlTest().getParameter("selenium.url");
			setDriver(browserType, baseURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
