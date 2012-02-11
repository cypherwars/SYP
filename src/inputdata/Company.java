package inputdata;

public class Company
{
	public String CommonName;
	public String CompanyLogo;
	public String CompanyPolicy;
	public String CompanyUrl;
	public String LegalName;
	public Brand brands[];
	public StoreDetail stores[];
	
	public String conString()
	{
		String temp= this.CommonName+";"+this.CompanyLogo+";"+this.CompanyPolicy+";"+this.CompanyUrl+";"+this.LegalName;
		for(int i=0;i<stores.length;i++)
		{
			temp+=";"+stores[i];
	    }
		return temp;
	}
}