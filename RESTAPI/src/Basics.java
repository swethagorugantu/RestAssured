import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Basics {
	
	@Test

	public void Test() {
		// TODO Auto-generated method stub
		
		//Base url or Host
		
		RestAssured.baseURI= "https://pilot.wright.edu";
		given().
				param("ou","426547").
				when().
				get("/d2l/lp/preferences/preferences_main/preferences_main.d2l").
				then().assertThat().header("server","Microsoft-IIS/10.0");		
				
	}

}
