<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script type='text/javascript' src='/WebApp2/dwr/interface/buddy.js'></script>
  <script type='text/javascript' src='/WebApp2/dwr/engine.js'></script>
 <script type='text/javascript' src='/WebApp2/dwr/util.js'></script>
<script type="text/javascript">

	function val()
	{
		var j=dwr.util.getValue('t1');
		buddy.isEmpty(j,handler);
	}
	function handler(data)
	{		//var s=dwr.setValue('s1','');
		
		if(data)
			{
			document.getElementById("s1").innerHTML="clean";
			}
		else{
			document.getElementById("s1").innerHTML="hey man!!!";
		}
	}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body">
<div id="content">This wil change</div>
<form id="a1" action="post">
<input type="text" id="t1" onkeyup="val()"/><span id="s1"></span>

</form>
</body>
</html>