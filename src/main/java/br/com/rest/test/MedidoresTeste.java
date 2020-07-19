package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.MedidoresPage;
import br.com.pages.TipoDispositivoPage;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedidoresTeste extends BaseTest{
	

	public static String MEDIDOR_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static String DESCRICAO_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer MEDIDOR_ID;
	
	
	@Test
	public void T01_incluirMedidor() {
		MedidoresPage incluirMedidor = new MedidoresPage();
		incluirMedidor.setNome(MEDIDOR_NAME);
		incluirMedidor.setEndianness_id(1);
		
		MEDIDOR_ID = given()
		 .body(incluirMedidor)
		 .when()
		 .post("/medidores")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
		 
	}
	
	@Test
	public void T02_listaMedidorEspecifico() {
		
		  given()
		 .when()
		 .pathParam("id", MEDIDOR_ID)
		 .get("/medidores/{id}")
		 .then()
		 .statusCode(200)
		 .body("itens.id", Matchers.is(MEDIDOR_ID))
		 ;
		 
	}
	
	@Test
	public void T03_listaMedidoresCadastrados() {
		
		  given()
		 .queryParam("page", "1")
		 .queryParam("limit", 1)
		 .when()
		 .get("/medidores")
		 .then()
		 .statusCode(200)
		 .body("itens.endianness",hasItem("BIG_8"))
		 ;
		 
	}
	
	@Test
	public void T04_atualizarMedidoresCadastrados() {
		
		MedidoresPage atualizarMedidor = new MedidoresPage();
		atualizarMedidor.setNome("Medidor Atualizado");
		atualizarMedidor.setEndianness_id(2);
		
		  given()
		  .body(atualizarMedidor)
		 .when()
		 .pathParam("id", MEDIDOR_ID)
		 .put("/medidores/{id}")
		 .then()
		 .statusCode(200)
		 .body("itens.nome",containsString("Medidor Atualizado"))
		 .body("itens.endianness", containsString("LITTLE_8"))
		 ;
		 
	}
	
	
	@Test
	public void T05_excluirMedidoresCadastrados() {
		
		  given()
		 .when()
		 .pathParam("id", MEDIDOR_ID)
		 .delete("/medidores/{id}")
		 .then()
		 .statusCode(204)
		 ;
		 
	}
	
	@Test
	public void T06_validarCamposObrigatorios() {
		
		          given()
				 .when()
				 .post("/medidores")
				 .then()
				 .statusCode(422)
				 .body("nome[0]", containsString("O campo nome é obrigatório."))
				 .body("endianness_id[0]", containsString("O campo endianness id é obrigatório."))
				 ;
		 
	}
	
	
	

}
