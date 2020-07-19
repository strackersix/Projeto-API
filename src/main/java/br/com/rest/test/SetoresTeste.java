package br.com.rest.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers;

import br.com.core.BaseTest;
import br.com.pages.SetorPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetoresTeste extends BaseTest{
	
	
	private static String SETOR_NAME = "Setor Testes automatizados " + System.nanoTime();
	public static Integer ID_SETOR;

   

    @Test
    public void T1_IncluirSetorComSucesso() {
    	SetorPage incluirSetor = new SetorPage();
    	incluirSetor.setNome(SETOR_NAME);
    	//incluirSetor.setIdExterno(1);
    	incluirSetor.setEmpresaId(2);
    	//incluirSetor.setSetorPai(1);
    	
    	ID_SETOR =  given()
    	        .body(incluirSetor)
    	        .when()
    	        .post("/setores")
    	        .then()
    	        .statusCode(201)
                .extract().path("itens.id");
    }
    

    @Test
    public void T2_lterarSetorComSucesso() {
    	SetorPage alteraSetor = new SetorPage();
    	alteraSetor.setNome(SETOR_NAME);
        given()
        .body(alteraSetor)
        .when()
        .pathParam("id",ID_SETOR)
        .put("/setores/{id}")
        .then()
        .statusCode(200)
        .body("itens.nome", Matchers.is	(SETOR_NAME));
    }

    @Test
    public void T3_ListarSetorEspecifico() {
        given()
        .when()
        .pathParam("id", ID_SETOR)
        .get("/setores/{id}")
        .then()
        .statusCode(200)
        .body("itens.nome", Matchers.is(SETOR_NAME));
    }
    
    @Test
    public void T4_DeletarSetor() {
        given()
        .when()
        .pathParam("id", ID_SETOR)
        .delete("/setores/{id}")
        .then()
        .statusCode(204);
    }
    
    @Test
    public void T5_validaCamposObrigatorio() {
    	
    	         given()
    	        .when()
    	        .post("/setores")
    	        .then()
    	        .statusCode(422)
    	        .body("nome[0]", Matchers.is("O campo nome é obrigatório."))
    	        ;
    }

   
    

}
