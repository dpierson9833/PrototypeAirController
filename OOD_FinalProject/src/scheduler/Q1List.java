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
    private FlightList q1Flights;
    private int size = 0;
    

    @Override
    public void printQuarterList() {
        System.out.println("\t\t\tFlight list for the listing: " + quarter + "\t\t\t");
        for(int i = 0; i < q1Flights.getSize(); i++){
            System.out.println("FlightID: " + q1Flights.getFlight(i).getFlightId()+ 
            		"\nFlight Date: " + q1Flights.getFlight(i).getFlightDate() + 
            		"\nSource Airport: " +q1Flights.getFlight(i).getSourceAp() +
            		"\nDestination Airport: " + q1Flights.getFlight(i).getDestAp() +  
            		"\nExpected Gate ID: " + q1Flights.getFlight(i).getExpGateId() + 
            		"\nGate ID: " +q1Flights.getFlight(i).getGateId() + 
            		"\nTime of Departure: " +q1Flights.getFlight(i).getToD() + 
            		"\nTime of Arrival: " +q1Flights.getFlight(i).getToA());
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
        this.q1Flights = new FlightList();
    }

    @Override
    public void addToQuarter(FlightList flightList){
        for (int i = 0; i < flightList.getSize(); i++){
            if ((flightList.getFlight(i).getToD() > 0 && flightList.getFlight(i).getToD() <= 25) && (flightList.getFlight(i).getToA() == -1)){
                q1Flights.addToList(flightList.getFlight(i));
                size++;
            }
            else if ((flightList.getFlight(i).getToA() > 0 && flightList.getFlight(i).getToA() <= 25) && (flightList.getFlight(i).getToD() == -1)){
                q1Flights.addToList(flightList.getFlight(i));
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
	public FlightList getList() {
		return q1Flights;
	}

   

}