package inputdata;

import java.io.*;
import java.util.*;

import com.hp.hpl.jena.util.FileManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.reasoner.rulesys.test.WebOntTestHarness;

import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import inputdata.*;

/**
 * Servlet implementation class readservicedata
 */
public class readservicedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readservicedata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ksh();
		
		
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		int numberofbrands = 1;

		int numberofstores = 1;

		int numberofproducts = 1;
		try
		{
		PrintWriter out=response.getWriter();
		Brand brands[] = new  Brand[numberofbrands];
		brands[0] = new Brand();
		brands[0].BrandName = request.getParameter("txtbrand");
		brands[0].Slogan = request.getParameter("txtslogan");

		ContactInformation_Store contactinformation = new ContactInformation_Store();
		contactinformation.EmailId = request.getParameter("txtemail");
		contactinformation.Phonenumber = request.getParameter("txtphone");

		//geo work
		StoreLocation location = new StoreLocation();
		location.City = request.getParameter("txtcity");
		location.State = request.getParameter("txtstate");
		String ld=request.getParameter("loc_data");
		String list1[]=handleLoc(ld);
		location.latitude = list1[0];
		location.Longitude = list1[1];
		

		TimingDetail timingdetail = new TimingDetail();
		timingdetail.WeekDayHours = request.getParameter("txtweekdayhours");
		timingdetail.WeekEndHours = request.getParameter("txtweekendhours");

		ProductDetail products[] = new ProductDetail[numberofproducts];
		products[0] = new ProductDetail();
		products[0].ProductCategory = request.getParameter("txtproductcategory");
		products[0].ProductDescription = request.getParameter("txtproductdescription");
		products[0].ProductName = request.getParameter("txtproductname");

		StoreDetail storedetails[] = new StoreDetail[numberofstores];
		storedetails[0] = new StoreDetail();
		storedetails[0].category =request.getParameter("lstcategory");
		storedetails[0].contactInformation = contactinformation;
		//delivery method
		storedetails[0].DeliveryMethod = request.getParameter("lstdeliverymechanism");
		storedetails[0].location = location;
		//payment method
		storedetails[0].PaymentMethod = request.getParameter("lstpaymentmethod");
		storedetails[0].products = products;
		storedetails[0].StoreName = request.getParameter("txtstorename");
		storedetails[0].timing = timingdetail;

		Company company = new Company();
		company.brands = brands;
		company.CommonName = request.getParameter("txtcompanyname");
		company.CompanyLogo = request.getParameter("txtlogo");
		company.CompanyPolicy = request.getParameter("txtpolicy");
		company.CompanyUrl = request.getParameter("txturl");
		company.LegalName = request.getParameter("txtlegalname");
		company.stores = storedetails;
		web2onto.PoplateServiceModel(company);
		 response.sendRedirect("index.jsp");
		 //web2onto.PoplateServiceModel(company);
		 //PopulateServiceModel(company);
		}
		catch(Exception e)
		{
			response.sendRedirect("servicereg.jsp");
			//e.printStackTrace();
	    }
	}
	public String[] handleLoc(String k)
	{
		StringTokenizer st=new StringTokenizer(k," ");
		System.out.print(k+"  "+st.countTokens());
		String[] temp=new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens())
		{
			temp[i]=new String();
			temp[i]=(st.nextToken());
			System.out.println(temp[i]);
			i++;
		}
		return temp;
	}
	public String constructLoc(String[] k)
	{
		String temp=new String();
				
		temp=k[0]+":"+k[1];	
	//System.out.println(temp[0]+"\n"+temp[1]);
		return temp;
	}
	/*
	public static void PopulateServiceModel(Company company)
	{
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

		InputStream stream = FileManager.get().open("Service.owl");

		String uri = "http://www.semanticweb.org/ontologies/2011/1/ServiceOntology.owl#";

		model.read(stream,uri);

		try{stream.close();}catch(Exception e) {System.out.println();}

		System.out.println(model.supportsSetOperations());

		OntClass Category = model.getOntClass(uri+"Category");

		ExtendedIterator<? extends OntResource> CategoryInstances = Category.listInstances(true);

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

		   OutputStream out = new FileOutputStream("Service.owl");

		   writer.write(model, out, uri);

		   out.close();

		   }
		   catch (Exception e) {
			   System.out.println(e.getMessage());
		   }

		}

	}
*/
	public static void ksh()
	{
		OntModel model = ModelFactory.createOntologyModel();
		InputStream stream = FileManager.get().open("Service.owl");

		String uri = "http://www.semanticweb.org/ontologies/2011/1/ServiceOntology.owl#";

		model.read(stream,uri);
	}
}

enum Category{BookStore,Eatery,ElectronicStore,MusicStore,SportsStore,Theatre};
enum PaymentMethod{Card,Cash,PayPal};
enum DeliveryMethod{HomeDelivery,StorePickUp};






 

