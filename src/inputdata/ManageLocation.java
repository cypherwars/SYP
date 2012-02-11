package inputdata;

public class ManageLocation
{
	public String ConcatLocation(Double latitude,Double longitude)
	{
		String returnString = latitude.toString()+";"+longitude.toString();
		return returnString;
	}
	
	public Double[] De_ConcatLocation(String Location){
		String[] temp  = Location.split(";");
		Double dlatitude = Double.parseDouble(temp[0]);
		Double dlongitude = Double.parseDouble(temp[1]);	
		Double[] returnDouble = new Double[]{dlatitude,dlongitude};
		return returnDouble;		
	}
}