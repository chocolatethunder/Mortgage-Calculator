/***

This MortgageCalculator class combines the Model, View, and Controller 
classes to produce the Mortage Calculator Program. 

@author Saurabh Tomar

Written for CPSC 233

Please note that there is no error checking.
All inputs are considered correct and sanitized.

*/

package lab4;

public class MortgageCalculator {

	public static void main (String[] args) {		
		
		MortgageModel theModel = new MortgageModel();
		MortgageView theView = new MortgageView();
		
		MortgageController theController = new MortgageController(theModel, theView);
		
		// Render the GUI Interface visible
		theView.setVisible(true);
		
	}

}