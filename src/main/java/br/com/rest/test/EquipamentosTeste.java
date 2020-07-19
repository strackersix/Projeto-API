package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.EquipamentoPage;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EquipamentosTeste extends BaseTest{
	
	private static String EQUIPAMENTO_NAME = "equipamento " + System.nanoTime();
	public  static String ALTERA_EQUIPAMENTO_NAME = "Altera Nome " + System.nanoTime();
	public static Integer ID_EQUIPAMENTO;
	
	
	
	@Test
	public void T1_incluirEquipamento() {
		EquipamentoPage incluirEquipamento = new EquipamentoPage();
		incluirEquipamento.setNome(EQUIPAMENTO_NAME);
		
		ID_EQUIPAMENTO = given()
		 .body(incluirEquipamento)
		 .when()
		 .post("/equipamentos")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
	}
	
	@Test
	public void T2_alterarEquipamento() {
		EquipamentoPage alterarEquipamento = new EquipamentoPage();
		alterarEquipamento.setNome(ALTERA_EQUIPAMENTO_NAME);
		
		given()
		.body(alterarEquipamento)
		.when()
		.pathParam("id", ID_EQUIPAMENTO)
		.put("/equipamentos/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ALTERA_EQUIPAMENTO_NAME));
	}
	
	@Test
	public void T3_listaEquipamentoEspecifico() {
		given()
		.when()
		.pathParam("id", ID_EQUIPAMENTO)
		.get("/equipamentos/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ALTERA_EQUIPAMENTO_NAME));
		
	}
	
	@Test
	public void T4_listaEquipamento() {
		given()
		.when()
		.get("/equipamentos")
		.then()
		.statusCode(200);
		assertThat(ALTERA_EQUIPAMENTO_NAME, containsString(ALTERA_EQUIPAMENTO_NAME));
		
	}
	
	@Test
	public void T5_deletaEquipamento() {
		given()
		.when()
		.pathParam("id", ID_EQUIPAMENTO)
		.delete("/equipamentos/{id}")
		.then()
		.statusCode(204)
		;
		
	}
	
	@Test
	public void T6_validaCamposObrigatorios() {
		given()
		 .when()
		 .post("/equipamentos")
		 .then()
		 .statusCode(422)
		 .body("nome[0]", Matchers.is("O campo nome é obrigatório."))
		 ;
	}
	
	
	
	

}
