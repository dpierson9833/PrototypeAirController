package gates;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;

/**
 * this class is a controller for the Gates objects
 * 
 * @author Matt
 * 
 *
 */
public class GateManipulator {
	private ArrayList<Gates> gateList = new ArrayList();
	private Gates a1 = new Gates("C3", -1, 0, 0); // Negative 1 symbolizes an empty field due to pointer exceptions when using null.
	private Gates a2 = new Gates("B10", -1, 1, 1);
	private Gates a3 = new Gates("A7", -1, 2, 2);
	private Gates a4 = new Gates("A8", -1, 3, 3);
	private Gates a5 = new Gates("T3", -1, 4, 4);
	private Gates a6 = new Gates("B13", -1, 5, 5);
	private Gates a7 = new Gates("A13", -1, 6, 6);
	private Gates a8 = new Gates("C11", 3, 7, 7);
	private Gates a9 = new Gates("A1", 7, 8, 8);
	private Gates a10 = new Gates("C8", -1, 9, 9);
	private Gates a11 = new Gates("B7", -1, 10, 10);
	private Gates a12 = new Gates("T6", -1, 11, 11);
	private Gates a13 = new Gates("A18", -1, 12, 12);
	private Gates a14 = new Gates("B3", -1, 13, 13);
	private Gates a15 = new Gates("C15", -1, 14, 14);
	private Gates a16 = new Gates("A5", -1, 15, 15);
	private Gates a17 = new Gates("A14", -1, 16, 16);
	private Gates a18 = new Gates("A15", -1, 17, 17);
	private Gates a19 = new Gates("A9", -1, 18, 18);
	private Gates a20 = new Gates("B1", -1, 19, 19);
	private Gates a21 = new Gates("B15", -1, 20, 20);
	private Gates a22 = new Gates("B6", -1, 21, 21);
	private Gates a23 = new Gates("C10", -1, 22, 22);
	private Gates a24 = new Gates("B8", -1, 23, 23);
	private Gates a25 = new Gates("C4", -1, 24, 24);
	private FlightList fl = new FlightList();
	
	/**
	 * constructor for gates manipulator that instantiates all gates
	 */
	public GateManipulator() {
		addingGates();
	}
	
	/**
	 * adds gates into gateList
	 */
	public void addingGates() {
		gateList.add(a1);
		gateList.add(a2);
		gateList.add(a3);
		gateList.add(a4);
		gateList.add(a5);
		gateList.add(a6);
		gateList.add(a7);
		gateList.add(a8);
		gateList.add(a9);
		gateList.add(a10);
		gateList.add(a11);
		gateList.add(a12);
		gateList.add(a13);
		gateList.add(a14);
		gateList.add(a15);
		gateList.add(a16);
		gateList.add(a17);
		gateList.add(a18);
		gateList.add(a19);
		gateList.add(a20);
		gateList.add(a21);
		gateList.add(a22);
		gateList.add(a23);
		gateList.add(a24);
		gateList.add(a25);
	}
	
	/**
	 * returns arraylist of gates
	 * 
	 * @return gateList
	 */
	public ArrayList<Gates> getGateList(){
		return gateList;
	}
	
