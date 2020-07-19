package br.com.rest.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sun.xml.internal.stream.Entity;

import br.com.core.BaseTest;
import br.com.pages.PropriedadePage;
import br.com.pages.TipoDispositivoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropriedadeTeste extends BaseTest{
	
	
	private static String PROPRIEDADE_NAME = "propriedade " + System.nanoTime();
	public static String ALTERA_NAME = "Alterar nome " + System.nanoTime();
	private static String TIPO_NAME = "FRONTEIRA " + System.nanoTime();
	public static Integer ID_PROPRIEDADE;
	public static Integer TIPO_DISPOSITIVO_ID ;
	
	
	
	@Test
	public void T1_incluirTipoDispositivo() {
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
	 public void T2_incluirPropriedade() {
	 PropriedadePage incluirPropriedade = new PropriedadePage();
	 incluirPropriedade.setNome(PROPRIEDADE_NAME);
	 incluirPropriedade.setTipo("float");
	 incluirPropriedade.setTipo_dispositivo_id(TIPO_DISPOSITIVO_ID);
	 incluirPropriedade.setCalculo("2");
	 
	 
	 ID_PROPRIEDADE = given()
			 .body(incluirPropriedade) 
			 .when()
			 .pathParam("id", TIPO_DISPOSITIVO_ID)
			 .post("/dispositivos_tipos/{id}/propriedades")
			 .then()
	         .statusCode(201)
	         .extract().path("itens.id"); 
	 
	 }
	 
	

	@Test
	public void T3_listaPropriedadeEspecifica() {
		given()
		.when()
		.pathParam("id", ID_PROPRIEDADE)
		.get("/propriedades/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(PROPRIEDADE_NAME));
		
	}
	
	@Test
	public void T4_listaPropriedadeDeUmDispositivo() {
		given()
		.when()
		.pathParam("id", TIPO_DISPOSITIVO_ID)
		.get("/dispositivos_tipos/{id}/propriedades")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(TIPO_NAME));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void T5_ListaPropriedadeOuTamplate() {
		List<Entity> list = new ArrayList<>();
		list = given()
				.when()
				.get("propriedade/list")
				.then()
				.statusCode(200)
				.extract().body().as(list.getClass());
	}
	
	@Test
	public void T6_AlterarPropriedade() {
		PropriedadePage alterarPropriedade = new PropriedadePage();
		alterarPropriedade.setNome(ALTERA_NAME);
		alterarPropriedade.setTipo("float");
		alterarPropriedade.setTipo_dispositivo_id(TIPO_DISPOSITIVO_ID);
		given()
		.when()
		.body(alterarPropriedade)
		.pathParam("id", ID_PROPRIEDADE)
		.put("propriedades/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ALTERA_NAME));
		
	}
	
	@Test
	public void T7_ExluirPropriedade() {
			given()
			.when()
			.pathParam("id", ID_PROPRIEDADE)
			.delete("/propriedades/{id}")
			.then()
			.statusCode(204)
			;
		}
	
	@Test
	public void T8_ValidarCamposObrigatorios() {
			given()
			.when()
			.pathParam("id", TIPO_DISPOSITIVO_ID)
			.post("dispositivos_tipos/{id}/propriedades")
			.then()
			.statusCode(422)
			.body("nome[0]", Matchers.is("O campo nome é obrigatório."))
			.body("tipo[0]", Matchers.is("O campo tipo é obrigatório."))
			.body("tipo_dispositivo_id[0]", Matchers.is("O campo tipo dispositivo id é obrigatório."))
			;
		}
	}
	

