package takeoffandlanding;

import java.util.ArrayList;
import java.util.Iterator;

import gates.GateManipulator;
import gates.RunwayManipulator;
import gates.Runways;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;

/**
 * This class controls the function of launching flight objects to the runway and out of their gates
 * @author Will and David
 *
 */
public class RunwayControl {
	private FlightList fl;
	private RunwayManipulator rm;
	private GateManipulator gm;
	private Takeoff to;
	private Landing ln;
	private String rwStatus1 = "";
	private String rwStatus2 = "";
	private String rwStatus3 = "";
	private String rwStatus4 = "";
	
	/**
	 * constructor for the RunwayLaunch object launch option
	 * 
	 * @param GateManipulator
	 * @param RunwayManipulator
	 * @param Takeoff
	 */
	public RunwayControl(GateManipulator gm, RunwayManipulator rm, Takeoff to) {
		this.gm = gm;
		this.rm = rm;
		this.to = to;
	}
	
	/**
	 * constructor for the RunwayLaunch landing function
	 * 
	 * @param GateManipulator
	 * @param RunwayManipulator
	 * @param Landing
	 */
	public RunwayControl(GateManipulator gm, RunwayManipulator rm, Landing ln) {
		this.gm = gm;
		this.rm = rm;
		this.ln = ln;
	}
	
	/**
	 * causes the selected planes to depart using the runways
	 */
	public void departure(FlightList fl) {
		//get list of flights that are departing - DONE
		//find an empty runway - DONE
		//remove flight from gate - DONE
		//send flight to runway - DONE
		//warmup - 
		//accelerating -
		//leaving ground -
		//clear runway - 
		Iterator flIter;
		Iterator runwayIter;
		ArrayList<Runways> runways = new ArrayList();;
		
		do {
			runwayIter = rm.createIterator();
			
			//remove planes from gates
			flIter = fl.createIterator();
			while(flIter.hasNext()) {
				Flight flight = (Flight) flIter.next();
				
				gm.removePID(flight.getGateId());
				flight.setGateId(-1);
			}//end of while
				
			//get empty runway
			while(runwayIter.hasNext()) {
				Runways runway = (Runways) runwayIter.next();
				
				if (rm.runwayIsEmpty(runway)) {
					runways.add(runway);
				}
			}//end of while
			
			//add planes to empty runway
			flIter = fl.createIterator();
			while(flIter.hasNext()) {
				Flight flight = (Flight) flIter.next();
				
				//add planes to runways in list
				for(Runways runway : runways) {
					rm.updatingRunways(runway.getRunwayID(), flight.getFlightId());
				}//end of for
			}//end of while
			
			//remove planes from runway
			runwayIter = rm.createIterator();
			while(runwayIter.hasNext()) {
				Runways runway = (Runways) runwayIter.next();
				
				rm.updatingRunways(runway.getRunwayID(), -1);
				//ADD MESSAGE HERE
			}//end of while

		}while(!allEmpty());//end of do-while
	}
	
	/**
	 * update status of runways to send to progress bar
	 * 
	 * @param int whichRunway
	 * @param String message
	 */
	public void updateStatus(int whichRunway, String message) {
		
		//going to try and use these for live updating messages
		if(whichRunway == 1) {
			rwStatus1 = message;
		}else if(whichRunway == 2) {
			rwStatus2 = message;
		}else if(whichRunway == 3) {
			rwStatus3 = message;
		}else if(whichRunway == 4) {
			rwStatus4 = message;
		}
	}
	
	/**
	 * removes flight from gate
	 */
	
	/**
	 * returns true if every runway is empty
	 *
	 * @return boolean
	 */
	public boolean allEmpty() {
		boolean empty = true;
		Iterator runwayIter = rm.createIterator();
		
		while(runwayIter.hasNext()) {
			Runways runway = (Runways) runwayIter.next();
			
			if(!rm.runwayIsEmpty(runway)) {
				empty = false;
			}
		}
		
		return empty;
	}
}
