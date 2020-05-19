package org.jira.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class IssuesWithAttachmentMedium {

	@Test(enabled = true)
	public void getMediumIssues() {
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
		Response response = RestAssured
				.given()
					.log().all()
				.and()
					.param("jql", "project=MAY")
				.when()
					.get();
		
		JsonPath jsonResponse = response.jsonPath();

		List<String> list = jsonResponse.getList("issues.id");
		System.out.println(list);
		Map<String,String> attachmentIdList = new HashMap<String,String>();
		for(String a : list) {
			RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/issue/";
			RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
			
			Response response1 = RestAssured
					.given()
						.log().all()
					.when()
						.get(a);
			
//			response1.prettyPrint();
			
			JsonPath jsonRespone1 = response1.jsonPath();
//			System.out.println(jsonRespone1.getString("fields.attachment.id"));
//			System.out.println(jsonRespone1.getString("fields.attachment.id").isEmpty());
			if(!(jsonRespone1.getString("fields.attachment.id").equals("[]"))) {
				System.out.println(jsonRespone1.getString("fields.priority.name"));
				if(jsonRespone1.getString("fields.priority.name").equals("Medium"))
				attachmentIdList.put(a,jsonRespone1.getString("fields.attachment.filename"));	
			}
				
			
//			System.out.println();
		}
		System.out.println(attachmentIdList);
		
//		for(Entry a : attachmentIdList.entrySet()) {
//			if(!(a.getValue().equals("[]"))) {
//				System.out.println(true);
//			}else {
//				System.out.println(false);
//				
//			}
//		}
		
	}

	
}
