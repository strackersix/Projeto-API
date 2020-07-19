package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.DispositivosPage;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DispositivosTeste extends BaseTest{
	
	public static String DISPOSITIVO_CODE = br.com.utils.NumericRandon.Numeric.randomNumeric(8);
	public static Integer ID_DISPOSITIVO;
	

	
	
	@Test
	public void T1_incluirDispositivo() {
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
	    public void T2_listaDispositivoEspecifico() { 
	       given()
	      .when()
	      .pathParam("id", ID_DISPOSITIVO)
		  .get("/dispositivos/{id}")
		  .then() 
		  .statusCode(200)
		  .body("itens.id", Matchers.is(ID_DISPOSITIVO));
		  
		  }
	    
	    @Test 
	    public void T3_listaDispositivoPorCodigo() { 
	    	given()
	      .when()
	      .pathParam("code", DISPOSITIVO_CODE)
		  .get("/dispositivos/by-code/{code}")
		  .then() 
		  .statusCode(200)
		 . body("itens.codigo", Matchers.is(DISPOSITIVO_CODE));
		  
		  }
	    
	    @Test
	    public void T4_ListarTodosDispositivos() {
	    	given()
	    	.when()
	    	.get("/dispositivos")
	    	.then()
	    	.statusCode(200);
	    	assertThat(DISPOSITIVO_CODE, containsString(DISPOSITIVO_CODE));
	    }
	    
	    @Test
	    public void T5_ListaRegistroDeAlarme() {
	    	given()
	    	.when()
	    	.get("/dispositivos")
	    	.then()
	    	.statusCode(200)
	    	.body("itens.status.nome[0]", Matchers.is("Desconectado"));
	    	
	    }
	    
	    @Test
	    public void T6_AutorizarDispositoEnviarMensagensParaTelaDeProbe() {
	    	given()
	    	.when()
	    	.pathParam("code", DISPOSITIVO_CODE)
	    	.get("dispositivos/autorizar/{code}")
	    	.then()
	    	.statusCode(201)
	    	.body("codigo", Matchers.is(DISPOSITIVO_CODE));
	    }
	    
	    @Test
	    public void T7_AtualizarDispositivo() {
		 DispositivosPage atualizarDispositivo = new DispositivosPage();
		 atualizarDispositivo.setEquipamento_id(13);
		 atualizarDispositivo.setTipo_id(1);
		 atualizarDispositivo.setDescricao("TESTES ATUALIZADOS QA");
		 atualizarDispositivo.setConstante_medidor(1);
		 atualizarDispositivo.setRelacao_potencial(1);
		 atualizarDispositivo.setRelacao_corrente(1);
		 atualizarDispositivo.setDistribuidora_id(1);
		 	    	
	    	given()
			.body(atualizarDispositivo)
			 .when()
			  .pathParam("id", ID_DISPOSITIVO)
			 .put("/dispositivos/{id}")
			 .then()
			 .statusCode(200)
				 .body("itens.descricao", Matchers.is("TESTES ATUALIZADOS QA"));
	    }
	    
	    @Test
	    public void T8_RemoverDispositivo() {
	    	given()
	    	.when()
	    	.pathParam("id", ID_DISPOSITIVO)
	    	.delete("/dispositivos/{id}")
	    	.then()
	    	.statusCode(204)
	    	;
	    }
	
	    @Test
		public void T9_validarCamposObrigatorios() {
			
			given()
			 .when()
			 .post("/dispositivos")
			 .then()
			 .statusCode(422)
			 .body("descricao[0]", Matchers.is("O campo descricao é obrigatório."))
			 .body("constante_medidor[0]", Matchers.is("O campo constante medidor é obrigatório."))
			 .body("relacao_corrente[0]", Matchers.is("O campo relacao corrente é obrigatório."))
			 .body("relacao_potencial[0]", Matchers.is("O campo relacao potencial é obrigatório."))
			 .body("tipo_id[0]", Matchers.is("O campo tipo id é obrigatório."))
			 ;
			 
		}
	
	
}
