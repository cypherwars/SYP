package inputdata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

public class Search {
	 public static ArrayList<Company> retrieveCompanyInformation(String k)
	 {
		 	OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
			
			InputStream stream = FileManager.get().open("F:\\eclipse helios ws\\WebApp2\\WebContent\\ServiceData.owl");
			
			String uri = "http://www.semanticweb.org/ontologies/2011/1/ServiceOntology.owl#";
			
			model.read(stream,uri);
			
			int numberofbrands = 1;
			
			int numberofstores = 1;
			
			int numberofproducts = 3;
			
			Brand brands[] = new  Brand[numberofbrands];
		
			OntProperty ProductName = model.getOntProperty(uri+"ProductName");	
			String temp = "<"+ProductName.getURI()+">";
			OntProperty hasProduct = model.getOntProperty(uri+"hasProduct");
			String temp2 = "<"+hasProduct.getURI()+">";
			OntProperty hasStore = model.getOntProperty(uri+"hasStore");
			String temp3 = "<"+hasStore.getURI()+">";
			OntProperty ProductCategory = model.getOntProperty(uri+"ProductCategory");
			String pc = "<"+ProductCategory.getURI()+">";
			OntProperty ProductDescription = model.getOntProperty(uri+"ProductDescription");
			String pd = "<"+ProductDescription.getURI()+">";
			String name = k;
			String query = "select * where {?company "+temp3+" ?store .?store "+temp2+" ?x .?x "+temp+" '"+name+"'.?x "+pc+" ?pc.?x "+pd+" ?pd}"; 
						
		    
			JenaExploration je = new JenaExploration();
			
			StringBuffer buffer = new StringBuffer();
			
			ArrayList<String> prefix = je.generatePrefix(model);	
			
			for(int i = 0 ; i<prefix.size();i++)
			{
				buffer.append(prefix.get(i));
			}
			
			buffer.append(query);
			
			Query question = QueryFactory.create(buffer.toString());
			
			QueryExecution qexec = QueryExecutionFactory.create(question,model);
					
			com.hp.hpl.jena.query.ResultSet resultset =  qexec.execSelect();
			
			String companyuri = "";
			String storeuri = "";
			String locationuri = "";
			String timinguri = "";
			String producturi = "";
			String contacturi = "";
			String branduri = "";
			
			ArrayList<Company> companies = new ArrayList<Company>();
			while(resultset.hasNext())
			{
				QuerySolution solution = resultset.nextSolution();
				
				RDFNode node1 = solution.get("?company");
				RDFNode node2 = solution.get("?store");
				RDFNode node3 = solution.get("?x");
			 	RDFNode node4 = solution.get("?pc");
			 	RDFNode node5 = solution.get("?pd");
				    companyuri  = node1.asResource().getURI();	
				    storeuri = node2.asResource().getURI();
				    producturi = node3.asResource().getURI();
  				
					ContactInformation_Store contactinformation = new ContactInformation_Store();
					StoreLocation location = new StoreLocation();
					TimingDetail timingdetail = new TimingDetail();
					ProductDetail products[] = new ProductDetail[numberofproducts];
					StoreDetail storedetails[] = new StoreDetail[numberofstores];
					Company company = new Company();
					
					Individual individual = model.getIndividual(companyuri);
					OntProperty CommonName = model.getOntProperty(uri+"CommonName");
					OntProperty CompanyLogo = model.getOntProperty(uri+"CompanyLogo");
					OntProperty CompanyPolicy = model.getOntProperty(uri+"CompanyPolicy");
					OntProperty CompanyUrl = model.getOntProperty(uri+"CompanyUrl");
					OntProperty LegalName = model.getOntProperty(uri+"LegalName");
					company.CommonName	= individual.getPropertyValue(CommonName).toString();
					company.CompanyLogo = individual.getPropertyValue(CompanyLogo).toString();
					company.CompanyPolicy	= individual.getPropertyValue(CompanyPolicy).toString();
					company.CompanyUrl = individual.getPropertyValue(CompanyUrl).toString();
					company.LegalName	= individual.getPropertyValue(LegalName).toString();
					
				    Individual Store = model.getIndividual(storeuri);
				    OntProperty DeliveryMethod = model.getOntProperty(uri+"DeliveryMethod");			
					OntProperty StoreName = model.getOntProperty(uri+"StoreName");	
					OntProperty hasCategory = model.getOntProperty(uri+"hasCategory");
					OntProperty hasPaymentMethod = model.getOntProperty(uri+"hasPaymentMethod");
					
					storedetails[0] = new StoreDetail();
					storedetails[0].category = Store.getPropertyValue(hasCategory).toString();
					storedetails[0].StoreName = Store.getPropertyValue(StoreName).toString();
					storedetails[0].PaymentMethod = Store.getPropertyValue(hasPaymentMethod).toString();
					storedetails[0].DeliveryMethod = Store.getPropertyValue(DeliveryMethod).toString();
					
					OntProperty hasLocation = model.getOntProperty(uri+"hasLocation");
					locationuri = Store.getPropertyResourceValue(hasLocation).getURI();
					Individual StoreLocation = model.getIndividual(locationuri);
					
					OntProperty City = model.getOntProperty(uri+"City");			
					OntProperty State = model.getOntProperty(uri+"State");			
					OntProperty Latitude = model.getOntProperty(uri+"Latitude");			
					OntProperty Longitude = model.getOntProperty(uri+"Longitude");	
					 
					location.City = StoreLocation.getPropertyValue(City).toString();
					location.State = StoreLocation.getPropertyValue(State).toString();
					location.latitude = StoreLocation.getPropertyValue(Latitude).toString();
					location.Longitude = StoreLocation.getPropertyValue(Longitude).toString();
					
					storedetails[0].location = location;
					
					OntProperty hasTiming = model.getOntProperty(uri+"hasTiming");			
					timinguri = Store.getPropertyResourceValue(hasTiming).getURI();			
					Individual TimingDetail = model.getIndividual(timinguri);			
					OntProperty WeekDayHours = model.getOntProperty(uri+"WeekDayHours");			
					OntProperty WeekEndHours = model.getOntProperty(uri+"WeekEndHours");
					timingdetail.WeekEndHours = TimingDetail.getPropertyValue(WeekEndHours).toString();
					timingdetail.WeekDayHours =  TimingDetail.getPropertyValue(WeekDayHours).toString();
					storedetails[0].timing = timingdetail;
					
							
					Individual iProductDetail = model.getIndividual(producturi);
									   	
					ProductName = model.getOntProperty(uri+"ProductName");
					 products[0] = new ProductDetail();	
					products[0].ProductCategory = iProductDetail.getPropertyValue(ProductCategory).toString();
					products[0].ProductCategory = iProductDetail.getPropertyValue(ProductDescription).toString();
					products[0].ProductName = iProductDetail.getPropertyValue(ProductName).toString();
					
					
					
					storedetails[0].products = products;
					
					OntProperty hasContactInformation = model.getOntProperty(uri+"hasContactInformation");
					contacturi = Store.getPropertyResourceValue(hasContactInformation).getURI();
						
					Individual iContactinformation = model.getIndividual(contacturi);		
					OntProperty EmailId = model.getOntProperty(uri+"EmailId");
					OntProperty PhoneNumber = model.getOntProperty(uri+"PhoneNumber");	
				   contactinformation.EmailId  = iContactinformation.getPropertyValue(EmailId).toString();
				   contactinformation.EmailId= iContactinformation.getPropertyValue(PhoneNumber).toString();
					
				   storedetails[0].contactInformation = contactinformation;
					
					company.stores = storedetails;
					companies.add(company);
				
				     
			}
			return companies;
			
		
	 }

