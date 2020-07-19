package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.hamcrest.collection.HasItemInArray;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.DistribuidoraPage;
import br.com.utils.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DistribuidoraTeste extends BaseTest{
	
	public static final String nome = "Testes Automatizados QA " + Random.randomAlphaNumeric(5);
	public static final String cnpj = Random.randomAlphaNumeric(14);
	private static String DISTRIBUIDORA_NAME = "distribuidora " + System.nanoTime();
	public static Integer ID_DISTRIBUIDORA;
	
	
	@Test
	public void T1_incluirDistribuidora() {
		DistribuidoraPage distribuidora = new DistribuidoraPage();
		distribuidora.setNome(nome);
		distribuidora.setCnpj(cnpj);
		distribuidora.setEstado("SP");
		distribuidora.setSubmercado("São Paulo");
		distribuidora.setHorario_inicio_ponta("16:55:00");
		distribuidora.setHorario_final_ponta("22:55:00");
		distribuidora.setHorario_inicio_reativo("16:55:00");
		distribuidora.setHorario_final_reativo("22:55:00");
		
		ID_DISTRIBUIDORA = given()
		 .body(distribuidora)
		 .when()
		 .post("/distribuidoras")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
	}
	
	@Test
	public void T2_alterarDistribuidora() {
		DistribuidoraPage alterarDistribuidora = new DistribuidoraPage();
		alterarDistribuidora.setNome("QA ATUALIZADO");
		alterarDistribuidora.setCnpj(cnpj);
		alterarDistribuidora.setHorario_inicio_ponta("16:55:00");
		alterarDistribuidora.setHorario_final_ponta("22:55:00");
		alterarDistribuidora.setHorario_inicio_reativo("16:55:00");
		alterarDistribuidora.setHorario_final_reativo("22:55:00");
		
		given()
		.body(alterarDistribuidora)
		.when()
		.pathParam("id", ID_DISTRIBUIDORA)
		.put("/distribuidoras/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is("QA ATUALIZADO"));
	}
	
	@Test
	public void T3_listaDistribuidoraEspecifica() {
		given()
		.when()
		.pathParam("id", ID_DISTRIBUIDORA)
		.get("/distribuidoras/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is("QA ATUALIZADO"));
		
	}
	
	@Test
	public void T4_listaDistribuidora() {
		given()
		.when()
		.get("/distribuidoras")
		.then()
		.statusCode(200);
		assertThat(DISTRIBUIDORA_NAME, containsString(DISTRIBUIDORA_NAME));
		
	}
	
	@Test
	public void T5_deletaDistribuidora() {
		given()
		.when()
		.pathParam("id", ID_DISTRIBUIDORA)
		.delete("/distribuidoras/{id}")
		.then()
		.statusCode(204)
		;
		
	}
	
	
	@Test
	public void T6_camposObrigatorios() {
		given()
		.when()
		.post("/distribuidoras")
		.then()
		.statusCode(422)
		.body("cnpj[0]", containsString("O campo cnpj é obrigatório."))
		.body("horario_inicio_ponta[0]", containsString("O campo horario inicio ponta é obrigatório."))
		.body("horario_final_ponta[0]",containsString("O campo horario final ponta é obrigatório."))
		.body("horario_inicio_reativo[0]", containsString("O campo horario inicio reativo é obrigatório."))
		.body("horario_final_reativo[0]", containsString("O campo horario final reativo é obrigatório."));
		
		;
		
	}
	
	

}
