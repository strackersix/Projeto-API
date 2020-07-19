package br.com.rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.core.BaseTest;
import br.com.pages.EmpresaPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresasTeste extends BaseTest{
	
	
	public String EMPRESA_NAME = "EMPRESA QUALITY ASSURANCE AUTOMAÇÃO " + System.nanoTime();
	public static String ALTERA_EMPRESA_NAME = "EMPRESA QUALITY ASSURANCE AUTOMAÇÃO " + System.nanoTime();
	public String EMAIL = "qa@" + System.nanoTime();
	public static Integer ID_EMPRESA;
	
	
	@Test
	public void T1_incluirEmpresa() {
		EmpresaPage  incluirEmpresa = new EmpresaPage();
		incluirEmpresa.setNome(EMPRESA_NAME);
		incluirEmpresa.setContato_nome("Phelipe");
		incluirEmpresa.setContato_fixo("11111111111");
		incluirEmpresa.setContato_celular("11992062360");
		incluirEmpresa.setContato_email(EMAIL);
		incluirEmpresa.setEndereco("Rua Ministro Jesuino Cardoso");
		incluirEmpresa.setNumero(314);
		incluirEmpresa.setBairro("Vila Nova Conceição");
		incluirEmpresa.setCidade("São Paulo");
		
		
		
		ID_EMPRESA = given()
		 .body(incluirEmpresa)
		 .when()
		 .post("/empresas")
		 .then()
		 .statusCode(201)
		 .extract().path("itens.id");
	}
	
	@Test
	public void T2_alterarEmpresa() {
		EmpresaPage alterarNomeEmpresa = new EmpresaPage();
		alterarNomeEmpresa.setNome(ALTERA_EMPRESA_NAME);
		alterarNomeEmpresa.setContato_nome("Phelipe Alterado");
		alterarNomeEmpresa.setContato_fixo("12345678901");
		alterarNomeEmpresa.setContato_celular("12345678901");
		alterarNomeEmpresa.setContato_email(EMAIL);
		alterarNomeEmpresa.setEndereco("Rua Ministro Jesuino Cardoso Alterado");
		alterarNomeEmpresa.setNumero(413);
		alterarNomeEmpresa.setBairro("Vila Nova Conceição Alterado");
		alterarNomeEmpresa.setCidade("Alterado");
		
		given()
		.body(alterarNomeEmpresa)
		.when()
		.pathParam("id", ID_EMPRESA)
		.put("/empresas/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ALTERA_EMPRESA_NAME))
		.body("itens.contato_nome", Matchers.is("Phelipe Alterado"))
		.body("itens.contato_fixo", Matchers.is("12345678901"))
		.body("itens.contato_celular", Matchers.is("12345678901"))
		.body("itens.contato_email", Matchers.is(EMAIL))
		.body("itens.endereco", Matchers.is("Rua Ministro Jesuino Cardoso Alterado"))
		.body("itens.numero", Matchers.is(413))
		.body("itens.bairro", Matchers.is("Vila Nova Conceição Alterado"))
		.body("itens.cidade", Matchers.is("Alterado"))
		;
	}
	
	@Test
	public void T3_listaEmpresaEspecifica() {
		given()
		.when()
		.pathParam("id", ID_EMPRESA)
		.get("/empresas/{id}")
		.then()
		.statusCode(200)
		.body("itens.nome", Matchers.is(ALTERA_EMPRESA_NAME));
		
	}
	
	@Test
	public void T4_listaEmpresa() {
		given()
		.when()
		.pathParam("id", ID_EMPRESA)
		.get("/empresas/{id}")
		.then()
		.statusCode(200);
		assertThat(ALTERA_EMPRESA_NAME, containsString(ALTERA_EMPRESA_NAME));
		
	}
	
	@Test
	public void T5_deletaEmpresa() {
		given()
		.when()
		.pathParam("id", ID_EMPRESA)
		.delete("/empresas/{id}")
		.then()
		.statusCode(204)
		;
		
	}
	
	@Test
	public void T6_validarCamposObrigatorios() {
		
		given()
		 .when()
		 .post("/empresas")
		 .then()
		 .statusCode(422)
		 .body("nome[0]", Matchers.is("O campo nome é obrigatório."));
	}
	

}
