

import java.util.Iterator;
import java.util.List;

import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import scheduler.PersistentTime;
import gates.GateManipulator;
import gates.Gates;
import takeoffandlanding.Takeoff;

public class test {
	public static void main(String[] args) {
		//load flighs
		GateManipulator gm = new GateManipulator();
		Loader loader = new Loader(gm);
		loader.loadFlights();
		
		//Load takeoff to see what can be launched
		Takeoff to = new Takeoff(gm, loader);
		FlightList fl = to.getTakeoffList();
		Iterator flIter = fl.createIterator();
		
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();
			
			System.out.println("Flight " + flight.getFlightId() + " headed to " + flight.getDestAp() + " ready for takeoff!");
		}
	}
}
