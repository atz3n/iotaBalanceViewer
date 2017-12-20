package gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jota.utils.IotaUnitConverter;
import logic.Logic;
import settings.Defines;
import settings.MessageSettings;
import settings.Settings;
import utilities.IotaSeedGenerator;
import utilities.TextAreaWriter;

public class GuiWrapper {

	public static TextAreaWriter messenger;

	private static JTextField _iotaBalanceView;
	private static JTextField _fiatBalanceView;
	private static JTextField _priceView;
	private static JTextField _numOfAddField;
	private static JButton _startButton;
	private static JPasswordField _seedField;
	
	private static char defaultEchoChar;
	
	private static boolean isGenSeed = false;
	
	private static long iotaBalance = -1;
	private static double price = -1;
	
	
	
	private static String getFiatSymbol() {
		if(Settings.FIAT_CURRENCY.equals(Defines.USD)) return Defines.SYMB_USD;
		if(Settings.FIAT_CURRENCY.equals(Defines.EUR)) return Defines.SYMB_EUR;
		if(Settings.FIAT_CURRENCY.equals(Defines.GBP)) return Defines.SYMB_GBP;
		if(Settings.FIAT_CURRENCY.equals(Defines.JPY)) return Defines.SYMB_JPY;
		if(Settings.FIAT_CURRENCY.equals(Defines.CNY)) return Defines.SYMB_CNY;
		
		return Defines.SYMB_USD;
	}
		
	
	public static void init(JTextArea messageField, JTextField iotaBalanceView, JTextField fiatBalanceView, JTextField priceView, JButton startButton, JPasswordField seedField, JTextField numOfAddField) {
		_iotaBalanceView = iotaBalanceView;
		_fiatBalanceView = fiatBalanceView;
		_priceView = priceView;
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
		iotaBalance = -1;
		price = -1;
		
		
		String numOfAddresses = _numOfAddField.getText();
		String seed = new String(_seedField.getPassword());

		
		/* creating new Thread to decouple the logic from the GUI */
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				_seedField.setEditable(false);
				_numOfAddField.setEditable(false);
				_startButton.setEnabled(false);
				

				/* get cumulative balance */
				iotaBalance = Logic.getCumulativeBalance(seed, Integer.valueOf(numOfAddresses));

				
				if (iotaBalance != -1) {
					_iotaBalanceView.setText(IotaUnitConverter.convertRawIotaAmountToDisplayText(iotaBalance, true));
					
					
					/* get price from coinmarketcap.com */
					price = Logic.getPrice();

					
					if(price != -1) {
						String symbol = getFiatSymbol();

						BigDecimal rndPrice = new BigDecimal(price);
						_priceView.setText(String.valueOf(rndPrice.setScale(2, RoundingMode.HALF_UP)) + symbol);
						
						
						/* calculate price */
						BigDecimal miotaBalance = new BigDecimal(((double) iotaBalance / 1000000));
						BigDecimal fiat = Logic.calcFiatBalance(miotaBalance, new BigDecimal(price));

						_fiatBalanceView.setText(String.valueOf(fiat.setScale(2, RoundingMode.HALF_UP)) + symbol);
					}
				}
				
				_startButton.setEnabled(true);
				_numOfAddField.setEditable(true);
				_seedField.setEditable(true);
			}
		} )).start();
	}
}
