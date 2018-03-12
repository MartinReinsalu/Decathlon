package calulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AthleteAndPoints {

	public HashMap<String, ArrayList<Integer>> athletePointsByEvent = new HashMap<String, ArrayList<Integer>>();
	public HashMap<String, ArrayList<String>> athleteResultsByEvent = new HashMap<String, ArrayList<String>>();
	public LinkedHashMap<String, Integer> sortedAthletePoints = new LinkedHashMap<String, Integer>();
	
	public void pointsSum(HashMap<String, ArrayList<String>> hm){
		EventCalculator ec = new EventCalculator();
		HashMap<String, Integer> athletePoints = new HashMap<String, Integer>();
		
		for(String athleteName : hm.keySet()){
			int sum = 0;
			ArrayList<Integer> pointsByEvent = new ArrayList<Integer>();
			ArrayList<String> results = new ArrayList<String>();
			for(int i = 0; i < hm.get(athleteName).size(); i++){
				sum += ec.getPointsByEventNumber(hm.get(athleteName).get(i), i);
				pointsByEvent.add(ec.getPointsByEventNumber(hm.get(athleteName).get(i), i));
				results.add(hm.get(athleteName).get(i));
			}
			athletePoints.put(athleteName, sum);
			athletePointsByEvent.put(athleteName, pointsByEvent);
			athleteResultsByEvent.put(athleteName, results);
		}
		
		List<String> keys = new ArrayList<String>(athletePoints.keySet());
		List<Integer> values = new ArrayList<Integer>(athletePoints.values());
		List<Integer> valuesCopy = new ArrayList<Integer>(athletePoints.values());
		Collections.sort(valuesCopy);
		Collections.reverse(valuesCopy);
		
		for(int i = 0; i < keys.size(); i++){
			List<Integer> indexes = allIndexes(valuesCopy.get(i), values);
			if(indexes.size() > 1){
				for(int n : indexes){
					sortedAthletePoints.put(keys.get(n), valuesCopy.get(i));
				}
			}
			else{
				sortedAthletePoints.put(keys.get(values.indexOf(valuesCopy.get(i))), valuesCopy.get(i));
			}
		}
	}
	
	public ArrayList<Integer> allIndexes(int indx, List<Integer> values){
	    ArrayList<Integer> indexList = new ArrayList<Integer>();
	    for (int i = 0; i < values.size(); i++)
	        if(indx == values.get(i))
	            indexList.add(i);
	    return indexList;
	}

}
