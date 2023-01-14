Feature: Validation of AddPlaceAPI

@AddPlace
Scenario Outline: To verify if the place is being added successfully using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" using "POST" http request
	Then the API call got success with StatusCode 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name 	 | language | address 			 |
	|Michell | English 	| World Trade Center |
#	|Daniel  | French 	| Sea Cross Center 	 |

@DeletePlace
Scenario: Verify if Delete Place functionality is working 
	Given Delete place payload
	When User calls "deletePlaceAPI" using "POST" http request
	Then the API call got success with StatusCode 200
	And "status" in response body is "OK"