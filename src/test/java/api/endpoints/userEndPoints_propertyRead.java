package api.endpoints;

//Created for CRUD operations

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
//import static sun.nio.ch.IOUtil.load;

public class userEndPoints_propertyRead {

    /*private static Properties prop;
    public static void readProp(){
        String path = System.getProperty("user.dir")+"//src//test//resources//routes.properties";
        prop = new Properties();
        try(InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(path)){
            if(is==null) throw new RuntimeException("Unable to load file" + path);
            prop.load(is);
        }catch (Exception e){
            throw new RuntimeException("Unable to load config" + path,e);
        }
    }*/

    public static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load the property file
        return routes;
    }


    public static Response createUser(User payload){
        String post_url = getURL().getString("post_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }
    public static Response readUser(String username){
        String get_url = getURL().getString("get_url");
        Response response = given().pathParam("username",username)
                .when()
                .get(get_url);

        return response;
    }
    public static Response updateUser(User payload, String username){
        String put_url = getURL().getString("put_url");
        Response response = given()
                .pathParam("username",username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(put_url);

        return response;
    }
    public static Response deleteUser(String username){
        String delete_url = getURL().getString("delete_url");
        Response response = given().pathParam("username",username)
                .when()
                .delete(Routes.delete_url);

        return response;
    }
}
