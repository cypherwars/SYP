package inputdata;

public class Location{
	public String residential_location;
	public String occupational_location;
	public String current_location;	
	 public String show()
	 {
		 return residential_location+" "+occupational_location+" "+current_location;
	 }
	 public String[] convertToGoogleFormat(String k)
	 {
		 return k.split(":");
	 }
}