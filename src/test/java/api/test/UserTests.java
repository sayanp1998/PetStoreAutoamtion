package api.test;

import api.endpoints.userEndPoints;
import api.endpoints.userEndPoints_propertyRead;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userpayload;
    Logger logger;

    @BeforeClass
    public void setupData(){
        //Create a test data and it will be implemented in the tests
        faker = new Faker();
        userpayload = new User();

        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setUsername(faker.name().username());
        userpayload.setPassword(faker.internet().password(5,10));
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPhone(faker.phoneNumber().cellPhone());

        //Implement Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser(){

        logger.info("***** Creating User *****");
        Response response = userEndPoints.createUser(userpayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***** User is created *****");
    }
    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("Get User Test");
        Response response = userEndPoints.readUser(this.userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUseByName(){
        logger.info("Set user info");
        //Update data using payload
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());

        Response response = userEndPoints.updateUser(userpayload, this.userpayload.getUsername());
        response.then().log().body().statusCode(200); //chai  assertion
        Assert.assertEquals(response.getStatusCode(),200); //testng validation

        //checking data ater update
        Response readResponse = userEndPoints.readUser(this.userpayload.getUsername());
        readResponse.then().log().body();
        Assert.assertEquals(readResponse.getStatusCode(),200);

    }
    @Test(priority = 5, alwaysRun = false)
    public void testDeleteUserByName(){
        logger.info("Delete user info");
        Response response = userEndPoints.deleteUser(this.userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
