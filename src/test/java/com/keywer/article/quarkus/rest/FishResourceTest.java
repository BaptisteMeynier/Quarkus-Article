package com.keywer.article.quarkus.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FishResourceTest {

    @Test
    public void it_should_return_value_for_family_with_fishs() {
        given()
          .when().get("/shop/fish/family/Cichlidae")
          .then()
             .statusCode(200)
             .body(containsString("4"));
    }

}