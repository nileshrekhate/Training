package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@RunWith(Cucumber.class)

public class StepDefinition extends Utils {
	RequestSpecification resp;
	ResponseSpecification res;
	Response response;
	static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String langauge, String address) throws IOException {

		resp = given().spec(requestSpecification()).body(data.addPlacePayload(name, langauge, address));

	}

	@When("User calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String httpmethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);

		res = new ResponseSpecBuilder().expectStatusCode(200).build();

		if (httpmethod.equalsIgnoreCase("POST"))
			response = resp.when().post(resourceAPI.getResources());

		else if (httpmethod.equalsIgnoreCase("GET"))
			response = resp.when().get(resourceAPI.getResources());

	}

	@Then("^the API call got success with StatusCode 200$")
	public void the_api_call_got_success_with_statuscode_200() throws Throwable {

		assertEquals(response.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String keyValue, String ExpectedValue) throws Throwable {

		assertEquals(getJsonPath(response, keyValue), ExpectedValue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		place_id = getJsonPath(response, "place_id");
		resp = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_using_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);

	}

	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		
		resp = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}

}