package takeoffandlanding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import gates.GateManipulator;
import gates.Gates;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import scheduler.PersistentTime;

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
	private List<Flight> eligibleFlights;
	private FlightList fl;
	private ArrayList<Gates> eligibleGates;
	private List<Gates> filledGateList;
	private Gates gate;
	private int currentTime;
	
	/**
	 * a constructor for the takeoff class
	 * 
	 * @param GateManipulator
	 * @param Loader
	 */
	public Takeoff(GateManipulator gm, Loader l) {
		this.gm = gm;
		this.ld = l;
	}
	
	/**
	 * checks the current time in order to decide what 
	 * planes are able to be launched, and sends the list of occupied gates to the to takeoffFrame class
	 * 
	 * @param flightList
	 */
	public FlightList getTakeoffList() {
		//TEST
		System.out.println("Inside Takeoff.takeoff()");
		
		Iterator filledGateIterator;
		fl = new FlightList();
		pt = PersistentTime.getInstance();
		
		//get current time
		currentTime = pt.getTime();
		
		//get list of eligible flights and check which ones are in gates
		eligibleFlights = getQuarterList(currentTime);
		
		//get the gates of all eligible flights
		eligibleGates = new ArrayList<>();
		for(Flight flight : eligibleFlights) {
			if(flight.isInGate()) {
				gate = gm.getGate(flight.getGateId());
				eligibleGates.add(gate);
			}
		}
		
		filledGateList = gm.getFilledGates(eligibleGates);
		for(Gates gate : filledGateList) {
			System.out.println("Gate " + gate.getGateName() + " is ready for takeoff!");
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
	public List<Flight> getQuarterList(int currentTime){
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
}
