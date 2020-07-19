package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.containsString;
import br.com.core.BaseTest;
import br.com.pages.ContatosPage;
import br.com.utils.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContatosTeste extends BaseTest{
	
	
	public static String CONTATO_NAME = "contato " + System.nanoTime();
	public static String ATUALIZAR_NAME = "QUALITY ASSURANCE " + System.nanoTime();
	public static Integer ID_CONTATO;
	public static final String email =  Random.randomAlphaNumeric(5) +"@doc88.com.br";
	public static Integer ID_DISTRIBUIDORA = 1;
	public static Integer ID_EXTERNO = 1;
	
	@Test
	public void T1_IncluirContato() {
		ContatosPage incluirContato = new ContatosPage();
		incluirContato.setNome(CONTATO_NAME);
		incluirContato.setEmail(email);
		incluirContato.setTelefone("11992062360");
		incluirContato.setDistribuidora_id(1);
		incluirContato.setId_externo("1");
	
	ID_CONTATO = given()
			 .body(incluirContato)
			 .when()
			 .post("/distribuidoras_contatos")
			 .then()
			 .statusCode(201)
			 .extract().path("itens.id");
	System.out.println(ID_CONTATO);
		}
	
	
	@Test
	public void T2_AlterarContato() {
		ContatosPage alterarContato = new ContatosPage();
		alterarContato.setNome(ATUALIZAR_NAME);
		alterarContato.setEmail(email);
		alterarContato.setTelefone("11992062360");
		alterarContato.setDistribuidora_id(1);
		alterarContato.setId_externo("1");
		
		 given()
		.body(alterarContato)
		 .when()
		 .pathParam("id", ID_CONTATO)
		 .put("/distribuidoras_contatos/{id}")
		 .then()
		 .statusCode(200)
		 .body("itens.nome", Matchers.is(ATUALIZAR_NAME));
		 //.body("message",Matchers.is("Contato atualizado com sucesso"));
		 //validar se esta padrão da mensagem
		 
	}
	
	@Test
	public void T3_ListarContatoEspecifico() {
		given()
		.when()
		.pathParam("id", ID_CONTATO)
		.get("distribuidoras_contatos/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ATUALIZAR_NAME));
	}
	
	@Test
	public void T4_ListaTodosOsContatosDistribuidora() {
		given()
		.when()
		.pathParam("distribuidora_id", ID_DISTRIBUIDORA)
		.get("distribuidoras/{distribuidora_id}/contatos")
		.then()
		.statusCode(200);
		assertThat(ATUALIZAR_NAME, containsString(ATUALIZAR_NAME));
		
		
	}
	
	@Test
	public void T5_ListaContatoEspecifoPeloIdExterno() {
		given()
		.when()
		.pathParam("id_externo", ID_EXTERNO)
		.get("distribuidoras_contatos/id-externo/{id_externo}")
		.then()
		.statusCode(200)
		.body("itens.id_externo", Matchers.is("1"));
		
	}
	
	@Test
	public void T6_ExcluirContatoDaDistribuidora() {
		 given()
			 .when()
			 .pathParam("id", ID_CONTATO)
			 .delete("/distribuidoras_contatos/{id}")
			 .then()
			 .statusCode(204)
			 ;
	}
	
	@Test
	public void T7_ValidarCamposObrigatorios() {
	         given()
			 .when()
			 .post("/distribuidoras_contatos")
			 .then()
			 .statusCode(422)
			 .body("nome[0]", Matchers.is("O campo nome é obrigatório."))
			 .body("email[0]", Matchers.is("O campo email é obrigatório."))
			 .body("telefone[0]", Matchers.is("O campo telefone é obrigatório."))
			 .body("distribuidora_id[0]", Matchers.is("O campo distribuidora id é obrigatório."))
			 .body("id_externo[0]", Matchers.is("O campo id externo é obrigatório."))
			 ;
		}

}
