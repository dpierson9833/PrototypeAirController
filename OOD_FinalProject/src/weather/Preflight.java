package weather;


public class Preflight {

	public static void PreFlightStatus(String Condition) {
		
		if (Condition == "Clear") {
			//TakeoffProtocol
			System.out.println("Conditions are clear for takeoff");		
		} else {
			System.out.println("Ground planes. Conditions are not favorable for flight");
			DelayFlights.GroundFlights();
			//If conditions aren't favorable. Send to method that delays all flights 
		}
	}
}
