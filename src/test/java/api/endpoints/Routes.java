package api.endpoints;
/*
Create User(POST): https://petstore.swagger.io/v2/user
Get User(GET) : https://petstore.swagger.io/v2/user/{username}
Update user(PUT): https://petstore.swagger.io/v2/user/{username}
Delete User(DELETE):https://petstore.swagger.io/v2/user/{username}
*/
public class Routes {
    public static String base_url="https://petstore.swagger.io/v2";

    //User Module
    public static String post_url = base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String put_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";

    //Store Modulle URL's

    //Pet Module URL's
}
