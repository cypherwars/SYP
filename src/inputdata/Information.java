package inputdata;

import java.util.ArrayList;

public class Information {
	
		public String id;
		public String text;
		public String thumbnail = "";
		public String url;
		public String Description = "Not Available";
		public ArrayList<String>alias = new ArrayList<String>();
		
		public String printdata()
		{
			return text+";"+Description+";"+alias.toString();
		}
	}

