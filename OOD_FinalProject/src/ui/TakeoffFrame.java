package ui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import gates.GateManipulator;
import gates.RunwayManipulator;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import takeoffandlanding.RunwayControl;
import takeoffandlanding.Takeoff;
import weather.Singleton;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains the TakeOff Window which is activated by pressing the Take-off and Departure button on the main UI
 * @author David
 *
 */
public class TakeoffFrame {

	private JFrame frame;
	private JPanel flightListPanel;
	private JPanel panel_1;
	private JButton btnExecuteTakeoff;
	private JRadioButton rdbtnFlightTo;
	private JRadioButton rdbtnFlightTo_1;
	private JRadioButton rdbtnFlightTo_2;
	private List<JRadioButton> list;
	private GateManipulator gm;
	private RunwayManipulator rm;
	private Loader ld;
	private Takeoff to;
	private JButton btnCheckWeather;
	private FlightList launchedFlights = new FlightList();
	private ArrayList<Integer> flightIds = new ArrayList();
	private int numTimes;
	
	/**
	 * Create the application.
	 */
	public TakeoffFrame(GateManipulator gm, RunwayManipulator rm, Takeoff to, Loader ld) {
		this.gm = gm;
		this.rm = rm;
		this.ld = ld;
		this.to = to;
	}
	
	
	/**
	 * Launch the application.
	 */
	public void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeoffFrame window = new TakeoffFrame(gm, rm, to, ld);
					window.initialize();
					window.createEvents();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0));
		
		flightListPanel = new JPanel();
		flightListPanel.setLayout(new GridLayout(0, 1));
		
		JScrollPane scrollPane_1 = new JScrollPane(flightListPanel);
		
		panel.add(scrollPane_1);
		panel.add(scrollPane_1);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		
		initRadioButtons();
		
		btnCheckWeather = new JButton("Check Weather");
		
		btnExecuteTakeoff = new JButton("Execute Take-Off");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(80, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCheckWeather, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnExecuteTakeoff, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(35))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(86)
					.addComponent(btnCheckWeather)
					.addGap(18)
					.addComponent(btnExecuteTakeoff)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		
	}
	
	
	/**
	 * instantiates the list of radio based on the planes that are eligible to be launched
	 */
	public void initRadioButtons() {
		//Load takeoff to see what can be launched
		FlightList fl = to.getTakeoffList();
		
		//displays to console for debugging purposes
		Iterator flIter = fl.createIterator();
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();
			
			System.out.println("Inside initRadioButtons, Flight " + flight.getFlightId() + " headed to " + flight.getDestAp() + " ready for takeoff!");
		}//end of while
		
		//TESTING
		System.out.println("Inside TakeoffFrame.initRadioButtons()");
		
		Iterator flightListIter = fl.createIterator(); 
		list = new ArrayList<>();
		
		//adds JRadioButtons for each eligible flight
		int index = 0;
		while(flightListIter.hasNext()) {
			//TESTING
			System.out.println("Inside initRadioButtons 'adds JRadioButtons for each eligible flight'");
			
			Flight flight = (Flight) flightListIter.next();
			
			//add it to the list of JRadioButtons
			JRadioButton jrb = new JRadioButton("\tFlight " + flight.getFlightId() + " headed to " + flight.getDestAp());
			
			list.add(jrb);
			flightListPanel.add(jrb);
			
			flightIds.add(flight.getFlightId());
			index++;
			
			System.out.println("\tIndex: " + index);
			System.out.println("\tFlight ID: " + flight.getFlightId());
			
		}
	}
	
	/**
	 * instantiate action listeners for the TakeoffFrame
	 */
	public void createEvents() {
		btnCheckWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(frame, to.getWeatherString() + " F");
			}
		});
		
		btnExecuteTakeoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TESTING
				System.out.println("Inside action listener for executetakeoff");
				
				
				launchedFlights = new FlightList();
				String flightNames = "";
				
				int index = 0;
				for (JRadioButton jrb : list) {
                    if (jrb.isSelected()) {
                    	flightNames += jrb.getText() + "\n ";
                    	
                    	//add flights to launchedFlights
                    	addLaunchedFlight(flightIds.get(index));
                    	index++;
                    	//end of adding flights
                    	
                    }//end of if
                }//end of for
				
				//sets launched flights
				to.setLaunchedFlights(launchedFlights);
				
				//if no flights are selected, change display
				String message = "";
				if(flightNames == "") {
					message = "No planes were sent to the runway!";
					JOptionPane.showMessageDialog(flightListPanel, message);
					frame.dispose();
				}else {
					message = "Sending \n" + flightNames + "to runway!";
					
					//This will be removed when runways are working
					JOptionPane.showMessageDialog(flightListPanel, message);
					frame.dispose();
				}
				System.out.println(message); //FOR DEBUGGING
				
				
				//remove flights from their gates
				//remove planes from gates
				Iterator flIter = launchedFlights.createIterator();
				while(flIter.hasNext()) {
					Flight flight = (Flight) flIter.next();
					
					System.out.println("\tFlight no. " + flight.getFlightId() + " removed from gate " + flight.getGateId());
					
					ld.getQuarter1().removeFromList(flight);
					gm.removePID(flight.getGateId());
					flight.setGateId(-1);
				}//end of while
				
				
				//currently unavailable functionality
//				if(message.contains("Sending ")) {
//					frame.dispose();
//					RunwayFrame rw = new RunwayFrame(gm, rm, to.getLaunchedFlights());
//					rw.createFrame();
//				}
			}
		});
	}
	
	/**
	 * utility method that puts Flight into a list to be sent to be sent to the runway
	 * 
	 * @param flightId
	 */
	public void addLaunchedFlight(int flightId) {
		FlightList fl = ld.getFullFlightList();
		Flight flight = fl.getFlight(fl.findFlightIndex(flightId));
		
		launchedFlights.addToList(flight);
	}
}
