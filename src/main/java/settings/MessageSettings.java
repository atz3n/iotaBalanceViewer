package settings;

public class MessageSettings {

	public final static String ERROR_PREFIX = "ERROR: ";
	
	public final static String ERROR__NUM_OF_ADDR_NOT_AN_INTEGER = ERROR_PREFIX + "Number of addresses is not an integer value";
	public final static String ERROR__NUM_OF_ADDR_TO_SMALL = ERROR_PREFIX + "Number of addresses is smaller than 1";
	public final static String ERROR__NOT_A_SEED = ERROR_PREFIX + "Invalid Seed";
	
	public final static String ERROR__SEED_WRONG_CHAR = "Unvalid Seed. Only upper case letters and 9 allowed.";
	
	
	public final static String ERROR__CREATING_ADDRESSES = ERROR_PREFIX + "Oops! Something went wrong while trying to generate balances. :(\\nPlease try again";
	
	public final static String ERROR__GETTING_BALANCES_1 = ERROR_PREFIX + "Oops! Something went wrong while trying to connect to node ";
	public final static String ERROR__GETTING_BALANCES_2 = ". :(\\nMaybe it's a good idea to change the node and / or wait a minute before trying again.";
	
	public final static String ERROR__COINMARKETCAP = ERROR_PREFIX + "Couldn't get price from coinmarketcap.com. Price calculation skipped";

	public final static String ERROR__UNKONWN =  ERROR_PREFIX +  "Oops! A mysterious problem occurred. :(\\nDon't be sad. Try it again :(";
	
	
	public final static String INITIAL_TEXT = "Enter a Seed and push the Start button";
	
	public final static String CREATING_ADDRESSES = "Creating addresses...";
	public final static String GETTING_BALANCES = "Requesting balances...";
	
	
	public final static String MORE_CHAR_TO_SEED = " characters left.";
	public final static String TO_MANY_CHAR = "To many characters. Seed length must be exactly ";
	
	public final static String START_PROGRAM = "All right. Ready to go.\nPush the start button and launch the rocket ;)";
	public final static String GEN_SEED_WARNING = "\nCAUTION: SAVE YOUR SEED!!!\nYOU'LL LOSE ALL YOUR IOTAS IN CASE OF LOST!!!";
	
	public final static String ADDRESS_STRING = "address: ";
	public final static String ADDRESS_INDEX_STRING = "address index: ";
	public final static String BALANCE_INDEXES_INIT_STRING = "none";
	public final static String BALANCE_STRING = "balance: ";
	
	public final static String IOTA_HOLDING_INDEXES_STRING = "iota holding address indexes: ";
	
}
