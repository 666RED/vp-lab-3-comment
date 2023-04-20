package com.hongchen.vp_lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test2 implements ActionListener {
	
	// create the GUI components
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10]; // to store the digit buttons
    JButton[] functionButtons = new JButton[8]; // to store the operation buttons
    JButton addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton;
    JPanel panel;

    double num1 = 0, num2 = 0, result = 0;
    String operator;

    test2() {
    	// create the frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setBounds(500,250,420,550);
        frame.setLayout(null);
        
        // create the output screen of the calculator
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 32));
        textField.setEditable(false);

        // add the description of the buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        // add the operation buttons into the array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
        	// set the action for each operation button
        	functionButtons[i].setActionCommand(functionButtons[i].getText());
        	functionButtons[i].addActionListener(new ActionListener() { // register the listener
            	public void actionPerformed(ActionEvent e) {
            		if(e.getActionCommand() == "."){
            			if(!textField.getText().contains(".")) { // if no decimal point on the output screen
            				textField.setText(textField.getText() + "."); // add decimal point at the end of the output
            			}
            			return;
            		}else if(e.getActionCommand() == "Delete") {
            			// make the string manipulation easier
            			StringBuffer sb = new StringBuffer(textField.getText());
            			sb.deleteCharAt(sb.length()-1); // delete the last char, and assign the new string back to the output screen
            			String a = new String(sb);
            			textField.setText(a);
            			result = Double.valueOf(textField.getText());
            			return;
            		}
            		
            		if(textField.getText().length() == 0) {
            			return;
            		}else if(num1 == 0 ) { // user enter the first number
                    	num1 = Double.valueOf(textField.getText());
                    }else if(num2 == 0 ){ // user enter the second, third, fourth... number
                    	num2 = Double.valueOf(textField.getText());
                    }
            		 
            		if(e.getActionCommand() == "Clear") { // set everything to the original state
            			num1 = num2 = result = 0;
            			operator = null;
                		textField.setText("");
                		
            		}else if(e.getActionCommand() == "=") {
            			calculate(operator); // perform the calculation based on the type of operator that the user enter
            			textField.setText(String.format("%.9f",result)); // output formation
            			operator = "=";
            			
            		}else if(operator != null) { // when the user enter the second operator without pressing "=" button
            			calculate(operator);
            			operator = e.getActionCommand();
            			
            		}else { // when the user enter the first operator
            			operator = e.getActionCommand();
            			textField.setText("");
            		}
    			}
            });
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) { // register the listener for the digit buttons
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 400, 100, 50);
        clrButton.setBounds(150, 400, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // add all buttons into the panel (except delete and clear all button)
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // add panel, delete button, clear all button and text field into the frame
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new test2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) { // respond to button event (add digit onto the output screen)
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + String.valueOf(i));
               
            }
        }
    }
    
    public void calculate(String operator) {
    	// perform simple calculation based on the type of operator
    	switch(operator) {
        case "+":
            result = num1 + num2;
            break;
        case "-":
            result = num1 - num2;
            break;
        case "*":
            result = num1 * num2;
            break;
        case "/":
            result = num1 / num2;
            break;
        default:
            break;
		}
		num1 = result;
		num2 = 0;
		
		textField.setText("");
    }
}    