	/**
	 * returns true if a gate is empty
	 * 
	 * @param Gates object
	 * @return isEmpty
	 */
	public boolean gateIsEmpty(Gates A) {
		if ((A.getPlaneID()) == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * returns a list of the gates that have Flights occupying them
	 * 
	 * @return
	 */
	public ArrayList<Gates> getFilledGates(ArrayList<Gates> quarterList) throws NullPointerException{
		ArrayList<Gates> filledGates = new ArrayList();
		
		//TESTING
		System.out.println("Inside GateManipulator.getFilledGates()");
		if(quarterList.isEmpty()) {
			System.out.println("\tValue passed to GateManipulator.getFilledGates in GateManipulator is empty");
		}
		//END TESTING
		
		//iterate over arraylist and add each filled gate to a list
		for(Gates gates: quarterList) {
			if(!gateIsEmpty(gates)) {
				System.out.println("\tAdding gate " + gates.getGateName() + "to filledGatesList with plane no. " + gates.getPlaneID());
				filledGates.add(gates);
			}
		}
		
		//TESTING
		if(filledGates.isEmpty()) {
			System.out.println("\tFilled gates in GateManipulator.getFilledGates() is empty");
		}
		//END TESTING
		
		return filledGates;
	}
	
	/**
	 * returns gate object of gate with an id
	 * 
	 * @param gateId
	 * @return gate object
	 */
	public Gates getGate(int gateId) throws NullPointerException{
		Gates foundGate = null;
		
		for(Gates gate : gateList) {
			if(gate.getGateID() == gateId) {
				foundGate = gate;
				break;
			}
		}
		
		return foundGate;
	}
	
	/**
	 * adds plane to a gate by updating the planeID
	 * 
	 * @param planeID
	 * @param gateID
	 */
	public void updatePID(int pID, int gID) {
		int index = gID;
		
		//TESTNG
		System.out.println("Inside update PID, recieving index " + index + " from flight " + pID );
		
		if(gateIsEmpty(gateList.get(index))){
			gateList.get(index).setPlaneID(pID);
			gateList.set(index, gateList.get(index));
			
			//TESTING
			System.out.println("Added plane " + pID + " to gate " + gateList.get(index).getGateName());
		}else {
			
		}//end of if else
		
	}
	
	/**
	 * removes Pid from gate
	 * 
	 * @param gateID
	 */
	public void removePID(int gID) {
		int index = gID;
		
		if(index >= 0) {
			gateList.get(index).setPlaneID(-1);
			gateList.set(index, gateList.get(index));
			
			//TESTING 
			System.out.println("Removing plane from gate " + gateList.get(index).getGateName());		
		}else {
			System.out.println("There was no plane in this gate!");
		}
	}
	
	/**
	 * update multiple data fields in a gate
	 * 
	 * @param gateName
	 * @param planeID
	 * @param expectedGateID
	 * @param gateID
	 */
	public void updatingGates(String gName, int pID, int eID, int gID) {
		int index = gID; 
		
		gateList.get(index).setGateName(gName);
		gateList.get(index).setPlaneID(pID);
		gateList.get(index).setExpectedPlaneID(eID);
		gateList.set(index, gateList.get(index));
	}

	/**
	 * tester method for putting things in gates
	 */
	public void chaos() {
		updatingGates("A8", 2, 4, 4);
		updatingGates("B13", 7, 6, 6);
		updatingGates("A14", 24, 17, 17);
	}
	
    /**
     * converts a list of gates to a list of flights that occupy those gates
     * 
     * @param gateList 
     * @param ld
     * @return FlightList
     */
    public FlightList convertGateListToFlights(Iterator gateList, Loader ld) {
    	System.out.println("Inside GateManipulator.convertGateListToFlights");
    	FlightList fl = new FlightList();
    	Iterator gateIter = gateList;
    	Flight flight;
    	
    	while(gateList.hasNext()) {
    		Gates gate = (Gates) gateList.next();
    		
    		//TESTING
    		System.out.println("\tAttempting to add plane parked at " + gate.getGateName() + " to FlightList");
    		
    		flight = fl.getFlightAtGate(gate.getGateID(), ld.getFullFlightList().createIterator()); //get flight at gateId
    		
    		//if it is in a gate
    		if(flight.isInGate()) fl.addToList(flight);
    		
    		//TESTING
    		System.out.println("\tFlight " + flight.getFlightId() + " added to FlightList");
    	}
    	
    	return fl;
    }
    
    /**
     * returns id of gate searched by name
     * 
     * @param String gateName
     * @return int gateId
     */
    public int findGateId(String gateName) {
    	int gateId = -1;
    	
    	for(int i = 0; i < gateList.size(); i++) {
    		if(gateList.get(i).getGateName().equals(gateName)) {
    			gateId = gateList.get(i).getGateID();
    		}
    	}
    	
    	return gateId;
    }
	
	/**
	 * returns iterator for the array list of gates
	 * 
	 * @return gateList.iterator();
	 */
	public Iterator createIterator() {
		return gateList.iterator();
	}
}