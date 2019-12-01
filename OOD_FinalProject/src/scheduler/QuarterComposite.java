package scheduler;

import java.util.List;

public interface QuarterComposite {
    void printQuarterList();
    void addToQuarter(FlightList list);
    int getSize();
	List<Flight> getList();
}