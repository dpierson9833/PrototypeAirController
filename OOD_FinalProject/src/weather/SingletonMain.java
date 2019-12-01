package weather;


public class SingletonMain {

	public static void main(String[] args) {

		Singleton newInstance = Singleton.getInstance();

		System.out.println(newInstance.getCurrentWeather(newInstance.getWeather(), newInstance.getSeason()));

	}

}
