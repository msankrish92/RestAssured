package week2.Assignment;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Assignment2 {
	@DataProvider(name="file")
	public String[] dataProvider() {
		String[] file = new String[3];
		file[0] = "data.json";
		file[1] = "data1.json";
		file[2] = "data2.json";
		return file;
	}

	@Test(dataProvider = "file")
	public void createIssueUsingFile(String fileName) {
		
		File file = new File(fileName);
		
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/issue";
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com", "kEJxzmhkQzvdeP8iysWN2D1B");
		
		Response respone = RestAssured
				.given().log().all()
					.contentType(ContentType.JSON)
				.and()
					.accept(ContentType.JSON)
				.and()
					.body(file)
					.when()
						.post();
		
		respone.prettyPrint();

	}
	
}