	 public static void display(ArrayList<Company> companies)
	 {
		 for(int i =0 ;i<companies.size();i++)
		 {
			 Company company = companies.get(i);
			 System.out.println(company.CommonName);
			 StoreDetail[] storedetail = company.stores;
			 for(int j = 0 ;j<storedetail.length;j++)
			 {
				 StoreDetail store = storedetail[j];
				 System.out.println(store.StoreName);
				 System.out.println(store.location.latitude);
			 }
		 }
	 }
	 public static UserProfile readUserProfile(String userName)
	 {
		 OntModel model = ModelFactory.createOntologyModel();
			
			InputStream stream = FileManager.get().open("F:\\eclipse helios ws\\WebApp2\\WebContent\\UserData.owl");
			
			String uri = "http://www.semanticweb.org/ontologies/2011/2/Ontology1299586091336.owl#";
			
			model.read(stream,uri);
			
		 if(model.isEmpty())
		 {
			 System.out.println("Model Empty");
		 }
		String URI = uri+"KshitijShah";//username to be passed
		 Individual individual =  model.getIndividual(URI);
		/* StmtIterator iterator =  individual.listProperties();
		
		 while(iterator.hasNext())
		 {
			 System.out.println(iterator.next());
		 }
		 */
		    ContactInformation cinfo = new ContactInformation();
		 	Location loc  = new Location();
		 	Reading Reading = new Reading();
		 	Movies Movies = new Movies();	
		 	Music Music = new Music();	
		 	Sports Sports = new Sports();
		 	Eateries Eateries = new Eateries();	
		 	UserProfile profile = new UserProfile();
		 
		 
		 Property name = model.getOntProperty(uri+"name");
		 Statement stmt = individual.getProperty(name);
		 profile.name = stmt.getString();
		 
		 Property age = model.getOntProperty(uri+"age");
		  stmt = individual.getProperty(age);
		 profile.age = stmt.getString();
		 
		 Property gender = model.getOntProperty(uri+"gender");
		  stmt = individual.getProperty(gender);
		 profile.gender = stmt.getString();
		 
		 Property nationality = model.getOntProperty(uri+"nationality");
		  stmt = individual.getProperty(nationality);
		 profile.nationality =stmt.getString();
			 
		 Property profession = model.getOntProperty(uri+"profession");
		 stmt = individual.getProperty(profession);
		 profile.profession = stmt.getString();
		
		 Property dateofbirth = model.getOntProperty(uri+"dob");
		 stmt = individual.getProperty(dateofbirth);
		 profile.dateofbirth = stmt.getString();
		 
		 ObjectProperty likesReading = model.getObjectProperty(uri+"likesReading");
		 Resource reading =  individual.getPropertyResourceValue(likesReading);
		 ObjectProperty likesSports = model.getObjectProperty(uri+"likesSports");
		 Resource sports =  individual.getPropertyResourceValue(likesSports);
		 ObjectProperty likesMovies = model.getObjectProperty(uri+"likesMovies");
		 Resource movies = individual.getPropertyResourceValue(likesMovies);
		 ObjectProperty likesMusic = model.getObjectProperty(uri+"likesMusic");
		 Resource music =  individual.getPropertyResourceValue(likesMusic);
		 ObjectProperty likesEating = model.getObjectProperty(uri+"likesEating");
		 Resource eating =  individual.getPropertyResourceValue(likesEating);
		 ObjectProperty hasLocation = model.getObjectProperty(uri+"hasLocation");
		 Resource location = individual.getPropertyResourceValue(hasLocation);
		 ObjectProperty hasContactInformation = model.getObjectProperty(uri+"hasContactInformation");
		 Resource contactInformation = individual.getPropertyResourceValue(hasContactInformation);
		 
		 Property currentLocation = model.getOntProperty(uri+"currentLocation");
		 stmt = location.getProperty(currentLocation);
		 loc.current_location = stmt.getString();
		 
		 Property residentialLocation = model.getOntProperty(uri+"residentialLocation");
		 stmt = location.getProperty(residentialLocation);
		 loc.residential_location = stmt.getString();
		 
		 Property occupationalLocation = model.getOntProperty(uri+"occupationalLocation");
		 stmt = location.getProperty(occupationalLocation);
		 loc.occupational_location = stmt.getString();
		 
		 Property email = model.getOntProperty(uri+"email");
		 stmt =  contactInformation.getProperty(email);
		 cinfo.emailid = stmt.getString();
		 
		 Property landline = model.getOntProperty(uri+"landline");
		 stmt = contactInformation.getProperty(landline);
		 cinfo.landline = stmt.getString();
		 
		 Property mobile = model.getOntProperty(uri+"mobile");
		 stmt = contactInformation.getProperty(mobile);
		 cinfo.landline = stmt.getString();
		 
		 Property restaurant = model.getOntProperty(uri+"restaurant");
		 stmt =  eating.getProperty(restaurant);			
		 Eateries.restaurant = stmt.getString();
		 
		Property cuisine = model.getOntProperty(uri+"cuisine");
		stmt =  eating.getProperty(cuisine);
		Eateries.cuisine = stmt.getString();
		
			Property dish = model.getOntProperty(uri+"dish");
			stmt =  eating.getProperty(dish);
			Eateries.dish = stmt.getString();
		
			 Property preference = model.getOntProperty(uri+"preference");
			 stmt =  eating.getProperty(preference);
			 Eateries.preference = stmt.getString();
			 
			 Property club = model.getOntProperty(uri+"club");
			 stmt =  sports.getProperty(club);
			 Sports.club = stmt.getString();
			
			 Property sportsman = model.getOntProperty(uri+"sportsman");
			 stmt =  sports.getProperty(sportsman);
			 Sports.sportsman = stmt.getString();
		 
			 Property sport = model.getOntProperty(uri+"sport");
			 stmt =  sports.getProperty(sport);
			 Sports.sport =  stmt.getString();
			
			 Property song = model.getOntProperty(uri+"song");
			 stmt =  music.getProperty(song);
			 Music.song = stmt.getString();
		
			 Property genreMusic = model.getOntProperty(uri+"genreMusic");
			 stmt =  music.getProperty(genreMusic);
			 Music.genreMusic = stmt.getString();
	
			 Property artist = model.getOntProperty(uri+"artist");			 
			  stmt =  music.getProperty(artist);
			  Music.artist =   stmt.getString();
			  
			  Property band = model.getOntProperty(uri+"band");
			  stmt =  music.getProperty(band);
			  Music.band =  stmt.getString();
		
			 Property album = model.getOntProperty(uri+"album");
			 stmt =  music.getProperty(album);
			 Music.album = stmt.getString();
			
						 	
			 Property genreReading = model.getOntProperty(uri+"genreReading");
			 stmt =  reading.getProperty(genreReading);
			 Reading.genreReading = stmt.getString();
				
			 Property news = model.getOntProperty(uri+"news");
			 stmt =  reading.getProperty(news);
			 Reading.news = stmt.getString();			
		
			 Property magazine = model.getOntProperty(uri+"magazine");
			 stmt =  reading.getProperty(magazine);
			 Reading.magazine =  stmt.getString();
				
			 Property book = model.getOntProperty(uri+"book");
			 stmt =  reading.getProperty(book);
			 Reading.book =  stmt.getString();
					
			 Property blog = model.getOntProperty(uri+"blog");
			 stmt =  reading.getProperty(blog);
			 Reading.blog =  stmt.getString();
		
			 Property author = model.getOntProperty(uri+"author");
			 stmt =  reading.getProperty(author);
			 Reading.author = stmt.getString();
			
			 Property actor = model.getOntProperty(uri+"actor");			 
			 stmt =  movies.getProperty(actor);
			 Movies.actor = stmt.getString();
			 
			 Property actress = model.getOntProperty(uri+"actress");
			  stmt =  movies.getProperty(actress);
			  Movies.actress = stmt.getString();
		
	
			 Property director = model.getOntProperty(uri+"director");	
			 stmt =  movies.getProperty(director);
			 Movies.director = stmt.getString();
		
	
			 Property movie = model.getOntProperty(uri+"movie");
			 stmt =  movies.getProperty(movie);
			 Movies.movie =  stmt.getString();
	
			 Property genreMovie = model.getOntProperty(uri+"genreMovie");
			 stmt =  movies.getProperty(genreMovie);
			 Movies.genreMovie = stmt.getString();
			 
			profile.reading = Reading;
			profile.hasLocation = loc;
			profile.hascontactInfo = cinfo;
			profile.movies = Movies;
			profile.music = Music;
			profile.sports = Sports;
			profile.eateries = Eateries;
		return profile;
	 }
}

class JenaExploration {
	static ResultSet result;

public ArrayList<String> generatePrefix(Model model)
{
	ArrayList<String> prefix = new ArrayList<String>();
	Map<String,String> mp = model.getNsPrefixMap();
	Iterator<Entry<String, String>> it = mp.entrySet().iterator();		
	while (it.hasNext()) {
	        Map.Entry<String,String> pairs = (Map.Entry<String,String>)it.next();
	      //  System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        prefix.add("PREFIX "+pairs.getKey()+":"+"<"+pairs.getValue()+"/>");
	    }
	//prefix.remove(0);
	return prefix;
}

public ResultSet runSelectQuery(String query , Model model)
{
	try
	{
		
	StringBuffer buffer = new StringBuffer();
	
	ArrayList<String> prefix = generatePrefix(model);	
	
	for(int i = 0 ; i<prefix.size();i++)
		buffer.append(prefix.get(i));
	
	buffer.append(query);
	
	System.out.println(buffer.toString());
	
	Query question = QueryFactory.create(buffer.toString());
	
	QueryExecution qexec = QueryExecutionFactory.create(question,model);
			
	result = qexec.execSelect();
	
	return result;
	}
	
		catch (Exception e) {			
			return null;
	}

}

}
