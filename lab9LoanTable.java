//Tsagan Garyaeva
//Comp-271
//Lab 9 Loan Table
//With css 


package labs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class lab9LoanTable extends Application {
	
	TextField loanAmount = new TextField();
	TextField numberOfYears = new TextField();
	TextArea txtArea = new TextArea();

	@Override
	public void start(Stage window) throws Exception {
	
	//register an event	
	EventHandler<ActionEvent> handler = event -> changeRate();	
	loanAmount.setOnAction(handler);
	numberOfYears.setOnAction(handler);
	
	
	// HBox for menu strip	
	HBox menu = new HBox();
	Insets padding = new Insets(5);
	menu.setSpacing(3);
	menu.setPadding(padding);
	// Adding label for Loan Amount to the menu
	Label  lblLoanAmount= new Label("Loan Amount: ", loanAmount);
	lblLoanAmount.setContentDisplay(ContentDisplay.RIGHT);
	
	// Adding label for Numbers Of Years to the menu
		Label  lblNumOfYears= new Label("Number of Years: ", numberOfYears);
		lblNumOfYears.setContentDisplay(ContentDisplay.RIGHT);
	
	// Adding a button Show table 
		Button btn = new Button("Show Result");
		btn.setOnAction(handler);
	//Extra button to clear inputs for user's convenience	
		Button btnCl = new Button("Clear");
		btnCl.setOnAction(e-> clearInput());
	// Get all children
		menu.getChildren().addAll(lblLoanAmount, lblNumOfYears, btn, btnCl);
		
	// Border pane to embed menu and textArea
		BorderPane mainPane = new BorderPane(txtArea);
		mainPane.setTop(menu);
		
	// Show everything
		Scene scene = new Scene(mainPane, 750, 550);
		scene.getStylesheets().add("style.css");
		window.setTitle("Loan Calculator");
		window.setScene(scene);
		window.show();
			
		
	}
	
	
	
	private void clearInput() {
		loanAmount.clear();
		numberOfYears.clear();
		txtArea.clear();
	}
	private void changeRate() {
		double annualInterestRate = 5.00;

        double lAmount = Double.parseDouble(loanAmount.getText());

        double numOfYears = Double.parseDouble(numberOfYears.getText());

        String s = String.format("%-1s%20s%20s\n", "Interest Rate", "Monthly Payment", "Total Payment");
        // making loop to display different interest rates
        for ( ; annualInterestRate <= 8.00; annualInterestRate += 0.125) {

            // calculating monthly and total payment rates
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = lAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numOfYears * 12));

            double totalPayment = monthlyPayment * numOfYears * 12;

            
            String str = "%";

            // Displaying formatted info
            s += String.format("%-1.3f%s%19.2f%30.2f \n", annualInterestRate,   str, 
            		((int) (monthlyPayment * 100) / 100.0), ((int) (totalPayment * 100) / 100.0));
        }

        txtArea.setText(s);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
