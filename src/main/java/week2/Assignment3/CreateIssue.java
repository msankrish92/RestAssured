package week2.Assignment3;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssue extends SetUp {
	
	@Test
	public void createIssue() {
		Response respone = RestAssured
				.given().log().all()
					.contentType(ContentType.JSON)
				.and()
					.accept(ContentType.JSON)
				.and()
					.body("{\r\n" + 
							"    \"fields\": {\r\n" + 
							"       \"project\":\r\n" + 
							"       {\r\n" + 
							"          \"key\": \"MAY\"\r\n" + 
							"       },\r\n" + 
							"       \"summary\": \"Bug \",\r\n" + 
							"       \"description\": \"creating issues using restAssured\",\r\n" + 
							"       \"issuetype\": {\r\n" + 
							"          \"name\": \"Bug\"\r\n" + 
							"       }\r\n" + 
							"   }\r\n" + 
							"}")
					.when()
						.post();
		
		JsonPath jsonResponse = respone.jsonPath();
		jsonResponse.prettyPrint();
		createdIssueId = jsonResponse.getString("id");
		System.out.println(createdIssueId);
		
	}
	
}
