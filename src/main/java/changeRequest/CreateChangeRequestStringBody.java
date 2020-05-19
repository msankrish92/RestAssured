package changeRequest;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groovy.json.JsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateChangeRequestStringBody {

	@DataProvider(name="file", parallel = true)
	public String[] dataprovider() {
		String[] data = new String[2];
		data[0] = "data.json";
		data[1] = "data1.json";
		return data;
	}
	
//	@Test
//	public void createChangeRequest() {
//		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request";
//		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
//		Response response = RestAssured
//				.given()
//				.body("{\r\n" + 
//						"	\"short_description\":\"sanjay\"\r\n" + 
//						"}")
//				.contentType(ContentType.JSON)
//				.post();
//		response.prettyPrint();
//		
//	}
	
//	@Test
//	public void createChangeRequestUsingFile() {
//		File jsonFile = new File("data.json");
//		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request";
//		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
//		Response response = RestAssured
//				.given()
//				.body(jsonFile)
//				.contentType(ContentType.JSON)
//				.post();
//		response.prettyPrint();
//	
//	}
//	
//	@Test(dataProvider = "file")
//	public void createChangeRequestUsingFile(String fileName) {
//		File jsonFile = new File(fileName);
//		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request";
//		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
//		Response response = RestAssured
//				.given().log().all()
//				.body(jsonFile)
//				.contentType(ContentType.JSON)
//				.post();
//		response.prettyPrint();
//	
//	}
	
//	@Test(dataProvider = "file")
//	public void deleteChangeRequest(String fileName) {
//		
//		File jsonFile = new File(fileName);
//		
//		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request/";
//		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
//		
//		ValidatableResponse respone = RestAssured.given()
//				.log()
//				.all()
//				.delete("8ab9c6061bf0501034b340c6cc4bcbcb")
//				.then()
//				.assertThat()
//				.statusCode(204);
//	}
	
	
	@Test(dataProvider = "file")
	public void updateChangeRequest(String fileName) {
		File jsonFile = new File(fileName);
		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request/";
		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
		
		Response response = RestAssured.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body(jsonFile)
				.patch("c2b946061bf0501034b340c6cc4bcb8a");
				
		
		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		System.out.println(jsonResponse.getString("result.short_description"));
				
	}
	
}
