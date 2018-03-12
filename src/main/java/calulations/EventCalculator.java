package calulations;


public class EventCalculator {
	public int getPointsByEventNumber(String P, int n){
		int result = 0;
		switch (n){
			case 0:  result = (int) (25.4347 * Math.pow((18 - Double.parseDouble(P)), 1.81));
					 break;
			case 1:  result = (int) (0.14354 * Math.pow((Double.parseDouble(P)) * 100 - 220, 1.4));
					 break;
			case 2:  result = (int) (51.39 * Math.pow((Double.parseDouble(P)) - 1.5, 1.05));
					 break;
			case 3:  result = (int) (0.8465 * Math.pow((Double.parseDouble(P)) * 100 - 75, 1.42));
					 break;
			case 4:  result = (int) (1.53775 * Math.pow((82 - Double.parseDouble(P)), 1.81));
					 break;
			case 5:  result = (int) (5.74352 * Math.pow((28.5 - Double.parseDouble(P)), 1.92));
					 break;
			case 6:  result = (int) (12.91 * Math.pow((Double.parseDouble(P)) - 4, 1.1));
					 break;
			case 7:  result = (int) (0.2797 * Math.pow((Double.parseDouble(P)) * 100 - 100, 1.35));
					 break;
			case 8:  result = (int) (10.14 * Math.pow((Double.parseDouble(P)) - 7, 1.08));
					 break;
			case 9: String[] mintosec = P.split("\\.");
					 String converted = null;
					 converted = (Integer.parseInt(mintosec[0]) * 60 + Integer.parseInt(mintosec[1])) + "." + mintosec[2];
					 result = (int) (0.03768 * Math.pow((480 - Double.parseDouble(converted)), 1.85));
					 break;
			default: result = 0;
					 break;
			
		}
		
		return result;
	}

}
