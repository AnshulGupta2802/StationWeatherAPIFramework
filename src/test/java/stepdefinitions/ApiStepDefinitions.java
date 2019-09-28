package stepdefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import openweathermap.RegisterStationRequest;
import openweathermap.RegisterStationResponse;
import utils.DataUtil;
import init.ApiInit;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApiStepDefinitions {

	public static Response response;
	public static List<String> idList = new ArrayList<String>();
		
	@Given("^I want to execute openweathermap API$")
	public void i_want_to_execute_openweathermap_API() throws Throwable {
		RestAssured.baseURI = ApiInit.getBaseUri();
	}
	
	@When("^I submit a post request with parameters <external_id> and <name> and <latitude> and <longitude> and <altitude> without an API key$")
	public void i_submit_a_post_request_with_parameters_external_id_and_name_and_latitude_and_longitude_and_altitude_without_an_API_key(DataTable dataTable) throws Throwable {
	HashMap<String,String> data = DataUtil.convertDataTableToMap(dataTable).get(0);
	RegisterStationRequest registerStationReq = new RegisterStationRequest();
	registerStationReq.setExternal_id(data.get("external_id"));
	registerStationReq.setName(data.get("name"));
	registerStationReq.setLatitude(Double.parseDouble(data.get("latitude")));
	registerStationReq.setLongitude(Double.parseDouble(data.get("longitude")));
	registerStationReq.setAltitude(Integer.parseInt(data.get("altitude")));
	response = RestAssured.given().contentType("application/json").body(registerStationReq).post();
	}	
	
	@Then("^I validate the <messageBody> of the response$")
	public void i_validate_the_messageBody_of_the_response(DataTable dataTable) throws Throwable {
		HashMap<String,String> data = DataUtil.convertDataTableToMap(dataTable).get(0);
		Assert.assertEquals("Proper message is not displayed on performing the operation",data.get("messageBody"), response.getBody().asString());
	}
	
	@When("^I submit a post request with parameters <external_id> and <name> and <latitude> and <longitude> and <altitude> with API key$")
	public void i_submit_a_post_request_with_parameters_external_id_and_name_and_latitude_and_longitude_and_altitude_with_API_key(DataTable dataTable) throws Throwable {
		for (int i=0;i<2;i++) {
		HashMap<String,String> data = DataUtil.convertDataTableToMap(dataTable).get(i);
		RegisterStationRequest registerStationReq = new RegisterStationRequest();
		registerStationReq.setExternal_id(data.get("external_id"));
		registerStationReq.setName(data.get("name"));
		registerStationReq.setLatitude(Double.parseDouble(data.get("latitude")));
		registerStationReq.setLongitude(Double.parseDouble(data.get("longitude")));
		registerStationReq.setAltitude(Integer.parseInt(data.get("altitude")));
		response = RestAssured.given().queryParam("APPID",ApiInit.getApiKey()).contentType("application/json").body(registerStationReq).post();
		}
	}
	

	@Then("^I validate the correct HTTP <responseCode>$")
	public void i_validate_the_correct_HTTP_responseCode(DataTable dataTable) throws Throwable {
		HashMap<String,String> data = DataUtil.convertDataTableToMap(dataTable).get(0);
		Assert.assertEquals("Correct status code is not returned", response.getStatusCode(), Integer.parseInt(data.get("responseCode")));
	}

	@When("^I submit a Get stations request with API key$")
	public void i_submit_a_Get_stations_request_with_API_key() throws Throwable {
		response = RestAssured.given().queryParam("APPID",ApiInit.getApiKey()).contentType("application/json").request(Method.GET); 
	}
	
	@Then("^I validate the values are same as in the registration request$")
	public void i_validate_the_values_are_same_as_in_the_registration_request(DataTable dataTable) throws Throwable {
		RegisterStationResponse[] responseBody = response.getBody().as(RegisterStationResponse[].class);
		for (int i=0;i<responseBody.length;i++) {
			HashMap<String,String> data = DataUtil.convertDataTableToMap(dataTable).get(i);
			Assert.assertEquals("Incorrect external_id in registered station number "+(i+1),responseBody[i].getExternal_id(),data.get("external_id"));
			Assert.assertEquals("Incorrect name in registered station number "+(i+1),responseBody[i].getName(),data.get("name"));
			Assert.assertEquals("Incorrect latitude in registered station "+(i+1),responseBody[i].getLatitude().doubleValue(),Double.parseDouble(data.get("latitude")),0);
			Assert.assertEquals("Incorrect longitude in registered station "+(i+1),responseBody[i].getLongitude().doubleValue(),Double.parseDouble(data.get("longitude")),0);
			Assert.assertEquals("Incorrect altitude in registered station "+(i+1),responseBody[i].getAltitude(),Integer.parseInt(data.get("altitude")),0);
			//Storing the id of registered stations, to be used for deleting stations
			idList.add(responseBody[i].getId());
		}
		
	}

	@When("^I submit a delete request with an API key$")
	public void i_submit_a_delete_request_with_an_API_key() throws Throwable {
		for (String id:idList){
			response = RestAssured.given().queryParam("APPID",ApiInit.getApiKey()).contentType("application/json").delete("/"+id);
		}	
 
	}
	
}

