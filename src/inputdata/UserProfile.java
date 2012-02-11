package inputdata;

public class UserProfile{
	public String age;
	public String gender;
	public String dateofbirth;
	public String name;
	public String nationality;
	public String profession;
	public ContactInformation hascontactInfo;
	public Eateries eateries;
	public Movies movies;
	public Music music;
	public Sports sports;
	public Reading reading;
	public Location hasLocation;
	public String show()
	{
		String t=hascontactInfo.show()+"\n"+eateries.show()+"\n"+movies.show()+"\n"+music.show()+"\n"+sports.show()+" "+reading.show()+" "+hasLocation.show();
		return age+"\n "+gender+"\n "+dateofbirth+"\n "+name+" \n"+nationality+"\n "+profession+"\n"+t;
	}

}