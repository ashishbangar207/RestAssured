package DemoAPI;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutAPI {
	@Test
	public void PostAPIAutomation() {
		RestAssured.baseURI="https://reqres.in/api";
		RequestSpecification http=RestAssured.given();
		
		JSONObject inputbody=new JSONObject();
		inputbody.put("name", "Ashish b");
		inputbody.put("title", "Leader b");
		
		http.header("Content-Type", "application/json");
		http.body(inputbody.toJSONString());
		
		Response response = http.request(Method.PUT,"/users/2");
		
		String ResponseObj=response.getBody().asString();
		System.out.println("My respose is"+ResponseObj);
		
		
		int Statuscode =response.getStatusCode();
		Assert.assertEquals(Statuscode, 200);
		
		
		String StatusLine=response.getStatusLine();
		System.out.println("my status line is"+StatusLine);
		
	}

}
