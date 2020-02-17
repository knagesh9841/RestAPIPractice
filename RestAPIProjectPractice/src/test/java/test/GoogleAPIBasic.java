package test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class GoogleAPIBasic {
	
	@BeforeClass
	  public void setUp() {
		  
		  
	  }
	
  @Test
  public void googleAPIBasic() {
	  
	  String body = "{\\r\\n\\r\\n\\\"name\\\":\\\"Learn Appium Automation with Java\\\",\\r\\n\\\"isbn\\\":\\\"bcd\\\",\\r\\n\\\"aisle\\\":\\\"227\\\",\\r\\n\\\"author\\\":\\\"John foe\\\"\\r\\n}\\r\\n";
	  
	  RestAssured.baseURI = "http://216.10.245.166";
	  
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
