package resources;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException

	{
		if(req == null) 
		{
		
		PrintStream logs = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(logs)).addFilter(ResponseLoggingFilter.logResponseTo(logs))
				.setContentType(ContentType.JSON).build();

		return req;
		}
		return req;

	}

	public static String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\OnlySelenium\\RestAssuredBDDFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);

	}
	
	public String getJsonPath(Response response,String key){
		
		String ressp = response.asString();
		JsonPath js = new JsonPath(ressp);
		return js.get(key).toString();
	}
	
}
