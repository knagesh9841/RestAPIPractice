package restApiTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2ClientCredentials {
	
  @Test
  public void oAuth2ClientCredentials() {

	  RestAssured.baseURI = "http://coop.apps.symfonycasts.com";

	  Response resp = RestAssured.
			  given()
			  .formParam("client_id", "TestRestApi")
			  .formParam("client_secret", "0132899a7d02713e669bc9875ea9da70")
			  .formParam("grant_type", "client_credentials")
			  .when()
			  .post("/token")
			  .then()
			  .extract().response();

	  String accessToken = resp.jsonPath().get("access_token");
	  System.out.println("Access Token is :- "+accessToken);
	  System.out.println("Response code is "+resp.getStatusCode());


	 Response resp1 = RestAssured.given().
			  auth().
			  oauth2(accessToken).when().
			  post("/api/685/chickens-feed").then().
			  extract().response();

	  System.out.println("Response is after passing Access token is :- \n"+resp1.asString());
	  

  }
}
