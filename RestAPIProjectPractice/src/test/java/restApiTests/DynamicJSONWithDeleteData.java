package restApiTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import restAPIUtility.Utilities;

public class DynamicJSONWithDeleteData {
  
	@BeforeTest
	public void setUp() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	}


	@Test(priority = 1,dataProvider = "BooksData")
	public void addBookName(String isbn,String aisle) {


		Response response = given().log().all().
				header("Content-Type","application/json").
				body(Utilities.returnJsonData(isbn, aisle)).
				when().
				post("/Library/Addbook.php").
				then().log().all().
				extract().response();
		
		System.out.println("Book Added for"+isbn+"\t"+aisle+" Response status code is "+response.getStatusCode());
		
			
		response = given().log().all().
				header("Content-Type","application/json").
				body(Utilities.deleteData(isbn, aisle)).
				when().
				post("/Library/DeleteBook.php").
				then().log().all().
				extract().response();
		
		System.out.println("Book Deleted for"+isbn+"\t"+aisle+" Response status code is "+response.getStatusCode());
		
	}

	@AfterTest
	public void tearDown() {
	}
	
	@DataProvider
	  public Object[][] BooksData() {
		  return new Object[][] {{"ojfwtyy","936"},{"cwetee","4253"}, {"okmfet","533"} };
	  }
	
	
}
