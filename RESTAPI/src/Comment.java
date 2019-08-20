import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Comment {
	
Properties prop = new Properties();
	
	
	@BeforeTest
	public void getData() throws IOException {
	FileInputStream fis = new FileInputStream("C:\\Users\\sweth\\eclipse-workspace\\RESTAPI\\src\\env.properties");
	prop.load(fis);
	}
	
	
	@Test
	public void AddComment() {
		RestAssured.baseURI = prop.getProperty("JIRA");
		Response res = given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+Resources.getSessionKey()).
		body("{\r\n" + 
				"  \"body\": \"Adding a comment\",\r\n" + 
				"  \"visibility\": {\r\n" + 
				"    \"type\": \"role\",\r\n" + 
				"    \"value\": \"Administrators\"\r\n" + 
				"  }\r\n" + 
				"}").
		when().
		post("/rest/api/2/issue/10009/comment").then().statusCode(201).extract().response();
		
		String s = res.asString();
		JsonPath js = new JsonPath(s);
		String id = js.get("id");
		System.out.println(id);
		
		
		
		

}
}
