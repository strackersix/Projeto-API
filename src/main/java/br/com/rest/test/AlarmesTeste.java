package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.hamcrest.collection.HasItemInArray;

import static org.hamcrest.Matchers.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.AlarmesPage;
import br.com.pages.PropriedadePage;
import br.com.pages.TipoDispositivoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmesTeste extends BaseTest{

	public static String ALARME_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static String DESCRICAO_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer PROPRIEDADE_ID;
	private static String PROPRIEDADE_NAME = "propriedade " + System.nanoTime();
	public static String ALTERA_NAME = "Alterar nome " + System.nanoTime();
	public static Integer TIPO_DISPOSITIVO_ID ;
	public static Integer ALARME_ID ;
	private static String TIPO_NAME = "FRONTEIRA " + System.nanoTime();
	
	
	@Test
	public void T01_incluirTipoDispositivo() {
		TipoDispositivoPage incluirTipo = new TipoDispositivoPage();
		incluirTipo.setNome(TIPO_NAME);
		
		TIPO_DISPOSITIVO_ID = given()
		 .body(incluirTipo)
		 .when()
		 .post("/dispositivos_tipos")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
		 
	}
	

	  @Test 
	 public void T02_incluirPropriedade() {
	 PropriedadePage incluirPropriedade = new PropriedadePage();
	 incluirPropriedade.setNome(PROPRIEDADE_NAME);
	 incluirPropriedade.setTipo("float");
	 incluirPropriedade.setTipo_dispositivo_id(TIPO_DISPOSITIVO_ID);
	 incluirPropriedade.setCalculo("2");
	 
	 
	 PROPRIEDADE_ID = given()
			 .body(incluirPropriedade) 
			 .when()
			 .pathParam("id", TIPO_DISPOSITIVO_ID)
			 .post("/dispositivos_tipos/{id}/propriedades")
			 .then()
	         .statusCode(201)
	         .extract().path("itens.id"); 
	 System.out.println(PROPRIEDADE_ID);
	 
	 }
	
	
	@Test
	public void T03_incluirAlarme() {
		
		AlarmesPage incluirAlarme = new AlarmesPage();
		incluirAlarme.setNome(ALARME_NAME);
		incluirAlarme.setPropriedade_id(PROPRIEDADE_ID);
		incluirAlarme.setOperacao_logica_id(1);
		incluirAlarme.setValor_comparacao_1("10");
		incluirAlarme.setValor_retorno_1("2");
		incluirAlarme.setOperacao_logica_retorno_id(1);
		incluirAlarme.setValor_retorno_2("1");
		incluirAlarme.setDescricao(DESCRICAO_NAME);
		
		 ALARME_ID = given()
		.body(incluirAlarme) 
		 .when()
		 .post("/alarmes")
		 .then()
        .statusCode(201)
        .extract().path("itens.id"); 
		
	}
	
	@Test
	public void T04_listarAlarmeEspecifico() {
		
		              given()
					 .when()
					 .pathParam("id", ALARME_ID)
					 .get("/alarmes/{id}")
					 .then()
			        .statusCode(200)
			        .body("itens.id", Matchers.is(ALARME_ID))
			        ;
		
	}
	
	@Test
	public void T05_listarTodosAlarmesDeUmaPagina() {
		     given()
			 .when()
			 .get("/alarmes/list")
			 .then()
	        .statusCode(200)
	        .body("itens.current_page", Matchers.is(1))
	        ;
		
	}
	
	@Test
	public void T06_listarTodosStatusDeDispositivosExistentes() {
		     given()
			 .when()
			 .get("/alarmes/status")
			 .then()
	        .statusCode(200)
	        .body("itens",hasSize(4))
	        .body("itens", hasItems("conectado","desconectado","em_observacao","alarme"))
	        
	       
	        ;
		
	}
	
	@Test
	public void T07_listarTotalDeDispositivosDeDeterminadoStatus() {
		     given()
			 .when()
			 .get("/alarmes/total-por-status/conectado")
			 .then()
	        .statusCode(200)
	        //.body("itens",hasSize(4))
	        .body("itens.total", Matchers.is(0))
	        .body("itens.porcentagem",  Matchers.is(0))
	        ;
		
	}
	
	
	@Test
	public void T08_listarHistoricoDeStatusDosDispositivos() {
		     given()
		    .queryParam("codigo", "123456")
			 .queryParam("status", "desconectado")
			 .queryParam("limite", 1)
			 .get("/alarmes/historico")
			 .then()
	        .statusCode(200)
		    .body("itens.status[0]", Matchers.is("desconectado"))
		    .body("itens.codigo[0]", Matchers.is("123456"))
	        
	        ;
		
	}
	
	@Test
	public void T09_listarStatusAtualDeUmOuMaisDispositivosEexportar() {
		     given()
		    .queryParam("codigo", "123456")
			 .get("/alarmes/historico")
			 .then()
	        .statusCode(200)
		    .body("itens.codigo[0]", Matchers.is("123456"))
	        
	        ;
		
	}
	
	@Test
	public void T10_listarStatusAtualDeUmOuMaisDispositivo() {
		     given()
		    .queryParam("codigo", "123456")
		    .queryParam("limite", 1)
		   // .queryParam("initialDate", "2020-01-31")
		    .queryParam("codigo", "123456")
			 .get("/alarmes/historico")
			 .then()
	        .statusCode(200)
		    .body("itens.codigo[0]", Matchers.is("123456"))
		    .body("itens.status[0]", Matchers.is("desconectado"))
	        ;
		
	}
	
	@Test
	public void T11_listarAlarmeEspecifico() {
		 given()
		 .when()
		 .pathParam("id", ALARME_ID)
		 .get("/alarmes/{id}")
		 .then()
        .statusCode(200)
        .body("itens.id",Matchers.is(ALARME_ID))
        ;
	}
	
	@Test
	public void T12_atualizarAlarme() {
		
		AlarmesPage atualizarAlarme = new AlarmesPage();
		atualizarAlarme.setNome("Alarme Atualizado");
		atualizarAlarme.setPropriedade_id(PROPRIEDADE_ID);
		atualizarAlarme.setOperacao_logica_id(1);
		atualizarAlarme.setValor_comparacao_1("10");
		atualizarAlarme.setValor_retorno_1("2");
		atualizarAlarme.setOperacao_logica_retorno_id(1);
		atualizarAlarme.setValor_retorno_2("1");
		atualizarAlarme.setDescricao(DESCRICAO_NAME);
		
		 given()
		.body(atualizarAlarme) 
		 .when()
		 .pathParam("id", ALARME_ID)
		 .put("/alarmes/{id}")
		 .then()
        .statusCode(200)
        .body("itens.id", Matchers.is(ALARME_ID))
        .body("itens.nome", Matchers.is("Alarme Atualizado"))
        ;
		
	}
	
	
	@Test
	public void T13_desativarAlarme() {
		 given()
		 .when()
		 .pathParam("id", ALARME_ID)
		 .delete("/alarmes/{id}")
		 .then()
        .statusCode(200)
        .body("mensagem",Matchers.is("Alarme desativado com sucesso"))
        ;
	}
	
	@Test
	public void T14_reativarAlarme() {
		 given()
		 .when()
		 .pathParam("id", ALARME_ID)
		 .post("/alarmes/reativar/{id}")
		 .then()
        .statusCode(200)
        .body("mensagem",Matchers.is("Alarme reativado com sucesso"))
        ;
	}
	
	@Test
	public void T15_deletarAlarme() {
		 given()
		 .when()
		 .pathParam("id", ALARME_ID)
		 .delete("/alarmes/delete/{id}")
		 .then()
        .statusCode(200)
        .body("mensagem",Matchers.is("Alarme excluído com sucesso"))
        ;
	}
	
	@Test
	public void T16_validaCamposObrigatorio() {
		 given()
		 .when()
		 .post("/alarmes")
		 .then()
         .statusCode(422)
         .body("nome[0]", Matchers.is("O campo nome é obrigatório."))
		 .body("propriedade_id[0]", Matchers.is("O campo propriedade id é obrigatório."))
		 .body("operacao_logica_id[0]", Matchers.is("O campo operacao logica id é obrigatório."))
		 .body("valor_comparacao_1[0]", Matchers.is("O campo valor comparacao 1 é obrigatório."));
		
	}

}
