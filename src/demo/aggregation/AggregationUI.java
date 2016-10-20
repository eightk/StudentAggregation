package demo.aggregation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Level;

import demo.aggregation.form.AggregationControlPanel;
import demo.aggregation.model.StudentAggregationModel;
import demo.common.log.LoggingUtils;

/**
 * This is the main method with simple user interfaces. User can select the input from 5 preset source files from input folder.
 * Then the form will generate the corresponding output file in output folder.
 *  
 * @author $Author: richardl $
 */
public class AggregationUI {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AggregationUI window = new AggregationUI();					
					window.frame.setVisible(true);
					window.controlPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AggregationUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoggingUtils.initializeLogger();
		frame = new JFrame();
		frame.setBounds(100, 100, 543, 135);

		controlPanel = new AggregationControlPanel();
		controlPanel.setModel(new StudentAggregationModel());
		frame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
	
	private JFrame frame;
	private AggregationControlPanel controlPanel;

}
