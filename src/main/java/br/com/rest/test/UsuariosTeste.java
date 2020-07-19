package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.UsuariosPage;
import br.com.utils.Random;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuariosTeste extends BaseTest{
	
	

	private static final String  nome = "Testes Automatizados QA " + Random.randomAlphaNumeric(5);
	private static final String  email =  Random.randomAlphaNumeric(5) +"@doc88.com.br";
	public static Integer ID_USUARIO;
	
	
	@Test
	public void T1_incluirUsuario() {
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
	public void T2_alterarUsuario() {
		
		UsuariosPage usuarioAlterado =  new UsuariosPage();
		usuarioAlterado.setName("PHELIPE TESTES AUTOMATIZADOS");
		usuarioAlterado.setPassword("123456Qa");
		
		given()
		.body(usuarioAlterado)
		.when()
		.pathParam("id", ID_USUARIO)
		.put("/usuarios/{id}")
		.then()
		.statusCode(200)
		.body("itens.name", Matchers.is("PHELIPE TESTES AUTOMATIZADOS"));
	}
	
	@Test
	public void T3_listaUsuarioEspecifico() {
		given()
		.when()
		.pathParam("id", ID_USUARIO)
		.get("/usuarios/{id}")
		.then()
		.statusCode(200)
		.body("itens.name", Matchers.is("PHELIPE TESTES AUTOMATIZADOS"));
		
	}
	
	@Test
	public void T4_listaUsuario() {
		given()
		.when()
		.get("/usuarios")
		.then()
		.statusCode(200)
		.body(containsString("PHELIPE TESTES AUTOMATIZADOS"));
		
		
	}
	
	@Test
	public void T5_AlteraSenhaUsuario() {
		UsuariosPage alterarSenha =  new UsuariosPage();
		alterarSenha.setEmail(email);
		alterarSenha.setSenha_atual("123456Qa");
		alterarSenha.setNova_senha("123456");
		
		given()
		.body(alterarSenha)
		.when()
		.put("/usuarios/alterar-senha")
		.then()
		.statusCode(200)
		.body(Matchers.is("Senha alterada com sucesso"));
		
	}
	
	
	
	@Test
	public void T6_HabilitaUsuario() {
		given()	
		.when()
		.pathParam("id", ID_USUARIO)
		.put("usuarios/{id}/activate")
		.then()
		.statusCode(200)
		.body(containsString("Usuário habilitado com sucesso"));
	}
	
	@Test
	public void T7_DesabilitaUsuario() {
		given()
		.when()
		.pathParam("id", ID_USUARIO)
		.put("usuarios/{id}/deactivate")
		.then()
		.statusCode(200)
		.body(containsString("Usuário desabilitado com sucesso"));
	}
	
	
	@Test
	public void T8_deletaUsuario() {
		given()
		.when()
		.pathParam("id", ID_USUARIO)
		.delete("/usuarios/{id}")
		.then()
		.statusCode(204)
		;
		
	}
	@Test
	public void T9_validarCamposObrigatorios() {
		
		  given()
		 .when()
		 .post("/usuarios")
		 .then()
		 .statusCode(422)
		 .body("email[0]", containsString("O campo email é obrigatório."))
		 .body("password[0]", containsString("O campo password é obrigatório."))
		 ;
		 
	}

}
