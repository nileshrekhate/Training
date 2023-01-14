package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.Location;

public class TestDataBuild {

	public AddPlacePojo addPlacePayload(String name, String langauge, String address)
	{
		AddPlacePojo place = new AddPlacePojo();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(langauge);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("http://google.com");
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("Konark DS");
		typeList.add("Matlab");
			
		place.setTypes(typeList);
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		place.setLocation(loc);
		
		return place;
		
	}
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}
}
