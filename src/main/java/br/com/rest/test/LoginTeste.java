package br.com.rest.test;

import static io.restassured.RestAssured.given;


import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;

import br.com.core.BaseTest;
import io.restassured.RestAssured;

public class LoginTeste extends BaseTest{
		
		 @BeforeClass
		    public static void login() {
		        Map<String , String> login =  new HashMap<>();
		        login.put("email", "qa@doc88.com.br");
		        login.put("password", "123456qa");
		      String  TOKEN = given()
		         .body(login)
		        .when()
		         .post("/login")
		        .then()
		        .statusCode(200)
		        .extract().path("token");
		        RestAssured.requestSpecification.header("Authorization", "Bearer " + TOKEN);

		    }
}
