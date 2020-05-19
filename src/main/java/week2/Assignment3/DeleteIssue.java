package week2.Assignment3;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteIssue extends SetUp {
	
	@Test(dependsOnMethods = {"week2.Assignment3.CreateIssue.createIssue"})
	public void deleteIssue() {
	Response response = RestAssured
			.given()
				.log().all()
			.and()
				.delete(createdIssueId);
	
	System.out.println(response.getStatusCode());
	}
}
