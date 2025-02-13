package api.testcases;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import api.endpoints.userEndPoints;
import api.payload.user;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	user userPayload;
	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new user();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setuserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		 
	}
	@Test(priority=1)
	public void testCreateUser()
	{
		Response response = userEndPoints.createUser(userPayload);

		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		//log
		//logger.info("Create User executed.");
	}


	@Test(priority=2)
	public void testGetUserData()
	{
		Response response = userEndPoints.getUser(this.userPayload.getuserName());

		System.out.println("Read User Data.");
		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),404);

		//log
		//logger.info("Get User Data executed.");
	}

	@Test(priority=3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		Response response = userEndPoints.UpdateUser(this.userPayload.getuserName(),userPayload);


		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);

		//Read User data to check if first name is updated 

		Response responsePostUpdate = userEndPoints.getUser(this.userPayload.getuserName());

		System.out.println("After Update User Data.");

		responsePostUpdate.then().log().all();

		//log
		//logger.info("Update User executed.");

	}

	@Test(priority=4)
	public void testDeleteUser()
	{

		Response response = userEndPoints.DeleteUser(this.userPayload.getuserName());

		System.out.println("Delete User Data.");

		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),404);

		
		//log
				//logger.info("Delete User executed.");


	}
}
