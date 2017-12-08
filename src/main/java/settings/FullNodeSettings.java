package settings;

public class FullNodeSettings {
	
	
	public static String PROTOCOL = "https";
	public static String DOMAIN = "nodes.iota.cafe";
	public static String PORT = "443";
	
	
	
	public static void setBitfinexNode() {
		PROTOCOL = "http";
		DOMAIN = "iota.bitfinex.com";
		PORT = "80";
	}
	
	
	public static void setIotaSupportNode() {
		PROTOCOL = "http";
		DOMAIN = "service.iotasupport.com";
		PORT = "14265";
	}
	
	
	public static void setIotaCafeNode() {
		PROTOCOL = "https";
		DOMAIN = "nodes.iota.cafe";
		PORT = "443";
	}
	
}
