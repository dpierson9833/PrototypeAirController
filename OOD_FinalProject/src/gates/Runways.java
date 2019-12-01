package gates;

/**
 * 
 * @author Matt
 *
 * this class is for instantiating runway objects that are manipulated by the runway manipulatoor class
 * planes will occupy the runway and then be sent out
 */
public class Runways {
	private String runwayID;
	private int planeID;
	
	/**
	 * constructor for runway objects
	 * 
	 * @param rID
	 * @param pID
	 */
	Runways(String rID, int pID) {
		this.runwayID = rID;
		this.planeID = pID;
	}

	/**
	 * return runway id
	 * 
	 * @return runwayID
	 */
	public String getRunwayID() {
		return runwayID;
	}

	/**
	 * set id of runway
	 * 
	 * @param runwayID
	 */
	public void setRunwayID(String runwayID) {
		this.runwayID = runwayID;
	}

	/**
	 * returns id of plane currently occupying runway
	 * @return
	 */
	public int getPlaneID() {
		return planeID;
	}

	/**
	 * sets id of plane currently occupying runway
	 * 
	 * @param planeID
	 */
	public void setPlaneID(int planeID) {
		this.planeID = planeID;
	}
}
