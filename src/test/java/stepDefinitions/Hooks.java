package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinition stepDef = new StepDefinition();
		if(StepDefinition.place_id==null)
		{
			stepDef.add_place_payload_with("Michale", "German", "HANGARI");
			stepDef.user_calls_using_http_request("AddPlaceAPI", "POST");
			stepDef.verify_place_id_created_maps_to_using("Michale", "getPlaceAPI");
		}
		
	}
}
