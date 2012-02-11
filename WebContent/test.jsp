<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="org.json.simple.JSONObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function modalWin() {
	if (window.showModalDialog) {
	window.showModalDialog("xpopupex.htm","name",
	"dialogWidth:255px;dialogHeight:250px");
	} else {
	window.open('xpopupex.htm','name','height=255,width=250,toolbar=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no ,modal=yes');
	}
	}
$(function() {
	// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	$( "#dialog-message" ).dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});
	$( "#create-user" )
	.button()
	.click(function() {
		$( "#dialog-message" ).dialog( "open" );
		alert("Hello");
		return false;
	});
});

</script>

</head>

<body>
<a href="xpopupex.htm" target="name" onclick="modalWin(); return false;">click here</a>
<button id="create-user">Create new user</button>
	<div id="dialog-message" title="Download complete">
	
	<p>
		Currently using <b>36% of your storage space</b>.
	</p>
</div>
</body>
</html>