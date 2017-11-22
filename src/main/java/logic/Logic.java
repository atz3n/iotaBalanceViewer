package logic;

import gui.GuiWrapper;
import jota.IotaAPI;
import jota.dto.response.GetBalancesResponse;
import jota.dto.response.GetNewAddressResponse;
import jota.error.InvalidAddressException;
import jota.utils.Checksum;
import jota.utils.IotaUnitConverter;
import utilities.Settings;

public class Logic {

	
	public static String checkSeed(String seed) {
		String checksum = "not a seed";

		try {
			
			checksum = Checksum.addChecksum(seed);
			checksum = checksum.substring(checksum.length() - 3);
			
		} catch (InvalidAddressException e) {
			e.printStackTrace();
		}
		
		return checksum;
	}
	
		
	public static String getCumulativeBalance(String seed, int numOfAddresses) {
		try {
			
			IotaAPI iota = new IotaAPI.Builder()
							.protocol(Settings.IOTA_FULLNODE_PROTOCOL)
							.host(Settings.IOTA_FULLNODE_DOMAIN)
							.port(Settings.IOTA_FULLNODE_PORT)
							.build();

			
			/* get addresses derived from the seed*/
			GetNewAddressResponse gresp = iota.getNewAddress(seed, Settings.IOTA_ADDRESS_SECURITY_LVL, 0, true, numOfAddresses, true);
			
			
			/* convert address list to an Array */
			String[] addresses = new String[gresp.getAddresses().size()];
			addresses = gresp.getAddresses().toArray(addresses);
			
			
			/* remove checksum */
			String[] addressesWithoutChecksum = new String[gresp.getAddresses().size()];
			
			for(int i = 0 ; i < addresses.length ; i++) {
				addressesWithoutChecksum[i] = Checksum.removeChecksum(addresses[i]);
			}
			
			
			/* get address balances */
			GetBalancesResponse bresp = iota.getBalances(Settings.IOTA_CONFIRMATION_THRESHOLD, addressesWithoutChecksum);
			
			
			/* cumulate address balances */
			long overallBalance = 0;
			String iotaAddressIndex = "none";
			
			for(int i = 0 ; i < gresp.getAddresses().size() ; i++) {
				long balance = Long.parseLong(bresp.getBalances()[i]);
				overallBalance += balance; 

				GuiWrapper.messenger.writeLine("address: " + addresses[i]);
				GuiWrapper.messenger.writeLine("address index: " + i);
				
				GuiWrapper.messenger.writeLine("balance: " + IotaUnitConverter.convertRawIotaAmountToDisplayText(balance, true));
				GuiWrapper.messenger.writeEmptyLine();
				
				if(balance > 0) {
					if(iotaAddressIndex.equals("none")) iotaAddressIndex = "" + i;
					else iotaAddressIndex += ", " + i;
				}
			}
			
			GuiWrapper.messenger.writeEmptyLine();
			GuiWrapper.messenger.writeLine("iota holding address indexes: " + iotaAddressIndex);

			
			/* return formated cumulated seed balance */
			return IotaUnitConverter.convertRawIotaAmountToDisplayText(overallBalance, true);
			
			
		} catch (Exception e) {

			GuiWrapper.messenger.writeLine("ERROR: Oops! Something went wrong :(");
			e.printStackTrace();
			return null;
		}
	}
}
