package inputdata;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.*;


/**
 * Servlet implementation class readparam
 */
public class readparam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactInformation contact;
	 Location location;
	 Eateries eateries;
	 Sports sports;
	 Reading reading;
	 Music music;
	 Movies movies;
	 UserProfile userprofile;

	 public void initObjects()
	 {
	  contact=new ContactInformation();
	  location=new Location();
	  eateries=new Eateries();
	  sports=new Sports();
	  reading=new Reading();
	  music=new Music();
	  movies=new Movies();
	  userprofile=new UserProfile();
	 }  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readparam() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
		response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try 
	        {
	           /*
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet readparam</title>");  
	            out.println("</head>");
	            out.println("<body>");*/
	          //  out.println("<h1>Servlet readparam at " + request.getContextPath () + "</h1>");
	          
	          initObjects();  
	           userprofile.name=(request.getParameter("txtname"));
	           userprofile.age=(request.getParameter("txtage"));
	           userprofile.gender=request.getParameter("txtgender");
	           userprofile.dateofbirth=request.getParameter("txtdob");
	           userprofile.profession=request.getParameter("txtprofession");
	           userprofile.nationality=request.getParameter("txtnationality");
	           contact.emailid=request.getParameter("txtemail");
	           contact.mobile=request.getParameter("txtmobile");
	           contact.landline=request.getParameter("txtlandline");
	           //need to pass correct location
	           String ld=request.getParameter("loc_data");
	           String[] list1=constructLoc(handleLoc(ld));
	           location.current_location=list1[0];
	           location.occupational_location=list1[1];
	           location.residential_location=list1[0];
	           reading.author=request.getParameter("txtauthor");
	           reading.blog=request.getParameter("txtblog");
	           reading.book=request.getParameter("txtbook");
	           reading.magazine=request.getParameter("txtmagazine");
	           reading.news=request.getParameter("txtnews");
	           reading.genreReading=request.getParameter("txtgenre_reading");
	           //entertainment.language="English";
	           movies.actor=request.getParameter("txtactor");
	           movies.actress=request.getParameter("txtactress");
	           movies.director=request.getParameter("txtdirector");
	           movies.genreMovie=request.getParameter("txtgenre_movie");
	           movies.movie=request.getParameter("txtmovie");
	           music.album=request.getParameter("txtalbum");
	           music.artist=request.getParameter("txtartist");
	           music.band=request.getParameter("txtband");
	           music.song=request.getParameter("txtsong");
	           music.genreMusic=request.getParameter("txtgenre_music");
	           sports.club=request.getParameter("txtclub");
	           sports.sport=request.getParameter("txtsport");
	           sports.sportsman=request.getParameter("txtsportsman");
	           eateries.dish=request.getParameter("txtdish");
	           eateries.cuisine=request.getParameter("txtcuisine");
	           eateries.preference=request.getParameter("txtpreference");
	           eateries.restaurant=request.getParameter("txtrestaurant");

	           userprofile.eateries=eateries;
	           //userprofile.entertainment=entertainment;
	           userprofile.hasLocation=location;
	           userprofile.hascontactInfo=contact;
	           //userprofile.hasinterests=interests;
	           userprofile.movies=movies;
	           userprofile.music=music;
	           userprofile.reading=reading;
	           userprofile.sports=sports;
	           
	        //   web2onto.abc();
	          
	          web2onto.addUserData(userprofile);
	        //  out.print(userprofile.show());
	         
	         response.sendRedirect("index.jsp");
	         
	         //  out.println("</body>");
	         //   out.println("</html>");


	      }catch(Exception e)
	      {
	    	 // out.print(e.getMessage());
	    	  response.sendRedirect("userreg1.jsp");
	      }
	      finally{
	    	  
	      }
	}
   
	public String[] handleLoc(String k)
	{
		StringTokenizer st=new StringTokenizer(k," ");
		//System.out.print(st.countTokens());
		String[] temp=new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens())
		{
			temp[i]=new String();
			temp[i]=(st.nextToken());
			//System.out.println(temp[i]);
			i++;
		}
		return temp;
	}
	public String[] constructLoc(String[] k)
	{
		String[] temp=new String[k.length/2];
		temp[0]=new String();
		temp[1]=new String();
		temp[0]=k[0]+":"+k[1];	
		temp[1]=k[2]+":"+k[3];
		//System.out.println(temp[0]+"\n"+temp[1]);
		return temp;
	}
}











	
	



