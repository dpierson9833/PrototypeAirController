package takeoffandlanding;

import java.util.ArrayList;
import java.util.Iterator;

import gates.GateManipulator;
import gates.Gates;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import scheduler.PersistentTime;

/**
 * a class to control the landing functions inside the action panel of the main UI
 * 
 * @author Will
 *
 */
public class Landing {
	private GateManipulator gm;
	private Loader ld;
	private int currentTime;
	private FlightList eligibleFlights;
	private PersistentTime pt;
	private FlightList fl;
	private Gates gate;
	private ArrayList<Gates> eligibleGates;
	private FlightList landedFlights;
	
	/**
	 * constructor for Landing object
	 * 
	 * @param GateManipulator gm
	 * @param Loader ld
	 */
	public Landing(GateManipulator gm, Loader ld) {
		this.gm = gm;
		this.ld = ld;
	}
	
	//check which flights are arriving in the quarter
	//check if gate is filled
	//put them in correct gate
	/**
	 * returns a list of flights eligible to be landed in a quarter
	 * 
	 * @return
	 */
	public FlightList getLandingList() {
		//TEST
		System.out.println("Inside Landing.getLandingList()");
		
		Iterator filledGateIterator;
		fl = new FlightList();
		pt = PersistentTime.getInstance();
		
		//get current time
		currentTime = pt.getTime();
		
		//get list of eligible flights and check which ones are in gates
		eligibleFlights = getQuarterList(currentTime);
		
		Iterator flIter = eligibleFlights.createIterator();
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();
			
			if(flight.getToD() == -1) {
				fl.addToList(flight);
			}
		}
		
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
		if(landedFlights == null) {
			System.out.println("\t launched flights is null!");
		}
		
		return landedFlights;
	}
	
	/**
	 * sets the list of launched flights
	 * 
	 * @param launchedFlights
	 */
	public void setLaunchedFlights(FlightList launchedFlights) {
		//launched flights is set
		this.landedFlights = launchedFlights;
		
		System.out.println("Landed flights successfully set");
	}
}
