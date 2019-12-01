package gates;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Matt
 * 
 * a class for manipulating runway objects to allow planes to occupy them and then 
 * leave the runway to make room for new planes
 *
 */
public class RunwayManipulator {
	ArrayList<Runways> runwayList = new ArrayList();
	Runways r1 = new Runways("R1", -1);
	Runways r2 = new Runways("R2", -1);
	Runways r3 = new Runways("R3", -1);
	Runways r4 = new Runways("R4", -1);
	
	/**
	 * adds runways to the runwayList
	 */
	public void addingRunways() {
		runwayList.add(r1);
		runwayList.add(r2);
		runwayList.add(r3);
		runwayList.add(r4);
	}
	
	/**
	 * check if runway is empty or not
	 * 
	 * @param Runway object
	 * @return true if runway is empty
	 */
	public boolean runwayIsEmpty(Runways A) {
		if ((A.getPlaneID()) == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * updates runways with plane ids
	 * 
	 * @param runwayID
	 * @param planeID
	 */
	public void updatingRunways(String rID, int pID) {
		int index;
		if (rID.contains("R1")) {
			index = 0;
			r1.setPlaneID(pID);
			runwayList.set(index, r1);
		}
		if (rID.contains("R2")) {
			index = 1;
			r2.setPlaneID(pID);
			runwayList.set(index, r2);
		}
		if (rID.contains("R3")) {
			index = 2;
			r3.setPlaneID(pID);
			runwayList.set(index, r3);
		}
		if (rID.contains("R4")) {
			index = 3;
			r4.setPlaneID(pID);
			runwayList.set(index, r4);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator createIterator() {
		return runwayList.iterator();
	}
	
	public void chaos() {
		updatingRunways("R1", 8);
		updatingRunways("R2", 1);
		updatingRunways("R3", 23);
		updatingRunways("R4", 16);
	}
}
