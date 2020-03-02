package restApiTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddDeletePlaceWithStaticJSON {
	
	String placeid,body,responseString;
	
	@BeforeTest
	public void setUp() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	}


	@Test(priority = 1)
	public void addPlace() {

		
		body = "{\r\n" + "\r\n" + "    \"location\":{\r\n" + "\r\n" +
				  "        \"lat\" : -38.383494,\r\n" + "\r\n" +
				  "        \"lng\" : 33.427362\r\n" + "\r\n" + "    },\r\n" + "\r\n" +
				  "    \"accuracy\":50,\r\n" + "\r\n" + "    \"name\":\"Frontline house\",\r\n"
				  + "\r\n" + "    \"phone_number\":\"(+91) 983 893 3937\",\r\n" + "\r\n" +
				  "    \"address\" : \"29, side layout, cohen 09\",\r\n" + "\r\n" +
				  "    \"types\": [\"shoe park\",\"shop\"],\r\n" + "\r\n" +
				  "    \"website\" : \"http://google.com\",\r\n" + "\r\n" +
				  "    \"language\" : \"French-IN\"\r\n" + "\r\n" + "}\r\n" + "\r\n" + "";
		
		RequestSpecification reqSpec = given().
				header("Content-Type","application/json").
				queryParam("key", "qaclick123").body(body);
		
		Response resp = reqSpec.when().post("/maps/api/place/add/json").then().extract().response();
		
		responseString = resp.asString();
		
		System.out.println("Response is "+resp.getStatusCode());
		
		System.out.println("Response is :-"+responseString);
		
		JsonPath js= new JsonPath(responseString);
		
		placeid =js.get("place_id");
		
		System.out.println("Place id:- "+placeid);

		
	}

	@Test(priority = 2)
	public void deletePlace() {
		
		body = "{\r\n    \"place_id\":\""+placeid+"\"\r\n}\r\n";
		
		Response resp = given().
		header("Content-Type","application/json").
		queryParam("key", "qaclick123").body(body).
		when().
		post("/maps/api/place/delete/json").
		then().
		extract().response();
		
		responseString = resp.asString();
		
		System.out.println("Response is :-"+responseString);
		
	}
	
	
	@AfterTest
	public void tearDown() {
	}
	

	
}
