package scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to manipulate the 1st Quarter of flights
 * 
 * @author Marc
 *
 */
public class Q1List implements QuarterComposite {
    private String quarter;
    private List<Flight> q1Flights;
    private int size = 0;
    

    @Override
    public void printQuarterList() {
        System.out.println("\t\t\tFlight list for the listing: " + quarter + "\t\t\t");
        for(int i = 0; i < q1Flights.size(); i++){
            System.out.println("FlightID: " + q1Flights.get(i).getFlightId()+ 
            		"\nFlight Date: " + q1Flights.get(i).getFlightDate() + 
            		"\nSource Airport: " +q1Flights.get(i).getSourceAp() +
            		"\nDestination Airport: " + q1Flights.get(i).getDestAp() +  
            		"\nExpected Gate ID: " + q1Flights.get(i).getExpGateId() + 
            		"\nGate ID: " +q1Flights.get(i).getGateId() + 
            		"\nTime of Departure: " +q1Flights.get(i).getToD() + 
            		"\nTime of Arrival: " +q1Flights.get(i).getToA());
            System.out.println();
        }
    }
    
    /**
     * constructor for Q1 List 
     * 
     * @param quarter
     */
    public Q1List(String quarter){
        this.quarter = quarter;
        this.q1Flights = new ArrayList<Flight>();
    }

    @Override
    public void addToQuarter(FlightList flightList){
        for (int i = 0; i < flightList.getSize(); i++){
            if ((flightList.getFlight(i).getToD() > 0 && flightList.getFlight(i).getToD() <= 25) && (flightList.getFlight(i).getToA() == -1)){
                q1Flights.add(flightList.getFlight(i));
                size++;
            }
            else if ((flightList.getFlight(i).getToA() > 0 && flightList.getFlight(i).getToA() <= 25) && (flightList.getFlight(i).getToD() == -1)){
                q1Flights.add(flightList.getFlight(i));
                size++;
            }//end of if else
        }//end of for
        System.out.println("All possible flights added to quarter for the day.");
    }
    
    @Override
    public int getSize() {
        
        return size;
    }

	@Override
	public List<Flight> getList() {
		
		//TESTING
		if (q1Flights.isEmpty()) {
			System.out.println("q1Flights is empty");
		}
		
		return q1Flights;
	}

   

}