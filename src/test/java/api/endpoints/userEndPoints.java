package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class userEndPoints {
	

	
	public static Response createUser(user Payload) {
		
		Response respose=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(Payload)
		
		
		.when()
		.post(Routes.post_url);
		
		return respose;
	}
	
public static Response getUser(String UserName) {
		
		Response respose=given()
		.accept(ContentType.JSON)
		.pathParam("username",UserName)
		
		.when()
		.get(Routes.get_url);
		
		return respose;
}

public static Response UpdateUser(String username, user Payload) {
	
	Response respose=given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)

	.pathParam("username",username)
	.body(Payload)

	.when()
	.put(Routes.put_url);
	
	return respose;
}
public static Response DeleteUser(String UserName) {
	
	Response respose=given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)

	.pathParam("username",UserName)
	
	.when()
	.delete(Routes.delete_url);
	
	return respose;
}

}
