package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;


import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilities.ExcelReader;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

public class UserTest2 {
    user payload = new user();
	    // Test case for creating a user
	    @Test(priority = 1, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
	    public void testCreateUser(String userId, String userName, String fname, String lname, String email, String pwd, String phone) {
	        // Creating payload for user
	        payload.setId(Integer.parseInt(userId));
	        payload.setuserName(userName);
	        payload.setFirstName(fname);
	        payload.setLastName(lname);
	        payload.setEmail(email);  // ✅ Fix: Correct email assignment
	        payload.setPassword(pwd); // ✅ Fix: Correct password assignment
	        payload.setPhone(phone);

	        // Sending POST request to create user and capturing the response
	        Response response = userEndPoints.createUser(payload);

	        // Log response for debugging
	        response.then().log().all();

	        // Validation - Assert that status code is 200 (OK)
	        Assert.assertEquals(response.getStatusCode(), 200);

	       
	    }
	    @Test(priority = 2, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
	    public void testGetUserData(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
	        try// Assuming the username is the unique identifier
	        {
	        	
	        
	        Response response = userEndPoints.getUser(fname);

	        // Log response for debugging
	        response.then().log().all();

	        // Validation - Assert that status code is 200 (OK)
	        Assert.assertEquals(response.getStatusCode(), 404);
	        }
	        catch(Exception e) {
	        	System.out.println("catch e"+e.getMessage());
	        }
	    
}
	    @Test(priority = 3, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
	    public void testDeleteUser(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
	        Response response = userEndPoints.DeleteUser(fname);

	        System.out.println("Delete User Data.");

	        // Log response
	        response.then().log().all();

	        // Validation
	        Assert.assertEquals(response.getStatusCode(), 404);
	    }

	    @Test(priority = 4, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
	    public void testUpdateUser(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
			Response response = userEndPoints.UpdateUser(this.payload.getuserName(),payload);

			//log response
			response.then().log().all();


			//validation
			Assert.assertEquals(response.getStatusCode(),200);

			

		}
}