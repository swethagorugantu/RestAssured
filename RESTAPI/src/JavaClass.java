	import io.restassured.RestAssured;
		import io.restassured.http.ContentType;

		import static io.restassured.RestAssured.given;
		import static org.hamcrest.Matchers.equalTo;

public class JavaClass {

	public static void main(String[] args) {

				// TODO Auto-generated method stub
				
				//Base url or Host
		
		System.out.println("\"Swetha\"");
				
				RestAssured.baseURI= "https://pilot.wright.edu";
				given().
						param("ou","426547").
						when().
						get("/d2l/lp/preferences/preferences_main/preferences_main.d2l").
						then().assertThat().header("server","Microsoft-IIS/10.0").and().contentType("text/html");
			}

		


	

}
