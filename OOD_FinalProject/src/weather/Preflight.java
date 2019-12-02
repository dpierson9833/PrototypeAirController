package weather;

public class Preflight {

	public static boolean PreFlightStatus(Singleton.Weather Condition) {
		
		boolean OktoFly = false;
		
		if (Condition == Singleton.Weather.CLEAR) {
			
			OktoFly = true;
			
			
		} else {
			
			OktoFly = false;
			
		}
		return OktoFly;
	}
}