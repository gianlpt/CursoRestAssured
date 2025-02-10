package br.com.curso.restAssured;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserJsonTest {

    @Test
    public void verificacoPrimeiroNivel(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/users/1")
        .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", containsString("Silva"));
    }

    @Test
    public void verificacoPrimeiroNivelNew(){
        Response response = request(Method.GET,"https://restapi.wcaquino.me/users/1");

        //path
        assertEquals(1, (Integer) response.path("id"));


        //jsonpath
        JsonPath jsonPath =  new JsonPath(response.asString());
        assertEquals(1, jsonPath.getInt("id"));
    }

    @Test
    public void valoresSegundoNivel(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/users/2")
        .then()
            .statusCode(200)
            .body("id", is(2))
            .body("name", containsString("Joaquina"))
            .body("endereco.rua", is("Rua dos bobos"));
    }

    @Test
    public void verificarLista() {
        given()
        .when()
                .get("https://restapi.wcaquino.me/users/3")
        .then()
            .statusCode(200)
            .body("id", is(3))
            .body("name", containsString("Ana"))
            .body("filhos", hasSize(2))
            .body("filhos[0].name", is("Zezinho"))
            .body("filhos.name", hasItems("Luizinho"));

    }
}
