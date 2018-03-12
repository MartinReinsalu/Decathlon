package createXML;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import calulations.AthleteAndPoints;
import readFile.ReadCSVFile;

public class DataToXML {
	
	AthleteAndPoints aap = new AthleteAndPoints();
	ReadCSVFile rcf = new ReadCSVFile();
	
	List<String> headers = new ArrayList<String>(Arrays.asList(
			"_Name", "_Place", "_Total_score", "_100_metres", "_Long_jump", "_Shot_put", "_High_jump", 
			"_400_metres", "_110_metres_hurdles", "_Discus_throw", "_Pole_vault", 
			"_Javelin_throw", "_1500_metres"));
	
	String lastPlace = "";
	int lastFrequency = 0;
	
	public List<Integer> SharedPlaces(List<Integer> array){
		ArrayList<Integer> places = new ArrayList<Integer>();
		
		for(int i = 0; i < array.size(); i++){
			int count = 0;
			for(int j = 0; j < array.size(); j++){
				if(array.get(i).equals(array.get(j))){
					count += 1;
				}
			}
			places.add(count);
		}
		return places;
	}
	
	public String XMLoutput(String file) throws FileNotFoundException{
		aap.pointsSum(rcf.DatatoArray(file));
		String output = "";
		List<String> names = new ArrayList<String>(aap.sortedAthletePoints.keySet());
		List<Integer> sharedValues = new ArrayList<Integer>(aap.sortedAthletePoints.values());
		List<Integer> possibleSharedPlaces = new ArrayList<Integer>(SharedPlaces(sharedValues));
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document newDoc = dBuilder.newDocument();
		    Element element1 = newDoc.createElement("Decathlon");
		    newDoc.appendChild(element1);
		    
		    for(int name = 0; name < names.size(); name++){
		    	Element element2 = newDoc.createElement("Athlete");
	            element1.appendChild(element2);
		    	
		    	for (int i = 0; i < headers.size(); i++) {
		    		String header = headers.get(i);
		    		String value = null;
		    		
		    		if(i == 0){
		    			value = names.get(name);
		    		}
		    		else if(i == 1){
		    			if(possibleSharedPlaces.get(name).equals(1)){
		    				value = name + 1 + "";
		    			}
		    			else{
		    				int current = possibleSharedPlaces.get(name);
		    				if(current == lastFrequency){
		    					value = lastPlace;
		    				}
		    				else{
		    					lastFrequency = current;
		    					String v = name + 1 + "";
		    					for(int a = name + 1; a < possibleSharedPlaces.size(); a++){
		    						if(current == possibleSharedPlaces.get(a)){
		    							v += "-" + (a + 1);
		    						}
		    					}
		    					value = v;
		    					lastPlace = value;
		    					}
		    				}
		    			}
		    		else if(i == 2){
		    			
		    			value = "" + sharedValues.get(name);
		    		}
		    		else{
		    			value = aap.athleteResultsByEvent.get(names.get(name)).get(i - 3) + " (" + aap.athletePointsByEvent.get(names.get(name)).get(i - 3) + " points)";
		    		}
		    
		    		Element curElement = newDoc.createElement(header);
		    		curElement.appendChild(newDoc.createTextNode(value));
		    		element2.appendChild(curElement);
		    	}
		    }
		    
		    ByteArrayOutputStream bs = null;
		    OutputStreamWriter ow = null;

		    try {
		    	bs = new ByteArrayOutputStream();
		    	ow = new OutputStreamWriter(bs);

		        TransformerFactory tf = TransformerFactory.newInstance();
		        Transformer t = tf.newTransformer();
		        t.setOutputProperty(OutputKeys.INDENT, "yes");
		        t.setOutputProperty(OutputKeys.METHOD, "xml");
		        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		        Source ds = new DOMSource(newDoc);
		        Result result = new StreamResult(ow);
		        t.transform(ds, result);
		        
		        output += new String(bs.toByteArray());
		    } 
		    catch (Exception exp) {
		        exp.printStackTrace();
		    } 
		    finally {
		    	try {
		        	ow.close();
		        } 
		        catch (Exception e) {
		        }
		        try {
		        	bs.close();
		        } 
		        catch (Exception e) {
		        }
		    }
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		return output;
	}
	
	public void makeXMLfile(String file) throws FileNotFoundException{
		System.out.println(XMLoutput(file));
		PrintStream fileOut = new PrintStream(new FileOutputStream("decathlon.xml"));
		System.setOut(fileOut);
		System.out.println(XMLoutput(file));
	}

}
