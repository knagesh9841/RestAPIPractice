package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  
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


}
