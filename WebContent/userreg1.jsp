<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" class="cufon-active cufon-ready">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
                // map.addOverlay(marker);
               // marker.openInfoWindowHtml(address);
  			  
  			  
  			 // alert(gl.lat()+" "+gl.lang());
            }
          }
        );
      }
    }
    function callme()
    {
    	showAddress(document.getElementById("txthomecity").value+" "+document.getElementById("txthomestate").value);
    }
    function callme2()
    {
    	showAddress(document.getElementById("txtofficecity").value+" "+document.getElementById("txtofficestate").value);
    }
    
 
    </script> 
<meta charset="utf-8" xml:lang="en" />
<link rel="stylesheet" href="./Contacts2_files/reset.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="./Contacts2_files/layout.css"
	type="text/css" media="all" />
<link rel="stylesheet" href="./Contacts2_files/style.css"
	type="text/css" media="all" />
<script type="text/javascript" src="./Contacts2_files/jquery-1.4.2.js"></script>
<script type="text/javascript" src="./Contacts2_files/cufon-yui.js"></script>
<style type="text/css">
cufon {
	text-indent: 0 !important;
}

@media screen , projection {
	cufon {
		display: inline !important;
		display: inline-block !important;
		position: relative !important;
		vertical-align: middle !important;
		font-size: 1px !important;
		line-height: 1px !important;
	}
	cufon cufontext {
		display: -moz-inline-box !important;
		display: inline-block !important;
		width: 0 !important;
		height: 0 !important;
		overflow: hidden !important;
		text-indent: -10000in !important;
	}
	cufon canvas {
		position: relative !important;
	}
}

@media print {
	cufon {
		padding: 0 !important;
	}
	cufon canvas {
		display: none !important;
	}
}
</style>
<script type="text/javascript" src="./Contacts2_files/cufon-replace.js"></script>
<script type="text/javascript"
	src="./Contacts2_files/Myriad_Pro_600.font.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->


<!-- ksh: code for jquery tabs -->
<link type="text/css"
	href="css/custom-theme/jquery-ui-1.8.10.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.10.custom.min.js"></script>
<script type="text/javascript">
	$(function() {

		// Accordion
		$("#accordion").accordion({
			header : "h3"
		});

		// Tabs
		$('#tabs').tabs();

		// Dialog			
		$('#dialog').dialog({
			autoOpen : false,
			width : 600,
			buttons : {
				"Ok" : function() {
					$(this).dialog("close");
				},
				"Cancel" : function() {
					$(this).dialog("close");
				}
			}
		});

		// Dialog Link
		$('#dialog_link').click(function() {
			$('#dialog').dialog('open');
			return false;
		});

		// Datepicker
		$('#datepicker').datepicker({
			inline : true
		});

		// Slider
		$('#slider').slider({
			range : true,
			values : [ 17, 67 ]
		});

		// Progressbar
		$("#progressbar").progressbar({
			value : 20
		});

		//hover states on the static widgets
		$('#dialog_link, ul#icons li').hover(function() {
			$(this).addClass('ui-state-hover');
		}, function() {
			$(this).removeClass('ui-state-hover');
		});

	});
</script>
<link type="text/css" rel="stylesheet" href="css/suggest.min.css" />

