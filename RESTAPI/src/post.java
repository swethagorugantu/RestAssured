import static io.restassured.RestAssured.when;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



import org.testng.annotations.BeforeTest;



public class post {
	
	Properties pr = new Properties();
	
	@BeforeTest
	public void getValues() throws IOException {
		
		
		FileInputStream fis = new FileInputStream("C:\\Users\\sweth\\eclipse-workspace\\RESTAPI\\env.properties");
		//properties object will have knowledge about the file object as it contains the path of that properties file
		pr.load(fis);
		//pr.get("HOST");
		
	}
	
	@Test
	public void postData() {
		
		RestAssured.baseURI= pr.getProperty("HOST");
		
		
		//1. Extracting response
		
		Response res = given().
				body(Resources.payLoad()).
		when().
		post(Resources.getResource()).
		then().assertThat().header("server", "GitHub.com").and().body("message", equalTo("Not Found")).and().
		extract().response();
		
		//converting response to string format
	
		
		String s = res.asString();
		System.out.println(s);
		
		//2. Extract the json field from JSON o/p
		
		// to use the response and perform operations on it, it should be in JSON path
		//so we should convert the extracted string to JSON to use it further
		//This step reads the string and converts that into JSONpath and places the entire JSON into this js object
		
		
		JsonPath js = new JsonPath(s);
		String msg = js.get("message");
		System.out.println(msg);
		
		//3. Deleting a request
		
		given().
		body("{" + 
				"    \"message\": \""+msg+"\""+
	"}").
		
		when().
		post(Resources.getResource()).
		then().assertThat().header("server", "GitHub.com").and().body("message", equalTo("Not Found"));
		
		
		
		
		
	}

}
