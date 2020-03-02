package restApiTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restAPIUtility.Utilities;

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

		
		Response response = given().
				header("Content-Type","application/json").
				body(Utilities.returnJsonData("ojfwtyy","936")).
				when().
				post("/Library/Addbook.php").
				then().
				extract().response();
		
		System.out.println("Content Type:"+response.getContentType());
		
		System.out.println("Status Code:"+response.getStatusCode());
		System.out.println("Status Line:"+response.getStatusLine());
		System.out.println("Response Header:"+response.getHeaders());
		System.out.println("Response header Server:-"+response.getHeader("Server"));
		
		Headers allHeaders = response.headers();

		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}

		String respString = response.asString();
		
		System.out.println("Response is :- "+respString);
		JsonPath js = new JsonPath(respString);
		
		System.out.println("Result is:- "+js.get("Msg"));
		
		response = given().log().all().
				header("Content-Type","application/json").
				body(Utilities.deleteData("ojfwtyy","936")).
				when().
				post("/Library/DeleteBook.php").
				then().log().all().
				extract().response();
		
		System.out.println("Response Deleted is :- "+response.asString());
		
	}

	@AfterTest
	public void tearDown() {
	}

}
