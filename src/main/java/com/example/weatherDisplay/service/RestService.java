package com.example.weatherDisplay.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.weatherDisplay.model.Cities;
import com.example.weatherDisplay.model.City;
import com.example.weatherDisplay.utils.Utils;
import com.google.gson.Gson;



public class RestService {
	
	public static final String END_POINT = "http://interview.teridion.info:3000/cities";
	public static final String API_KEY = "46d1ce99bc87b62a79e45700450873ff";
	public static String WEATHER_API_CALL = "http://api.openweathermap.org/data/2.5/weather?q={city name}&appid=" + API_KEY;
	
	
	
	
	/* API 1 :  call to endpoint 
	 * return list of cities
	 */
	public static List<String> getCities() throws Exception {
		List<String> citiesArr = new ArrayList<String>();
		Gson g = new Gson();

		HttpResponse<String> response = createAPICall(END_POINT, 3);

		Cities cities = g.fromJson(response.body(), Cities.class);
		
		// loop through cities and retrieve without rank
		for(City c : cities.getArrCities()) {
			citiesArr.add(c.getName());
		}

		System.out.println(citiesArr.toString());
		//return new Gson().toJson(citiesArr);
		return citiesArr;
		
	}

	
	
	
	
	
	
	/* API 2 :  
	 */
	// make API Call to get city weather by city name
	public static String getCityWeather(String cityName, String weatherOrTemp) throws Exception {
		String  toReplace = "{city name}";
		WEATHER_API_CALL = WEATHER_API_CALL.replace(toReplace, cityName);
		HttpResponse<String> response = createAPICall(WEATHER_API_CALL, 3);
		String weatherOrTempVal = Utils.getPatternValue(response.body(), weatherOrTemp);
		System.out.println(cityName + ":" + weatherOrTempVal);
		WEATHER_API_CALL = WEATHER_API_CALL.replace(cityName, toReplace);
		return cityName + ":" + weatherOrTempVal;

	}
	
	
	// API 3 :
	// collect all cities and get their weather as well
	public static void collectAllCitiesWeathers() throws Exception {
		List<String> allCities = getCities();
		List<String> allCitiesWeather = new ArrayList<String>();
		
		for(String city : allCities) {
			allCitiesWeather.add(getCityWeather(city, "temp")); // get temprature
		}
		
	}

	
	public static HttpResponse<String> createAPICall(String apiUrl , int retries) throws Exception {
		HttpResponse<String> response = null;
	
		while(retries > 0) {

		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.header("accept", "application/json")
					.uri(URI.create(apiUrl.replace(" ", "%20"))) // fix space for city names
					.build();

			
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
				break;
				
			} catch (IOException e) {
				--retries;
				e.printStackTrace();
			} catch (InterruptedException e) {
				--retries;
				e.printStackTrace();
			}
		}
		
		if(response == null) throw new Exception("Failed API Call");
		
		return response;
	}

	

}
