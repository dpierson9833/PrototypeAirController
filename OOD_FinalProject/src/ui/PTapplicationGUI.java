package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import gates.GateManipulator;
import gates.Gates;
import gates.RunwayManipulator;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import scheduler.PersistentTime;
import takeoffandlanding.Takeoff;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * this class represents the driver and main bulk of the UI for our project
 * 
 * @author David
 *
 */
public class PTapplicationGUI {

	//declare ui elements
	private JFrame frmPlane;
	private JPanel panel;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel welcomePanel;
	private JPanel loginPanel;
	private JPanel actionPanel;
	private JPanel panel_1;
	private JButton btnLanding;
	private JButton btnShowActiveGates;
	private JButton button_2;
	private JButton btnTakeoffDeparture;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	
	//declare manipulator
	private static GateManipulator gm;
	private static RunwayManipulator rm;
	private static Loader ld;
	private static PersistentTime pt;
	private static Takeoff to;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		pt = PersistentTime.getInstance();
		gm = new GateManipulator();
		rm = new RunwayManipulator();
		ld = new Loader(gm);
		to = new Takeoff(gm, ld);
		
		//load assets
		ld.loadFlights();
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PTapplicationGUI window = new PTapplicationGUI();
					window.frmPlane.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PTapplicationGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initComponents();
		createEvents();
	}

	/**
	 * initialize components
	 */
	private void initComponents() {
		frmPlane = new JFrame();
		frmPlane.setTitle("Aircraft Controller Prototype");
		frmPlane.setBounds(100, 100, 440, 300);
		frmPlane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmPlane.getContentPane().setLayout(new CardLayout(0, 0));
		frmPlane.setLocationRelativeTo(null);
		
		panel = new JPanel();
		frmPlane.getContentPane().add(panel, "panel");
		panel.setLayout(new CardLayout(0, 0));
		

		panel_1 = new JPanel();
		panel.add(panel_1, "panel_1");
		panel_1.setLayout(new CardLayout(0, 0));
		

		welcomePanel = new JPanel();
		panel_1.add(welcomePanel, "welcomePanel");
		
		JLabel label_6 = new JLabel("Welcome to Air Traffic Controller");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		button_6 = new JButton("LOGIN");
		
		GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
		gl_welcomePanel.setHorizontalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addGap(180)
							.addComponent(button_6))
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addGap(113)
							.addComponent(label_6)))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		gl_welcomePanel.setVerticalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addComponent(label_6)
					.addGap(18)
					.addComponent(button_6)
					.addGap(116))
		);
		welcomePanel.setLayout(gl_welcomePanel);
		
		
		loginPanel = new JPanel();
		panel_1.add(loginPanel, "loginPanel");
		
		JLabel label_3 = new JLabel("Username:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		button_4 = new JButton("Cancel");
		
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setLocation(new Point(6, 0));
		
		button_5 = new JButton("Login");

		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("Enter Employee Credentials");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_loginPanel.createSequentialGroup()
									.addContainerGap()
									.addComponent(button_4)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.LEADING, gl_loginPanel.createSequentialGroup()
									.addGap(88)
									.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_3)
										.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addComponent(passwordField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_loginPanel.createSequentialGroup()
									.addComponent(button_5)
									.addGap(32))))
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addGap(119)
							.addComponent(label_5)))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(63)
					.addComponent(label_5)
					.addGap(18)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addGap(18)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(28)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_5)
						.addComponent(button_4))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		loginPanel.setLayout(gl_loginPanel);
		
		actionPanel = new JPanel();
		panel_1.add(actionPanel, "actionPanel");
		
		btnLanding = new JButton("Landing");
		
		btnShowActiveGates = new JButton("Show Active Gates");
		
		button_2 = new JButton("Exit Program");
		
		btnTakeoffDeparture = new JButton("Takeoff & Departure");

		
		JLabel label = new JLabel("Current Weather:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_1 = new JLabel("74 F");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel label_2 = new JLabel("What Would You Like To Do?");
		label_2.setFont(new Font("Calibri", Font.BOLD, 14));
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Quarter 1", "Quarter 2", "Quarter 3", "Quarter 4"}));		
		
		GroupLayout gl_actionPanel = new GroupLayout(actionPanel);
		gl_actionPanel.setHorizontalGroup(
			gl_actionPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_actionPanel.createSequentialGroup()
					.addGroup(gl_actionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_actionPanel.createSequentialGroup()
							.addGap(54)
							.addGroup(gl_actionPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLanding, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
								.addComponent(btnTakeoffDeparture, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_actionPanel.createSequentialGroup()
									.addComponent(btnShowActiveGates, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.LEADING, gl_actionPanel.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_actionPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_actionPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(63))
								.addGroup(gl_actionPanel.createSequentialGroup()
									.addGap(95)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_actionPanel.createSequentialGroup()
							.addGap(37)
							.addComponent(label_2)))
					.addContainerGap())
				.addGroup(gl_actionPanel.createSequentialGroup()
					.addContainerGap(305, Short.MAX_VALUE)
					.addComponent(button_2)
					.addGap(26))
		);
		gl_actionPanel.setVerticalGroup(
			gl_actionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actionPanel.createSequentialGroup()
					.addGroup(gl_actionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_actionPanel.createSequentialGroup()
							.addGap(42)
							.addComponent(label_2)
							.addGap(11)
							.addGroup(gl_actionPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTakeoffDeparture)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLanding)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnShowActiveGates))
						.addGroup(gl_actionPanel.createSequentialGroup()
							.addGap(91)
							.addComponent(label_1)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addComponent(button_2)
					.addGap(22))
		);
		actionPanel.setLayout(gl_actionPanel);
	}

	/**
	 * create button responses
	 */
	private void createEvents() {
		CardLayout c = (CardLayout) panel_1.getLayout();
		
		/**
		 * action listener for the page change to the welcome panel
		 */
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel_1, "welcomePanel");
			}
		});
		
		/**
		 * action listener for the page change to the login panel
		 */
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//CHECK CREDENTIALS HERE
				
				c.show(panel_1, "loginPanel");
			}
		});
		
		/**
		 * action listener for the page change to the action panel
		 */
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.show(panel_1, "actionPanel");
			}
		});
		
		/**
		 * action listener for the exit button on the action panel
		 */
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		/**
		 * action listener for the Take-off and Departure button
		 */
		btnTakeoffDeparture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TakeoffFrame init = new TakeoffFrame(gm, rm, to, ld);
				init.createFrame();
			}
		});
		
		/**
		 * action listener for the Show Active Gates button
		 */
		btnShowActiveGates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while(true) {
					//try and create live updates for gates here if you have time
					break;
				}
				String message = "";
				
				//get full FlightList
				FlightList fl = ld.getFullFlightList();
				Iterator flIter = ld.getFullFlightList().createIterator();
				
				//get Gates
				Iterator gatesIter = gm.createIterator();
				
				//display gates
				while(gatesIter.hasNext()) {
					Gates gate = (Gates) gatesIter.next();
					
					if(gate.getPlaneID() != -1) {
						message += (gate.getGateName() + ": Flight No. " + gate.getPlaneID() + " to " + fl.getFlightAtGate(gate.getGateID(), flIter).getDestAp())
									+ "\n\n";
					}else {
						message += (gate.getGateName() + ": EMPTY")
									+ "\n\n";
					}
				}
				
				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		/**
		 * action lisener for the Select Time Combo box
		 */
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String quarterTime = (String)cb.getSelectedItem();
		        switch (quarterTime) {
					case "Quarter 1": pt.setTime(1);
						break;
					case "Quarter 2": pt.setTime(2);
						break;
					case "Quarter 3": pt.setTime(3);
						break;
					case "Quarter 4": pt.setTime(4);
						break;
		        }
		        System.out.println("Quarter changed to " + pt.getTime() + " in program controller");
			}
		});
	}
}
