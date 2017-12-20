package logic;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import gui.GuiWrapper;
import jota.IotaAPI;
import jota.dto.response.GetBalancesResponse;
import jota.dto.response.GetNewAddressResponse;
import jota.error.InvalidAddressException;
import jota.utils.Checksum;
import jota.utils.IotaUnitConverter;
import settings.Defines;
import settings.MessageSettings;
import settings.Settings;
import utilities.CoinMarketCap;
import utilities.CoinMarketCap.CoinMarketCapException;
import utilities.CoinMarketCap.CurrencyConvert;

public class Logic {

	public static final String NOT_A_SEED = "NOT_A_SEED";
	public static final int SEED_LENGTH = 81;

	private enum State {
		INIT,
		CREATING_ADDRESSES,
		GETTING_BALANCES
	}
	
	
	
	public static String checkSeed(String seed) {
		String checksum = NOT_A_SEED;

		
		if(!Pattern.matches("[A-Z9]*", seed) || !(seed.length() == SEED_LENGTH)) {
			return checksum;
		}
		
		try {

			checksum = Checksum.addChecksum(seed);
			checksum = checksum.substring(checksum.length() - 3);
			
		} catch (InvalidAddressException e) {
			e.printStackTrace();
		}
		
		
		return checksum;
	}
	
		
	public static long getCumulativeBalance(String seed, int numOfAddresses) {
		GuiWrapper.messenger.clear();
		State state = State.INIT;

		try {
			
			IotaAPI iota = new IotaAPI.Builder()
							.protocol(Settings.FULL_NODE_SETTINGS.PROTOCOL)
							.host(Settings.FULL_NODE_SETTINGS.DOMAIN)
							.port(Settings.FULL_NODE_SETTINGS.PORT)
							.build();

			
			state = State.CREATING_ADDRESSES;
			GuiWrapper.messenger.writeLine(MessageSettings.CREATING_ADDRESSES);
			
			/* get addresses derived from the seed*/
			GetNewAddressResponse gresp = iota.getNewAddress(seed, Defines.IOTA_ADDRESS_SECURITY_LVL, 0, true, numOfAddresses, true);
			
			
			/* convert address list to an Array */
			String[] addresses = new String[gresp.getAddresses().size()];
			addresses = gresp.getAddresses().toArray(addresses);
			
			
			/* remove checksum */
			String[] addressesWithoutChecksum = new String[gresp.getAddresses().size()];
			
			for(int i = 0 ; i < addresses.length ; i++) {
				addressesWithoutChecksum[i] = Checksum.removeChecksum(addresses[i]);
			}
			
			GuiWrapper.messenger.writeLine(MessageSettings.GETTING_BALANCES);
			state = State.GETTING_BALANCES;
			
			/* get address balances */
			GetBalancesResponse bresp = iota.getBalances(Defines.IOTA_CONFIRMATION_THRESHOLD, addressesWithoutChecksum);
			
			
			GuiWrapper.messenger.clear();
			
			/* cumulate address balances */
			long cmlBalance = 0;
			String iotaAddressIndex = MessageSettings.BALANCE_INDEXES_INIT_STRING;
			
			for(int i = 0 ; i < gresp.getAddresses().size() ; i++) {
				long balance = Long.parseLong(bresp.getBalances()[i]);
				cmlBalance += balance; 

				GuiWrapper.messenger.writeLine(MessageSettings.ADDRESS_STRING + addresses[i]);
				GuiWrapper.messenger.writeLine(MessageSettings.ADDRESS_INDEX_STRING + i);
				
				GuiWrapper.messenger.writeLine(MessageSettings.BALANCE_STRING + IotaUnitConverter.convertRawIotaAmountToDisplayText(balance, true));
				GuiWrapper.messenger.writeEmptyLine();
				
				if(balance > 0) {
					if(iotaAddressIndex.equals(MessageSettings.BALANCE_INDEXES_INIT_STRING)) iotaAddressIndex = "" + i;
					else iotaAddressIndex += ", " + i;
				}
			}
			
			GuiWrapper.messenger.writeEmptyLine();
			GuiWrapper.messenger.writeLine(MessageSettings.IOTA_HOLDING_INDEXES_STRING + iotaAddressIndex);

			
			return cmlBalance;
			
			
		} catch (Exception e) {

			switch (state) {
			
				case CREATING_ADDRESSES:
					GuiWrapper.messenger.writeLine(MessageSettings.ERROR__CREATING_ADDRESSES);
					break;

				case GETTING_BALANCES:
					GuiWrapper.messenger.writeLine(MessageSettings.ERROR__GETTING_BALANCES_1 + Settings.FULL_NODE_SETTINGS.DOMAIN + MessageSettings.ERROR__GETTING_BALANCES_2);
					break;
					
				default:
					GuiWrapper.messenger.writeLine(MessageSettings.ERROR__UNKONWN);
					break;
			}
			
			e.printStackTrace();
			return -1;
		}
	}
		
	
	public static double getPrice() {
		double price = -1;
		
		CoinMarketCap.CurrencyConvert fiat = null;
		
		if(Settings.FIAT_CURRENCY.equals(Defines.USD)) fiat = null;
		if(Settings.FIAT_CURRENCY.equals(Defines.EUR)) fiat = CurrencyConvert.EUR;
		if(Settings.FIAT_CURRENCY.equals(Defines.GBP)) fiat = CurrencyConvert.GBP;
		if(Settings.FIAT_CURRENCY.equals(Defines.JPY)) fiat = CurrencyConvert.JPY;
		if(Settings.FIAT_CURRENCY.equals(Defines.CNY)) fiat = CurrencyConvert.CNY;
		
		
		try {
			
			CoinMarketCap market = new CoinMarketCap();
			CoinMarketCap.Ticker result = market.getTickerById("iota", fiat);
			
			if(Settings.FIAT_CURRENCY.equals(Defines.USD)) price = result.getPriceUsd().doubleValue();
			if(Settings.FIAT_CURRENCY.equals(Defines.EUR)) price = result.getPriceEur().doubleValue();
			if(Settings.FIAT_CURRENCY.equals(Defines.GBP)) price = result.getPriceGbp().doubleValue();
			if(Settings.FIAT_CURRENCY.equals(Defines.JPY)) price = result.getPriceJpy().doubleValue();
			if(Settings.FIAT_CURRENCY.equals(Defines.CNY)) price = result.getPriceCny().doubleValue();
			
		} catch (CoinMarketCapException e) {
			GuiWrapper.messenger.writeEmptyLine();
			GuiWrapper.messenger.writeLine(MessageSettings.ERROR__COINMARKETCAP);
		}
		
		return price;
	}
	
	
	public static BigDecimal calcFiatBalance(BigDecimal miotaBalance, BigDecimal price) {
		return miotaBalance.multiply(price);
	}
}
