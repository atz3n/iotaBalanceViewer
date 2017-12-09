package utilities;

import java.security.SecureRandom;

public class IotaSeedGenerator {

	private static final int SEED_LENGTH = 81;
	
	
	public static String createRandomSeed() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9";

		StringBuilder salt = new StringBuilder();
		SecureRandom rnd = new SecureRandom();
		
		while (salt.length() < SEED_LENGTH) { 
			int index = Math.abs(rnd.nextInt() % SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		
		return salt.toString();
	}
}
