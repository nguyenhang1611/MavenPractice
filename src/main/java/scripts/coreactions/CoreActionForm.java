package scripts.coreactions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @class CoreActionForm
 * 
 * @author HangNT
 * @since 2016/06/09
 *
 */
public class CoreActionForm {

	public static WebDriver driver = null;

	public CoreActionForm(WebDriver driver) {
		CoreActionForm.driver = driver;
	}

	/**
	 * click on Element
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param element
	 */
	public void clickOnElement(String element) {
		WebElement onElement = driver.findElement(getObject(element));
		try {
			onElement.click();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Click by javascript
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 */
	public void clickByJavascript(String element) {
		WebElement onElement = driver.findElement(getObject(element));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", onElement);
	}

	/**
	 * Verify Alert box present or not
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 */
	public boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver,
				5 /* timeout in seconds */);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	/********* Textbox ********/
	/**
	 * sendKeys to textbox
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param element
	 * @param value
	 */
	public void sendKeys(String element, String value) {
		WebElement sendKeysElement = driver.findElement(getObject(element));
		try {
			sendKeysElement.sendKeys(value);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/********* Checkbox/radio button ********************/
	/**
	 * check checkbox
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param element
	 */
	public void checkCheckbox(String element) {
		WebElement checkElement = driver.findElement(getObject(element));
		try {
			checkElement.click();
			if (!checkElement.isSelected()) {
				checkElement.click();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * uncheck checkbox
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param element
	 */
	public void uncheckCheckbox(String element) {
		WebElement checkElement = driver.findElement(getObject(element));
		try {
			checkElement.click();
			if (checkElement.isSelected()) {
				checkElement.click();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/********* Select box and radio button ********************/
	/**
	 * select value from drop down list by value
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param valueOption
	 * @param element
	 */
	public void selectValueFromDropDownList(String valueOption, String element) {
		Select selectElement = new Select(driver.findElement(getObject(element)));
		try {
			selectElement.selectByValue(valueOption);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * select value from drop down list by index
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param indexOption
	 * @param element
	 */
	public void selectIndexFromDropDownList(int indexOption, String element) {
		Select selectElement = new Select(driver.findElement(getObject(element)));
		try {
			selectElement.selectByIndex(indexOption);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * select value from drop down list by visible text
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param textOption
	 * @param element
	 */
	public void selectTextFromDropDownList(String textOption, String element) {
		Select selectElement = new Select(driver.findElement(getObject(element)));
		try {
			selectElement.selectByVisibleText(textOption);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get text of element
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param element
	 * @return
	 */
	public String getCurrentTextOfElement(String element) {
		return getWebElement(element).getText().trim();
	}

	public String getTextByJavascript(String element) {
		WebElement onElement = driver.findElement(getObject(element));
		String theTextIWant = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
				onElement);
		return theTextIWant;
	}

	/**
	 * maximize page
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * open the page
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @throws IOException
	 */
	public void openThePage(String url) {
		maximizeBrowser();
		driver.get(url);
	}

	/**
	 * get element
	 * 
	 * @param target
	 * @return
	 */
	public WebElement getWebElement(String target) {
		try {

			return driver.findElement(getObject(target));

		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * get list elements
	 * 
	 * @param target
	 * @return
	 */
	public List<WebElement> getWebElements(String target) {
		try {
			Thread.sleep(1000);
			return driver.findElements(getObject(target));

		} catch (NoSuchElementException | InterruptedException e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * get locator
	 * 
	 * @author HangNT
	 * @since 2016/06/09
	 * @param locator
	 * 
	 */
	public By getObject(String locator) {
		By by = null;
		try {
			if (locator.startsWith("id=")) {

				locator = locator.substring(3);
				by = By.id(locator);

			} else if (locator.startsWith("name=")) {

				locator = locator.substring(5);
				by = By.name(locator);

			} else if (locator.startsWith("css=")) {

				locator = locator.substring(4);
				by = By.cssSelector(locator);

			} else if (locator.startsWith("linkText=")) {

				locator = locator.substring(5);
				by = By.linkText(locator);

			} else if (locator.startsWith("xpath=")) {
				locator = locator.substring(6);
				by = By.xpath(locator);
			} else {
				by = By.xpath(locator);
			}

			return by;
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}
}