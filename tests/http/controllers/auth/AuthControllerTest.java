package http.controllers.auth;

import app.BaseTest;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AuthControllerTest extends BaseTest {

	@Test
	public void validLogin() throws Exception {
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("userName", "hax");
		jsonAsMap.put("password", "frækfyr69");

		given()
			.contentType(ContentType.JSON)
			.body(jsonAsMap)
		.when()
			.post("/authentication/login")
		.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("status", is(200))
			.body("message", notNullValue());
	}

	@Test
	public void invalidLogin() throws Exception {
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("userName", "invalid");
		jsonAsMap.put("password", "error!");

		given()
			.contentType(ContentType.JSON)
			.body(jsonAsMap)
		.when()
			.post("/authentication/login")
		.then()
			.statusCode(401)
			.contentType(ContentType.JSON);
	}

	@Test
	public void missingPassword() throws Exception {
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("userName", "invalid");

		given()
			.contentType(ContentType.JSON)
			.body(jsonAsMap)
		.when()
			.post("/authentication/login")
		.then()
			.statusCode(401)
			.contentType(ContentType.JSON);
	}
}