package takeoffandlanding;

import java.util.Iterator;

import gates.GateManipulator;
import gates.RunwayManipulator;
import gates.Runways;
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
	public void departure() {
		//get list of flights that are departing - DONE
		//find an empty runway -
		//remove flight from gate -
		//send flight to runway -
		//warmup -
		//accelerating -
		//leaving ground -
		//clear runway - 
		Iterator runwayIter;
		Runways emptyRunway = null;
		fl = to.getTakeoffList();
		
		//going to try and use these for live updating messages
		String rwStatus1 = "";
		String rwStatus2 = "";
		String rwStatus3 = "";
		String rwStatus4 = "";
		
		do {
			runwayIter = rm.createIterator();
			
			//get empty runway
			while(runwayIter.hasNext()) {
				Runways runway = (Runways) runwayIter.next();
				
				if (rm.runwayIsEmpty(runway)) {
					emptyRunway = runway;
					break;
				}
			}//end of while
			
			
			
			
			
		}while(!allEmpty());//end of do-while
	}
	
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
