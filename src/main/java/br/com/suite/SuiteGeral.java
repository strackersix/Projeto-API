package br.com.suite;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.core.BaseTest;
import br.com.rest.test.AcoesTeste;
import br.com.rest.test.AlarmesTeste;
import br.com.rest.test.AutenticacaoTeste;
import br.com.rest.test.BuscarTestes;
import br.com.rest.test.ComentariosTeste;
import br.com.rest.test.ContatosTeste;
import br.com.rest.test.DispositivosTeste;
import br.com.rest.test.DistribuidoraTeste;
import br.com.rest.test.EmpresasTeste;
import br.com.rest.test.EquipamentosTeste;
import br.com.rest.test.MedidoresTeste;
import br.com.rest.test.PropriedadeTeste;
import br.com.rest.test.SetoresTeste;
import br.com.rest.test.TipoDeDispositivosTeste;
import br.com.rest.test.UsuariosTeste;
import io.restassured.RestAssured;

@RunWith(Suite.class)
@SuiteClasses({

	MedidoresTeste.class,
	BuscarTestes.class,
	TipoDeDispositivosTeste.class,
	SetoresTeste.class,
	EquipamentosTeste.class,
	EmpresasTeste.class,
	DistribuidoraTeste.class,
	UsuariosTeste.class,
	DispositivosTeste.class,
	PropriedadeTeste.class,
	ContatosTeste.class,
	ComentariosTeste.class,
	AlarmesTeste.class,
	AcoesTeste.class,
	AutenticacaoTeste.class,
	

})

public class SuiteGeral extends BaseTest {

	@BeforeClass
	public static void login() {
	
		Map<String, String> login = new HashMap<>();
		
		login.put("email", "phelipe.santos@comerc.com.br");
		login.put("password", "123456qa");
		
		String TOKEN = 
		
		given()
			.body(login)
		.when()
			.post("/login")
		.then()
			.statusCode(200)
			.extract()
			.path("token");
			RestAssured.requestSpecification.header("Authorization", "Bearer " + TOKEN);

	}

	@AfterClass
	public static void finaliza() {

	}

}
