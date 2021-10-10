package com.example.weatherDisplay;



import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.weatherDisplay.service.RestService;
import com.example.weatherDisplay.utils.Utils;


@SpringBootApplication
public class WeatherDisplayApplication {

	public static final String END_POINT = "http://interview.teridion.info:3000/cities";
	public static final String API_KEY = "46d1ce99bc87b62a79e45700450873ff";
	public static String WEATHER_API_CALL = "http://api.openweathermap.org/data/2.5/weather?q={city name}&appid=" + API_KEY;
	
	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(WeatherDisplayApplication.class, args);

		
		 // creates an object of Scanner
	    Scanner input = new Scanner(System.in);
	    boolean readInput = true;
	    
	    System.out.println("1. get all cities");
	    System.out.println("2. get city weather: <city name>");
	    System.out.println("3. display all cities's  weather");
	    System.out.println("4. exit");
	    
	    while(readInput) {
	    	
	    	System.out.println("Choose api test option: ");
	    	input = new Scanner(System.in);
	    	
		    switch(input.nextInt()) {
			    case 1:
			    	Utils.generateRateLimitSleepTime(); // sleep
			    	RestService.getCities();
			    	break;
			    case 2:
			    	Utils.generateRateLimitSleepTime(); // sleep
			    	RestService.getCities();
			    	System.out.println("Type city name from list above: ");
			    	Scanner cityName = new Scanner(System.in);
			    	Utils.generateRateLimitSleepTime(); // sleep
			    	RestService.getCityWeather(cityName.nextLine(), "description");
			    	break;
			    case 3:
			    	Utils.generateRateLimitSleepTime(); // sleep
			    	RestService.collectAllCitiesWeathers();
			    	break;
			    case 4:
			    	readInput = false;
			    	System.out.println("exiting App... Bye");
			    	System.exit(1);
			    	break;
	    	
	    	}
	    }
		
		
	}

}
