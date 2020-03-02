package restApiTests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.DeletePlaceRequest;
import pojoClasses.DeletePlaceResponse;
import pojoClasses.ResponseDetails;

public class AddDeletePlaceWithHashMap {

	@Test
	public void addDeletePlaceWithHashMap() {

		HashMap<String, Object> mainMap = new HashMap<String, Object>();

		mainMap.put("accuracy", 50);
		mainMap.put("name", "Frontline house");
		mainMap.put("phone_number", "(+91) 983 893 3937");
		mainMap.put("address", "29, side layout, cohen 09");

		List<String> mylist1 = new ArrayList<String>();
		mylist1.add("shoe park");
		mylist1.add("shop");
		mainMap.put("types", mylist1);

		mainMap.put("website", "http://google.com");
		mainMap.put("language", "French-IN");

		HashMap<String, Object> innerMap = new HashMap<String, Object>();
		innerMap.put("lat", -38.383494);
		innerMap.put("lng", 33.427362);
		mainMap.put("location", innerMap);

		System.out.println("JSON Data:/\n"+mainMap);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RequestSpecification resp=given().spec(req)
				.body(mainMap);

		ResponseDetails res = resp.when().
				post("/maps/api/place/add/json").
				then().spec(resspec).extract().response().as(ResponseDetails.class);


		String placeid=res.getPlace_id();
		System.out.println("Place ID is:- "+placeid);

		DeletePlaceRequest bk = new DeletePlaceRequest();
		bk.setPlace_id(placeid);

		RestAssured.baseURI = "http://216.10.245.166";
		
	
		DeletePlaceResponse delete = given(). queryParam("key","qaclick123").
				body(bk). when().
				post("/maps/api/place/delete/json").
				then().extract().response().as(DeletePlaceResponse.class);

		System.out.println("Delete Request Response is :- "+delete.getStatus());


	}

}
