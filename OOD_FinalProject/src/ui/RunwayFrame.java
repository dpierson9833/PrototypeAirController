package ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import gates.GateManipulator;
import gates.RunwayManipulator;
import scheduler.Flight;
import scheduler.FlightList;
import scheduler.Loader;
import takeoffandlanding.Landing;
import takeoffandlanding.RunwayControl;
import takeoffandlanding.Takeoff;

/**
 *  This class represents the visual aspect of the RunwayLaunch class, which sends planes out of their gates and into the skies
 *  It is currently broken and not in use during the final program
 *  
 * @author David
 *
 */
public class RunwayFrame {
	private GateManipulator gm;
	private RunwayManipulator rm;
	private RunwayControl rc;
	private FlightList fl;
	private JFrame frame;
	private JButton btnDone;
	private JProgressBar progressBar;
	private JLabel lbLoadingBar;
	private Task task;
	
	/**
	 * Utility class for calling rc methods
	 * 
	 * @author David
	 *
	 */
	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
        		rc.setFlightList(fl);
        		setProgress(0);
        		
        		while(rc.getStatus().equalsIgnoreCase("All runways are now clear!")) {
        			rc.departure();
        			int progress = rc.getProgress();
        			setProgress(Math.min(progress, 100));
        		}
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
        	Toolkit.getDefaultToolkit().beep();
            btnDone.setEnabled(true);
            frame.setCursor(null); //turn off the wait cursor
            lbLoadingBar.setText("All planes have left the runways!");
            progressBar.setValue(progressBar.getMaximum());
        }
    }
	
	
	/**
	 * constructor for the RunwayFrame class
	 * 
	 * @param GateManipulator gateManipulator
	 * @param RunwayManipulator runwayManipulator
	 */
	public RunwayFrame(GateManipulator gm, RunwayManipulator rm, FlightList fl) {
		this.gm = gm;
		this.rm = rm;
		this.fl = fl;
		
		rc = new RunwayControl(gm, rm);
	}
	
	/**
	 * Launch the application
	 */
	public void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunwayFrame window = new RunwayFrame(gm, rm, fl);
					
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
		frame.setBounds(100, 100, 450, 195);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel runwayPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(runwayPanel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(runwayPanel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
		);
		
		//TESTING
		System.out.println("Inside RunwayFrame intialize");
		Iterator flIter = fl.createIterator();
		while(flIter.hasNext()) {
			Flight flight = (Flight) flIter.next();
			
			System.out.println("\tFlight no " + flight.getFlightId());
		}//END TESTING
		
		if(fl == null) {
			System.out.println("\tFlightList fl is null");
		}
		
		//progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
	    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    task = new Task();
	    task.addPropertyChangeListener(new PropertyChangeListener() {
	        
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	if("progress" == evt.getPropertyName()) {
	    			int progress = (Integer) evt.getNewValue();
	                progressBar.setValue(progress);
	                lbLoadingBar.setText(rc.getStatus());
                }
            }
        });
        task.execute();
		
		lbLoadingBar = new JLabel("Waiting for status...");
		lbLoadingBar.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnDone = new JButton("Done");
		GroupLayout gl_runwayPanel = new GroupLayout(runwayPanel);
		gl_runwayPanel.setHorizontalGroup(
			gl_runwayPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_runwayPanel.createSequentialGroup()
					.addGap(82)
					.addComponent(lbLoadingBar, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
				.addGroup(gl_runwayPanel.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
				.addGroup(gl_runwayPanel.createSequentialGroup()
					.addContainerGap(193, Short.MAX_VALUE)
					.addComponent(btnDone)
					.addGap(184))
		);
		btnDone.setEnabled(false);
		gl_runwayPanel.setVerticalGroup(
			gl_runwayPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_runwayPanel.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addComponent(lbLoadingBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDone)
					.addGap(25))
		);
		runwayPanel.setLayout(gl_runwayPanel);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * what to do when progress bar changes
	 * @param evt
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            lbLoadingBar.setText(rc.getStatus());
		}
    }
	
	
	/**
	 * instantiate action listeners for the RunwayFrame
	 */
	public void createEvents() {
		//DONE BUTTON LISTENER
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}
}
