package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutenticacaoTeste extends BaseTest{
	
	@Test
	public void T01_autenticaUsuario() {
	          given()
			 .when()
			 .post("/login")
			 .then()
			 .statusCode(401)
			 .body("error", Matchers.is("Unauthorized"));

}
	
	@Test
	public void T02_deslogaUsuario() {
	          given()
			 .when()
			 .post("/logout")
			 .then()
			 .statusCode(201);

}

}