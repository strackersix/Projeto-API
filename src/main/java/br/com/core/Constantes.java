package br.com.core;

import io.restassured.http.ContentType;

public interface Constantes {
	
	String APP_BASE_URL = "https://api-qa.zordon.app/api/";
	//Integer APP_PORT = 443;
	String APP_BASE_PATH = "v3";
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	Long MAX_TIMEOUT = 5000L;

}
