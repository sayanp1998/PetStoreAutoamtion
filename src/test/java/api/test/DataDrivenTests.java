package api.test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
//import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTests {
    User userpayload;
    //Logger logger;
    @Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String password, String phNo){
        //logger.info("**** Data Provide iplemented ****");
        userpayload = new User();

        userpayload.setId(Integer.parseInt(userID));
        userpayload.setUsername(userName);
        userpayload.setFirstName(fname);
        userpayload.setLastName(lname);
        userpayload.setEmail(userEmail);
        userpayload.setPassword(password);
        userpayload.setPhone(phNo);

        Response response = userEndPoints.createUser(userpayload);
        Assert.assertEquals(response.getStatusCode(),200);

        //logger.info("**** Users created from test data ****");
    }

    @Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){
        //logger.info("Delete users");
        Response response = userEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
