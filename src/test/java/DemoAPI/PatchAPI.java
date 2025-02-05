package DemoAPI;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchAPI {

	@Test
	public void PATCHAPIAutomation() {
		RestAssured.baseURI="https://reqres.in/api";
		RequestSpecification http= RestAssured.given();
		
		JSONObject inputbody= new JSONObject();
		inputbody.put("name", "AshishBBBBBBBBBBBBBBBB");
		inputbody.put("title", "LeaderBBBBBBBBBBBBBBBBBBBBBBB");
		
		http.header("Content-Type", "application/json");
		http.body(inputbody.toJSONString());
		
		Response response = http.request(Method.PUT,"/users/2");
		
		String ResposeObject=response.getBody().asString();
		System.out.println("My response"+ResposeObject);
				int statuscode=response.getStatusCode();
				Assert.assertEquals(statuscode, 200);
				
				String StatusLine=response.getStatusLine();
				System.out.println("MY STATUS LINE"+StatusLine);

		
		
		
	}


}
