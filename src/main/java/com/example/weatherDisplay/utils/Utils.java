package com.example.weatherDisplay.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
		// generate random number between 1s to 5s
		public static void generateRateLimitSleepTime() {
			int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1); 
			System.out.println("Sleep for " + randomNum + " sec");
			try {
				Thread.sleep(randomNum * 1_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public static String getPatternValue(String jsonObject , String patternType) {
        
		String endOfLine = ".*,";
		String res = "";
		
        Pattern pattern = Pattern.compile(patternType + endOfLine);
        Matcher matcher = pattern.matcher(jsonObject);
        if(matcher.find()) {
        	res = matcher.group(0).split(":")[1].replace(",","");
        	res = res.replace("\"feels_like\"","");
        	res = res.replace("\"icon\"", "");
        }
        
        return res;
       
       
	}
}
