package com.hepsiBurada.step_definitions;

import com.hepsiBurada.utilities.ConfigurationReader;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
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
        int id = 2;
        response = given().accept(ContentType.JSON)
                .get("pet/"+id);

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("response code does not match", 200, response.statusCode());

    }

    @When("Test Post Request")
    public void testPostRequest() {

        int id = 111;
        JSONObject json = new JSONObject();
        json.put("petId", id);
        json.put("name", "Max");
        json.put("status", "available");

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json.toString())
                .post("pet");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("response code does not match", 200, response.statusCode());
    }
}
