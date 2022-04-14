/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.7
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.*;

public class RequestFormGUI extends JFrame implements ActionListener, MouseListener {
	private ArrayList<int[]> households;
	private int numHouseholds = 1;
	
	private JLabel instructions;
	private JLabel femaleLabel;
	private JLabel maleLabel;
	private JLabel over8Label;
	private JLabel under8Label;
	
	private JTextField femaleInput;
	private JTextField maleInput;
	private JTextField over8Input;
	private JTextField under8Input;

	private JButton makeOrderForm;
	private JButton addHousehold;
	
	
	
	public RequestFormGUI(){
		super("Welcome to the Peanut Butter Scenario Foodbank!");
		setupGUI();
		setSize(550, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.households = new ArrayList<>();
	}
	/**
		Make the GUI to accept the amount of clients 
		can create multiple households by hitting the "Add household" button
		or create an order form by hitting the "Create order form" button
		*/
	
	public void setupGUI(){
		
		instructions = new JLabel("Enter quantity of each type of client. Enter '0' if unapplicable.");
		femaleLabel = new JLabel("Adult Females:");
		maleLabel = new JLabel ("Adult Males:");
		over8Label = new JLabel("Children Over 8:");
		under8Label = new JLabel("Children Under 8:");
		
		maleInput = new JTextField("", 15);
		femaleInput = new JTextField("", 15);
		under8Input = new JTextField("", 15);
		over8Input = new JTextField("", 15);
		
		maleInput.addMouseListener(this);
		femaleInput.addMouseListener(this);
		under8Input.addMouseListener(this);
		over8Input.addMouseListener(this);
		
		makeOrderForm = new JButton("Create order form");
		makeOrderForm.addActionListener(this);
		
		addHousehold = new JButton("Add another household");
		addHousehold.addActionListener(this);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		
		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());
		
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new FlowLayout());
		
		headerPanel.add(instructions);
		clientPanel.add(femaleLabel);
		clientPanel.add(femaleInput);
		clientPanel.add(maleLabel);
		clientPanel.add(maleInput);
		clientPanel.add(over8Label);
		clientPanel.add(over8Input);
		clientPanel.add(under8Label);
		clientPanel.add(under8Input);
		choicePanel.add(makeOrderForm);
		choicePanel.add(addHousehold);
		
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(clientPanel, BorderLayout.CENTER);
		this.add(choicePanel, BorderLayout.PAGE_END);
				
	}
	
	/**
	This method is triggered when a button is pressed. Once a button is pressed by the user, 
	the values the user entered into the text fields of the GUI are saved into an ArrayList
	of int[]. 
	validateInput() is then called to ensure the saved inputs are valid.
	If the user pressed the "Create order form" button, a new RequestForm object will be 
	created using the saved information and a new OrderForm object will also be created.
	If the user pressed the "Add household" button, the text fields will be cleared and
	the number of households iterated so the new information entered does not overwrite 
	that which was previously entered.
	
	@param event	A button was pushed; either "Create order form" or "Add household".
					There are different outcomes depending on the source of the event.
	*/
	
	public void actionPerformed(ActionEvent event){
		int[] tempHousehold = new int[4];

		try {
			tempHousehold[0] = Integer.parseInt(femaleInput.getText());
			tempHousehold[1] = Integer.parseInt(maleInput.getText());
			tempHousehold[2] = Integer.parseInt(over8Input.getText());
			tempHousehold[3] = Integer.parseInt(under8Input.getText());
			//read the integer values in the fields into the tempHousehold array
				//if any of the values are not numbers, a NumberFormatException will be thrown
		} catch (NumberFormatException e) {
			//if the exception is thrown, show an error popup, and clear the fields
			JOptionPane.showMessageDialog(this,
					"One or more of the values entered was either not a number or too big to be an Integer");
			femaleInput.setText("");
			maleInput.setText("");
			over8Input.setText("");
			under8Input.setText("");
			return;
		}

		households.add(tempHousehold);

		if(validateInput()){
			if (event.getSource().equals(makeOrderForm)){
				RequestForm families = new RequestForm(households);
				OrderForm form  = new OrderForm(families);
				form.createForm();
				
				JOptionPane.showMessageDialog(this, "Order Form created as \n" + form.getFilename());
				households.clear();
				numHouseholds = 1;
				femaleInput.setText("");
				maleInput.setText("");
				over8Input.setText("");
				under8Input.setText("");
			}
			if (event.getSource().equals(addHousehold)){
				setNumHouseholds(numHouseholds+1);
				femaleInput.setText("");
				maleInput.setText("");
				over8Input.setText("");
				under8Input.setText("");
			}
		}
		else {
			households.remove(numHouseholds - 1);
			//if the household to be added was invalid, remove it from the ArrayList
		}
	}
	
	public void mouseClicked(MouseEvent event) {}
	
	public void mouseEntered(MouseEvent event) {}

    public void mouseExited(MouseEvent event) {}

    public void mousePressed(MouseEvent event) {}

    public void mouseReleased(MouseEvent event) {}
	
	/**
	This method checks if any of the user inputs were invalid (negative or non-numerical).
	If an input is invalid, the user will be told that the quantity of client type they 
	entered is invalid and allInputValid is set to false.
	
	@return allInputValid	A boolean indicating if the entered quantity of clients is 
							valid or not.
	*/
	private boolean validateInput() {
		boolean allInputValid = true;
		if(households.get(numHouseholds-1)[0] < 0){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, femaleInput.getText() + " is an invalid amount of Adult Females.");
			femaleInput.setText("");
		}
		if(households.get(numHouseholds-1)[1] < 0){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, maleInput.getText() + " is an invalid amount of Adult Males.");
			maleInput.setText("");
		}
		if(households.get(numHouseholds-1)[2] < 0){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, over8Input.getText() + " is an invalid amount of Children Over 8.");
			over8Input.setText("");
		}
		if(households.get(numHouseholds-1)[3] < 0){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, under8Input.getText() + " is an invalid amount of Children Under 8.");
			under8Input.setText("");
		}

		return allInputValid;
	}
	
	/**
	This method sets the number of households that the user has entered. Used in 
	iteration should multiple households be entered.
	
	@param number 	an integer representing the number that numHouseholds will 
					be set to. Normally numHouseholds+=1.
	*/
	private void setNumHouseholds(int number){
		numHouseholds = number;
	}
	
	/**
	This method retreives the number of households that were entered by the 
	user so it can be used by other classes.
	
	@return this.numHouseholds	an integer representing the quantity of households 
								for the associated request.
	*/
	public int getNumHouseholds(){
		return this.numHouseholds;
	}
	
	public static void main(String[] args){
		RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
		requestForm.initializeConnection();
		EventQueue.invokeLater(() -> {
			new RequestFormGUI().setVisible(true);
		});
		requestForm.setFoodValues();
		requestForm.setClientValues();
		requestForm.close();
	}
}
