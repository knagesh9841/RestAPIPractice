package restApiTests;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.DeletePlaceRequest;
import pojoClasses.DeletePlaceResponse;
import pojoClasses.Details;
import pojoClasses.Location;
import pojoClasses.ResponseDetails;

public class AddDeletePlaceWithPOJO {
	
	
  @Test
  public void addDeletePlaceWithPOJO() throws JsonProcessingException {
	  
	  RestAssured.baseURI = "http://216.10.245.166";
	  
	  Details det = new Details();
	  det.setAccuracy(50);
	  det.setName("Frontline house");
	  det.setPhone_number("(+91) 983 893 3937");
	  det.setAddress("29, side layout, cohen 09");
	  List<String> mylist = new ArrayList<String>();
	  mylist.add("shoe park");
	  mylist.add("shop");
	  det.setTypes(mylist);
	  det.setWebsite("http://google.com");
	  det.setLanguage("French-IN");
	  Location location = new Location();
	  location.setLat(-38.383494);
	  location.setLng(33.427362);
	  det.setLocation(location);
	  
	  ObjectMapper map = new ObjectMapper();
	  
	  String myData = map.writerWithDefaultPrettyPrinter().writeValueAsString(det);
	  
	  System.out.println("Response Data is :-"+myData);
	  
	  
	  RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	  
	  ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	  
	  RequestSpecification resp=given().spec(req)
			  .body(det);
	  
	  ResponseDetails res = resp.when().
	  post("/maps/api/place/add/json").
	  then().spec(resspec).extract().response().as(ResponseDetails.class);
	  

	  String placeid=res.getPlace_id();
	  System.out.println("Place ID is:- "+placeid);
	  
	  DeletePlaceRequest bk = new DeletePlaceRequest();
	  bk.setPlace_id(placeid);
	  
	  DeletePlaceResponse delete = given(). queryParam("key","qaclick123").
	  body(bk). when().
	  post("/maps/api/place/delete/json").
	  then().extract().response().as(DeletePlaceResponse.class);
	  
	  System.out.println("Delete Request Response is :- "+delete.getStatus());
	  
	  
  }
  
}
