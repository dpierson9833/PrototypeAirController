package scheduler;

/**
 * 
 * @author David
 *
 *
 *	this class is a Singleton implemetation of a persistent time that is used to determine
 *  what flights are eligible for launch
 */
public class PersistentTime {
	private static int currentTime;
	private static PersistentTime firstInstance = null;

	/**
	 * private constructor to prevent instantiation
	 */
	private PersistentTime() {
	}

	/**
	 * public constructor that insures a single instance of this class
	 * 
	 * @return PersistentTime object
	 */
	public static PersistentTime getInstance() {

		if (firstInstance == null) {

			firstInstance = new PersistentTime();
			currentTime = 1;
		}

		return firstInstance;
	}
	
	/**
	 * sets the time in a quarter system (1-4)
	 * 
	 * @param quarter
	 */
	public void setTime(int quarter) {
		firstInstance.currentTime = quarter;
	}
	
	/**
	 * returns current time as quarter(1-4)
	 * 
	 * @return currentTime
	 */
	public int getTime() {
		return firstInstance.currentTime;
	}
	
	
}

