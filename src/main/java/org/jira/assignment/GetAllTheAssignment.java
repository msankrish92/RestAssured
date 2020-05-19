package org.jira.assignment;

import java.io.Reader;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAllTheAssignment {

	@Test(enabled = true)
	public void getAllAssignment() {
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
		Response response = RestAssured
				.given()
					.log().all()
				.and()
					.param("jql", "project=MAY")
				.when()
					.get();
				
		response.prettyPrint();
		
		JsonPath jsonResponse = response.jsonPath();
		List<Object> list = jsonResponse.getList("issues.id");
		
		System.out.println(list.size());
		System.out.println(list.get(list.size()-1));
	
	}

	
}
