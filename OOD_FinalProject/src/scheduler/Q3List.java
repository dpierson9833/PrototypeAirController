package scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to manipulate the 3rd Quarter of flights
 * 
 * @author Marc
 *
 */
public class Q3List implements QuarterComposite {
    private String quarter;
    private List<Flight> q3Flights;
    private int size = 0;
    
    @Override
    public void printQuarterList() {
        System.out.println("\t\t\tFlight list for the listing: " + quarter + "\t\t\t");
        for(int i = 0; i < q3Flights.size(); i++){
            System.out.println("FlightID: " + q3Flights.get(i).getFlightId()+ 
            		"\nFlight Date: " + q3Flights.get(i).getFlightDate() + 
            		"\nSource Airport: " +q3Flights.get(i).getSourceAp() +
            		"\nDestination Airport: " + q3Flights.get(i).getDestAp() + 
            		"\nExpected Gate ID: " + q3Flights.get(i).getExpGateId() + 
            		"\nGate ID: " +q3Flights.get(i).getGateId() + 
            		"\nTime of Departure: " +q3Flights.get(i).getToD() + 
            		"\nTime of Arrival: " +q3Flights.get(i).getToA());
            System.out.println();
        }
    }
    
    public Q3List(String quarter){
        this.quarter = quarter;
        this.q3Flights = new ArrayList<>();
    }

    @Override
    public void addToQuarter(FlightList flightList){
        for (int i = 0; i < flightList.getSize(); i++){
            if ((flightList.getFlight(i).getToD() > 50 && flightList.getFlight(i).getToD() <= 75) && (flightList.getFlight(i).getToA() == -1)){
                q3Flights.add(flightList.getFlight(i));
                size++;
            }
            else if ((flightList.getFlight(i).getToA() > 50 && flightList.getFlight(i).getToA() <= 75) && (flightList.getFlight(i).getToD() == -1)){
                q3Flights.add(flightList.getFlight(i));
                size++;
            }
        }
        System.out.println("All possible flights added to quarter for the day.");
    }

    @Override
    public int getSize() {
        return size;
    }

	@Override
	public List<Flight> getList() {
		// TODO Auto-generated method stub
		return q3Flights;
	}

   
}