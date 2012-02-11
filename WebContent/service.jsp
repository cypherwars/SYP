<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.util.*,org.json.simple.JSONObject,inputdata.Search,inputdata.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function k()
{
var jf="somev = { 'color' : 'blue'}";
eval(jf);
alert(somev.color);
}
</script>
</head>
<body onload="k()">
 <div>
 <%
   ArrayList<Company> al=Search.retrieveCompanyInformation();
   for(int i=0;i<al.size();i++)
   
 %>
 </div>
</body>
</html>