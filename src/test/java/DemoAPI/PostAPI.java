package DemoAPI;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPI {
	
	
	@Test
	public void PostAPIAutomation() {
		RestAssured.baseURI="https://reqres.in/api";
		RequestSpecification http= RestAssured.given();
		
		JSONObject inputbody= new JSONObject();
		inputbody.put("name", "Ashish");
		inputbody.put("title", "Leader");
		
		http.header("Content-Type", "application/json");
		http.body(inputbody.toJSONString());
		
		Response response =http.request(Method.POST,"/users");
		
		String ResposeObject=response.getBody().asString();
		System.out.println("My response"+ResposeObject);
				int statuscode=response.getStatusCode();
				Assert.assertEquals(statuscode, 201);
				
				String StatusLine=response.getStatusLine();
				System.out.println("MY STATUS LINE"+StatusLine);

		
		
		
	}

}
