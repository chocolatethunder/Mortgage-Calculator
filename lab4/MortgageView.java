/***

This MortgageView class passes the user data from the UI to the controller and 
displays the processed data on the GUI passed on by the controller and the model. 

@author Saurabh Tomar

Written for CPSC 233

*/

package lab4;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;

public class MortgageView extends JFrame {
	
	// Set the disaply UI Resolution
	private static final int WIN_WIDTH = 640;
	private static final int WIN_HEIGHT = 600;
	
	// Layout UI Components
	private JPanel mainPanel = new JPanel(new GridLayout(3,3));	
	
	// Input UI Components
	private JLabel numOfMonPayLabel1 = new JLabel ("Amortization period");
	private JTextField numOfMonPay = new JTextField(3);
	private JLabel numOfMonPayLabel2 = new JLabel ("Months");
	
	private JLabel principleLabel1 = new JLabel ("Principle");
	private JLabel principleLabel2 = new JLabel ("$");
	private JTextField principle = new JTextField(7);
	
	private JLabel annInterestLabel1 = new JLabel ("Annual Interest");
	private JTextField annIntRate = new JTextField(5);
	private JLabel annInterestLabel2 = new JLabel ("%");
	
	private JLabel payOptionsLabel = new JLabel ("Payment");
	private String[] payOptions = {"Monthly","Bi-Weekly","Weekly"};
	private JComboBox<String> payFreq = new JComboBox<>(payOptions);
	
	private JLabel compOptionsLabel = new JLabel ("Compounding");
	private String[] compOptions = {"Daily","Weekly","Monthly","Semi-Annually","Annually"};
	private JComboBox<String> compFreq = new JComboBox<>(compOptions);
	
	private JButton calculateMortgage = new JButton("Calculate");
	private JButton endProgram = new JButton("Close");
	
	// OutPut UI Components
	private JLabel monthPaymentLabel1 = new JLabel ("Monthly Payment");
	private JLabel monthPaymentLabel2 = new JLabel ("$");
	private JTextField monthlyPayment = new JTextField(7);
	
	private JLabel totalInterestLabel1 = new JLabel ("Total Interest");
	private JLabel totalInterestLabel2 = new JLabel ("$");
	private JTextField totalInterest = new JTextField(7);
	
	private JLabel totalIntAndPrincLabel1 = new JLabel ("Total Mortgage");
	private JLabel totalIntAndPrincLabel2 = new JLabel ("$");
	private JTextField totalIntAndPrinc = new JTextField(10);
	
	private JLabel interToPrincLabel = new JLabel ("Interest to Principle Ratio");
	private JTextField interToPrincRatio = new JTextField(5);
	
	private JLabel avgIntPerYearLabel1 = new JLabel ("Average Interest Per Year");
	private JLabel avgIntPerYearLabel2 = new JLabel ("$");
	private JTextField avgInterestPerYear = new JTextField(7);
	
	private JLabel avgIntPerMonthLabel1 = new JLabel ("Average Interest Per Month");
	private JLabel avgIntPerMonthLabel2 = new JLabel ("$");
	private JTextField avgInterestPerMonth = new JTextField(7);
	
	private JLabel amortYearsLabel1 = new JLabel ("Amortization period");
	private JTextField amortYears = new JTextField(5);
	private JLabel amortYearsLabel2 = new JLabel ("Year(s)");
	
	// Table Setup
	private String[] colNames = {"Periodic Payment","Monthly Payment","Interest Paid","Principle Paid","Balance Owing"};
	private DefaultTableModel tableModel = new DefaultTableModel(null, colNames);
	private JTable dataTable = new JTable(tableModel);
	private JScrollPane tableScroll = new JScrollPane(dataTable);
	
