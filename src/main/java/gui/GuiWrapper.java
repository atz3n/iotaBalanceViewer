package gui;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Logic;
import utilities.TextAreaWriter;

public class GuiWrapper {

	public static TextAreaWriter messenger;

	private static JTextField _checksum;
	private static JTextField _balance;

	
	private static boolean isValidValue(String seed, String numOfAddresses) {

		if (Integer.valueOf(numOfAddresses) == null) {
			messenger.writeLine("ERROR: Number of addresses is not an integer value");
			return false;
		} else if (Integer.valueOf(numOfAddresses) < 1) {
			messenger.writeLine("ERROR: Number of addresses is smaller than 1");
			return false;
		}

		if (Logic.checkSeed(seed).equals("not a seed")) {
			messenger.writeLine("ERROR:_ Seed is not valid");
			return false;
		}

		return true;
	}

	
	public static void init(JTextArea messageField, JTextField checksum, JTextField balance) {
		_checksum = checksum;
		_balance = balance;

		messenger = new TextAreaWriter(messageField);

		messenger.writeLine("Enter a Seed and push the Start button");
	}

	
	public static void getBalance(String seed, String numOfAddresses) {
		messenger.clear();

		if (!isValidValue(seed, numOfAddresses)) return;

		
		/* get checksum */
		_checksum.setText(Logic.checkSeed(seed));

		
		/* get cumulative balance */
		String cumBalance = Logic.getCumulativeBalance(seed, Integer.valueOf(numOfAddresses));
		if (cumBalance != null) _balance.setText(cumBalance);
	}
}
