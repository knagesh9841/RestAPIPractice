package restApiTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.AfterTest;

public class StatusLineHeaderExample {

	@BeforeTest
	public void setUp() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	}


	@Test
	public void googleBasicAPI() {

		String body = "{\\r\\n\\r\\n\\\"name\\\":\\\"Learn Appium Automation with Java\\\",\\r\\n\\\"isbn\\\":\\\"bcdqwe\\\",\\r\\n\\\"aisle\\\":\\\"227123\\\",\\r\\n\\\"author\\\":\\\"John foe\\\"\\r\\n}\\r\\n";

		Response response = given().
				header("Content-Type","application/json").
				body(body).
				when().
				post("/Library/Addbook.php").
				then().
				extract().response();
		
		System.out.println("Content Type:"+response.getContentType());
		
		System.out.println("Status Code:"+response.getStatusCode());
		System.out.println("Status Line:"+response.getStatusLine());
		System.out.println("Response Header:"+response.getHeaders());
		
		
		
		
	}

	@AfterTest
	public void tearDown() {
	}

}
