package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateChangeRequest extends SetUp{

	@Test
	public void createChangeRequestUsingFile() {
		
		
		Response response = RestAssured
				.given().log().all()
				.body("{\r\n" + 
						"	\"short_description\":\"sanjay\"\r\n" + 
						"}")
				.contentType(ContentType.JSON)
				.post();
		response.prettyPrint();
		
		JsonPath jsonResponse = response.jsonPath();
		sys_id = jsonResponse.getString("result.sys_id");
	
	}
	
}
