package init;

import utils.ReadPropFile;

public class ApiInit {
	private static ReadPropFile readTestPropFile = new ReadPropFile();
	private static String apiKey;
	private static String baseUri;
	
	public void init(){
		readTestPropFile.loadDefaultPropFile();
		apiKey = readTestPropFile.getPropertyValue("APIKey");
		baseUri = readTestPropFile.getPropertyValue("ServiceURL");
	}
	
	public static String getApiKey() {
		return apiKey;
	}
	
	public static String getBaseUri() {
		return baseUri;
	}
}
