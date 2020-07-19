package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.ComentariosPage;
import br.com.pages.UsuariosPage;
import br.com.utils.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComentariosTeste extends BaseTest{
	
	
	private static final String  nome = "Testes Automatizados QA " + Random.randomAlphaNumeric(5);
	private static final String  email =  Random.randomAlphaNumeric(5) +"@doc88.com.br";
	private static String COMENTARIO_NAME = "comentario " + System.nanoTime();
	public static Integer ID_USUARIO;
	public static String ID_TYPE = "RegistroAlarme";
	public static Integer REFERENCE_ID = 1;
	
	
	
	
	@Test
	public void T01_incluirUsuario() {
		UsuariosPage usuario =  new UsuariosPage();
		usuario.setName(nome);
		usuario.setEmail(email);
		usuario.setPassword("123456");
		
		ID_USUARIO = given()
		 .body(usuario)
		 .when()
		 .post("/usuarios")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
	}
	
	
	
	@Test
	public void T02_validaCamposObrigatorios() {
	      given()
		 .when()
		 .pathParam("type", ID_TYPE)
		 .pathParam("referenceId", ID_USUARIO)
		 .post("/comentarios/{type}/{referenceId}")
		 .then()
		 .statusCode(422)
		 .body("comment[0]", Matchers.is("O campo comment é obrigatório."))
		 .body("user_id[0]", Matchers.is("O campo user id é obrigatório."));
		
	}
	
	
	@Test
	public void T03_inlcuirNovoComentario() {
		ComentariosPage incluirComentario = new ComentariosPage();
		incluirComentario.setUser_id(ID_USUARIO);
		incluirComentario.setComment(COMENTARIO_NAME);
	   
		
		          given()
				 .when()
				 .body(incluirComentario)
				 .pathParam("type", ID_TYPE)
				 .pathParam("referenceId", ID_USUARIO)
				 .post("/comentarios/{type}/{referenceId}")
				 .then()
				 .statusCode(201)
				 .body("comment", containsString(COMENTARIO_NAME))
				 .body("commentable_type", containsString("App\\RegistroAlarme"))
				 ;
			}
	
	
	@Test
	public void T04_deletarUsuario() {
		given()
		 .when()
		 .pathParam("id", ID_USUARIO)
		 .delete("/usuarios/{id}")
		 .then()
		 .statusCode(204)
		 ;
	}
	
	
}
