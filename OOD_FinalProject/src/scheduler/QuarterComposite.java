package scheduler;

import java.util.List;

/**
 * an interface to access the functions of the Composite classes in this package
 * 
 * @author Marc
 *
 */
public interface QuarterComposite {
    void printQuarterList();
    void addToQuarter(FlightList list);
    int getSize();
	FlightList getList();
}