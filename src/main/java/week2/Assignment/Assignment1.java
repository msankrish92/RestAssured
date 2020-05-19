package week2.Assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Assignment1 {
//	1. Get all the issues which are create in last 24 hrs from Jira 
//	2. Create Issue in jira by passing body as String
//	3. Delete the first issue in jira
	@Test(enabled = false)
	public void getAllIssuesWithIn24hrs() {
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
	
		Response response = RestAssured
				.given()
					.log().all()
				.get();
		
		JsonPath jsonResponse = response.jsonPath();
		List<String> idList = jsonResponse.getList("issues.id");
		List<String> idCreatedDate = jsonResponse.getList("issues.fields.created");
		
		Map<String, String> idAndTime = new HashMap<String, String>();
		int i = 0;
		for(String a : idList){
			idAndTime.put(a, idCreatedDate.get(i));
			i++;
		}
		System.out.println(idAndTime);
		
		long twoFourHoursInMil = 24 * 60 * 60 * 1000;
		DateTime dt = new DateTime();
		long millis = dt.getMillis();
		long lastTwoFourHoursInMil = millis - twoFourHoursInMil;
//		System.out.println(lastTwoFourHoursInMil);
		int beforeCount = 0;
		int afterCount = 0;
		for(Entry a : idAndTime.entrySet()) {
			String input = (String) a.getValue();
			DateTimeZone zone = DateTimeZone.UTC; // Or DateTimeZone.UTC
			DateTime dateTime = new DateTime( input, zone );
			long millisecondsSinceUnixEpoch = dateTime.getMillis();
			beforeCount++;
//			System.out.println(millisecondsSinceUnixEpoch);
			if(millisecondsSinceUnixEpoch>lastTwoFourHoursInMil) {
				System.out.println(a.getKey());
				afterCount++;
			}
		}
//		System.out.println(beforeCount);
//		System.out.println(afterCount);
		
	}
	@Test(enabled = false)
	public void createIssueUsingBodyAsString() {
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/issue";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
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
		
		respone.prettyPrint();
		
		JsonPath jsonRespone = respone.jsonPath();
		
		String createdId = jsonRespone.getString("id");
		System.out.println(createdId);
	
	}

	
	@Test
	public void deleteIssue() {
		
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/issue";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
		
		Response response = RestAssured
				.given()
					.log().all()
				.and()
					.delete("11341");
		
		System.out.println(response.getStatusCode());
	}
	
	

}
