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
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import takeoffandlanding.Takeoff;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	private Loader ld;
	
	/**
	 * Create the application.
	 */
	public TakeoffFrame(GateManipulator gm, Loader ld) {
		this.gm = gm;
		this.ld = ld;
	}
	
	
	/**
	 * Launch the application.
	 */
	public void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeoffFrame window = new TakeoffFrame(gm, ld);
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
		
		JButton btnCheckWeather = new JButton("Check Weather");
		
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
		Takeoff to = new Takeoff(gm, ld);
		FlightList fl = to.takeoff();
		
		//displays to console for debugging purposes
		Iterator flIter = fl.createIterator();
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();
			
			System.out.println("Flight " + flight.getFlightId() + " headed to " + flight.getDestAp() + " ready for takeoff!");
		}//end of while
		
		//TESTING
		System.out.println("Inside TakeoffFrame.initRadioButtons()");
		
		Iterator flightListIter = fl.createIterator(); 
		list = new ArrayList<>();
		
		//adds JRadioButtons for each eligible flight
		while(flightListIter.hasNext()) {
			Flight flight = (Flight) flightListIter.next();
			
			JRadioButton jrb = new JRadioButton("Flight " + flight.getFlightId() + " headed to " + flight.getDestAp());
			list.add(jrb);
			flightListPanel.add(jrb);
		}
	}
	
	/**
	 * instantiate action listeners for the TakeoffFrame
	 */
	public void createEvents() {
		btnExecuteTakeoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String flightNames = "";
				
				for (JRadioButton jrb : list) {
                    if (jrb.isSelected()) {
                    	flightNames += jrb.getText() + " ";
                    }//end of if
                }//end of for
				
				//if no flights are selected, change display
				String message = (flightNames == "") ? "No planes were sent to the runway!" : "Sending " + flightNames + "to runway!";
				JOptionPane.showMessageDialog(frame, message);
			}
		});
	}
}
