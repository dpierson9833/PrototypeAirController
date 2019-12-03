package weather;

import java.util.Scanner;

//Command Pattern
//Class not working at the moment 
public class DelayFlights {
	
	public static void checkFlightStatus(boolean OktoFly) {
		
		if (OktoFly = false) {
			
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
	}
	
	public static void GroundFlights(boolean OktoFly) {
		
		// Change all current and future flights to "Delay" on interface.
		if (OktoFly = true) {
			System.out.println("True");
		} else {
			System.out.println("All flights grounded");
			
		}
	}

}
