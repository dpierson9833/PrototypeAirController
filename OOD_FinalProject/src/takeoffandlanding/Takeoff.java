package takeoffandlanding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import gates.GateManipulator;
import gates.Gates;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import scheduler.PersistentTime;
import weather.GetCurrentWeather;
import weather.Singleton;

/**
 * this class checks to prerequisite conditions for flights that are taking off
 * and sends a list of eligible flights to the TakeoffFrame to be instantiated as radio buttons
 *
 *
 *
 *
 * NOTE: Checking of weather has been moved to the TakeOffFrame to make sure that no flights are launched
 * 
 * @author Will
 * 
 */
public class Takeoff {
	private PersistentTime pt;
	private GateManipulator gm;
	private Loader ld;
	private FlightList eligibleFlights;
	private FlightList fl;
	private FlightList launchedFlights = new FlightList();;
	private ArrayList<Gates> eligibleGates;
	private List<Gates> filledGateList;
	private Gates gate;
	private int currentTime; 
	private Singleton single = Singleton.getInstance(); //WeatherClass
	public String condition; //WeatherClass
	public boolean status; //WeatherClass
	public String weatherString; //WeatherClass
	
	
	/**
	 * a constructor for the takeoff class
	 * 
	 * @param GateManipulator
	 * @param Loader
	 */
	public Takeoff(GateManipulator gm, Loader l) {
		this.gm = gm;
		this.ld = l;
		
		System.out.println("NEW TAKEOFF CREATED");
	}
	
	/**
	 *a class to get current weather condition (Clear or Severe)
	 *
	 *@param Singleton.getWeather
	 *
	 */	
	public String getCurrentWeather() {
		
		//Test
		System.out.println("Inside TakeOff.Singleton() -> getWeather");
		
		condition = single.getCurrentCondition(single.getWeather());
		
		return condition;
	}

	/**
	 *a class to get the String value of weather
	 *
	 *@param GetCurrentWeather
	 *@param getWeather
	 *@param getSeason
	 */	
	public String getWeatherString() {
		//Test
		System.out.println("Inside Takeoff.Singleton() -> GetCurrentWeather");
		
		weatherString = GetCurrentWeather.getWeatherString(GetCurrentWeather.getWeather(single.getWeather(), single.getSeason()));
		
		return weatherString;
	}

	/**
	 *a class to get the boolean value of OktoFly
	 *
	 *
	 *
	 */	
	
	public boolean getFlightStatus() {
		
		//Test
		System.out.println("Inside Takeoff.Singleton() -> PreFlight ");
		
		single = Singleton.getInstance();
		
		return status = single.PreFlight();
		
		
	}
		
	/**
	 * checks the current time in order to decide what 
	 * planes are able to be launched, and sends the list of occupied gates to the to takeoffFrame class
	 * 
	 * @param flightList
	 */
	public FlightList getTakeoffList() {
		
		
		//TEST
		System.out.println("Inside Takeoff.getTakeoffList()");
		
		Iterator filledGateIterator;
		fl = new FlightList();
		pt = PersistentTime.getInstance();
		
		//get current time
		currentTime = pt.getTime();
		
		//get list of eligible flights and check which ones are in gates
		eligibleFlights = getQuarterList(currentTime);
		
		Iterator elIter = eligibleFlights.createIterator();
		
		//get the gates of all eligible flights
		eligibleGates = new ArrayList<>();
		while(elIter.hasNext()) {
			Flight flight = (Flight) elIter.next();
			
			if(flight.isInGate()) {
				System.out.println("IM INSIDE THE IF");
				gate = gm.getGate(flight.getGateId());
				eligibleGates.add(gate);
				System.out.println("Inside Takeoff, Flight no. " + flight.getFlightId() + " fills gate " + flight.getGateId());
			}
		}//end of while
		
		
		//problem has to be here
		filledGateList = gm.getFilledGates(eligibleGates);
		for(Gates gate : filledGateList) {
			System.out.println("Inside takeoff, Gate " + gate.getGateName() + " is ready for takeoff!");
		}
		
		fl = gm.convertGateListToFlights(filledGateList.iterator(), ld);
		
		return fl;
	}
	
	
	/**
	 * return list of flights in current Quarter
	 * 
	 * @param currentTime
	 * @return list of flights in current Quarter
	 */
	public FlightList getQuarterList(int currentTime){
		if(currentTime == 1) {
			System.out.println("Returning quarter1");
			return ld.getQuarter1();
		}else if(currentTime == 2){
			System.out.println("Returning quarter2");
			return ld.getQuarter2();
		}else if(currentTime == 3) {
			System.out.println("Returning quarter3");
			return ld.getQuarter3();
		}else if(currentTime == 4) {
			System.out.println("Returning quarter4");
			return ld.getQuarter4();
		}else{
			System.out.println("Unavailable Quarter Exception");
			return null;
		}//end of if else
	}
	
	/**
	 * returns the list of launched Flights
	 * 
	 * @return launchedFlights
	 */
	public FlightList getLaunchedFlights() {
		if(launchedFlights == null) {
			System.out.println("\t launched flights is null!");
		}
		
		return launchedFlights;
	}
	
	/**
	 * sets the list of launched flights
	 * 
	 * @param launchedFlights
	 */
	public void setLaunchedFlights(FlightList launchedFlights) {
		//launched flights is set
		this.launchedFlights = launchedFlights;
		
		System.out.println("Launched flights successfully set");
	}

	
}
