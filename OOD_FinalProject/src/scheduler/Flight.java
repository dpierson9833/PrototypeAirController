package scheduler;


/**
 * This class creates the object instance of the flight class which will be used to
 * populate the runways and gates
 * 
 * @author Marc
 * 
 */
public class Flight {
    
    private int flightId; /*unique flight ID*/
    private String flightDate; /*the date of flight's departure or arrival*/
    private String sourceAp; /*the source airport of the flight*/
    private String destAp; /*the destination of the flight*/
    private String expGateId; /*the gate that is expected to hold the plane once it arrives*/
    private int gateId; /*the gate that is currently occupied by this flight*/
    private int ToD; /*time of departure*/
    private int ToA; /*time of arrival*/
    
    /**
     * Constructor for Flight object
     * 
     * @param flightId
     * @param flightDate
     * @param sourceAp
     * @param destAp
     * @param planeId
     * @param expGateId
     * @param gateId
     * @param ToD
     * @param ToA
     */
    public Flight(int flightId, String flightDate, String sourceAp, String destAp, String expGateId, int gateId, int ToD, int ToA){
        this.flightId = flightId;
        this.flightDate = flightDate;
        this.sourceAp = sourceAp;
        this.destAp = destAp;
        this.expGateId = expGateId;
        this.gateId = gateId;
        this.ToD = ToD;
        this.ToA = ToA;
    }
    
    /**
     * returns id of Flight
     * 
     * @return flightId
     */
    public int getFlightId() {
        return this.flightId;
    }
    
    /**
     * set FlightID
     * 
     * @param flightId
     */
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    /**
     * returns date of flight
     * 
     * @return flightDate
     */
    public String getFlightDate() {
        return this.flightDate;
    }

    /**
     * sets date of flight
     * 
     * @param flightDate
     */
    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    /**
     * returns source Airport
     * 
     * @return sourceAp
     */
    public String getSourceAp() {
        return this.sourceAp;
    }

    /**
     * sets the source Airport
     * 
     * @param sourceAp
     */
    public void setSourceAp(String sourceAp) {
        this.sourceAp = sourceAp;
    }

    /**
     * returns destination Airport
     * 
     * @return destAp
     */
    public String getDestAp() {
        return this.destAp;
    }

    /**
     * sets destination airport
     * 
     * @param destAp
     */
    public void setDestAp(String destAp) {
        this.destAp = destAp;
    }
    
    /**
     * gets expected gateId
     * 
     * @return expGateId
     */
    public String getExpGateId() {
        return this.expGateId;
    }
    
    /**
     * sets expected gateId
     * 
     * @param expGateId
     */
    public void setExpGateId(String expGateId) {
        this.expGateId = expGateId;
    }

    public int getGateId() {
        return this.gateId;
    }

    /**
     * sets gateId
     * 
     * @param gateId
     */
    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    /**
     * returns time of departure
     * 
     * @return ToD
     */
    public int getToD() {
        return this.ToD;
    }

    /**
     * sets time of departure
     * 
     * @param ToD
     */
    public void setToD(int ToD) {
        this.ToD = ToD;
    }

    /**
     * returns time of arrival
     * 
     * @return ToA
     */
    public int getToA() {
        return this.ToA;
    }

    /**
     * sets time of arrival
     * 
     * @param ToA
     */
    public void setToA(int ToA) {
        this.ToA = ToA;
    }
    
	/**
	 * check if flight is currently occupying a gate
	 * 
	 * @param flight
	 * @return true if occupying gate
	 */
	public boolean isInGate(){
		return (getGateId() != -1) ? true : false;
	}

    /**
     * prints flight info to console
     */
    public void printFlightInfo(){
        System.out.println("FlightID: " + flightId + 
        		"\nFlight Date: " + flightDate + 
        		"\nSource Airport: " + sourceAp + 
        		"\nDestination Airport: " + destAp + 
        		"\nExpected Gate ID: " + expGateId + 
        		"\nGate ID: " +gateId + 
        		"\nTime of Departure: " + ToD + 
        		"\nTime of Arrival: " +ToA);
    }
}