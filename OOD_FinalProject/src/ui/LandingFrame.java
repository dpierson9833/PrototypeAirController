package ui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import gates.GateManipulator;
import gates.RunwayManipulator;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import takeoffandlanding.Landing;
import takeoffandlanding.RunwayControl;
import takeoffandlanding.Takeoff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains the Landing Window which is activated by pressing the Landing button on the main UI
 * @author David
 *
 */
public class LandingFrame {

	private JFrame frame;
	private JPanel flightListPanel;
	private JPanel panel_1;
	private JButton btnExecuteLanding;
	private JButton btnCheckWeather;
	private JRadioButton rdbtnFlightTo;
	private JRadioButton rdbtnFlightTo_1;
	private JRadioButton rdbtnFlightTo_2;
	private List<JRadioButton> list;
	private GateManipulator gm;
	private RunwayManipulator rm;
	private Loader ld;
	private Landing ln;
	private FlightList landedFlights = new FlightList();
	private ArrayList<Integer> flightIds = new ArrayList();
	private int numTimes;
	

	/**
	 * Create the application.
	 */
	public LandingFrame(GateManipulator gm, RunwayManipulator rm, Landing ln, Loader ld) {
		this.gm = gm;
		this.rm = rm;
		this.ld = ld;
		this.ln = ln;
	}


	/**
	 * Launch the application.
	 */
	public void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingFrame window = new LandingFrame(gm, rm, ln, ld);
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

		btnExecuteLanding = new JButton("Execute Landing");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
						.addContainerGap(80, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnCheckWeather, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExecuteLanding, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(35))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(86)
						.addComponent(btnCheckWeather)
						.addGap(18)
						.addComponent(btnExecuteLanding)
						.addContainerGap(115, Short.MAX_VALUE))
				);
		panel_1.setLayout(gl_panel_1);



	}


	/**
	 * instantiates the list of radio based on the planes that are eligible to be landed
	 */
	public void initRadioButtons() {
		//Load takeoff to see what can be launched
		FlightList fl = ln.getLandingList();

		//displays to console for debugging purposes
		Iterator flIter = fl.createIterator();
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();

			System.out.println("Inside initRadioButtons, Flight " + flight.getFlightId() + " headed to gate " + flight.getExpGateId() + " ready for landing!");
		}//end of while

		//TESTING
		System.out.println("Inside LandingFrame.initRadioButtons()");

		Iterator flightListIter = fl.createIterator(); 
		list = new ArrayList<>();

		//adds JRadioButtons for each eligible flight
		int index = 0;
		while(flightListIter.hasNext()) {
			//TESTING
			System.out.println("Inside initRadioButtons 'adds JRadioButtons for each eligible flight'");

			Flight flight = (Flight) flightListIter.next();

			//add it to the list of JRadioButtons
			JRadioButton jrb = new JRadioButton("\tFlight " + flight.getFlightId() + " from " + flight.getSourceAp());

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
			public void actionPerformed(ActionEvent arg0){
				//Testing
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(frame, "Weather is " + ln.getWeatherString() + " F");

			}
		});
		btnExecuteLanding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TESTING
				System.out.println("Inside action listener for execute Landing");


				landedFlights = new FlightList();
				String flightNames = "";

				int index = 0;
				for (JRadioButton jrb : list) {
					if (jrb.isSelected()) {
						flightNames += jrb.getText() + "\n ";

						//add flights to launchedFlights
						addLandedFlight(flightIds.get(index));
						index++;
						//end of adding flights

					}//end of if
				}//end of for
//
//				//sets launched flights
//				to.setLaunchedFlights(landeddFlights);

				//if no flights are selected, change display
				String message = "";
				if(flightNames == "") {
					message = "No planes were landed!";
					JOptionPane.showMessageDialog(flightListPanel, message);
					frame.dispose();
				}else {
					message = "Sending \n" + flightNames + "to their gates!";

					//This will be removed when runways are working
					JOptionPane.showMessageDialog(flightListPanel, message);
					frame.dispose();
				}
				System.out.println(message); //FOR DEBUGGING


				//remove flights from their gates
				//remove planes from gates
				Iterator flIter = landedFlights.createIterator();
				while(flIter.hasNext()) {
					Flight flight = (Flight) flIter.next();
					int gateId = gm.findGateId(flight.getExpGateId());

					flight.setGateId(gateId);
					gm.updatePID(flight.getFlightId(), gateId);
					flight.setToD(flight.getToA());
					flight.setToA(-1);
				}//end of while
			}
		});
	}

	/**
	 * utility method that puts Flight into a list to be sent to be sent to the runway
	 * 
	 * @param flightId
	 */
	public void addLandedFlight(int flightId) {
		FlightList fl = ld.getFullFlightList();
		Flight flight = fl.getFlight(fl.findFlightIndex(flightId));

		landedFlights.addToList(flight);
	}
}