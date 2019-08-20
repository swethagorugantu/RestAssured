import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Jira {
	
	
	
	Properties prop = new Properties();
	
	
	@BeforeTest
	public void getData() throws IOException {
	FileInputStream fis = new FileInputStream("C:\\Users\\sweth\\eclipse-workspace\\RESTAPI\\src\\env.properties");
	prop.load(fis);
	}
	
	
	@Test
	public void JiraAPI() {
		RestAssured.baseURI = prop.getProperty("JIRA");
		Response res = given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+Resources.getSessionKey()).
		body("{\r\n" + 
				"	\"fields\": {\r\n" + 
				"		\"project\":\r\n" + 
				"		{\r\n" + 
				"			\"key\": \"RES\"\r\n" + 
				"		},\r\n" + 
				"		\"summary\": \"JIRA RestAssured Defect\",\r\n" + 
				"		\"description\": \"First Bug\",\r\n" + 
				"		\"issuetype\":{\r\n" + 
				"			\"name\": \"Bug\"\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}")
		.when().
		post("/rest/api/2/issue").
		then().statusCode(201).extract().response();
		
		String s = res.asString();
		JsonPath js = new JsonPath(s);
		String id = js.get("id");
		System.out.println(id);
		
		
		
		
	}

}
