package weather;

import java.util.concurrent.TimeUnit;
//Command Pattern

public class DelayFlights {

	public static void GroundFlights() {
		//Change all current and future flights to "Delay" on interface.
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All flights grounded");
		
	}
	
}
