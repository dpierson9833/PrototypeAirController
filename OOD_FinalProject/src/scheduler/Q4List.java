package scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to manipulate the 4th Quarter of flights
 * 
 * @author Marc
 *
 */
public class Q4List implements QuarterComposite {
    private String quarter;
    private List<Flight> q4Flights;
    private int size = 0;
    
    @Override
    public void printQuarterList() {
        System.out.println("\t\t\tFlight list for the listing: " + quarter + "\t\t\t");
        for(int i = 0; i < q4Flights.size(); i++){
            System.out.println("FlightID: " + q4Flights.get(i).getFlightId() + 
            		"\nFlight Date: " + q4Flights.get(i).getFlightDate() + 
            		"\nSource Airport: " +q4Flights.get(i).getSourceAp() +
            		"\nDestination Airport: " + q4Flights.get(i).getDestAp() + 
            		"\nExpected Gate ID: " + q4Flights.get(i).getExpGateId() + 
            		"\nGate ID: " +q4Flights.get(i).getGateId() + 
            		"\nTime of Departure: " +q4Flights.get(i).getToD()+ 
            		"\nTime of Arrival: " +q4Flights.get(i).getToA());
            System.out.println();
        }
    }
    
    public Q4List(String quarter){
        this.quarter = quarter;
        this.q4Flights = new ArrayList<>();
    }

    @Override
    public void addToQuarter(FlightList flightList){
        for (int i = 0; i < flightList.getSize(); i++){
            if ((flightList.getFlight(i).getToD() > 75 && flightList.getFlight(i).getToD() <= 99) && (flightList.getFlight(i).getToA() == -1)){
                q4Flights.add(flightList.getFlight(i));
                size++;
            }
            else if ((flightList.getFlight(i).getToA() > 75 && flightList.getFlight(i).getToA() <= 99) && (flightList.getFlight(i).getToD() == -1)){
                q4Flights.add(flightList.getFlight(i));
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
		return q4Flights;
	}

    

}