package br.com.curso.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class HelloWorld {
    public static void main(String[] args) {
                Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

                ValidatableResponse validatableResponse = response.then();
                validatableResponse.statusCode(200);
    }
}
