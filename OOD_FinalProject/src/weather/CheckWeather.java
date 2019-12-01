package weather;


public class CheckWeather {
	
	public static void getCheckWeather() {
	
	Singleton newInstance = Singleton.getInstance();
	
	newInstance.getCurrentWeather(newInstance.getWeather(), newInstance.getSeason());
	
	newInstance.getCurrentCondition(newInstance.getWeather());
	
	newInstance.DisplayCurrentWeather();
	
	newInstance.PreFlight();
	
	}
}
