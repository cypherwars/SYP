package inputdata;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setSession
 */
public class setSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		
	HttpSession session=request.getSession();
	session.removeAttribute("companyinfo");
	session.removeAttribute("fbinfo");
	session.removeAttribute("status");
	session.removeAttribute("storename");
	session.removeAttribute("profile");
	session.removeAttribute("storedetail");
	session.removeAttribute("searchtext");
	String k=request.getParameter("searchbox");
	
	if(k==null)k="dan_brown";
	ArrayList<Company> acl=Search.retrieveCompanyInformation(k);
	session.setAttribute("companyinfo",acl);
	k=k.toLowerCase();
	String temp=k.replaceAll(" ","_");
	ArrayList<Information> atl=FreeBase.start(temp,"KshitijShah");
	session.setAttribute("profile",FreeBase.readUserProfile("KshitijShah"));
	session.setAttribute("fbinfo",atl);
	session.setAttribute("searchtext",temp);
	String status="";
	if(atl ==null && acl==null)
		status="1";
	else if (atl !=null && acl!= null)
		status="2";
	else if(acl== null & atl!= null)
		status="3";
	else status="4";
	session.setAttribute("status",status);
	response.sendRedirect("index.jsp?i"+Math.random());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
