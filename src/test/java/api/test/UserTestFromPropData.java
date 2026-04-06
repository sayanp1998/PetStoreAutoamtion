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

public class UserTestFromPropData {

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
    public void testPostUserByNamePropFileRead(){

        logger.info("***** Creating User *****");
        Response response = userEndPoints_propertyRead.createUser(userpayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***** User is created *****");
    }

    @Test(priority = 2)
    public void testGetUserByNamePropFileRead(){
        logger.info("Get User Test using prop file");
        Response response = userEndPoints_propertyRead.readUser(this.userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
