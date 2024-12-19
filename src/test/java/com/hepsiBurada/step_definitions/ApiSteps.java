package com.hepsiBurada.step_definitions;

import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;


public class ApiSteps {

    Response response;

    @When("Test Get Request")
    public void testGetRequest() {
        response = given().accept(ContentType.JSON)
                .get("/gen/clients/ada");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("response code does not match",200, response.statusCode());

    }

    @When("Test Post Request")
    public void testPostRequest() {

        JSONObject json = new JSONObject();
        json.put("key1", "value1");
        json.put("key2", 123);
        json.put("key3", true);

        response = given().accept(ContentType.JSON)
                .body(json)
                .post("/gen/servers/spring");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("response code does not match",200, response.statusCode());
    }
}
