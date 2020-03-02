package restApiTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import restAPIUtility.Utilities;

public class OAuth2AuthorizationCode {
	
	  @Test
	  public void oAuth2AuthorizationCode() {

		  
		  WebDriverManager.edgedriver().architecture(io.github.bonigarcia.wdm.Architecture.X32).setup();
		  EdgeDriver driver = new EdgeDriver();
		  driver.get("http://coop.apps.symfonycasts.com/authorize?client_id=TestRestApi&client_secret=0132899a7d02713e669bc9875ea9da70&scope =chickens-feed&redirect_uri=http://coop.apps.symfonycasts.com/api&response_type=code");
		  
		  Utilities.waitForElementVisible(driver, By.name("_username"), 30);
		  
		  WebElement userName = driver.findElement(By.name("_username"));
		  WebElement password = driver.findElement(By.name("_password"));
		  WebElement loginBtn = driver.findElement(By.className("btn btn-primary"));
		  
		  userName.sendKeys("knagesh143s@gmail.com");
		  password.sendKeys("OAuth2");
		  
		  loginBtn.click();
		  
		  Utilities.waitForElementVisible(driver,By.xpath("//a[contains(text(),'Yes, I Authorize This Request')]"), 30);
		  
		  WebElement btn = driver.findElement(By.xpath("//a[contains(text(),'Yes, I Authorize This Request')]"));
		  
		  btn.click();
		  
		  Utilities.waitForElementVisible(driver,By.xpath("//span[text()='The Coop']"), 30);
		  
		  String codeURL = driver.getCurrentUrl();
		   
		  driver.close();
		  
		  String str[] = codeURL.split("=");
		  
		  String code = str[1].substring(0, str[1].length()-1);
		  
		  System.out.println("Code is:"+code);
		  
		  code = code.trim();

		  RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
		  
		  
		  Response resp = RestAssured.
				  given()
				  .formParam("client_id", "TestRestApi")
				  .formParam("client_secret", "0132899a7d02713e669bc9875ea9da70")
				  .formParam("grant_type", "authorization_code")
				  .formParam("code", code)
				  .formParam("redirect_uri", "http://coop.apps.symfonycasts.com/api")
				  .when()
				  .post("/token")
				  .then()
				  .extract().response();

		  String accessToken = resp.jsonPath().get("access_token");
		  System.out.println("Access Token is :- "+accessToken);
		  System.out.println("Response code is "+resp.getStatusCode());

		  RestAssured.baseURI = "http://coop.apps.symfonycasts.com";

		 Response resp1 = RestAssured.given().
				  auth().
				  oauth2(accessToken).when().
				  post("/api/685/chickens-feed").then().
				  extract().response();

		  System.out.println("Response is after passing Access token is :- \n"+resp1.asString());
		  System.out.println("Response code is "+resp1.getStatusCode());
		  
		  // It will fail due to UnAuthorized

	  }


}
