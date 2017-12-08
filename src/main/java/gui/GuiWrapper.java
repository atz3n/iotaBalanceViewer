package gui;

import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Logic;
import settings.MessageSettings;
import utilities.TextAreaWriter;

public class GuiWrapper {

	public static TextAreaWriter messenger;

	private static JTextField _balance;
	private static JButton _startButton;
	private static JPasswordField _seedField;
	
	private static String cumBalance;

	
	
	private static boolean checkValue(String numOfAddresses) {

		if (Integer.valueOf(numOfAddresses) == null) {
			messenger.writeLine(MessageSettings.ERROR__NUM_OF_ADDR_NOT_AN_INTEGER);
			return false;
		} else if (Integer.valueOf(numOfAddresses) < 1) {
			messenger.writeLine(MessageSettings.ERROR__NUM_OF_ADDR_TO_SMALL);
			return false;
		}

		return true;
	}
	
	
	public static void init(JTextArea messageField, JTextField balance, JButton startButton, JPasswordField seedField) {
		_balance = balance;
		_startButton = startButton;
		_seedField = seedField;
		
		messenger = new TextAreaWriter(messageField);
		messenger.writeLine(MessageSettings.INITIAL_TEXT);
	}
	
	
	public static String getChecksum(String seed) {
		messenger.clear();
		_startButton.setEnabled(false);
		
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
		_startButton.setEnabled(true);
		
		return checksum;
	}
	
	
	public static void getBalance(String seed, String numOfAddresses) {
		messenger.clear();
		cumBalance = null;

		
		/* creating new Thread to decouple the logic from the GUI */
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				_seedField.setEditable(false);
				_startButton.setEnabled(false);
				
				if (!checkValue(numOfAddresses)) return;

				/* get cumulative balance */
				cumBalance = Logic.getCumulativeBalance(seed, Integer.valueOf(numOfAddresses));

				
				if (cumBalance != null) _balance.setText(cumBalance);
				
				_startButton.setEnabled(true);
				_seedField.setEditable(true);
			}
		} )).start();
	}
}