	// Misc
	private DecimalFormat df = new DecimalFormat("0.00");
	
	
	public MortgageView() {
		
		// Setup the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIN_WIDTH,WIN_HEIGHT);
		this.setTitle("Maze Bank Mortgage Calculator");
		
		// Setup all the components of the input UI components
			JPanel inputPanel = new JPanel();
			
				JPanel inputPanel_Sub1 = new JPanel();
				
					inputPanel_Sub1.add(numOfMonPayLabel1);
					inputPanel_Sub1.add(numOfMonPay);
					inputPanel_Sub1.add(numOfMonPayLabel2);
					numOfMonPay.setText("24");
					
				JPanel inputPanel_Sub2 = new JPanel();
					
					inputPanel_Sub2.add(principleLabel1);
					inputPanel_Sub2.add(principleLabel2);
					inputPanel_Sub2.add(principle);
					principle.setText("100000");
				
				JPanel inputPanel_Sub3 = new JPanel();
				
					inputPanel_Sub3.add(annInterestLabel1);
					inputPanel_Sub3.add(annIntRate);
					inputPanel_Sub3.add(annInterestLabel2);
					annIntRate.setText("5");
				
				JPanel inputPanel_Sub4 = new JPanel();
					
					payFreq.setSelectedItem(payOptions[0]);
					inputPanel_Sub4.add(payOptionsLabel);
					inputPanel_Sub4.add(payFreq);
				
				JPanel inputPanel_Sub5 = new JPanel();
					
					compFreq.setSelectedItem(compOptions[3]);
					inputPanel_Sub5.add(compOptionsLabel);
					inputPanel_Sub5.add(compFreq);
					
				JPanel inputPanel_Sub6 = new JPanel();
					
					inputPanel_Sub6.add(calculateMortgage);
					inputPanel_Sub6.add(endProgram);
			
		// Add all the components of the input UI components
			inputPanel.add(inputPanel_Sub1);
			inputPanel.add(inputPanel_Sub2);
			inputPanel.add(inputPanel_Sub3);
			inputPanel.add(inputPanel_Sub4);
			inputPanel.add(inputPanel_Sub5);
			inputPanel.add(inputPanel_Sub6);
			
		// Setup all the components of the output UI components
			JPanel outputPanel = new JPanel();
				
				JPanel outputPanel_Sub1 = new JPanel();
				
					outputPanel_Sub1.add(monthPaymentLabel1);
					outputPanel_Sub1.add(monthPaymentLabel2);
					outputPanel_Sub1.add(monthlyPayment);
					monthlyPayment.setEditable(false);
					
					outputPanel_Sub1.add(totalInterestLabel1);
					outputPanel_Sub1.add(totalInterestLabel2);
					outputPanel_Sub1.add(totalInterest);
					totalInterest.setEditable(false);
				
				JPanel outputPanel_Sub2 = new JPanel();
				
					outputPanel_Sub2.add(totalIntAndPrincLabel1);
					outputPanel_Sub2.add(totalIntAndPrincLabel2);
					outputPanel_Sub2.add(totalIntAndPrinc);
					totalIntAndPrinc.setEditable(false);
					
					outputPanel_Sub2.add(interToPrincLabel);
					outputPanel_Sub2.add(interToPrincRatio);
					interToPrincRatio.setEditable(false);
				
				JPanel outputPanel_Sub3 = new JPanel();
				
					outputPanel_Sub3.add(avgIntPerYearLabel1);
					outputPanel_Sub3.add(avgIntPerYearLabel2);
					outputPanel_Sub3.add(avgInterestPerYear);
					avgInterestPerYear.setEditable(false);
					
					outputPanel_Sub3.add(avgIntPerMonthLabel1);
					outputPanel_Sub3.add(avgIntPerMonthLabel2);
					outputPanel_Sub3.add(avgInterestPerMonth);
					avgInterestPerMonth.setEditable(false);
				
				JPanel outputPanel_Sub4 = new JPanel();
				
					outputPanel_Sub4.add(amortYearsLabel1);
					outputPanel_Sub4.add(amortYears);
					outputPanel_Sub4.add(amortYearsLabel2);
					amortYears.setEditable(false);
		
		// Add all the components of the output UI components
			outputPanel.add(outputPanel_Sub1);
			outputPanel.add(outputPanel_Sub2);
			outputPanel.add(outputPanel_Sub3);	
			outputPanel.add(outputPanel_Sub4);	

		// Setup all the components of the table components
			JPanel tableDisplay = new JPanel();				
				
				tableDisplay.setLayout(new BorderLayout());
				tableDisplay.add(tableScroll, BorderLayout.CENTER);
		
		// Add all the panels containing the UI elements to the main panel
		mainPanel.add(inputPanel);
		mainPanel.add(outputPanel);
		mainPanel.add(tableDisplay);		
		
		// Add the main panel to the frame
		this.add(mainPanel);
		
	}
	
