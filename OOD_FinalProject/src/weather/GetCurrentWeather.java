package weather;
import java.util.Random;

import weather.Singleton.Season;

public class GetCurrentWeather {

//Returns string of Clear or Severe 
	public static String getCondition(Singleton.Weather weatherCondition) {
		String status;

		if (weatherCondition == Singleton.Weather.CLEAR) {

			status = "Clear";
			System.out.println("*TEST1* Should return clear");

		} else {

			status = "Severe";
			System.out.println("*TEST2* Should return severe");
		}

		return status;
	}
	
	
//Returns string of weather value	
	public static String getWeatherString(int weather) {
		
		String weatherString; 
		
		weatherString = Integer.toString(weather);
		
		return weatherString;
		
	}
	
//int return type 
	public static int getWeather(Singleton.Weather weather12, Singleton.Season season) {

		int finalWeather = 0;
		//String variable that will be sent to PreFlightStatus function 

		Singleton.Season Cseason = season;

		Singleton.Weather weather1 = weather12;
		
		//if statement to keep weather as winter
		if (Cseason!=Season.WINTER) {
			Cseason = Season.WINTER;
		}

		Random randomGenerator = new Random();

		int lowTemp = 0;

		int highTemp = 0;

		int result = 0;

		switch (Cseason) {

		case WINTER:

			switch (weather1) {

			case CLEAR:

				lowTemp = 10;
				
				highTemp = 76;
				
				System.out.println("Weather conditions are clear.");

				break;

			case SEVERE_WEATHER:

				lowTemp = -5;
				
				highTemp = 0;
				
				System.out.println("Warning! Weather conditions are severe.");

				break;
			}
			result = randomGenerator.nextInt(highTemp - lowTemp) + lowTemp;
			
			System.out.println(result + " degrees");
			
			finalWeather = result;

			break;

		case SPRING:

			switch (weather1) {

			case CLEAR:

				lowTemp = 70;
				
				highTemp = 99;
				
				System.out.println("Weather conditions are clear.");

				break;

			case SEVERE_WEATHER:

				lowTemp = 70;
				
				highTemp = 95;
				
				System.out.println("Warning! Weather conditions are severe.");

				break;
			}
			result = randomGenerator.nextInt(highTemp - lowTemp) + lowTemp;
			
			System.out.println(result + " degrees");
			
			finalWeather = result;
			
			break;
		case SUMMER:

			switch (weather1) {

			case CLEAR:

				lowTemp = 80;
				
				highTemp = 110;
				
				System.out.println("Weather conditions are clear.");

				break;

			case SEVERE_WEATHER:

				lowTemp = 85;
				
				highTemp = 100;
				
				System.out.println("Warning! Weather conditions are severe.");

				break;
			}
			result = randomGenerator.nextInt(highTemp - lowTemp) + lowTemp;
			
			System.out.println(result + " degrees");
			
			finalWeather = result;
			
			break;

		case FALL:

			switch (weather1) {

			case CLEAR:
				lowTemp = 75;
				
				highTemp = 90;
				
				System.out.println("Weather is clear for takeoff");

				break;
			case SEVERE_WEATHER:

				lowTemp = 75;
				
				highTemp = 95;
				
				System.out.println("Warning! Weather conditions are severe.");
				
				break;
			}
			result = randomGenerator.nextInt(highTemp - lowTemp) + lowTemp;
			
			System.out.println(result + " degrees");
			
			finalWeather = result;
			
			break;
		} 
		return finalWeather;
		
		} 
	}

