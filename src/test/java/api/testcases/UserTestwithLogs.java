package api.testcases;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilities.ExcelReader;
import io.restassured.response.Response;

import io.qameta.allure.*;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

@Epic("User Management API Tests")  // High-level module
@Feature("User API Test Scenarios") // Feature category
public class UserTestwithLogs {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserTestwithLogs.class);
    user payload = new user();

    @Test(priority = 1, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create User API Test")
    @Description("Verifies the creation of a user via API")
    @Step("Executing create user test with userId: {0}")
    public void testCreateUser(String userId, String userName, String fname, String lname, String email, String pwd, String phone) {
        logger.info("Starting testCreateUser with userId: {}", userId);

        // Creating payload for user
        payload.setId(Integer.parseInt(userId));
        payload.setuserName(userName);
        payload.setFirstName(fname);
        payload.setLastName(lname);
        payload.setEmail(email);
        payload.setPassword(pwd);
        payload.setPhone(phone);

        logger.debug("User payload created: {}", payload);

        // Sending POST request
        logger.info("Sending POST request to create user: {}", userName);
        Response response = userEndPoints.createUser(payload);

        logger.info("Received response: statusCode={} responseBody={}", response.getStatusCode(), response.asString());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User created successfully with userId: {}", userId);
    }

    @Test(priority = 2, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("Get User Data API Test")
    @Description("Retrieves user details from API")
    public void testGetUserData(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
        try {
            logger.info("Starting testGetUserData for FirstName: {}", fname);

            // Sending GET request
            Response response = userEndPoints.getUser(fname);

            logger.info("Received response: statusCode={} responseBody={}", response.getStatusCode(), response.asString());
            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(), 404);
            logger.info("User retrieval attempt finished with status code: 404 (Not Found)");
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage());
        }
    }

    @Test(priority = 3, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
    @Severity(SeverityLevel.NORMAL)
    @Story("Delete User API Test")
    @Description("Deletes user from API")
    public void testDeleteUser(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
        logger.info("Starting testDeleteUser for FirstName: {}", fname);

        // Sending DELETE request
        Response response = userEndPoints.DeleteUser(fname);

        logger.info("Received response: statusCode={} responseBody={}", response.getStatusCode(), response.asString());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404);
        logger.info("User deletion attempt finished with status code: 404 (Not Found)");
    }

    @Test(priority = 4, dataProvider = "testdata", dataProviderClass = ExcelReader.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update User API Test")
    @Description("Updates user details in API")
    public void testUpdateUser(String userId, String FirstName, String fname, String lname, String email, String pwd, String phone) {
        logger.info("Starting testUpdateUser for userName: {}", this.payload.getuserName());

        // Sending PUT request
        Response response = userEndPoints.UpdateUser(this.payload.getuserName(), payload);

        logger.info("Received response: statusCode={} responseBody={}", response.getStatusCode(), response.asString());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User updated successfully with userName: {}", this.payload.getuserName());
    }
}


