package week2.Assignment3;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class SetUp {
	
	public static String createdIssueId;
	
	@BeforeSuite
	public void setUp() {
	RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/issue";
	RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
	}
}
