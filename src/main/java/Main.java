import java.io.FileNotFoundException;

import createXML.DataToXML;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		DataToXML dtx = new DataToXML();
		dtx.makeXMLfile("results.csv");
	}
}
