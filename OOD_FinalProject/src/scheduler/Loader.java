package scheduler;

import java.util.Iterator;
import java.util.List;

import gates.Gates;
import gates.GateManipulator;

/**
 * This class controls the loading of flights to test the controller system
 * This functionality mimics a database containing flights that would be available to the program
 * 
 * Flights with a time of arrival of '-1' indicate that they currently in the airport and are departing
 * Flights with a time of departure of '-1' indicate that they are not currently at the airport
 *
 * 
 * @author Marc and David
 * 
 */
public class Loader {
	private QuarterComposite q1001 = new Q1List("Quarter 1");
	private QuarterComposite q2001 = new Q2List("Quarter 2");
	private QuarterComposite q3001 = new Q3List("Quarter 3"); 
	private QuarterComposite q4001 = new Q4List("Quarter 4");
	private FlightList list001 = new FlightList();
	private GateManipulator gm;
	
	/**
	 * constructor for the loader class
	 * 
	 * @param GateManipulator
	 */
	public Loader(GateManipulator gm) {
		this.gm = gm;
	}
	
	/**
	 * loads flights into their respective gates
	 */
	public void loadFlights() {
		
		//Last two variables in constructor ToD and ToA respectively
		//-1 values in ToD indicate that they are not in the airport
		//-1 values in ToA indicate that they are currently in the airport
		
		//Departing Flights
		Flight flight1 = new Flight(1, "2019-01-01", "ATL", "ADQ", "C3", -1, 10, -1);//departing
    	Flight flight2 = new Flight(2, "2019-01-01", "ATL", "PWN", "B10", -1, 21, -1);//departing
    	Flight flight3 = new Flight(3, "2019-01-01", "ATL", "PWN", "A7", -1, 56, -1);//departing
        Flight flight7 = new Flight(7, "2019-01-01", "ATL", "IWA", "A13", -1, 74, -1);//departing
    	Flight flight10 = new Flight(10, "2019-01-01", "ATL", "OKC", "C8", -1, 91, -1);//departing
 		//end of departing flights	
 			
		//Arriving Flights
        Flight flight4 = new Flight(4, "2019-01-01", "MLB", "ATL", "A8", -1, -1, 87);//arriving
        Flight flight5 = new Flight(5, "2019-01-01", "BNA", "ATL", "T3", -1, -1, 32);//arriving
        Flight flight6 = new Flight(6, "2019-01-01", "SFB", "ATL", "B13", -1, -1, 42);//arriving
        Flight flight8 = new Flight(8, "2019-01-01", "ATL", "FAT", "C11", -1, -1, 64);//arriving
        Flight flight9 = new Flight(9, "2019-01-01", "TPA", "ATL", "A1", -1, -1, 61);//arriving
        //end of arriving flights
        
        
        //Add flights to list
        list001.addToList(flight1);
        list001.addToList(flight2);
        list001.addToList(flight3);
        list001.addToList(flight4);
        list001.addToList(flight5);
        list001.addToList(flight6);
        list001.addToList(flight7);
        list001.addToList(flight8);
        list001.addToList(flight9);
        list001.addToList(flight10);
        list001.printFlightList();
        
        //adds flights to their respective gates
        for(int i = 0; i < list001.getSize(); i++) {
        	if(list001.getFlight(i).getToA() == -1) { //if plane is departing    		
        		gm.updatePID(list001.getFlight(i).getFlightId(), findGateIndex(list001.getFlight(i).getExpGateId()));
        		list001.getFlight(i).setGateId(findGateIndex(list001.getFlight(i).getExpGateId()));
        	}
        }

        q1001.addToQuarter(list001);
        q1001.printQuarterList();
        
        q2001.addToQuarter(list001);
        q2001.printQuarterList();

        q3001.addToQuarter(list001);
        q3001.printQuarterList();

        q4001.addToQuarter(list001);
        q4001.printQuarterList();
        
		//TESTING
		System.out.println("Inside Loader.loadFlights(), all flights loaded into quarters");
    }
	
	/**
	 * returns list of flights in Quarter1
	 * 
	 * @return Quarter 1 Flight List
	 */
	public List<Flight> getQuarter1(){
		return q1001.getList();
	}
	
	/**
	 * returns list of flights in Quarter2
	 * 
	 * @return Quarter 2 Flight List
	 */
	public List<Flight> getQuarter2(){
		return q2001.getList();
	}
	
	/**
	 * returns list of flights in Quarter3
	 * 
	 * @return Quarter 3 Flight List
	 */
	public List<Flight> getQuarter3(){
		return q3001.getList();
	}
	
	/**
	 * returns list of flights in Quarter4
	 * 
	 * @return Quarter 4 Flight List
	 */
	public List<Flight> getQuarter4(){
		return q4001.getList();
	}
	
	public FlightList getFullFlightList() {
		return list001;
	}
	
	/**
	 * finds the gate ID using the name of the gate
	 * 
	 * @param gateName
	 * @return gateID
	 */
	public int findGateIndex(String gateName) {
		//TESTING
		System.out.println("Inside Loader.findGateIndex()");
		
		
		Iterator gateIter = gm.createIterator();
		int gateId = -1; //cannot find
		
		while(gateIter.hasNext()) {
			Gates gate = (Gates) gateIter.next(); /* create gate object over iterator list */
			
			//check if gate names are the same and return that gate Id
			if(gateName.equalsIgnoreCase(gate.getGateName())) {
				gateId = gate.getGateID();
			}//end of if
		}
		
		return gateId;
	}
}
