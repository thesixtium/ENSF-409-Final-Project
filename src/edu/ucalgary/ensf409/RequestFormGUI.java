/**
 * @author Aleksander Berezowski
 * @author Danielle Jourdain
 * @author Philippa Madill
 * @version 1.2
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RequestFormGUI extends JFrame implements ActionListener, MouseListener {
	private ArrayList<int[]> households;
	private int numHouseholds = 1;
	private Household household;
	
	private JLabel instructions;
	private JLabel femaleLabel;
	private JLabel maleLabel;
	private JLabel over8Label;
	private JLabel under8Label;
	
	private JTextField femaleInput;
	private JTextField maleInput;
	private JTextField over8Input;
	private JTextField under8Input;
	
	
	
	public RequestFormGUI(){
		super("Create a request form for one or more households");
		setupGUI();
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void setupGUI(){
		/*
		Make the GUI to accept the amount of clients 
		can create multiple households by hitting the "Add household" button
		or create an order form by hitting the "Create order form" button
		*/
		instructions = new JLabel("Enter quantity of each type of client. Enter '0' if unapplicable");
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
		
		JButton makeOrderForm = new JButton("Create order form");
		makeOrderForm.addActionListener(this);
		
		JButton addHousehold = new JButton("Add household");
		addHousehold.addActionListener(this);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		
		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FLowLayout());
		
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
	
	public void actionPerformed(ActionEvent event){
		//I want this to happen either way, but if create order form is pressed I want it 
		//to send the household[][] to Household the stop accepting inputs and 
		//if add household is pressed I want it to do this then numHouseholds+=1
		//and continue accepting inputs.
<<<<<<< Updated upstream
		int[] tempHousehold = new int[4];
=======
		int[] tempHousehold = new int[4]
		//pass the entered data into an int array so it can be added to an array list and then 
		//RequestForm can create a Household object
>>>>>>> Stashed changes
		tempHousehold[0] = Integer.parseInt(femaleInput.getText());
		tempHousehold[1] = Integer.parseInt(maleInput.getText());
		tempHousehold[2] = Integer.parseInt(over8Input.getText());
		tempHousehold[3] = Integer.parseInt(under8Input.getText());
		/*households[numHouseholds-1][0] = Integer.parseInt(femaleInput.getText());
		households[numHouseholds-1][1] = Integer.parseInt(maleInput.getText());
		households[numHouseholds-1][2] = Integer.parseInt(over8Input.getText());
		households[numHouseholds-1][3] = Integer.parseInt(under8Input.getText());*/
		households.add(tempHousehold);
		if(validateInput()){
		if (event.getSource().equals(makeOrderForm)){
			RequestForm families = new RequestForm(households);
			OrderForm form  = new OrderForm();
			form.createOrderForm();
			
			//Household household = new Household(households);
			JOptionPane.showMessageDialog(this, "Order Form Created as \n" + form.getFilename());
		}
		if (event.getSource().equals(addhousehold)){
			//resets the inputs to accept new ones after storing the old ones
			//increases number of households by one
			setNumHouseholds(numHouseholds+1);
			femaleInput.setText("");
			maleInput.setText("");
			over8Input.setText("");
			under8Input.setText("");
		}
		}
	}
	
	public void mouseClicked(MouseEvent event){
		
	}
	
	 public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
	private boolean validateInput(){
		/*
		If any inputs are negative or contain non numerical inputs, they are invalid.
		*/
		boolean allInputValid = true;
		if(households.get(numHouseholds-1)[0]<0 || femaleInput.getText().contains("^[0-9]+$")){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, femaleInput.getText() + " is an invalid amount of Adult Females.");
		}
		if(households.get(numHouseholds-1)[1]<0 || maleInput.getText().contains("^[0-9]+$")){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, maleInput.getText() + " is an invalid amount of Adult Males.");
		}
		if(households.get(numHouseholds-1)[2]<0 || over8Input.getText().contains("^[0-9]+$")){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, over8Input.getText() + " is an invalid amount of Children Over 8.");
		}
		if(households.get(numHouseholds-1)[3]<0 || under8Input.getText().contains("^[0-9]+$")){
			allInputValid = false;
			JOptionPane.showMessageDialog(this, under8Input.getText() + " is an invalid amount of Children Under 8.");
		}
		return allInputValid;
	}
	private void setNumHouseholds(int number){
		this.numHouseholds = number;
	}
	
	private int getNumHouseholds(){
		return this.numHouseholds;
	}
	
public static void main(String[] args){
		RequestFormDatabase requestForm = new RequestFormDatabase("jdbc:mysql://localhost/inventory","student","ensf" );
		requestForm.initializeConnection();
		EventQueue.invokeLater(() -> {
			new GUIRequestForm().setVisible(true);
		});
		requestForm.close();
	}

	public ArrayList<Hamper> getHampers() {
        Iterator<Household> iter = h.iterator();
        ArrayList<Hamper> result = new ArrayList<>();
        while(iter.hasNext()) {
            Household i = iter.next();
            result.add(i.getFamilyHamper());
        }

        return result;
    }
	
	public OrderForm createOrderForm(){
		
	}
}