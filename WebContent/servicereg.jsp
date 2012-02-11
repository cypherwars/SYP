<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>

<html lang="en" class="cufon-active cufon-ready">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contacts</title>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAuPsJpk3MBtDpJ4G8cqBnjRRaGTYH6UMl8mADNa0YKuWNNa8VNxQCzVBXTx2DYyXGsTOxpWhvIG7Djw" type="text/javascript"></script> 
<script type="text/javascript"> 
    var map = null;
    var geocoder = null;
    
    function initialize() {
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map_canvas"));
        document.getElementById("map_canvas").style.visibility="hidden";
        map.setCenter(new GLatLng(37.4419, -122.1419), 13);
        geocoder = new GClientGeocoder();
        geocoder.setBaseCountryCode("in");
      }
    } 
    function showAddress(address) {
        if (geocoder) {
          geocoder.getLatLng(
            address,
            function(point) {
              if (!point) {
                alert(address + " not found");
              } else {
                
                map.setCenter(point, 13);
                var marker = new GMarker(point);
                var gl=marker.getLatLng();
             //  alert(gl.lat()+"  "+gl.lng());
               document.getElementById("loc_data").value=document.getElementById("loc_data").value+"  "+gl.lat()+"  "+gl.lng();
              
            }
          }
        );
      }
    }
    function callme()
    {
    	showAddress(document.getElementById("txtcity").value+" "+document.getElementById("txtstate").value);
    	
    }
    </script> 
<link rel="stylesheet" href="./Contacts2_files/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="./Contacts2_files/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="./Contacts2_files/style.css" type="text/css" media="all">
<script type="text/javascript" src="./Contacts2_files/jquery-1.4.2.js"></script>
<script type="text/javascript" src="./Contacts2_files/cufon-yui.js"></script>
<style type="text/css">cufon{text-indent:0!important;}@media screen,projection{cufon{display:inline!important;display:inline-block!important;position:relative!important;vertical-align:middle!important;font-size:1px!important;line-height:1px!important;}cufon cufontext{display:-moz-inline-box!important;display:inline-block!important;width:0!important;height:0!important;overflow:hidden!important;text-indent:-10000in!important;}cufon canvas{position:relative!important;}}@media print{cufon{padding:0!important;}cufon canvas{display:none!important;}}
</style>
<script type="text/javascript" src="./Contacts2_files/cufon-replace.js"></script>  
<script type="text/javascript" src="./Contacts2_files/Myriad_Pro_600.font.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->


<!-- ksh: code for jquery tabs -->
	<link type="text/css" href="css/custom-theme/jquery-ui-1.8.10.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
		<script type="text/javascript">
			$(function(){

				// Accordion
				$("#accordion").accordion({ header: "h3" });
	
				// Tabs
				$('#tabs').tabs();
	

				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() { 
							$(this).dialog("close"); 
						}, 
						"Cancel": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				// Datepicker
				$('#datepicker').datepicker({
					inline: true
				});
				
				// Slider
				$('#slider').slider({
					range: true,
					values: [17, 67]
				});
				
				// Progressbar
				$("#progressbar").progressbar({
					value: 20 
				});
				
				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);
				
			});
		</script>
        <link type="text/css" rel="stylesheet" href="css/suggest.min.css" />

<script type="text/javascript" src="js/suggest.min.js"></script>
<script type="text/javascript">
$(function() {
$("#txtnationality,#txtprofession,#txtauthor,#txtblog,#txtbook,#txtmagazine,#txtgenre_reading,#txtnews,#txtalbum,#txtartist,#txtsong,#txtgenre_music,#txtband,#txtactor,#txtactress,#txtdirector,#txtmovie,#txtgenre_movie,#txtcuisine,#txtrestaurant,#txtdish,#txtpreference,#txtsport,#txtsportsman,#txtclub")
  .suggest()
  .bind("fb-select", function(e, data) {
    alert(data.name + ", " + data.id);
  });
});

</script>
</head>
<body id="page2" onload="initialize()" onunload="GUnload()">
<div class="extra">
	<div class="main">
<!-- header -->
	<header>
			<div class="wrapper">
				<h1><a href="index.html" id="logo">SEMANTIC Yellow Pages</a></h1>
				<div class="right">
					<div class="wrapper">
						<form id="search" action="" method="post">
							<div class="bg">
								<input type="submit" class="submit" value="">
								<input type="text" class="input">
							</div>
						</form>
					</div>
					<div class="wrapper">
						<nav>
							<ul id="top_nav">
								<li><a href="#">Register</a></li>
								<li><a href="#">Log In</a></li>
								<li><a href="#">Help</a></li>
							</ul>
						</nav>
					</div>	
				</div>
			</div>
			<nav>
				<ul id="menu">
					<li><a href="index.jsp" class="nav1">Home</a></li>
					<li><a href="userreg1.jsp" class="nav2">User Profile </a></li>
					<li><a href="servicereg.jsp" class="nav3">Service Profile</a></li>
					<li><a href="test.jsp" class="nav4">About</a></li>
					<li class="end"><a href="Contacts.html" class="nav5">Contacts</a></li>
				</ul>
			</nav>
			<div class="text">
				<img src="images/text1.jpg" alt="">
				<h2>Semantic Yellow Pages</h2>
				<p>Semantic Yellow Pages is a user centric search engine which is aimed at providing fast results relavant to the current user according to his preferences and lifestyle ! In other words its just customized for you !</p>
				<a href="#" class="button">Read More</a>
			</div>
			<div class="img"><img src="images/semanticlogo.jpg" height='300px' width='347px' alt="" /></div>
		</header>
