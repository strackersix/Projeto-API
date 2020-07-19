package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.DispositivosPage;
import br.com.pages.MedidoresPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BuscarTestes extends BaseTest{
	
	public static String MEDIDOR_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static String DESCRICAO_NAME = "Alarme Automatizado " + br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer MEDIDOR_ID;
	public static String DISPOSITIVO_CODE = br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer ID_DISPOSITIVO;
	
	
	@Test
	public void T01_incluirMedidorParaBusca() {
	
		MedidoresPage incluirMedidor = new MedidoresPage();
		
		incluirMedidor.setNome(MEDIDOR_NAME);
		incluirMedidor.setEndianness_id(1);
		
		MEDIDOR_ID =
		
		given()
			.body(incluirMedidor)
		.when()
			.post("/medidores")
		.then()
			.statusCode(201)
			.extract().path("itens.id")
		
		;
		
	}
	
	
	@Test
	public void T02_retornaListaDeMedidoresParaMontaroOptionSelect() {
		
		given()
		.when()
			.queryParam("search", MEDIDOR_NAME)
			.get("buscar/opcoes-medidores")
		.then()
			.statusCode(200)
			.body("itens.label", hasItem(MEDIDOR_NAME))
		
		;
		
	}
	
	
	
	@Test
	public void T03_incluirDispositivoParaBusca() {
		
		DispositivosPage incluirDispositivo = new DispositivosPage();
		
		incluirDispositivo.setCodigo(DISPOSITIVO_CODE);
		incluirDispositivo.setTipo_id(1);
		incluirDispositivo.setDescricao("TESTES AUTOMATIZADOS QA");
		incluirDispositivo.setConstante_medidor(2);
		incluirDispositivo.setRelacao_potencial(2);
		incluirDispositivo.setRelacao_corrente(2);
		incluirDispositivo.setDistribuidora_id(1);
		
		ID_DISPOSITIVO = 
				
		given()
			.body(incluirDispositivo)
		.when()
			.post("/dispositivos")
		.then()
			.statusCode(201)
			.extract().path("itens.id");
		 
	}
	
	
	
	@Test
	public void T04_retornaListaDeDispositosParaMontaroOptionSelec() {
		given()
		.when()
		.queryParam("search", DISPOSITIVO_CODE)
		.get("buscar/opcoes-dispositivos")
		.then()
		.statusCode(200)
		.body("itens.label", hasItem(DISPOSITIVO_CODE + " - TESTES AUTOMATIZADOS QA"))
		;
	}
	
	@Test
	public void T05_retornaAsVariaveisDeUmDispositivo() {
		given()
		.when()
		.pathParam("id", ID_DISPOSITIVO)
		.get("buscar/opcoes-propriedades-dispositivo/{id}")
		.then()
		.statusCode(200)
		.body("itens.value",hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 27))
		.body("itens.label", hasItems("message", "log", "espid", "version", "resets", "rst_reason", "conn", "vbatt", "pbatt", "location", "voltalarm", "wifiip", "rssi", "ssid", "siglvl", "op", "imei", "imsi", "status", "active", "repo_active", "reactive", "spiffs", "spiff3"
))
		;
	}
	
	@Test
	public void T06_retornaAsVariaveisDeUmMedidor() {
		given()
		.when()
		.pathParam("id", MEDIDOR_ID)
		.get("buscar/opcoes-variaveis-medidor/{id}")
		.then()
		.statusCode(201);
	}	
	
	
	@Test
	public void T07_retornaEmListaOresultadoDaCategoriaInformada() {
		given()
		.when()
		.pathParam("categoria","Setor")
		.get("buscar/{categoria}/opcoes-pai")
		.then()
		.statusCode(201);
	}	
	
}
