package takeoffandlanding;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.SwingWorker;

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
public class RunwayControl{
	private RunwayManipulator rm;
	private GateManipulator gm;
	private String rwStatus1 = "";
	private String rwStatus2 = "";
	private String rwStatus3 = "";
	private String rwStatus4 = "";
	private String status;
	private FlightList fl;
	private int sizeOfTask;
	private int progress;
	
	/**
	 * constructor for the RunwayLaunch object launch option
	 * 
	 * @param GateManipulator
	 * @param RunwayManipulator
	 * @param Takeoff
	 */
	public RunwayControl(GateManipulator gm, RunwayManipulator rm) {
		this.gm = gm;
		this.rm = rm;
	}
	
	/**
	 * causes the selected planes to depart using the runways
	 */
	public void departure() {
		progress = 0;
		//Initialize progress property.

		//TESTING
		System.out.println("Inside RunwayControl.departure()");

		Iterator flIter = fl.createIterator();
		Iterator runwayIter;
		ArrayList<Runways> runways = new ArrayList();

		sizeOfTask = 100;


		//TESTING
		System.out.println("\tEntering loop!");

		//Start of do-while
		do {

			runwayIter = rm.createIterator();

			//remove planes from gates
			flIter = fl.createIterator();
			while(flIter.hasNext()) {
				Flight flight = (Flight) flIter.next();

				gm.removePID(flight.getGateId());

				status = "Plane no. " + flight.getFlightId() + " headed for " + flight.getDestAp() + " has left the gate...";
				
				progress += 10;


				flight.setGateId(-1);

				sleep();
			}//end of while

			//get empty runway
			while(runwayIter.hasNext()) {
				Runways runway = (Runways) runwayIter.next();

				if (rm.runwayIsEmpty(runway)) {
					runways.add(runway);
					updateStatus(runway.getRunwayID(), "Runway " + runway.getRunwayID() + " is empty and ready for a plane...");
					
					progress += 15;
					setProgress(Math.min(progress, 100));

				}else {					
					updateStatus(runway.getRunwayID(), "Runway " + runway.getRunwayID() + " is full, the plane will have to wait...");
					
					progress += 15;

				}

				sleep();
			}//end of while

			//add planes to empty runway
			flIter = fl.createIterator();
			while(flIter.hasNext()) {
				Flight flight = (Flight) flIter.next();

				//add planes to runways in list
				for(Runways runway : runways) {
					rm.updatingRunways(runway.getRunwayID(), flight.getFlightId());
					updateStatus(runway.getRunwayID(), "Flight no. " + flight.getFlightId() + " entering runway " + runway.getRunwayID() + "...");
					
					progress += 15;

				}//end of for

				sleep();
			}//end of while

			//remove planes from runway
			runwayIter = rm.createIterator();
			while(runwayIter.hasNext()) {
				Runways runway = (Runways) runwayIter.next();

				updateStatus(runway.getRunwayID(), "Flight no " + runway.getPlaneID() + " has departed! " + "Runway " + runway.getRunwayID() + " is now empty...");
				rm.updatingRunways(runway.getRunwayID(), -1);
				
				progress += 10;

				sleep();
			}//end of while

		}while(!allEmpty());//end of do-while

		status = "All runways are now clear!";
		progress = sizeOfTask;
	}
	
	/**
	 * update status of runways to send to progress bar
	 * 
	 * @param int whichRunway
	 * @param String message
	 */
	public String updateStatus(String whichRunway, String message) {
		
		//going to try and use these for live updating messages
		if(whichRunway.equals("R1")) {
			rwStatus1 = message;
			return rwStatus1;
		}else if(whichRunway.equals("R2")) {
			rwStatus2 = message;
			return rwStatus2;
		}else if(whichRunway.equals("R3")) {
			rwStatus3 = message;
			return rwStatus3;
		}else {
			rwStatus4 = message;
			return rwStatus4;
		}
	}
	
	/**
	 * sets progress
	 * 
	 * @param int progress
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	/**
	 * returns progress
	 * 
	 * @return int progress
	 */
	public int getProgress() {
		return progress;
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
			
			//TESTING
			System.out.println("\tInside allEmpty(), runway is filled by " + runway.getPlaneID());
			
			if(!rm.runwayIsEmpty(runway)) {
				empty = false;
			}
		}
		
		return empty;
	}
	
	/**
	 * Recieves a string from the runway operations and sends it to the UI
	 * 
	 * @param status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * returns the size of the runway task for the Progress bar
	 * 
	 * @return sizeOfTask
	 */
	public int getSizeOfTask() {
		return sizeOfTask;
	}
	
	/**
	 * utility method to simulate the passage of time for runway functions
	 */
	public void sleep() {
		System.out.println("\tInside sleep");
		
		try {
            Thread.sleep(1);
        } catch (InterruptedException ignore) {}
	}

	/**
	 * a method used to pass the launchedFlight FlightList
	 * 
	 * @param FlightList fl
	 */
	public void setFlightList(FlightList fl) {
		this.fl = fl;
	}
}
