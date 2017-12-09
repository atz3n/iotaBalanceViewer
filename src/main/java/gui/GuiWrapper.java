package gui;

import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Logic;
import settings.MessageSettings;
import utilities.IotaSeedGenerator;
import utilities.TextAreaWriter;

public class GuiWrapper {

	public static TextAreaWriter messenger;

	private static JTextField _balance;
	private static JTextField _numOfAddField;
	private static JButton _startButton;
	private static JPasswordField _seedField;
	
	private static String cumBalance;
	
	private static char defaultEchoChar;
	
	private static boolean isGenSeed = false;
	
		
	
	public static void init(JTextArea messageField, JTextField balance, JButton startButton, JPasswordField seedField, JTextField numOfAddField) {
		_balance = balance;
		_startButton = startButton;
		_seedField = seedField;
		_numOfAddField = numOfAddField;
		
		defaultEchoChar = _seedField.getEchoChar();
		
		messenger = new TextAreaWriter(messageField);
		messenger.writeLine(MessageSettings.INITIAL_TEXT);
	}
	
	
	public static void checkNumOfAddresses() {
		String numOfAddresses = _numOfAddField.getText();

		try {
			
			if (Integer.valueOf(numOfAddresses) < 1) {
				messenger.clear();
				messenger.writeLine(MessageSettings.ERROR__NUM_OF_ADDR_TO_SMALL);
				_startButton.setEnabled(false);
				return;
			}
			
		} catch(Exception e) { // text is not convertible to a number
			
			messenger.clear();
			messenger.writeLine(MessageSettings.ERROR__NUM_OF_ADDR_NOT_AN_INTEGER);
			_startButton.setEnabled(false);
			return;
		}

		_startButton.setEnabled(true);
	}
	

	public static void hidePassword(boolean flag) {
		if(flag) _seedField.setEchoChar(defaultEchoChar); 
        else	 _seedField.setEchoChar((char) 0);  
	}
	
	
	public static void getRandomSeed() {
		isGenSeed = true;
		_seedField.setText(IotaSeedGenerator.createRandomSeed());
	}
	
	
	public static String getChecksum() {
		messenger.clear();
		_startButton.setEnabled(false);
		
		String seed = new String(_seedField.getPassword());
		String checksum = "";
		
		
		/* check chars */
		if(!Pattern.matches("[A-Z9]*", seed)) {
			messenger.writeLine(MessageSettings.ERROR__SEED_WRONG_CHAR);
			return checksum;
		}
		
		
		/* check length */
		if (seed.length() != Logic.SEED_LENGTH) {
			
			if(seed.length() == 0) { 
				messenger.writeLine(MessageSettings.INITIAL_TEXT);
			} else if(seed.length() < Logic.SEED_LENGTH) {
				messenger.writeLine((Logic.SEED_LENGTH - seed.length()) + MessageSettings.MORE_CHAR_TO_SEED);
			} else {
				messenger.writeLine(MessageSettings.TO_MANY_CHAR + Logic.SEED_LENGTH);
			}
			
			 return checksum;
		} 
			
		
		/* get checksum */
		checksum = Logic.checkSeed(seed);
		
		messenger.writeLine(MessageSettings.START_PROGRAM);
		
		if(isGenSeed) messenger.writeLine(MessageSettings.GEN_SEED_WARNING);
		isGenSeed = false;
		
		_startButton.setEnabled(true);
		
		return checksum;
	}
	
	
	public static void getBalance() {
		messenger.clear();
		cumBalance = null;
		
		String numOfAddresses = _numOfAddField.getText();
		String seed = new String(_seedField.getPassword());

		
		/* creating new Thread to decouple the logic from the GUI */
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				_seedField.setEditable(false);
				_startButton.setEnabled(false);
				

				/* get cumulative balance */
				cumBalance = Logic.getCumulativeBalance(seed, Integer.valueOf(numOfAddresses));

				
				if (cumBalance != null) _balance.setText(cumBalance);
				
				_startButton.setEnabled(true);
				_seedField.setEditable(true);
			}
		} )).start();
	}
}
