import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Resources {
	
	public static String getResource() {
		String str = "users/swethagorugantu/login";
		return str;
		
	}
	
	
	public static String payLoad() {
		String b = "{\r\n" + 
				"    \"message\": \"Not Found\",\r\n" + 
				"    \"documentation_url\": \"https://developer.github.com/v3\"\r\n" + 
				"}";
		return b;
	}
	
	public static String getSessionKey() {
		
		Response res = given().header("Content-Type","application/json").
		body("{ \"username\": \"swetha.gorugantu\", \"password\": \"Omsair@m1903\" }").
		when().post("/rest/auth/1/session").
		then().statusCode(200).extract().response();
		
		String s = res.asString();
		JsonPath js = new JsonPath(s);
		String val = js.get("session.value");
		System.out.println(val);
		return val;
	}

}
