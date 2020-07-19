package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.AcoesPage;
import br.com.pages.DispositivosPage;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcoesTeste extends BaseTest{

	

	public static String DISPOSITIVO_CODE = br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static String URL = "www."+br.com.utils.NumericRandon.Numeric.randomNumeric(8)+".com.br";
	public static Integer ID_DISPOSITIVO;
	

	
	
	@Test
	public void T01_incluirDispositivo() {
		DispositivosPage incluirDispositivo = new DispositivosPage();
		incluirDispositivo.setCodigo(DISPOSITIVO_CODE);
		incluirDispositivo.setTipo_id(1);
		incluirDispositivo.setDescricao("TESTES AUTOMATIZADOS QA");
		incluirDispositivo.setConstante_medidor(2);
		incluirDispositivo.setRelacao_potencial(2);
		incluirDispositivo.setRelacao_corrente(2);
		incluirDispositivo.setDistribuidora_id(1);
		
		ID_DISPOSITIVO = given()
		 .body(incluirDispositivo)
		 .when()
		 .post("/dispositivos")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
		 
	}
	
	@Test
	public void T02_incluirNomeDoRepositorioDoFirmware() {
		
		AcoesPage incluirAcao = new AcoesPage();
		incluirAcao.setUrl(URL);
		       given()
				 .body(incluirAcao)
				 .when()
				 .pathParam("dispositivo", DISPOSITIVO_CODE)
				 .post("/acoes/{dispositivo}/salvar-repositorio")
				 .then()
				 .statusCode(201)
				 .body("itens.codigo", Matchers.is(DISPOSITIVO_CODE))
				 .body("itens.url_firmware", Matchers.is(URL))
				 ;
				
		
	}
	
	@Test
	public void T03_atualizarFirmwareDoDispositivo() {
		
		       given()
				 .when()
				 .pathParam("dispositivo", DISPOSITIVO_CODE)
				 .post("/acoes/{dispositivo}/atualizar-firmware")
				 .then()
				 .statusCode(200)
				 .body("message", Matchers.is("Solicitação de atualização de firmware realizada com sucesso"))
				 ;
				
		
	}
	
	@Test
	public void T04_enviarTodosOsDadosDoDispositivo() {
		
		       given()
				 .when()
				 .pathParam("dispositivo", DISPOSITIVO_CODE)
				 .post("/acoes/{dispositivo}/receber-dados")
				 .then()
				 .statusCode(200)
				 .body("message", Matchers.is("Solicitação de recebimento dos dados realizada com sucesso"))
				 ;
				
		
	}
	
	@Test
	public void T05_receberLatitudeElongitudeDoDispositivo() {
		
		       given()
				 .when()
				 .pathParam("dispositivo", DISPOSITIVO_CODE)
				 .post("/acoes/{dispositivo}/receber-coordenadas")
				 .then()
				 .statusCode(200)
				 .body("message", Matchers.is("Solicitação de recebimento das coordenadas realizada com sucesso"))
				 ;
				
		
	}
	
}
