package demo.aggregation.form;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import demo.aggregation.driver.StudentAggregationDriver;
import demo.common.driver.AggregationDriver;
import demo.common.model.AggregationModel;
import demo.common.util.FileUtils;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

/**
 * The user interface used by the main UI.
 * It contains filters for driver and input file name
 * Aggregate button is used to generate the output file
 * Close button is used to close the panel and all the wrappers.
 * AggregationModel: model for the UI, it stores the necessary filter informations
 * drivers: the list of all the drivers can be used, there is only one right now
 * TODO: Use the JFileChooser to select the input file and output file
 * 
 * @author richardl
 */
public class AggregationControlPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AggregationControlPanel() {
		initComponents();
	}

	private void initComponents() {
		// init drivers
		drivers.put("Student Aggregation Driver",
				StudentAggregationDriver.getDefault());

		setLayout(new BorderLayout(0, 0));

		btnPanel = new JPanel();
		add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		aggregateBtn = new JButton("Aggregate");
		aggregateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aggregateBtnActionPerformed(e);
			}
		});
		btnPanel.add(aggregateBtn);

		closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeBtnActionPerformed(e);
			}
		});
		btnPanel.add(closeBtn);

		NorthPanel = new JPanel();
		add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setLayout(new BoxLayout(NorthPanel, BoxLayout.X_AXIS));

		driverPanel = new JPanel();
		NorthPanel.add(driverPanel);
		driverPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		driverLabel = new JLabel("Select Driver");
		driverPanel.add(driverLabel);

		driverComboBox = new JComboBox();
		updateDriverComboBox();
		driverComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				driverComboBoxActionPerformed(e);
			}
		});

		driverPanel.add(driverComboBox);

		inputFilePanel = new JPanel();
		NorthPanel.add(inputFilePanel);
		inputFilePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		inputFileLabel = new JLabel("Input File Name: ");
		inputFilePanel.add(inputFileLabel);

		inputFileCB = new JComboBox();
		initInputFileCB();
		inputFileCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputFileCBActionPerformed(e);
			}
		});
		inputFilePanel.add(inputFileCB);
	}

	/* Set the driver list from outside */
	public void setDrivers(Map<String, AggregationDriver> drivers) {
		this.drivers.clear();
		this.drivers.putAll(drivers);
		updateDriverComboBox();
	}

	/* Once the drivers changed, update the driver combo box value*/
	private void updateDriverComboBox() {
		driverComboBox.removeAllItems();
		for (String key : drivers.keySet()) {
			driverComboBox.addItem(key);
		}
	}

	/**
	 *  Hard coded 5 sample file name into the input file drop down,
	 *  TODO: use JFileChooser to choose file
	 */
	private void initInputFileCB() {
		inputFileCB.addItem("testSample1");
		inputFileCB.addItem("testSample2");
		inputFileCB.addItem("testSample3");
		inputFileCB.addItem("testSample4");
		inputFileCB.addItem("testSample5");
	}

	/* Update the model, if selecting a different driver */
	private void driverComboBoxActionPerformed(ActionEvent e) {
		model.setAggregationDriver(drivers.get(driverComboBox.getSelectedItem()));
	}

	/* Run the aggregation */
	private void aggregateBtnActionPerformed(ActionEvent e) {
		model.aggregate();
	}

	/* Close the UI and release the resources */
	private void closeBtnActionPerformed(ActionEvent e) {
		Container scanner = this.getParent();
		while (scanner != null) {
			if (scanner instanceof JDialog || scanner instanceof JFrame) {
				break;
			}
			scanner = scanner.getParent();
		}
		if (scanner instanceof JDialog) {
			((JDialog) scanner).setVisible(false);
			((JDialog) scanner).dispose();
		} else if (scanner instanceof JFrame) {
			((JFrame) scanner).setVisible(false);
			((JFrame) scanner).dispose();
		}
	}

	/* update the model if selecting a different input file */
	private void inputFileCBActionPerformed(ActionEvent e) {
		model.setInputFileName(FileUtils.getAbsolutePath() + "\\input\\"
				+ inputFileCB.getSelectedItem() + ".txt");
		model.setOutputFileName(FileUtils.getAbsolutePath()
				+ "\\output\\result-" + inputFileCB.getSelectedItem() + ".txt");
	}

	/* Set the model from outside and do the basic model initialization based on UI filters */
	public void setModel(AggregationModel model) {
		this.model = model;
		model.setAggregationDriver(drivers.get(driverComboBox.getSelectedItem()));
		model.setInputFileName(FileUtils.getAbsolutePath() + "\\input\\"
				+ inputFileCB.getSelectedItem() + ".txt");
		model.setOutputFileName(FileUtils.getAbsolutePath()
				+ "\\output\\result-" + inputFileCB.getSelectedItem() + ".txt");
	}

	private JPanel inputFilePanel;
	private JLabel inputFileLabel;
	private JPanel driverPanel;
	private JLabel driverLabel;
	private JComboBox driverComboBox;
	private JPanel btnPanel;
	private JButton aggregateBtn;
	private JButton closeBtn;
	private JComboBox inputFileCB;
	private JPanel NorthPanel;

	private AggregationModel model;
	private final Map<String, AggregationDriver> drivers = new TreeMap<>();
}
