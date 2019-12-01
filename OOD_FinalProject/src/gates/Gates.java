package gates;

public class Gates {
	private String gateName;
	private int planeID;
	private int expectedPlaneID;
	private int gateID;
	
	/**
	 * constructor for gate object
	 * 
	 * @param gName
	 * @param pID
	 * @param eID
	 * @param gID
	 */
	Gates(String gName, int pID, int eID, int gID) {
		this.gateName = gName;
		this.planeID = pID;
		this.expectedPlaneID = eID;
		this.gateID = gID;
	}

	/**
	 * sets id of gate object
	 * 
	 * @param gateID
	 */
	public void setGateID(int gateID) {
		this.gateID = gateID;
	}
	
	/**
	 * returns gate id
	 * 
	 * @return gateID
	 */
	public int getGateID() {
		return gateID;
	}
	
	/**
	 * return name of gate
	 * 
	 * @return gateName
 	 */
	public String getGateName() {
		return gateName;
	}

	/**
	 * sets name of Gate
	 * 
	 * @param gateName
	 */
	public void setGateName(String gateName) {
		this.gateName = gateName;
	}

	/**
	 * sets planeID
	 * 
	 * @param planeID
	 */
	public void setPlaneID(int planeID) {
		this.planeID = planeID;
	}
	
	/**
	 * returns planeID
	 * 
	 * @return planeID
	 */
	public int getPlaneID() {
		return planeID;
	}
	
	/**
	 * sets expected ID of plane that gates expect to land inside
	 * 
	 * @param expectedPlaneID
	 */
	public void setExpectedPlaneID(int expectedPlaneID) {
		this.expectedPlaneID = expectedPlaneID;
	}
	
	/**
	 * returns expected ID of plane that gates expect to land inside
	 * 
	 * @return expectedPlaneID
	 */
	public int getExpectedPlaneID() {
		return expectedPlaneID;
	}
}
