package weather;

import java.util.Random;

public class GetCurrentWeather {

	public static String getWeather(Singleton.Weather weather12, Singleton.Season season) {
		
		//String variable that will be sent to PreFlightStatus function 
		String conditionA;

		Singleton.Season Cseason = season;

		Singleton.Weather weather1 = weather12;

		switch (Cseason) {

		case WINTER:

			switch (weather1) {

			case CLEAR:
				Random randomGenerator = new Random();
				int lowW1 = 25;
				int highW1 = 51;
				int resultW1 = randomGenerator.nextInt(highW1 - lowW1) + lowW1;
				System.out.println(resultW1 + " degrees");
				System.out.println("Weather conditions are clear");
				return "clear";
			case SEVERE_WEATHER:
				Random randomGenerator1 = new Random();
				int lowW = -31;
				int highW = 0;
				int resultW = randomGenerator1.nextInt(highW - lowW) + lowW;
				System.out.println(resultW + " degrees");
				System.out.println("Warning weather conditions are severe. Blizzard approaching");
				return "severe";
			}
			break;

		case SPRING:

			switch (weather1) {

			case CLEAR:
				Random randomGeneratorSP = new Random();
				int lowSp = 70;
				int highSp = 99;
				int resultSp = randomGeneratorSP.nextInt(highSp - lowSp) + lowSp;
				System.out.println(resultSp + " degrees");
				System.out.println("Weather conditions are clear");
				return "clear";
			case SEVERE_WEATHER:
				Random randomGeneratorS = new Random();
				int lowS = 70;
				int highS = 95;
				int resultS = randomGeneratorS.nextInt(highS - lowS) + lowS;
				System.out.println(resultS + " degrees");
				System.out.println("Weather is too severe for takeoff. Hurricane approaching");
				return "severe";
			}
			break;

		case SUMMER:

			switch (weather1) {

			case CLEAR:
				Random randomGeneratorSu = new Random();
				int lowSu = 80;
				int highSu = 110;
				int resultSu = randomGeneratorSu.nextInt(highSu - lowSu) + lowSu;
				System.out.println(resultSu + " degrees");
				System.out.println("Weather is clear for takeoff");
				return "clear";
			case SEVERE_WEATHER:
				Random randomGeneratorSum = new Random();
				int lowSum = 85;
				int highSum = 100;
				int resultSum = randomGeneratorSum.nextInt(highSum - lowSum) + lowSum;
				System.out.println(resultSum + " degrees");
				System.out.println("Weather is too severe for takeoff. Tornado approaching");
				return "severe";
			}
			break;

		case FALL:

			switch (weather1) {

			case CLEAR:
				Random randomGeneratorFa = new Random();
				int lowFa = 75;
				int highFa = 90;
				int resultFa = randomGeneratorFa.nextInt(highFa - lowFa) + lowFa;
				System.out.println(resultFa + " degrees");
				System.out.println("Weather is clear for takeoff");
				return "clear";
			case SEVERE_WEATHER:
				Random randomGeneratorFall = new Random();
				int lowFall = 75;
				int highFall = 95;
				int resultFall = randomGeneratorFall.nextInt(highFall - lowFall) + lowFall;
				System.out.println(resultFall + " degrees");
				System.out.println("Weather is too severe for takeoff. Hurricane approaching");
				return "severe";
			}
			break;
		}
		if (weather1 == Singleton.Weather.CLEAR) {
			conditionA = "Clear";
		} else {
			conditionA = "Severe";
		}
		Preflight.PreFlightStatus(conditionA);
		
		return "error";
	}

}
