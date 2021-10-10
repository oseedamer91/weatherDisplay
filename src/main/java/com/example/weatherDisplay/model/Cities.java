package com.example.weatherDisplay.model;

import java.util.List;

public class Cities {
	
	private String apiVersion;
	private String key;
	private List<City> cities;
	
	public Cities(String api, String key, List<City> arrCities) {
		super();
		this.apiVersion = api;
		this.key = key;
		this.cities = arrCities;
	}

	public String getApi() {
		return apiVersion;
	}

	public void setApi(String api) {
		this.apiVersion = api;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<City> getArrCities() {
		return cities;
	}

	public void setArrCities(List<City> arrCities) {
		this.cities = arrCities;
	}
	
	
}
