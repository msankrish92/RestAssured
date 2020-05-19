package chaining;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateChangeRequest extends SetUp{

	@Test(dependsOnMethods = {"chaining.CreateChangeRequest.createChangeRequestUsingFile"})
	public void updateChangeRequest(){
		
		
		Response response = RestAssured.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"	\"short_description\":\"sanjay\"\r\n" + 
						"}")
				.patch(sys_id);
				
		
		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		System.out.println(jsonResponse.getString("result.short_description"));
				
	}
	
	
}
