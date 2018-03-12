package readFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadCSVFile {
	HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
	
	
	public HashMap<String, ArrayList<String>> DatatoArray(String file) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(file));
		while(sc.hasNext()){
			String[] lineSplitted = sc.nextLine().split(";");
			ArrayList<String> decathlonData = new ArrayList<String>();
			for(int i = 1; i < lineSplitted.length - 1; i++){
				decathlonData.add(lineSplitted[i]);
			}
			decathlonData.add(lineSplitted[10].trim());
			hm.put(lineSplitted[0], decathlonData);
		}
		sc.close();

		return hm;
	}
}
