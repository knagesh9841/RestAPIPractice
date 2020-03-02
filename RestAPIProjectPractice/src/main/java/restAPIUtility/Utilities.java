package restAPIUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	/**
	 * This Method will Update JSON with Dynamic data
	 * @param isbn
	 * @param aisle
	 * @return
	 */
	
	public static String returnJsonData(String isbn,String aisle) 
	{
		String data = "{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"John foe\"\r\n}\r\n";
		return data;
	}
	
	/**
	 * This Method will Update JSON with Dynamic data
	 * @param isbn
	 * @param aisle
	 * @return
	 */
	
	public static String deleteData(String isbn,String aisle)
	{
		String deleteData = isbn+aisle;
		String data = "{\r\n \r\n\"ID\" : \""+deleteData+"\"\r\n \r\n}\r\n";
		return data;
	}
	
	
	/**
	 * Wait for an element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisVisibleOrNot
	 */
	
	public static boolean waitForElementVisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		boolean sFlag =false;
		try {
	
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
	
			if(element!=null)
			{
				sFlag = true;
			}
			
			
			return sFlag;
	
		} catch (Exception e) {
	
			
			return sFlag;
		}
	
	}

}
