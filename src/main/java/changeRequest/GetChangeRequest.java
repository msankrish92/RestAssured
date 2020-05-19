package changeRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetChangeRequest {
	
	@Test
	public void getChangeRequest() {
		
		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/";
		
		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("type", "emergency");
		parameterMap.put("sysparm_fields", "number,type,sys_id");
//		parameterMap.put("sysparm_fields", "");
//		parameterMap.put("sysparm_fields", "");
		Response respone = RestAssured
				.given()
				.accept(ContentType.XML)
				.queryParams(parameterMap)
//				.queryParam("sysparm_fields", "number")
//				.queryParam("sysparm_fields", "type")
//				.param("change_request", "change_request")
				.get("change_request")
				;
		respone.prettyPrint();
		System.out.println(respone.getStatusCode());
		System.out.println(respone.getTime());
		
		XmlPath jsonResponse = respone.xmlPath();
		
		List<String> changeRequestList = jsonResponse.getList("response.result.number");
		int size = changeRequestList.size();
		int lastChangeRequest = size-1;
		System.out.println("Total number of ChangeRequest of type emergency: " + size);
		System.out.println("First Matching sys_id: " + jsonResponse.getString("response.result.sys_id[0]"));
		System.out.println("Last request number: " + jsonResponse.getString("response.result.number["+lastChangeRequest+"]"));
	}

}
