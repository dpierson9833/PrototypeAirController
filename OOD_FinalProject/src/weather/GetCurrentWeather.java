package weather;
import java.util.Random;

public class GetCurrentWeather {


	public static String getCondition(Singleton.Weather weatherCondition) {
		String status;

		if (weatherCondition == Singleton.Weather.CLEAR) {

			status = "Clear";

		} else {

			status = "Severe";
		}

		return status;
	}

	public static int getWeather(Singleton.Weather weather12, Singleton.Season season) {

		int finalWeather = 0;
		
		//String variable that will be sent to PreFlightStatus function 

		Singleton.Season Cseason = season;

		Singleton.Weather weather1 = weather12;

		Random randomGenerator = new Random();

		int lowTemp = 0;

		int highTemp = 0;

		int result = 0;

		switch (Cseason) {

		case WINTER:

			switch (weather1) {

			case CLEAR:

				lowTemp = 25;
				
				highTemp = 51;
				
				System.out.println("Weather conditions are clear.");

				break;

			case SEVERE_WEATHER:

				lowTemp = -31;
				
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
