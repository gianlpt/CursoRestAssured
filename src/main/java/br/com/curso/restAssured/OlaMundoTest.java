package br.com.curso.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class OlaMundoTest {

    @Test
    @DisplayName("Ola Mundo!")
    public void testOlaMundo() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        assertEquals("Ola Mundo!", response.getBody().asString());
    }

    @Test
    @DisplayName("Outras formas RestAssures")
    public void testOutrasFormas() {

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

    @Test
    public void devoConhecerMatchersHamcrest() {
        assertThat("Maria", Matchers.is("Maria"));
        assertThat(128, Matchers.is(128));
        assertThat(128, Matchers.isA(Integer.class));
        assertThat(128d, Matchers.isA(Double.class));
        assertThat(128d, Matchers.greaterThan(120d));
        assertThat(128d, Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1, 3, 5, 7, 9));
        assertThat(impares, containsInAnyOrder(1, 3, 5, 9, 7));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1, 5));

        assertThat("Maria", is(not("João")));
        assertThat("Maria", not("João"));
        assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
        assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
    }

    @Test
    public void validarBody(){
        given()// Pré-condições (headers, body, auth, etc.)
        .when() //Ação
            .get("https://restapi.wcaquino.me/ola")
        .then()// Validações
            .statusCode(200)
            .body(is("Ola Mundo!"))
            .body(containsString("Mundo"))
            .body(is(not(nullValue())));

    }



}

