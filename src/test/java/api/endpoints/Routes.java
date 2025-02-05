package api.endpoints;

public class Routes {
/*
 Create User	Post	https://petstore.swagger.io/v2/user
Get User 	Get	https://petstore.swagger.io/v2/user/{username}
Update User	Put	https://petstore.swagger.io/v2/swagger.json/user/{username}
Delete User	Delete	https://petstore.swagger.io/v2/swagger.json/user/{username}

 */
	
	public static String Base_url="https://petstore.swagger.io/v2";
	
	
	public static String post_url=Base_url+"/user";
	public static String get_url=Base_url+"/user/{username}";
	public static String put_url=Base_url+"/user/{username}";
	public static String delete_url=Base_url+"/user/{username}";
	
	//pet modules urls
	
	//store modules urls

}
