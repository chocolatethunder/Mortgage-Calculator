/***

This MortgageModel class recieves and processes the data and passes it to the controller

@author Saurabh Tomar

Written for CPSC 233

*/

package lab4;

public class MortgageModel {
	
	// Initialize variables
	private int numMonPayments;
	private double principle;
	private double annIntRate;
	private double compFreq;
	private double payFreq;
	private double lengthInYears;
	private double totalNumberOfPayments;	
	private double interest;
	private double blendedPayment;	
	private double totalInterest;	
	private double[][] schedule;
	
	/***
	Empty constructor for the model
	*/
	public MortgageModel() {
		this(0,0.0,5.0,2.0,1.0);
	}
	
	/***
	Constructor for code testing without the use of a GUI
	@param numMonPayments Amortization of mortage in months
	@param principle The principle amount of the mortgage
	@param annIntRate The annual interest rate of the mortgage
	@param compoundFreq The compound frequency (daily(365), weekly(52), monthly(12), semi-annually(2), annually(1))
	@param paymentFreq How often the customer will be making payments in a month (weekly(52), bi-weekly(26), monthly(12))
	*/
	public MortgageModel(int numMonPayments, double principle, double annIntRate, double compoundFreq, double paymentFreq) {
		this.numMonPayments = numMonPayments;
		this.principle 		= principle;
		this.annIntRate 	= (annIntRate/100);
		this.compFreq 		= compoundFreq;
		this.payFreq 		= paymentFreq;
		
		this.lengthInYears 			= this.numMonPayments/12.0;
		this.totalNumberOfPayments 	= this.lengthInYears*this.payFreq;
	}
	
	// Set 
	
	/***
	Sets the total number of monthly payments for the length of the mortgage and calculate the length of the mortgage in years
	@param numOfPayments Total number of payment months in the mortage
	*/
	public void setNumOfMonthlyPayments(int numOfPayments) {		
		this.numMonPayments = numOfPayments;	
		this.lengthInYears 	= this.numMonPayments/12.0;
	}
	
	/***
	Sets the principle amount of the mortgage
	@param principle Principle amount of the mortgage
	*/
	public void setPrinciple(double principle) {
		this.principle = principle;
	}
	
	/***
	Sets the annual interest rate in a x/100 format
	@param interestRate Annual interest rate
	*/
	public void setAnnIntRate(double interestRate) {
		this.annIntRate = (interestRate/100);
	}
	
	/***
	Sets the frequency during a year in which interest will be compounded. (daily(365), weekly(52), monthly(12), semi-annually(2), annually(1))
	@param compFreq Compound Frequency
	*/
	public void setCompoundFreq (double compFreq) {
		this.compFreq = compFreq;
	}
	
	/***
	Sets the how often a customer will be paying their mortgage in a month. (weekly(52), bi-weekly(26), monthly(12))
	@param payFreq Payment Frequency
	*/
	public void setPaymentFreq(double payFreq) {
		this.payFreq = payFreq;
		this.totalNumberOfPayments = this.lengthInYears*this.payFreq;
	}
	
	// Calculate
	
	/***
	Calculate the interest factor
	@return Interest factor
	*/
	private double calculateInterest() {
		return Math.pow(((this.annIntRate/this.compFreq) + 1),(this.compFreq/this.payFreq)) - 1;
	}
	
	/***
	Calculate the blended monthly payment
	@return Monthly Payment
	*/
	private double calculateMonthlyPayment() {
		return (this.principle * this.calculateInterest())/(1 - Math.pow((this.calculateInterest()+1),(this.totalNumberOfPayments*-1)));
	}
	
	// Get
	
	/***
	Calculate the schedule of the mortgage and store it in an array to be displayed later
	@return The array containing the schedule of the mortage payment
	*/
	public double[][] getSchedule() {
		
		// Initialize variables
		int counter 		= (int)this.totalNumberOfPayments-1;
		int month			= 1;
		
		// Store the calculations into a variable to prevent stress on the system
		double principleAmt = this.principle;
		double interestFact = this.calculateInterest();
		this.blendedPayment = this.calculateMonthlyPayment();
		
		// Start a dynamic array
		this.schedule 		= new double[(int)this.totalNumberOfPayments][5];
		
		// Intiate the total interest summation variable
		this.totalInterest 	= 0.0;
		
		while(counter >= 0) {
			
			schedule[counter][0] = month; // Month
			schedule[counter][1] = this.blendedPayment; // Blended Payment
			schedule[counter][2] = (principleAmt*interestFact); // Interest Paid
			schedule[counter][3] = this.blendedPayment-(schedule[counter][2]); // Principle Paid
			schedule[counter][4] = principleAmt-(schedule[counter][3]); // New amount owing
			
			this.totalInterest += schedule[counter][2];
			principleAmt -= schedule[counter][3];
			counter--;
			month++;
			
		}
		
		return this.schedule;
		
	}
	
	/***
	Return the total blended monthly payment
	@return Blended Monthly Payment
	*/
	public double getBlendPayment() {
		return this.calculateMonthlyPayment();
	}
	
	/***
	Return the total interest paid for the mortgage
	@return Total Interest paid
	*/
	public double getTotalInterest() {		
		return this.totalInterest;		
	}
	
	/***
	Return the total of principle and total interest paid
	@return Total cost of the Mortgage
	*/
	public double totalCostOfMortgage(){
		return this.totalInterest+this.principle;
	}
	
	/***
	Calculate and return the ratio of the interest to the principle
	@return Interest to Principle Ratio
	*/
	public double getRatio() {
		return this.totalInterest/this.principle;
	}
	
	/***
	Calculate and return the average interest paid per year
	@return Average interest per year paid
	*/
	public double getAvgInterestPerYear() {
		return this.totalInterest/this.lengthInYears;
	}
	
	/***
	Calculate and return the average interest paid per month
	@return Average interest per month paid
	*/
	public double getAvgInterestPerMonth() {
		return this.totalInterest/this.numMonPayments;
	}
	
	/***
	Return the length of the mortage in years
	@return Amortization in Years
	*/
	public double getAmrtInYears() {
		return this.lengthInYears;
	}

}