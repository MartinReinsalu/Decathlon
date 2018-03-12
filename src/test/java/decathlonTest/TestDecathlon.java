package decathlonTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import calulations.EventCalculator;
import createXML.DataToXML;

public class TestDecathlon {
	@Test
	public void EventCalulatorTest(){
		EventCalculator eventcal = new EventCalculator();
		assertEquals(536, eventcal.getPointsByEventNumber("12.61", 0));
		assertEquals(382, eventcal.getPointsByEventNumber("5.00", 1));
		assertEquals(439, eventcal.getPointsByEventNumber("9.22", 2));
		assertEquals(389, eventcal.getPointsByEventNumber("1.50", 3));
		assertEquals(400, eventcal.getPointsByEventNumber("60.39", 4));
		assertEquals(685, eventcal.getPointsByEventNumber("16.43", 5));
		assertEquals(302, eventcal.getPointsByEventNumber("21.60", 6));
		assertEquals(264, eventcal.getPointsByEventNumber("2.60", 7));
		assertEquals(382, eventcal.getPointsByEventNumber("35.81", 8));
		assertEquals(421, eventcal.getPointsByEventNumber("5.25.72", 9));
	}
	
	String output = 
	"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" +
	"<Decathlon>\r\n" +
	"    <Athlete>\r\n" +
	"        <_Name>John Smith</_Name>\r\n" +
    "        <_Place>1</_Place>\r\n" +
    "        <_Total_score>4200</_Total_score>\r\n" +
    "        <_100_metres>12.61 (536 points)</_100_metres>\r\n" +
    "        <_Long_jump>5.00 (382 points)</_Long_jump>\r\n" +
    "        <_Shot_put>9.22 (439 points)</_Shot_put>\r\n" +
    "        <_High_jump>1.50 (389 points)</_High_jump>\r\n" +
    "        <_400_metres>60.39 (400 points)</_400_metres>\r\n" +
    "        <_110_metres_hurdles>16.43 (685 points)</_110_metres_hurdles>\r\n" +
    "        <_Discus_throw>21.60 (302 points)</_Discus_throw>\r\n" +
    "        <_Pole_vault>2.60 (264 points)</_Pole_vault>\r\n" +
    "        <_Javelin_throw>35.81 (382 points)</_Javelin_throw>\r\n" +
    "        <_1500_metres>5.25.72 (421 points)</_1500_metres>\r\n" +
    "    </Athlete>\r\n" +
    "    <Athlete>\r\n" +
    "        <_Name>Jaan Lepp</_Name>\r\n" +
    "        <_Place>2</_Place>\r\n" +
    "        <_Total_score>3494</_Total_score>\r\n" +
    "        <_100_metres>13.75 (348 points)</_100_metres>\r\n" +
    "        <_Long_jump>4.84 (352 points)</_Long_jump>\r\n" +
    "        <_Shot_put>10.12 (493 points)</_Shot_put>\r\n" +
    "        <_High_jump>1.50 (389 points)</_High_jump>\r\n" +
    "        <_400_metres>68.44 (172 points)</_400_metres>\r\n" +
    "        <_110_metres_hurdles>19.18 (417 points)</_110_metres_hurdles>\r\n" +
    "        <_Discus_throw>30.85 (481 points)</_Discus_throw>\r\n" +
    "        <_Pole_vault>2.80 (309 points)</_Pole_vault>\r\n" +
    "        <_Javelin_throw>33.88 (354 points)</_Javelin_throw>\r\n" +
    "        <_1500_metres>6.22.75 (179 points)</_1500_metres>\r\n" +
    "    </Athlete>\r\n" +
    "    <Athlete>\r\n" +
    "        <_Name>Jane Doe</_Name>\r\n" +
    "        <_Place>3</_Place>\r\n" +
    "        <_Total_score>3199</_Total_score>\r\n" +
    "        <_100_metres>13.04 (461 points)</_100_metres>\r\n" +
    "        <_Long_jump>4.53 (295 points)</_Long_jump>\r\n" +
    "        <_Shot_put>7.79 (354 points)</_Shot_put>\r\n" +
    "        <_High_jump>1.55 (426 points)</_High_jump>\r\n" +
    "        <_400_metres>64.72 (267 points)</_400_metres>\r\n" +
    "        <_110_metres_hurdles>18.74 (455 points)</_110_metres_hurdles>\r\n" +
    "        <_Discus_throw>24.20 (352 points)</_Discus_throw>\r\n" +
    "        <_Pole_vault>2.40 (220 points)</_Pole_vault>\r\n" +
    "        <_Javelin_throw>28.20 (274 points)</_Javelin_throw>\r\n" +
    "        <_1500_metres>6.50.76 (95 points)</_1500_metres>\r\n" +
    "    </Athlete>\r\n" +
    "    <Athlete>\r\n" +
    "        <_Name>Foo Bar</_Name>\r\n" +
    "        <_Place>4</_Place>\r\n" +
    "        <_Total_score>3099</_Total_score>\r\n" +
    "        <_100_metres>13.43 (397 points)</_100_metres>\r\n" +
    "        <_Long_jump>4.35 (264 points)</_Long_jump>\r\n" +
    "        <_Shot_put>8.64 (404 points)</_Shot_put>\r\n" +
    "        <_High_jump>1.50 (389 points)</_High_jump>\r\n" +
    "        <_400_metres>66.06 (230 points)</_400_metres>\r\n" +
    "        <_110_metres_hurdles>19.05 (428 points)</_110_metres_hurdles>\r\n" +
    "        <_Discus_throw>24.89 (365 points)</_Discus_throw>\r\n" +
    "        <_Pole_vault>2.20 (179 points)</_Pole_vault>\r\n" +
    "        <_Javelin_throw>33.48 (348 points)</_Javelin_throw>\r\n" +
    "        <_1500_metres>6.51.01 (95 points)</_1500_metres>\r\n" +
    "    </Athlete>\r\n" +
"</Decathlon>\r\n";
	
	DataToXML datatoxml = new DataToXML();
	
	@Test
	public void outputTest() throws FileNotFoundException{
		assertEquals(output, datatoxml.XMLoutput("results.csv"));
	}
	
	public boolean fileExists(String inputFile) throws FileNotFoundException{
		boolean exists = false;
		datatoxml.makeXMLfile(inputFile);
		
		File f = new File("decathlon.xml");
		if (f.exists()) {
		   exists = true;
		}
		return exists;
	}
		
	@Test
	public void outputFileExistsTest() throws FileNotFoundException{
		assertEquals(true, fileExists("results.csv"));
	}
}
