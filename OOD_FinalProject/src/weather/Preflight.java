package weather;

import java.util.Scanner;

public class Preflight {

	public static boolean PreFlightStatus(Singleton.Weather Condition) {
		
		boolean OktoFly = false;
		
		if (Condition == Singleton.Weather.CLEAR) {
			
			OktoFly = true;
			
			System.out.println("Conditions are clear for takeoff");	
			
		} else {
			
			OktoFly = false;
			
			Scanner input = new Scanner(System.in);
			
			System.out.println("Weather conditions are severe. Would you like to ground all planes? Yes or No");
			
			String response = input.nextLine();
			
			if (response == "Yes") {
				DelayFlights.GroundFlights(OktoFly);
			} 
			//Else if no send to class that allows manual change of flights 
			
			System.out.println("Ground planes. Conditions are not favorable for flight");	
			
			input.close();
			
		}
		return OktoFly;
	}
}