<script type="text/javascript" src="js/suggest.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#txtgender").suggest({
			type : '/people/gender'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtnationality").suggest({
			type : '/location/country'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtprofession").suggest({
			type : '/people/profession'
		}).bind("fb-select", function(e, data) {
		});

		$("#txthomestate").suggest({
			type : '/location/in_state'
		}).bind("fb-select", function(e, data) {
		});

		/*	$("#txthomecity").suggest({
			type : '/location/in_city'
		}).bind("fb-select", function(e, data) {
		});
*/
		$("#txthomedistrict").suggest({
			type : '/location/in_district'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtofficestate").suggest({
			type : '/location/in_state'
		}).bind("fb-select", function(e, data) {
		});

	/*	$("#txtofficecity").suggest({
			type : '/location/in_city'
		}).bind("fb-select", function(e, data) {
		});
*/
		$("#txtofficedistrict").suggest({
			type : '/location/in_district'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtdirector").suggest({
			type : '/film/director'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtactor").suggest({
			type : '/film/actor'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtactress").suggest({
			type : '/film/actor'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtgenre_movie").suggest({
			type : '/film/film_genre'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtmovie").suggest({
			type : '/film/film'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtauthor").suggest({
			type : '/book/author'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtblog").suggest({
			type : '/internet/blog'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtbook").suggest({
			type : '/book/book'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtmagazine").suggest({
			type : '/book/magazine'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtnews").suggest({
			type : '/book/newspaper'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtgenre_reading").suggest({
			type : '/book/book_subject'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtalbum").suggest({
			type : '/music/album'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtartist").suggest({
			type : '/music/artist'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtband").suggest({
			type : '/music/musician'
		}, {
			type : '/music/artist'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtgenre_music").suggest({
			type : '/music/genre'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtsong").suggest({
			type : '/music/track'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtsport").suggest({
			type : '/sports/sport'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtsportsman").suggest({
			type : '/sports/pro_athlete'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtclub").suggest({
			type : '/sports/sports_team'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtpreference").suggest({
			type : '/food/diet'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtdish").suggest({
			type : '/food/dish'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtcuisine").suggest({
			type : '/dining/cuisine'
		}).bind("fb-select", function(e, data) {
		});

		$("#txtrestaurant").suggest({
			type : '/dining/restaurant'
		}).bind("fb-select", function(e, data) {
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
					<h1>
						<a href="index.html" id="logo">SEMANTIC Yellow Pages</a>
					</h1>
					<div class="right">
						<div class="wrapper">
							<form id="search" action="" method="post">
								<div class="bg">
									<input type="submit" class="submit" value=""> <input
										type="text" class="input">
								</div>
							</form>
						</div>
						<div class="wrapper">
							<nav>
								<ul id="top_nav">
									<li><a href="#">Register</a>
									</li>
									<li><a href="#">Log In</a>
									</li>
									<li><a href="#">Help</a>
									</li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<nav>
					<ul id="menu">
						<li><a href="index.jsp" class="nav1">Home</a>
						</li>
						<li><a href="userreg1.jsp" class="nav2">User Profile </a>
						</li>
						<li><a href="servicereg.jsp" class="nav3">Service Profile</a>
						</li>
						<li><a href="test.jsp" class="nav4">About</a>
						</li>
						<li class="end"><a href="Contacts.html" class="nav5">Contacts</a>
						</li>
					</ul>
				</nav>
				<div class="text">
					<img src="images/text1.jpg" alt="">
					<h2>Semantic Yellow Pages</h2>
					<p>Semantic Yellow Pages is a user centric search engine which
						is aimed at providing fast results relavant to the current user
						according to his preferences and lifestyle ! In other words its
						just customized for you !</p>
					<a href="#" class="button">Read More</a>
				</div>
				<div class="img">
					<img src="images/semanticlogo.jpg" height='300px' width='347px' alt="" />
				</div>
			</header>
			<!-- / header -->
			<!-- content -->
			<section id="content">

				<form id="regformuser" action="readparam" action="GET">
					<div id="tabs"
						class="ui-tabs ui-widget ui-widget-content ui-corner-all">
						<ul
							class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
							<li
								class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active"><a
								href="#tabs-1">User Profile</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-2">Contact
									Information</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-3">Location
									Details</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-4">Interests:Reading</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-5">Interests:Music</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-6">Interests:Movies</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-7">Interests:Sports</a>
							</li>
							<li class="ui-state-default ui-corner-top"><a href="#tabs-8">Interests:Eateries</a>
							</li>
						</ul>
						<div id="tabs-1"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom">
							<table>
								<tr>
									<td>Name</td>
									<td><input type="text" id="txtname" name="txtname" />
									</td>
								</tr>

								<tr>
									<td>Age</td>
									<td><input type="text" id="txtage" name="txtage" />
									</td>
								</tr>
								<tr>
									<td>Gender</td>
									<td><input type="text" id="txtgender" name="txtgender" />
									</td>
								</tr>
								<tr>
									<td>Date of Birth</td>
									<td><input type="text" id="txtdob" name="txtdob" />
									</td>
								</tr>
								<tr>
									<td>Nationality</td>
									<td><input type="text" id="txtnationality"
										name="txtnationality" />
									</td>
								</tr>
								<tr>
									<td>Profession</td>
									<td><input type="text" id="txtprofession"
										name="txtprofession" />
									</td>
								</tr>

							</table>
						</div>
						<div id="tabs-2"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Email</td>
									<td><input type="text" id="txtemail" name="txtemail" />
									</td>
								</tr>
								<tr>
									<td>Mobile</td>
									<td><input type="text" id="txtmobile" name="txtmobile" />
									</td>
								</tr>
								<tr>
									<td>Landline</td>
									<td><input type="text" id="txtlandline" name="txtlandline" />
									</td>
								</tr>
							</table>
						</div>
						<div id="tabs-3"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>
										<fieldset>
											<legend>HomeLocation</legend>
											<table>
												<tr>
													<td>State</td>
													<td><input type="text" id="txthomestate"
														name="txthomestate" /></td>
												</tr>

												<tr>
													<td>District</td>
													<td><input type="text" id="txthomedistrict"
														name="txthomedistrict" /></td>
												</tr>
												<tr>
													<td>City</td>
													<td><input type="text" id="txthomecity"
														name="txthomecity" /></td>
												</tr>
												<tr>
													<td>Street Address</td>
													<td><input type="text" id="txthomestreetaddress"
														name="txthomestreetaddress"  onblur="callme()"/></td>
												</tr>
												<tr>
													<td>Zip</td>
													<td><input type="text" id="txthomezip"
														name="txthomezip" /></td>
												</tr>
											</table>
										</fieldset></td>
									<td></td>
									<td>
										<fieldset>
											<legend>&nbsp &nbsp OfficeLocation</legend>
											<table>
												<tr>
													<td>&nbsp &nbsp State</td>
													<td><input type="text" id="txtofficestate"
														name="txtofficestate" /></td>
												</tr>

												<tr>
													<td>&nbsp &nbsp District</td>
													<td><input type="text" id="txtofficedistrict"
														name="txtofficedistrict" /></td>
												</tr>
												<tr>
													<td>&nbsp &nbsp City</td>
													<td><input type="text" id="txtofficecity"
														name="txtofficecity" /></td>
												</tr>
												<tr>
													<td>&nbsp &nbsp Street Address</td>
													<td><input type="text" id="txtofficestreetaddress"
														name="txtofficestreetaddress" onblur="callme2()"/></td>
												</tr>

												<tr>
													<td>&nbsp &nbsp Zip</td>
													<td><input type="text" id="txtofficezip"
														name="txtofficezip" /></td>
												</tr>
											</table>
										</fieldset></td>
								</tr>
							</table>



						</div>
						<div id="tabs-4"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Author</td>
									<td><input type="text" id="txtauthor" name="txtauthor" />
									</td>
								</tr>
								<tr>
									<td>Blog</td>
									<td><input type="text" id="txtblog" name="txtblog" />
									</td>
								</tr>
								<tr>
									<td>Book</td>
									<td><input type="text" id="txtbook" name="txtbook" />
								</tr>
								</tr>
								<tr>
									<td>Magazine</td>
									<td><input type="text" id="txtmagazine" name="txtmagazine" />
									</td>
								</tr>
								<tr>
									<td>Genre</td>
									<td><input type="text" id="txtgenre_reading"
										name="txtgenre_reading" />
									</td>
								</tr>
								<tr>
									<td>News</td>
									<td><input type="text" id="txtnews" name="txtnews" />
									</td>
								</tr>
							</table>
						</div>
						<div id="tabs-5"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Album</td>
									<td><input type="text" id="txtalbum" name="txtalbum" />
									</td>
								</tr>
								<tr>
									<td>Artiste</td>
									<td><input type="text" id="txtartist" name="txtartist" />
									</td>
								</tr>
								<tr>
									<td>Band</td>
									<td><input type="text" id="txtband" name="txtband" />
								</tr>
								</tr>
								<tr>
									<td>Genre</td>
									<td><input type="text" id="txtgenre_music"
										name="txtgenre_music" />
									</td>
								</tr>
								<tr>
									<td>Song</td>
									<td><input type="text" id="txtsong" name="txtsong" />
									</td>
								</tr>
							</table>
						</div>
						<div id="tabs-6"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Actor</td>
									<td><input type="text" id="txtactor" name="txtactor" />
									</td>
								</tr>
								<tr>
									<td>Actress</td>
									<td><input type="text" id="txtactress" name="txtactress" />
									</td>
								</tr>
								<tr>
									<td>Director</td>
									<td><input type="text" id="txtdirector" name="txtdirector" />
								</tr>
								</tr>
								<tr>
									<td>Movie</td>
									<td><input type="text" id="txtmovie" name="txtmovie" />
									</td>
								</tr>
								<tr>
									<td>Genre</td>
									<td><input type="text" id="txtgenre_movie"
										name="txtgenre_movie" />
									</td>
								</tr>

							</table>
						</div>
						<div id="tabs-7"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Sport</td>
									<td><input type="text" id="txtsport" name="txtsport" />
									</td>
								</tr>
								<tr>
									<td>Sportsman</td>
									<td><input type="text" id="txtsportsman"
										name="txtsportsman" />
									</td>
								</tr>
								<tr>
									<td>Club</td>
									<td><input type="text" id="txtclub" name="txtclub" />
								</tr>
								</tr>

							</table>
						</div>
						<div id="tabs-8"
							class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide">
							<table>
								<tr>
									<td>Cuisine</td>
									<td><input type="text" id="txtcuisine" name="txtcuisine" />
									</td>
								</tr>
								<tr>
									<td>Dish</td>
									<td><input type="text" id="txtdish" name="txtdish" />
									</td>
								</tr>
								<tr>
									<td>Preference</td>
									<td><input type="text" id="txtpreference"
										name="txtpreference" />
								</tr>
								</tr>
								<tr>
									<td>Restaurant</td>
									<td><input type="text" id="txtrestaurant"
										name="txtrestaurant" />
								</tr>
								</tr>
							</table>
						</div>
						<div id="map_canvas" style="width: 0px; height: 0px"></div>
						<input type="text" id="loc_data" name="loc_data" style="visibility:hidden"/> 
						<input	type="submit" id="go" text="GO!" value="go!" />
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
	<script type="text/javascript">
		Cufon.now();
	</script>

</body>
</html>