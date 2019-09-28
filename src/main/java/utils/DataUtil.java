package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cucumber.api.DataTable;

public class DataUtil {

	public static HashMap<Integer,HashMap<String,String>> convertDataTableToMap(DataTable dataTable){
		List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);
		HashMap<Integer,HashMap<String,String>> hmData = new HashMap<Integer, HashMap<String, String>>();
		for(int i=0;i<data.size();i++) {
			HashMap<String,String>eachData = new HashMap<String, String>();
			Map<String,String> eachMap = data.get(i);
			for (Entry<String,String> entry : eachMap.entrySet()){
				eachData.put(entry.getKey(), entry.getValue());
			}
			hmData.put(i, eachData);
		}
		return hmData;
	}
}