<!-- / header -->
<!-- content -->
		<section id="content">
       
      <form id="regformuser" action="readservicedata" method="post">
				<div id="tabs"
					class="ui-tabs ui-widget ui-widget-content ui-corner-all">
					<ul
						class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
						<li
							class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active"><a
							href="#tabs-1">Company</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-2">Contact
								Information</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-3">Brand</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-4">Product
								Details</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-5">Store
								Details</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-6">Store
								Location</a>
						</li>
						<li class="ui-state-default ui-corner-top"><a href="#tabs-7">Timings</a>
						</li>
					</ul>
					<div id="tabs-1"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom">
						<table>
							<tr>
								<td>Company Name</td>
								<td><input type="text" id="txtcompanyname"
									name="txtcompanyname" />
								</td>
							</tr>

							<tr>
								<td>Logo</td>
								<td><input type="text" id="txtlogo" name="txtlogo" />
								</td>
							</tr>
							<tr>
								<td>Policy</td>
								<td><input type="text" id="txtpolicy" name="txtpolicy" />
								</td>
							</tr>
							<tr>
								<td>URL</td>
								<td><input type="text" id="txturl" name="txturl" />
								</td>
							</tr>
							<tr>
								<td>Legal Name</td>
								<td><input type="text" id="txtlegalname"
									name="txtlegalname" />
								</td>
							</tr>
							<tr>
								<td>Brand</td>
								<td><input type="text" id="txtbrand" name="txtbrand" />
								</td>
							</tr>
							<tr>
								<td>Store</td>
								<td><input type="text" id="txtstore" name="txtstore" />
								</td>
							</tr>
						</table>
					</div>
					<div id="tabs-2"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Email</td>
								<td><input type="text" id="txtemail" name="txtemail"/>
								</td>
							</tr>
							<tr>
								<td>Phone</td>
								<td><input type="text" id="txtphone" name="txtphone"/>
								</td>
						</table>
					</div>
					<div id="tabs-3"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Brand Name</td>
								<td><input type="text" id="txtbrandname"
									name="txtbrandname" />
								</td>
							</tr>
							<tr>
								<td>Slogan</td>
								<td><input type="text" id="txtslogan" name="txtslogan" />
								</td>
							</tr>
						</table>
					</div>
					<div id="tabs-4"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Product Name</td>
								<td><input type="text" id="txtproductname"
									name="txtproductname" />
								</td>
							</tr>
							<tr>
								<td>Description</td>
								<td><input type="text" id="txtproductdescription"
									name="txtproductdescription" />
								</td>
							</tr>
							<tr>
								<td>Category</td>
								<td><input type="text" id="txtproductcategory"
									name="txtproductcategory" />
							</tr>
							</tr>
						</table>
					</div>
					<div id="tabs-5"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Store Name</td>
								<td><input type="text" id="txtstorename"
									name="txtstorename" />
								</td>
							</tr>
							<tr>
								<td>Category</td>
								<td>
								<select name="lstcategory">
								<option>BookStore</option>
								<option>Eatery</option>
								<option>ElectronicStore</option>
								<option>MusicStore</option>
								<option>SportsStore</option>
								<option>Theatre</option>
								</select>
								</td>
							</tr>
							<tr><td>Payment Method</<td><td><select name="lstpaymentmethod"><option>Card</option><option>Cash</option><option>PayPal</option></select></td></tr>
							<tr><td>Delivery Mechanism</td><td><select name="lstdeliverymechanism"><option>HomeDelivery</option><option>StorePickUp</option></td>
							</tr>

						</table>
					</div>
					<div id="tabs-6"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>City</td>
								<td><input type="text" id="txtcity" name="txtcity" />
								</td>
							</tr>
							<tr>
								<td>State</td>
								<td><input type="text" id="txtstate" name="txtstate" />
								</td>
							</tr>
						</table>
					</div>
					<div id="tabs-7"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Weekday Hours</td>
								<td><input type="text" id="txtweekdayhours"
									name="txtweekdayhours" onblur="callme()"/>
								</td>
							</tr>
							<tr>
								<td>Weekend Hours</td>
								<td><input type="text" id="txtweekendhours"
									name="txtweekendhours" />
								</td>
							</tr>

						</table>
					</div>
					<div id="tabs-8"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
						<table>
							<tr>
								<td>Cuisine</td>
								<td><input type="text" id="txtcuisine" />
								</td>
							</tr>
							<tr>
								<td>Dish</td>
								<td><input type="text" id="txtdish" />
								</td>
							</tr>
							<tr>
								<td>Preference</td>
								<td><input type="text" id="txtpreference" />
							</tr>
							</tr>
							<tr>
								<td>Restaurant</td>
								<td><input type="text" id="txtrestaurant" />
							</tr>
							</tr>
						</table>
					</div>
						<div id="map_canvas" style="width: 0px; height: 0px"></div>
						<input type="text" id="loc_data" name="loc_data" style="visibility:hidden"/> 
					<input type="submit" id="go" text="GO!" value="go!" />
				</div>
			</form>
        	
		</section>
<!-- / content -->
	</div>
	<div class="block"></div>
</div>
<div class="body1">
	<div class="main">
<!-- footer -->
		<footer>
			<a href="#" target="_blank">Website template</a> designed by Noobs<br>
			<a href="#" target="_blank">3D Models</a> provided by Noobs
		</footer>
<!-- / footer -->
	</div>
</div>
<script type="text/javascript"> Cufon.now(); </script>

</body></html>