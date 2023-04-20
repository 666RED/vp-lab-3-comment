package com.hongchen.vp_lab3;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame{

	// create GUI components
	JTextField tf;
	JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	JButton btnMul, btnDiv, btnPlus, btnSub, btnClearAll, btnEqual, btnDel, btnDecPt;
	JPanel panDigit, panOperation;
	
	public static double result = 0; // temporary result
	public static String operand;
	public static int opCount = 0; // number of operand
	public static boolean pressEqual = false; //is btnEqual pressed
	public static int numOfDecPt = 0; // number of decimal point
	public static boolean zeroError = false;
	
	Main() {
		
		tf = new JTextField("");
		tf.setBounds(20, 20, 450, 50);
		tf.setEditable(false);
		tf.setFont(new Font("Arial", Font.PLAIN, 25));		
		
		btn0 = new JButton("0");
		btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");
		btn6 = new JButton("6");
		btn7 = new JButton("7");
		btn8 = new JButton("8");
		btn9 = new JButton("9");
		
		btnMul = new JButton("*");
		btnMul.setFont(new Font("Arial", Font.PLAIN, 25));
		btnDiv = new JButton("/");
		btnDiv.setFont(new Font("Arial", Font.PLAIN, 25));
		btnPlus = new JButton("+");
		btnPlus.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSub = new JButton("-");
		btnSub.setFont(new Font("Arial", Font.PLAIN, 25));
		btnClearAll = new JButton("C");
		btnClearAll.setFont(new Font("Arial", Font.PLAIN, 25));
		btnEqual = new JButton("=");
		btnEqual.setFont(new Font("Arial", Font.PLAIN, 25));
		btnDel = new JButton("Del");
		btnDel.setFont(new Font("Arial", Font.PLAIN, 25));
		btnDecPt = new JButton(".");
		btnDecPt.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JButton[] btnArray = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnMul, btnDiv, btnPlus, btnSub, btnClearAll, btnEqual, btnDel, btnDecPt};
		
		panDigit = new JPanel();
		panDigit.setBounds(10, 150, 230, 300);
		panDigit.setBorder(BorderFactory.createLineBorder(Color.black));
		panDigit.setLayout(new GridLayout(4, 3));
		
		panOperation = new JPanel();
		panOperation.setBounds(270, 100, 200, 350);
		panOperation.setBorder(BorderFactory.createLineBorder(Color.black));
		panOperation.setLayout(new GridLayout(4, 2));
		
		panDigit.add(btn7);
		panDigit.add(btn8);
		panDigit.add(btn9);
		panDigit.add(btn4);
		panDigit.add(btn5);
		panDigit.add(btn6);
		panDigit.add(btn1);
		panDigit.add(btn2);
		panDigit.add(btn3);
		panDigit.add(new JPanel());
		panDigit.add(btn0);
		panDigit.add(new JPanel());
		
		panOperation.add(btnDel);
		panOperation.add(btnClearAll);
		panOperation.add(btnMul);
		panOperation.add(btnDiv);
		panOperation.add(btnPlus);
		panOperation.add(btnSub);
		panOperation.add(btnDecPt);
		panOperation.add(btnEqual);
		
		// register source
		for(int i = 0; i < btnArray.length; i++) {
			addSource(btnArray[i]);
		}
		
		add(tf);
		add(panDigit);
		add(panOperation);
		
		setTitle("CS Calculator");
		setSize(500, 500);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void addSource(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// after pressing equal and get the answer, if user press digit / decPt, clear the tf
				if(pressEqual && e.getSource() != btnPlus && e.getSource() != btnSub && e.getSource() != btnMul && e.getSource() != btnDiv && e.getSource() != btnClearAll && e.getSource() != btnDel && e.getSource() != btnEqual) {
					tf.setText("");
					pressEqual = false;
				}
				if(e.getSource() == btn0) {
					tf.setText(tf.getText() + btn0.getText());
				}else if(e.getSource() == btn1) {
					tf.setText(tf.getText() + btn1.getText());
				}else if(e.getSource() == btn2) {
					tf.setText(tf.getText() + btn2.getText());
				}else if(e.getSource() == btn3) {
					tf.setText(tf.getText() + btn3.getText());
				}else if(e.getSource() == btn4) {
					tf.setText(tf.getText() + btn4.getText());
				}else if(e.getSource() == btn5) {
					tf.setText(tf.getText() + btn5.getText());
				}else if(e.getSource() == btn6) {
					tf.setText(tf.getText() + btn6.getText());
				}else if(e.getSource() == btn7) {
					tf.setText(tf.getText() + btn7.getText());
				}else if(e.getSource() == btn8) {
					tf.setText(tf.getText() + btn8.getText());
				}else if(e.getSource() == btn9) {
					tf.setText(tf.getText() + btn9.getText());
				}else if(e.getSource() == btnEqual && !tf.getText().isEmpty() && !tf.getText().equals(".")) {
					if(opCount == 0) { // no operand pressed (result equals to what user enter)
						result = Double.parseDouble(tf.getText());
					}else if(operand == "/" && tf.getText().equals("0")) { // avoid zero error
						tf.setText("Divided By Zero Error");
						clear();
						pressEqual = true;
						return;
					}else {
						calculate();
					}
					// get back to the original setting
					tf.setText(String.valueOf(result));
					pressEqual = true;
					clear();
				}else if(e.getSource() == btnClearAll) {
					result = 0;
					tf.setText("");
					pressEqual = false;
					clear();
				}else if((e.getSource() == btnPlus || e.getSource() == btnSub || e.getSource() == btnMul || e.getSource() == btnDiv) && !tf.getText().isEmpty() && !tf.getText().equals(".") && !tf.getText().equals("Divided By Zero Error")) {
					opCount++;
					if(opCount > 1) { // if user not press equal and direct press operand, direct calculation
						calculate();
					}else {
						result = Double.parseDouble(tf.getText()); // save the first digit to result, and let user continues entering next digit
					}
					if(e.getSource() == btnPlus) {
						operand = btnPlus.getText();
					}else if(e.getSource() == btnSub) {
						operand = btnSub.getText();
					}else if(e.getSource() == btnMul) {
						operand = btnMul.getText();
					}else if(e.getSource() == btnDiv) {
						operand = btnDiv.getText();
					}
					if(!zeroError) {
						tf.setText("");
						pressEqual = false;
					}else {
						pressEqual = true;
					}
					numOfDecPt = 0;
				}else if(e.getSource() == btnDel && !tf.getText().isEmpty()) {
					if(tf.getText().charAt(tf.getText().length() - 1) == '.') {
						numOfDecPt = 0; // let the user able to enter decPt
					}
					tf.setText(tf.getText().substring(0, tf.getText().length() - 1)); // delete the last char
					pressEqual = false;
				}else if(e.getSource() == btnDecPt) {
					if(numOfDecPt == 0 && !tf.getText().contains(".")) { // adding decPt if there is no decPt in tf
						tf.setText(tf.getText() + btnDecPt.getText());
						numOfDecPt++;
					}
				}
			}
		});
	}
	
	public void calculate() {
		zeroError = false;
		if(operand == "+") {
			result += Double.parseDouble(tf.getText());
		}else if(operand == "-") {
			result -= Double.parseDouble(tf.getText());
		}else if(operand == "*") {
			result *= Double.parseDouble(tf.getText());
		}else if(operand == "/") {
			if(Double.parseDouble(tf.getText()) == 0) {
				tf.setText("Divided By Zero Error");
				result = 0;
				zeroError = true;
			}else {
				result /= Double.parseDouble(tf.getText());
			}
		}
	}
	
	public void clear() {
		opCount = 0;
		operand = "";
		numOfDecPt = 0;
	}
}