	// Get 
	
	/***
	Passes the total number of Amortization in months
	@return Returns the total number of Amortization in months
	*/
	public int getAmortMonths() {
		return Integer.parseInt(numOfMonPay.getText());	
	}
	
	/***
	Passes the principle amount of the mortgage 
	@return Returns the principle amount of the mortgage
	*/
	public double getPrinciple() {
		return Double.parseDouble(principle.getText());
	}
	
	/***
	Passes the interest rate
	@return Returns the interest factor
	*/
	public double getInterestRate() {
		return Double.parseDouble(annIntRate.getText());
	}
	
	/***
	Passes the payment frequency in string format
	@return Returns payment frequency in string format
	*/
	public String getPaymentFreq() {
		return (String)payFreq.getSelectedItem();
	}
	
	/***
	Passes the compound frequency in string format
	@return Returns compound frequency in string format
	*/
	public String getCompoundFreq() {
		return (String)compFreq.getSelectedItem();
	}
	
	// Set 
	
	/***
	Displays the value of the blended monthly payment on the GUI
	@param monPay Blended Monthly Payment
	*/
	public void setMonthlyPayment(double monPay) {
		monthlyPayment.setText(String.format("%.2f", monPay));
	}
	
	/***
	Displays the total interest paid for the mortgage on the GUI
	@param totInt Total Interest on Mortgage
	*/
	public void setTotalInterest(double totInt) {
		totalInterest.setText(String.format("%.2f", totInt));
	}
	
	/***
	Displays the total of the mortgage and total interest paid on the mortgage on the GUI
	@param totMort Total principle and interest
	*/
	public void setTotalMortgage(double totMort) {
		totalIntAndPrinc.setText(String.format("%.2f", totMort));
	}
	
	/***
	Displays the interest to principle ratio on the GUI
	@param ipRatio Interest to principle ratio in decimal
	*/
	public void setInterestPrincipleRatio(double ipRatio) {
		interToPrincRatio.setText(String.format("%.2f", ipRatio));
	}
	
	/***
	Displays the average interest paid per year on the GUI
	@param avgIntYear Average interest paid per year
	*/
	public void setAvgInterestPaidYearly(double avgIntYear) {
		avgInterestPerYear.setText(String.format("%.2f", avgIntYear));
	}
	
	/***
	Displays the average interest paid per month on the GUI
	@param avgIntMon Average interest paid per month
	*/
	public void setAvgInterestMonthly(double avgIntMon) {
		avgInterestPerMonth.setText(String.format("%.2f", avgIntMon));
	}
	
	/***
	Displays the amortization in years on the GUI
	@param amrtyears Amortization in years
	*/
	public void setAmortInYears(double amrtyears) {
		amortYears.setText(String.format("%.2f", amrtyears));
	}
	
	/***
	Displays the mortgage data in a table
	@param mortData Array containing mortgage schedule
	*/
	public void setMortdata(double[][] mortData) {
		
		// Clean the previous table data
		dataTable.invalidate();
		tableModel.setRowCount(0);
		
		// Add the data row by row to the table
		for (int i = mortData.length-1; i >= 0; i--) {
			
			tableModel.addRow(new Object[]{
				String.format("%.0f",mortData[i][0]),
				String.format("$ %.2f",mortData[i][1]),
				String.format("$ %.2f",mortData[i][2]),
				String.format("$ %.2f",mortData[i][3]),
				String.format("$ %.2f",mortData[i][4])
				});
	
		}	
		
		// Reset the scrollpane
		tableScroll.repaint();
	}
	
	// Controllers 
	
	/***
	Tells which object to fire when calculateMortgage button is clicked
	@param theListener The listening object
	*/
	public void addCalculateListener(ActionListener theListener) {
		calculateMortgage.addActionListener(theListener);
	}
	
	/***
	Tells which object to fire when endProgram button is clicked
	@param theListener The listening object
	*/
	public void addCloseListener(ActionListener theListener) {
		endProgram.addActionListener(theListener);
	}
	
}