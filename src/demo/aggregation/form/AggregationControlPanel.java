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

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

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

	public void setDrivers(Map<String, AggregationDriver> drivers) {
		this.drivers.clear();
		this.drivers.putAll(drivers);
		updateDriverComboBox();
	}

	private void updateDriverComboBox() {
		driverComboBox.removeAllItems();
		for (String key : drivers.keySet()) {
			driverComboBox.addItem(key);
		}
	}

	private void initInputFileCB() {
		inputFileCB.addItem("testSample1");
		inputFileCB.addItem("testSample2");
		inputFileCB.addItem("testSample3");
		inputFileCB.addItem("testSample4");
		inputFileCB.addItem("testSample5");
	}

	private void driverComboBoxActionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		model.setAggregationDriver(drivers.get(cb.getSelectedItem()));
	}

	private void aggregateBtnActionPerformed(ActionEvent e) {
		model.aggregate();
	}

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

	private void inputFileCBActionPerformed(ActionEvent e) {
		model.setInputFileName(new File(".").getAbsolutePath() + "\\input\\"
				+ inputFileCB.getSelectedItem() + ".txt");
		model.setOutputFileName(new File(".").getAbsolutePath()
				+ "\\output\\result-" + inputFileCB.getSelectedItem() + ".txt");
	}

	public void setModel(AggregationModel model) {
		this.model = model;
		model.setAggregationDriver(drivers.get(driverComboBox.getSelectedItem()));
		model.setInputFileName(new File(".").getAbsolutePath() + "\\input\\"
				+ inputFileCB.getSelectedItem() + ".txt");
		model.setOutputFileName(new File(".").getAbsolutePath()
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
	private File previousFile;
	private final Map<String, AggregationDriver> drivers = new TreeMap<>();
}
