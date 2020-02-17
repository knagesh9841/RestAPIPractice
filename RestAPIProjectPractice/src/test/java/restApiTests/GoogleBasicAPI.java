package restApiTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterClass;

public class GoogleBasicAPI {

	@BeforeClass
	public void setUp() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	}


	@Test
	public void googleBasicAPI() {

		String body = "{\\r\\n\\r\\n\\\"name\\\":\\\"Learn Appium Automation with Java\\\",\\r\\n\\\"isbn\\\":\\\"bcd\\\",\\r\\n\\\"aisle\\\":\\\"227\\\",\\r\\n\\\"author\\\":\\\"John foe\\\"\\r\\n}\\r\\n";

		Response response = given().
				header("Content-Type","application/json").
				body(body).
				when().
				post("/Library/Addbook.php").
				then().
				extract().response();

		System.out.println(response.getStatusCode());
	}

	@AfterClass
	public void tearDown() {
	}

}
