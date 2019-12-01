package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gates.GateManipulator;
import scheduler.Loader;

/**
 *  This class represents the visual aspect of the RunwayLaunch class, which sends planes out of their gates and into the skies
 * @author David
 *
 */
public class RunwayFrame {
	private GateManipulator gm;
	private Loader ld;
	private JFrame frame;
	
	/**
	 * constructor for the RunwayFrame class
	 * 
	 * @param gm
	 * @param ld
	 */
	public RunwayFrame(GateManipulator gm, Loader ld) {
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
					RunwayFrame window = new RunwayFrame(gm, ld);
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
		
	}

	
	/**
	 * instantiate action listeners for the RunwayFrame
	 */
	public void createEvents() {
		
	}
}
