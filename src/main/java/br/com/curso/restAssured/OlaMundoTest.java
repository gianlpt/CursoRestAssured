package br.com.curso.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


public class OlaMundoTest {

    @Test
    @DisplayName("Ola Mundo!")
    public void testOlaMundo(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    @DisplayName("Outras formas RestAssures")
    public void testOutrasFormas(){

        RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);

    }


        @Test
        @DisplayName("Modo Fluente!")
        public void modoFluente() {

            given()// Pré-condições (headers, body, auth, etc.)
            .when() //Ação
                .get("https://restapi.wcaquino.me/ola")
            .then()// Validações
                .statusCode(200);
        }
    }

