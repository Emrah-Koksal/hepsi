package com.hepsiBurada.step_definitions;

import com.hepsiBurada.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class ApiSteps {

    Response response;
    @When("Get the pet with the id of {int}")
    public void getThePetWithTheIdOf(int id) {
        response = given().accept(ContentType.JSON)
                .get("pet/"+id);

        response.prettyPrint();
    }

    @Then("Verify response status code as {int}")
    public void verifyResponseStatusCodeAsStatusCode(int expectedStatusCode) {
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("response code does not match", expectedStatusCode, response.statusCode());
    }

    @Then("Assert API results with the info of Pet's {string} , {string}")
    public void assertAPIResultsWithTheInfoOfPetS(String expectedName, String expectedStatus) {
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("name");
        String actualStatus = jsonPath.getString("status");
        System.out.println("actualName = " + actualName);
        System.out.println("actualStatus = " + actualStatus);
        Assert.assertEquals("name does not match",expectedName, actualName );
        Assert.assertEquals("status does not match",expectedStatus, actualStatus );
    }

    @When("Test Get Request")
    public void testGetRequest() {


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

    }


    @When("Post the pet with the id of {int} {String} {String}")
    public void postThePetWithTheIdOfId(int id, String name, String status) {
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

    }


    @When("Post the pet with the id of {int} {string}")
    public void postThePetWithTheIdOf(Integer id, String name, String status) {
        JSONObject json = new JSONObject();
        json.put("petId", id);
        json.put("name", "name");
        json.put("status", status);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json.toString())
                .post("pet");

        response.prettyPrint();
    }

    @Then("Verify response body {string} and {string}")
    public void verifyResponseBodyAnd(String expectedType, String expectedMessage) {

        JsonPath jsonPath = response.jsonPath();
        String actualType = jsonPath.getString("type");
        String actualMessage = jsonPath.getString("message");
        System.out.println("actualtype = " + actualType);
        System.out.println("actualmessage = " + actualMessage);
        Assert.assertEquals("name does not match",expectedType, actualType );
        Assert.assertEquals("status does not match",expectedMessage, actualMessage );
    }

    @When("Post the pet {int}, and name as {string} and status as {string}")
    public void postThePetIdAndNameAsAndStatusAs(int id, String name, String status) {
        JSONObject json = new JSONObject();
        json.put("petId", id);
        json.put("name", name);
        json.put("status", status);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json.toString())
                .post("pet");

        response.prettyPrint();
    }
}
