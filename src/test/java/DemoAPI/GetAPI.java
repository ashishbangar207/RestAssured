package DemoAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPI {
	//https://reqres.in/api/users/2
	
	@Test
	public void GetAPIAutomation() {
		
	RestAssured.baseURI="https://reqres.in/api/users";
	RequestSpecification http = RestAssured.given();
	Response response=http.request(Method.GET,"/2");
	String ResponseObject=response.getBody().asString();
	System.out.println("My responce is"+ResponseObject);
	
	int StatusCode=response.getStatusCode();
	Assert.assertEquals(StatusCode, 200);
	
	
	String StatusLine=response.getStatusLine();
	
	System.out.println("my status line is"+StatusLine);

}
}