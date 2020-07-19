package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.TipoDispositivoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipoDeDispositivosTeste extends BaseTest{
	
	public static String TIPO_DISPOSITIVO_NAME = br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer ID_TIPO_DISPOSITIVO;
	
	@Test
	public void T01_incluirTipoDeDispositivo() {
		TipoDispositivoPage incluirDispositivo = new TipoDispositivoPage();
		incluirDispositivo.setNome(TIPO_DISPOSITIVO_NAME);
	
		ID_TIPO_DISPOSITIVO = given()
			 .body(incluirDispositivo)
			 .when()
			 .post("/dispositivos_tipos")
			 .then()
			 .statusCode(201)
			 .body("itens.nome", Matchers.is(TIPO_DISPOSITIVO_NAME))
			 .extract().path("itens.id")
			 ;
		}
	
	@Test
	public void T02_listarTipoDispositivoPeloId() {
		given()
		.when()
		.pathParam("id", ID_TIPO_DISPOSITIVO)
		.get("dispositivos_tipos/{id}")
		.then()
		.statusCode(200)
		.body("itens.id", Matchers.is(ID_TIPO_DISPOSITIVO))
		;
	}
	
	@Test
	public void T03_listarTodosDispositivos() {
		given()
		.when()
		.get("dispositivos_tipos")
		.then()
		.statusCode(200)
		.body(containsString(TIPO_DISPOSITIVO_NAME))
		;
	}
	
	@Test
	public void T04_atualizarDispositivos() {
		TipoDispositivoPage atualizarDispositivo = new TipoDispositivoPage();
		atualizarDispositivo.setNome("Atualiza tipo de dispositivo");
		
		given()
		.body(atualizarDispositivo)
		.when()
		.pathParam("id", ID_TIPO_DISPOSITIVO)
		.put("dispositivos_tipos/{id}")
		.then()
		.statusCode(200)
		.body("itens.id", Matchers.is(ID_TIPO_DISPOSITIVO))
		.body("itens.nome", Matchers.is("Atualiza tipo de dispositivo"))
		;
	}
	
	
	@Test
	public void T05_removerDispositivos() {
		given()
		.when()
		.pathParam("id", ID_TIPO_DISPOSITIVO)
		.delete("dispositivos_tipos/{id}")
		.then()
		.statusCode(204)
		
		;
	}
	
	
	@Test
	public void T06_validarCamposObrigatorios() {
		given()
		.when()
		.post("dispositivos_tipos")
		.then()
		.statusCode(422)
		.body("nome", contains("O campo nome é obrigatório."))
		
		;
	}
	
		
		

}
