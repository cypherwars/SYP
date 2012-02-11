/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inputdata;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


public class web2onto {
	public static void addUserData(UserProfile profile)
	{
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
		
		InputStream stream = FileManager.get().open("F:\\eclipse helios ws\\WebApp2\\WebContent\\UserData.owl");
		
		String uri = "http://www.semanticweb.org/ontologies/2011/2/Ontology1299586091336.owl#";
		
		model.read(stream,uri);
		
		if(model.isEmpty())
		{
			System.out.println("Empty");
		}
		
		OntClass user = model.getOntClass(uri+"User");
		Individual iuser =  user.createIndividual(uri+profile.name); 
	 	Property name = model.getOntProperty(uri+"name");
	 	iuser.addProperty(name, profile.name);
	 	Property age = model.getOntProperty(uri+"age");
	 	iuser.addProperty(age,profile.age+"");
	 	Property gender = model.getOntProperty(uri+"gender");
	 	iuser.addProperty(gender, profile.gender);
	 	Property nationality = model.getOntProperty(uri+"nationality");
	 	iuser.addProperty(nationality, profile.nationality);
	 	Property profession = model.getOntProperty(uri+"profession");
	 	iuser.addProperty(profession, profile.profession);
	 	Property dob = model.getOntProperty(uri+"dob");
	 	iuser.addProperty(dob, profile.dateofbirth);
	 	//Location
	 	
	 	OntClass location = model.getOntClass(uri+"Location");
	 	
	 	Individual ilocation =  location.createIndividual(uri+profile.name+"location");
	 	
	 	Property currentLocation = model.getOntProperty(uri+"currentLocation");
	 	
	 	Property residentialLocation = model.getOntProperty(uri+"residentialLocation");
	 	
	 	Property occupationalLocation = model.getOntProperty(uri+"occupationalLocation");
	 	
	 	ilocation.addProperty(currentLocation, profile.hasLocation.current_location);
	 	
	 	ilocation.addProperty(residentialLocation, profile.hasLocation.residential_location);
	 	
	 	ilocation.addProperty(occupationalLocation, profile.hasLocation.occupational_location);
	 	
	 	ObjectProperty hasLocation = model.getObjectProperty(uri+"hasLocation");
	  
	  	iuser.addProperty(hasLocation, ilocation);
	
	  	//ContactInformation
	  	
	  	OntClass contact = model.getOntClass(uri+"Contact");
	  	
	  	Individual  icontact = contact.createIndividual(uri+profile.name+"contact");
	  	
	  	Property email = model.getOntProperty(uri+"email");
	  	
	  	Property landline = model.getOntProperty(uri+"landline");
	  	
	  	Property mobile = model.getOntProperty(uri+"mobile");
	  	
	  	icontact.addProperty(email,profile.hascontactInfo.emailid);
	  	
	  	icontact.addProperty(mobile,profile.hascontactInfo.mobile);
	  	
	  	icontact.addProperty(landline,profile.hascontactInfo.landline);
	  	
	  	ObjectProperty hasContactInformation = model.getObjectProperty(uri+"hasContactInformation");
	  	
	  	iuser.addProperty(hasContactInformation, icontact);
	  	
	  	//Eateries
	  	
	  	OntClass eateries =model.getOntClass(uri+"Eateries");
	  	
	  	Individual ieateries = eateries.createIndividual(uri+profile.name+"eateries");
	  	
	  	Property cuisine = model.getOntProperty(uri+"cuisine");
	  	
	  	Property dish = model.getOntProperty(uri+"dish");
	  	
	  	Property preference = model.getOntProperty(uri+"preference");
	  	
	  	Property restaurant = model.getOntProperty(uri+"restaurant");
	  	
	  	ieateries.addProperty(cuisine, profile.eateries.cuisine);
	  	
	  	ieateries.addProperty(dish, profile.eateries.dish);
	  	
	  	ieateries.addProperty(preference, profile.eateries.preference);
	  	
	  	ieateries.addProperty(restaurant, profile.eateries.restaurant);
	  	
	  	ObjectProperty likesEating = model.getObjectProperty(uri+"likesEating");
	  	
	  	iuser.addProperty(likesEating, ieateries);
	  	
	  	//Music
	  	
	  	OntClass music = model.getOntClass(uri+"Music");
	  	
	  	Individual imusic = music.createIndividual(uri+profile.name+"music");
	  	
	  	Property album = model.getOntProperty(uri+"album");
	  	Property artist = model.getOntProperty(uri+"artist");
	  	Property band = model.getOntProperty(uri+"band");
	  	Property genreMusic = model.getOntProperty(uri+"genreMusic");
	  	Property song = model.getOntProperty(uri+"song");
	  	
	  	imusic.addProperty(album, profile.music.album);
	  	imusic.addProperty(artist, profile.music.artist);
	  	imusic.addProperty(band, profile.music.band);
	  	imusic.addProperty(genreMusic, profile.music.genreMusic);
	  	imusic.addProperty(song,profile.music.song);
	  	
	  	ObjectProperty likesMusic = model.getObjectProperty(uri+"likesMusic");
	  	iuser.addProperty(likesMusic, imusic);
	  	
	  	
	  	//Movies
	  	
        OntClass movies = model.getOntClass(uri+"Movies");
	  	
	  	Individual imovie = movies.createIndividual(uri+profile.name+"movie");
	  	
	  	Property movie = model.getOntProperty(uri+"movie");
	  	Property actor = model.getOntProperty(uri+"actor");
	  	Property actress = model.getOntProperty(uri+"actress");
	  	Property director = model.getOntProperty(uri+"director");
	  	Property genreMovie = model.getOntProperty(uri+"genreMovie");
	  	
	  	imovie.addProperty(movie, profile.movies.movie);
	  	imovie.addProperty(actor, profile.movies.actor);
	  	imovie.addProperty(actress, profile.movies.actress);
	  	imovie.addProperty(director, profile.movies.director);
	  	imovie.addProperty(genreMovie, profile.movies.genreMovie);
	  	
	  	ObjectProperty likesMovies = model.getObjectProperty(uri+"likesMovies");
	  	
	  	iuser.addProperty(likesMovies, imovie);
	  	
	  	//Sports
	  	
	  	OntClass sports = model.getOntClass(uri+"Sports");
	  	
	  	Individual isports = sports.createIndividual(uri+profile.name+"sports");
	  	
	  	Property sport = model.getOntProperty(uri+"sport");
	  	Property sportsman = model.getOntProperty(uri+"sportsman");
	  	Property club = model.getOntProperty(uri+"club");
	  	
	  	isports.addProperty(sport, profile.sports.sport);
	  	isports.addProperty(sportsman, profile.sports.sportsman);
	  	isports.addProperty(club, profile.sports.club);
	  	
	  	ObjectProperty likesSports = model.getObjectProperty(uri+"likesSports");
	  	
	  	iuser.addProperty(likesSports, isports);
	  	
	  	
	  	//Reading
	  	OntClass reading = model.getOntClass(uri+"Reading");
	  	
	  	Individual ireading = reading.createIndividual(uri+profile.name+"reading");
	  	
	  	Property author = model.getOntProperty(uri+"author");
	  	Property blog = model.getOntProperty(uri+"blog");
	  	Property book = model.getOntProperty(uri+"book");
	  	Property news = model.getOntProperty(uri+"news");
	  	Property magazine = model.getOntProperty(uri+"magazine");
	  	Property genreReading = model.getOntProperty(uri+"genreReading");
	  	
	  	ireading.addProperty(author, profile.reading.author);
	  	ireading.addProperty(blog, profile.reading.blog);
	  	ireading.addProperty(book, profile.reading.book);
	  	ireading.addProperty(magazine, profile.reading.magazine);
	  	ireading.addProperty(news, profile.reading.news);
	  	ireading.addProperty(genreReading, profile.reading.genreReading);
	  	
	  	ObjectProperty likesReading = model.getObjectProperty(uri+"likesReading");
	  	
	  	iuser.addProperty(likesReading, ireading);
	  	
	  
	  	RDFWriter writer = model.getWriter("RDF/XML");
		   
		   writer.setProperty("showXmlDeclaration",true);
		   
		   try {
			   
		   OutputStream out = new FileOutputStream("F:\\eclipse helios ws\\WebApp2\\WebContent\\UserData.owl");
		   
		   writer.write(model, out, uri);
		   		   
		   out.close();
		   
		   }
		   catch (Exception e) {			   
			   System.out.println(e.getMessage());			
		   }
	  		  
	}
	

public static void abc()
    {
        ContactInformation cinfo = new ContactInformation();
	 	cinfo.emailid = "abhirath@hotmail.com";
	 	cinfo.mobile = "921332410";
	 	cinfo.landline = "022-323455428";

	 	Location loc  = new Location();
	 	loc.occupational_location = "33.23;43.45";
	 	loc.residential_location = "33.23;43.45";
	 	loc.current_location = "33.23;43.45";

	 //	Interests interest = new Interests();
	 //	interest.interestName = "Reading";

	 	Reading reading = new Reading();
	 	reading.author = "DanBrof";
	 	reading.blog = "http://gotfrag2.com";
	 	reading.book = "Computing";
	 	reading.magazine = "wave";
	 	reading.news = "HT";
	 	reading.genreReading = "romantic";

	 //	Entertainment entertainment = new Entertainment();
	 //	entertainment.language = "English";

	 	Movies movies = new Movies();
	 	movies.actor = "SalmanKhan";
	 	movies.actress = "Nina";
	 	movies.director = "AnuragBasu";
	 	movies.genreMovie = "Adventure";
	 	movies.movie = "Border";

	 	Music music = new Music();
	 	music.album = "HybridTheory";
	 	music.artist = "Jacob";
	 	music.band = "Evanescence";
	 	music.genreMusic = "Metal";
	 	music.song = "NumbEncore";

	 	Sports sports = new Sports();
	 	sports.club = "ACMilan";
	 	sports.sport= "Football";
	 	sports.sportsman = "Henry";

	 	Eateries eateries = new Eateries();
	 	eateries.cuisine = "Chinese";
	 	eateries.dish="Burger";
	 	eateries.preference = "Vegetarian";
	 	eateries.restaurant = "McDonalds";


	 	UserProfile profile = new UserProfile();

	 	profile.name = "AbhirathAnuwal";
	 	profile.age = "11";
	 	profile.gender = "Male";
	 	profile.profession = "ConMan";
	 	profile.dateofbirth = "11-11-1989";
	 	profile.nationality = "Zimbabwe";
	 	profile.reading = reading;
	 	profile.hasLocation = loc;
	 	profile.hascontactInfo = cinfo;
	 	profile.movies = movies;
	 	profile.music = music;
	 	profile.sports = sports;
	 	profile.eateries = eateries;
	
                 System.out.println("Hello");
       addUserData(profile);
          
           
      
}
public static void xyz()
    {
    int numberofbrands = 1;

		int numberofstores = 1;

		int numberofproducts = 1;

		Brand brands[] = new  Brand[numberofbrands];
		brands[0] = new Brand();
		brands[0].BrandName = "xperia";
		brands[0].Slogan = "Smart Phones For Smart Users";

		ContactInformation_Store contactinformation = new ContactInformation_Store();
		contactinformation.EmailId = "vijaysales@gmail.com";
		contactinformation.Phonenumber = "9876543210";

		StoreLocation location = new StoreLocation();
		location.City = "Mumbai";
		location.latitude = "45.234";
		location.Longitude = "74.54";
		location.State = "Maharashtra";

		TimingDetail timingdetail = new TimingDetail();
		timingdetail.WeekDayHours = "10-6";
		timingdetail.WeekEndHours = "10-8";

		ProductDetail products[] = new ProductDetail[numberofproducts];
		products[0] = new ProductDetail();
		products[0].ProductCategory = "SmartPhones";
		products[0].ProductDescription = "Smart Phone with Touch Pad ";
		products[0].ProductName = "X-63";

		StoreDetail storedetails[] = new StoreDetail[numberofstores];
		storedetails[0] = new StoreDetail();
		storedetails[0].category = Category.ElectronicStore.name();
		storedetails[0].contactInformation = contactinformation;
		storedetails[0].DeliveryMethod = DeliveryMethod.HomeDelivery.name();
		storedetails[0].location = location;
		storedetails[0].PaymentMethod = PaymentMethod.Card.name();
		storedetails[0].products = products;
		storedetails[0].StoreName = "Nokia Priority Dealer";
		storedetails[0].timing = timingdetail;

		Company company = new Company();
		company.brands = brands;
		company.CommonName = "Ercisson";
		company.CompanyLogo = "http://Sony.com/Image.gif";
		company.CompanyPolicy = "Dis-Connecting People";
		company.CompanyUrl = "http://son.co.in";
		company.LegalName = "SonyIndiaPvt.Ltd.";
		company.stores = storedetails;
                PoplateServiceModel(company);

}

public static void PoplateServiceModel(Company company)
	{
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

		InputStream stream = FileManager.get().open("F:\\eclipse helios ws\\WebApp2\\WebContent\\ServiceData.owl");

		String uri = "http://www.semanticweb.org/ontologies/2011/1/ServiceOntology.owl#";

		model.read(stream,uri);

		try{stream.close();}catch(Exception e) {System.out.println();}

		//System.out.println(model.supportsSetOperations());

		OntClass Category = model.getOntClass(uri+"Category");

		ExtendedIterator<? extends OntResource> CategoryInstances = Category.listInstances(true);

		//System.out.println("Ashutosh");
		String[] CategoryIndividuals = new String[6];
		int k = 0;

		while(CategoryInstances.hasNext())
		{
			OntResource r = CategoryInstances.next();
			CategoryIndividuals[k++] = r.getURI();
			//System.out.println(r.getURI());

		}

		OntClass Payment = model.getOntClass(uri+"Payment");
		ExtendedIterator<? extends OntResource> PaymentInstances = Payment.listInstances(true);
		String[] PaymentIndividuals   = new String[3];

		k = 0 ;
		while(PaymentInstances.hasNext())
		{
			OntResource r = PaymentInstances.next();
			PaymentIndividuals[k++] = r.getURI();
			//System.out.println(r.getURI());
		}

		OntClass Company = model.getOntClass(uri+"Company");

		Individual iCompany = Company.createIndividual(uri+company.LegalName);

		//Data Properties Of Company
		OntProperty CommonName = model.getOntProperty(uri+"CommonName");
		OntProperty CompanyLogo = model.getOntProperty(uri+"CompanyLogo");
		OntProperty CompanyPolicy = model.getOntProperty(uri+"CompanyPolicy");
		OntProperty CompanyUrl = model.getOntProperty(uri+"CompanyUrl");
		OntProperty LegalName = model.getOntProperty(uri+"LegalName");

		iCompany.addProperty(CommonName, company.CommonName);
		iCompany.addProperty(CompanyLogo, company.CompanyLogo);
		iCompany.addProperty(CompanyPolicy, CompanyPolicy);
		iCompany.addProperty(CompanyUrl , company.CompanyUrl);
		iCompany.addProperty(LegalName, company.LegalName);


		//ObjectProperty hasBrand Of Company

		OntProperty hasBrand = model.getOntProperty(uri+"hasBrand");
		Brand brands []= company.brands;

		for (int i =0 ;i<brands.length;i++)
		{
			//System.out.println(brands.length);
			//System.out.println(brands[i].BrandName);

			OntClass Brand = model.getOntClass(uri+"Brand");
			Individual iBrand = Brand.createIndividual(uri+brands[i].BrandName+"Brand");
			OntProperty BrandName = model.getOntProperty(uri+"BrandName");
			OntProperty Slogan = model.getOntProperty(uri+"Slogan");
			iBrand.addProperty(BrandName, brands[i].BrandName);
			iBrand.addProperty(Slogan, brands[i].Slogan);
			iCompany.addProperty(hasBrand, iBrand);
		}

		//ObjectPropert hasStore of Company

		OntProperty hasStore = model.getOntProperty(uri+"hasStore");
		StoreDetail stores[] = company.stores;

		for(int i = 0 ;i<stores.length;i++)
		{

				OntClass StoreDetail = model.getOntClass(uri+"StoreDetail");
				Individual iStoreDetail = StoreDetail.createIndividual(uri+stores[i].StoreName+"Store");
				OntProperty DeliveryMethod = model.getOntProperty(uri+"DeliveryMethod");
				OntProperty StoreName = model.getOntProperty(uri+"StoreName");
				iStoreDetail.addProperty(DeliveryMethod, stores[i].DeliveryMethod);
				iStoreDetail.addProperty(StoreName, stores[i].StoreName);

			//ObjectProperty hasCategoery for StoreDetail

			OntProperty hasCategory = model.getOntProperty(uri+"hasCategory");

			if(stores[i].category.equalsIgnoreCase("BookStore"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[5]);}
			else if(stores[i].category.equalsIgnoreCase("Eatery"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[4]);}
			else if(stores[i].category.equalsIgnoreCase("ElectronicStore"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[3]);}
			else if(stores[i].category.equalsIgnoreCase("MusicStore"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[2]);}
			else if(stores[i].category.equalsIgnoreCase("SportsStore"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[1]);}
			else if(stores[i].category.equalsIgnoreCase("Theatre"))
			{ iStoreDetail.addProperty(hasCategory,CategoryIndividuals[0]);}

			//ObjectProperty hasContactInformation for StoresDetail

				OntProperty hasContactInformation = model.getOntProperty(uri+"hasContactInformation");
				OntClass ContactInformation = model.getOntClass(uri+"ContactInformation");
				Individual iContactinformation = ContactInformation.createIndividual(uri+stores[i].StoreName+"ContactInformation");
				OntProperty EmailId = model.getOntProperty(uri+"EmailId");
			
				OntProperty PhoneNumber = model.getOntProperty(uri+"PhoneNumber");
				iContactinformation.addProperty(EmailId,stores[i].contactInformation.EmailId);
				iContactinformation.addProperty(PhoneNumber, stores[i].contactInformation.Phonenumber);
				iStoreDetail.addProperty(hasContactInformation, iContactinformation);

			//ObjectPropety hasLocation for StoreDetail

				OntProperty hasLocation = model.getOntProperty(uri+"hasLocation");
				OntClass Location = model.getOntClass(uri+"Location");
				Individual iLocation = Location.createIndividual(uri+stores[i].StoreName+"Location");
				OntProperty City = model.getOntProperty(uri+"City");
				OntProperty State = model.getOntProperty(uri+"State");
				OntProperty Latitude = model.getOntProperty(uri+"Latitude");
				OntProperty Longitude = model.getOntProperty(uri+"Longitude");
				iLocation.addProperty(City, stores[i].location.City);
				iLocation.addProperty(State, stores[i].location.State);
				iLocation.addProperty(Latitude, stores[i].location.latitude);
				iLocation.addProperty(Longitude, stores[i].location.Longitude);
				iStoreDetail.addProperty(hasLocation, iLocation);

			//ObjectProperty hasTiming for StoreDetail

				OntProperty hasTiming = model.getOntProperty(uri+"hasTiming");
				OntClass TimingDetail = model.getOntClass(uri+"TmingDetail");
				Individual iTimingDetail = TimingDetail.createIndividual(uri+stores[i].StoreName+"TimingDetail");
				OntProperty WeekDayHours = model.getOntProperty(uri+"WeekDayHours");
				OntProperty WeekEndHours = model.getOntProperty(uri+"WeekEndHours");
				iTimingDetail.addProperty(WeekDayHours,stores[i].timing.WeekDayHours);
				iTimingDetail.addProperty(WeekEndHours, stores[i].timing.WeekEndHours);
				iStoreDetail.addProperty(hasTiming ,iTimingDetail);

			//ObjectProperty hasPaymentMethod for StoreDetail

			OntProperty hasPaymentMethod = model.getOntProperty(uri+"hasPaymentMethod");

			if(stores[i].PaymentMethod.equalsIgnoreCase("PayPal")){
				iStoreDetail.addProperty(hasPaymentMethod, PaymentIndividuals[2]);
			}
			else if(stores[i].PaymentMethod.equalsIgnoreCase("Cash")){
				iStoreDetail.addProperty(hasPaymentMethod, PaymentIndividuals[1]);
			}
			else if(stores[i].PaymentMethod.equalsIgnoreCase("Card")){
				iStoreDetail.addProperty(hasPaymentMethod, PaymentIndividuals[0]);
			}

			//ObjectProperty hasProduct for StoreDetail

			ProductDetail productdetail[] = stores[i].products;

			for(int j = 0 ;j < productdetail.length; j++)
			{
				OntProperty hasProduct = model.getOntProperty(uri+"hasProduct");
				OntClass ProductDetail = model.getOntClass(uri+"ProductDetail");
				Individual iProductDetail = ProductDetail.createIndividual(uri+stores[i].StoreName+stores[i].products[j].ProductName+"ProductDetail");
				OntProperty ProductCategory = model.getOntProperty(uri+"ProductCategory");
				OntProperty ProductDescription = model.getOntProperty(uri+"ProductDescription");
				OntProperty ProductName = model.getOntProperty(uri+"ProductName");
				iProductDetail.addProperty(ProductCategory, stores[i].products[j].ProductCategory);
				iProductDetail.addProperty(ProductDescription, stores[i].products[j].ProductDescription);
				iProductDetail.addProperty(ProductName,stores[i].products[j].ProductName);
				iStoreDetail.addProperty(hasProduct,iProductDetail);
			}

		   iCompany.addProperty(hasStore, iStoreDetail);

		   RDFWriter writer = model.getWriter("RDF/XML");

		   writer.setProperty("showXmlDeclaration", true);

		   try {

		   OutputStream out = new FileOutputStream("F:\\eclipse helios ws\\WebApp2\\WebContent\\ServiceData.owl");

		   writer.write(model, out, uri);

		   out.close();

		   }
		   catch (Exception e) {
			   System.out.println(e.getMessage());
		   }

		}

	}
public static void main(String[] args)
{
	//abc();
}
}
