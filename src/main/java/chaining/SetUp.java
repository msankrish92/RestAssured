package chaining;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;

public class SetUp {
	
	public static String sys_id;
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://dev89232.service-now.com/api/now/v2/table/change_request";
//		RestAssured.authentication = RestAssured.basic("admin", "Dec@1234");
		RestAssured.authentication = RestAssured.oauth2("Mj6ReiPkIVCjCzA9ud1AZX2xyYhxVVTMHW24aDG-2ywSG7rwaTXStAAbfWy_PYSSm3iEIJKMMMYtLILTfXcGKA");
	}
	
	@DataProvider(name="file")
	public String[] dataprovider() {
		String[] data = new String[2];
		data[0] = "data.json";
		data[1] = "data1.json";
		return data;
	}
	
	
}
