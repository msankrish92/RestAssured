package changeRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class GetChangeRequestHam {

	public void getChangeRequesst() {
		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request";
		RestAssured.authentication = RestAssured
				.oauth2("Mj6ReiPkIVCjCzA9ud1AZX2xyYhxVVTMHW24aDG-2ywSG7rwaTXStAAbfWy_PYSSm3iEIJKMMMYtLILTfXcGKA");
		
		ValidatableResponse response = RestAssured
				.given()
					.accept(ContentType.XML)
				.when()
					.get()
				.then()
					.body(containsString("sys_id"));
		
				
				
	}
	
}
