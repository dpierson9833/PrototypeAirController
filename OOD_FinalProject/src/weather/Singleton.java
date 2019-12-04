package weather;

import java.util.Random;

public class Singleton {

	
	private static Singleton firstInstance = null;

	// Enum for 4 Seasons
	enum Season {
		WINTER, SPRING, SUMMER, FALL
	}
	
	// Enum for 2 weather conditions
	enum Weather {
		CLEAR, SEVERE_WEATHER
	}
	// Class to randomly choose a value
	
	class RandomEnumWeather<E extends Enum<Weather>> {
		Random RND = new Random();
		E[] values;

		public RandomEnumWeather(Class<E> token) {
			values = token.getEnumConstants();
		}

		public E randomWeather() {
			return values[RND.nextInt(values.length)];
		}

	}

	// **********************************************
	
	
	class RandomEnumSeason<E extends Enum<Season>> {
	
		Random RND = new Random();
		E[] values;
		
		public RandomEnumSeason(Class<E> token) {
			values = token.getEnumConstants();
		}

		public E randomSeason() {
			return values[RND.nextInt(values.length)];
		}

	}

	// List all the enums for season
	RandomEnumSeason<Season> r = new RandomEnumSeason<Season>(Season.class);

	// List all the enums for weather
	RandomEnumWeather<Weather> e = new RandomEnumWeather<Weather>(Weather.class);

	// Generates a random season
	
	Season Cseason = r.randomSeason();
	
	// Generates a random weather event
	Weather Cweather = e.randomWeather();
		
	boolean status = false;

	// Private singleton to prevent instantiation.
	private Singleton() {
	}

	public static Singleton getInstance() {

		if (firstInstance == null) {

			firstInstance = new Singleton();

		}

		return firstInstance;
	}
	
	
	public void setSeason(Season season1) {
		firstInstance.Cseason= season1;
	}

	public void setWeather(Weather weather1) {
		firstInstance.Cweather= weather1;
	}
	
	public static Season getSeason() {
		return firstInstance.Cseason;
	}

	
	public static Weather getWeather() {
		return firstInstance.Cweather;
	}

//	int newWeather;	
	String weatherCondition;
	
	public String getCurrentCondition(Weather condition) {
		
		String currentCondition = GetCurrentWeather.getCondition(condition);
		
		weatherCondition = currentCondition;
		
		return weatherCondition;
	}

//
	public boolean PreFlight() {
		return status = Preflight.PreFlightStatus(Cweather);	
	}
//


}
