/***

This MortgageController class uses the processing from the Model to display 
or capture data from the View. 

@author Saurabh Tomar

Written for CPSC 233

*/

package lab4;

import java.awt.event.*;

public class MortgageController {
	
	private MortgageModel theModel;
	private MortgageView theView;	
	
	/***
	Default constructor uses the Model and the View object passed to create listener events
	@param theModel Instance object of the processing class that does the calculations
	@param theView Instance object of the GUI interface to cpature and display information
	*/
	public MortgageController(MortgageModel theModel, MortgageView theView) {
		this.theModel = theModel;
		this.theView = theView;
		// Listen for the Calculate button
		this.theView.addCalculateListener(new MortgageListener());
		// Listen for the Close button
		this.theView.addCloseListener(new EndprogramListener());
	}
	
	/***
	Response for capturing the required info and displaying the results to the View
	*/
	class MortgageListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// Capture the data from the user
			theModel.setNumOfMonthlyPayments(theView.getAmortMonths());
			theModel.setPrinciple(theView.getPrinciple());
			theModel.setAnnIntRate(theView.getInterestRate());			
			String compFreq = theView.getCompoundFreq();
			String payFreq = theView.getPaymentFreq();
			
			// Set options for the compunding frequency
			switch (compFreq) {
				
				case "Daily":
				theModel.setCompoundFreq(365.0);
				break;
				
				case "Weekly":
				theModel.setCompoundFreq(52.0);
				break;
				
				case "Monthly":
				theModel.setCompoundFreq(12.0);
				break;
				
				case "Semi-Annually":
				theModel.setCompoundFreq(2.0);
				break;
				
				case "Annually":
				theModel.setCompoundFreq(1.0);
				break;
				
				default:
				theModel.setCompoundFreq(2.0);
				break;
				
			}
			
			// Set options for payment frequency
			switch (payFreq) {
				
				case "Monthly":
				theModel.setPaymentFreq(12.0);
				break;
				
				case "Bi-Weekly":
				theModel.setPaymentFreq(26.0);
				break;
				
				case "Weekly":
				theModel.setPaymentFreq(52.0);
				break;
				
				default:
				theModel.setPaymentFreq(12.0);
				break;
				
			}
			
			// Process and capture the morgage data
			theView.setMortdata(theModel.getSchedule());
			
			// Pass the results from the Model's caluclations to the View to display. 
			theView.setMonthlyPayment(theModel.getBlendPayment());
			theView.setTotalInterest(theModel.getTotalInterest());			
			theView.setTotalMortgage(theModel.totalCostOfMortgage());			
			theView.setInterestPrincipleRatio(theModel.getRatio());
			theView.setAvgInterestPaidYearly(theModel.getAvgInterestPerYear());
			theView.setAvgInterestMonthly(theModel.getAvgInterestPerMonth());
			theView.setAmortInYears(theModel.getAmrtInYears());			
			
		}	
		
	}
	
	/***
	Response for closing the program
	*/
	class EndprogramListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			System.exit(0);	
			
		}	
		
	}
	

}