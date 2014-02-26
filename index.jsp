<%@ page import="java.util.*,java.io.*, java.sql.*, Load.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <!--
    Created by Artisteer v3.1.0.46558
    Base template (without users data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Swaziland Farming</title>

    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- InstanceBeginEditable name="doctitle" -->
<meta name="description" content="All the information you need to know about farming, markets, produce and suppliers in Swaziland and Southern Africa"/>
<meta name="keywords" content="ict,agriculture,farm,farming,swaziland,south,africa,southern africa,market,buy,sell,purchase,fruits,vegetable,livestock,meat,abbatoir,pack house,fish,chicken,produce,soil,gis"/>

<link rel="stylesheet" href="tinyfader/fader.css">
<script src="tinyfader/fader.js"></script>

    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- InstanceBegin template="/Templates/template1.dwt" codeOutsideHTMLIsLocked="false" -->
</head>
<body width=800 height=600>
<%
    try {
        
        
    
 %>    
<div id="art-page-background-glare-wrapper">
    <div id="art-page-background-glare"></div>
</div>
<div id="art-main">
    <div class="cleared reset-box"></div>
    <div class="art-box art-sheet">
        <div class="art-box-body art-sheet-body">
            <div class="art-header">
                <div class="art-logo">
                <h1 class="art-logo-name"><a href="index.jsp">SWAZILAND FARMING</a></h1><small><b>market intelligence system</b></small>
                </div>
            </div>
            <div class="cleared reset-box"></div>
<!--<div class="art-bar art-nav">-->
<div>
<p> </p>
</div>
<div class="cleared reset-box"></div>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1">
<div class="art-box art-vmenublock">
    <div class="art-box-body art-vmenublock-body">
                <div class="art-bar art-vmenublockheader">
                    <h3 class="t"> </h3>
                </div>
                <div class="art-box art-vmenublockcontent">
                    <div class="art-box-body art-vmenublockcontent-body">
                <ul class="art-vmenu">
	<li>
<%
    String uName = Load.getUsername(request, response);

    out.println("<li><a href=\"about.jsp?uname=" + uName + "\"><b>About</b></a></li><li>");
    out.println("<a href=\"login.jsp?uname=" + uName + "\"><b>Login</b></a></li><li>");
    out.println("<a href=\"index.jsp?uname=" + uName + "\" class=active><b>Home</b></a></li><li>");
    out.println("<a href=\"farmers.jsp?uname=" + uName + "\"><b>Farmers</b></a></li><li>");
	out.println("<a href=\"markets.jsp?uname=" + uName + "\"><b>Markets</b></a></li><li>");
    out.println("<a href=\"produce.jsp?uname=" + uName + "\"><b>Produce</b></a></li><li>");
    out.println("<a href=\"soil.jsp?uname=" + uName + "\"><b>Soil</b></a></li><li>");
    out.println("<a href=\"reports.jsp?uname=" + uName + "\"><b>Reports</b></a></li><li>");
    out.println("<a href=\"inputsuppliers.jsp?uname=" + uName + "\"><b>Input Suppliers</b></a></li><li>");
    out.println("<a href=\"maps.jsp?uname=" +  uName  + "\"><b>Maps</b></a></li><li>");
    out.println("<a href=\"contactus.jsp?uname=" +  uName  + "\"><b>Contact Us</b></a></li><li>");%>
	</li></b>

</ul>
                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>
<!--<img src="images\swazilandlogo.JPG"><br><br>-->
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
                <div class="art-bar art-blockheader">
                    <h3 class="t"> </h3>
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <p></p>                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>

                          <div class="cleared"></div>
                        </div>
                        <div class="art-layout-cell art-content">
<div class="art-box art-post">
    <div class="art-box-body art-post-body">
<div class="art-post-inner art-article">
                                <h2 class="art-postheader">  </h2>
                                <div class="art-postcontent">
<p>
                                
<!--<h3>FARMING IN SWAZILAND</h3-->
<!--<p><img src="images\pawpaw.JPG"></p-->
			<ul id="slides">
				<li><img src="images/veg1.jpg" width="450" height="300"></li>
				<li><img src="images/veg2.JPG" width="450" height="300"></li>
				<li><img src="images/veg3.JPG" width="450" height="300"></li>
				<li><img src="images/veg3.JPG" width="450" height="300"></li>
			</ul>

	<ol id="pagination" class="pagination">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ol>

<script>
var ss = new TINY.fader.init('ss', {
	id: 'slides',
	auto: 4,
	resume: true,
	navid: 'pagination',
	navEvent: 'mouseover',
	activeClass: 'current',
	pauseHover: true
});
</script>
<p><br><br><br></p>
<p>Information and Communication Technology (ICT) has become a major role player in the Agricultural sector by providing a platform that enables simple and affordable means of communicating and facilitating transactions by creating links
between all stakeholders. With the consistent use of ICT, it has been proven that farmers are able to cut down on operational and many other costs. 
The document <b><a href="ictinagriculture.rtf"><u>ICT in Agriculture</u></a></b> contains more information about ICT's role on Agriculture.</p>		
<p>The site is open to everyone, but we have identified the stakeholders below as those that will find the site most useful:</b></p>
        <p><b>Farmers, Pack Houses, Hospitality Operators,  Wholesalers,  Retailers, Bakkie Vendors, Road Side Vendors, 
        Municipal Market Vendors, Swaziland Water and Agricultural Development Enterprise, Swaziland Ministry of Agriculture,
        Funding Agencies, Sponsors, Donors, Commercial Financial Institutions, Input Suppliers, Swaziland, Southern Africa, GIS.</b></p>
                </div>
                <div class="cleared">
                </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>
        <div class="cleared"></div>
                        </div>
                        <div class="art-layout-cell art-sidebar2">
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
                <div class="art-bar art-blockheader">
                    <h3 class="t">Search</h3>
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <div>
  <form method="get" name="searchform" action="#">
    <input type="text" value="" name="s" style="width: 95%;" />
     <span class="art-button-wrapper">
      <span class="art-button-l"> </span>
      <span class="art-button-r"> </span>
      <a class="art-button" href="javascript:void(0)">Search</a>
    </span> 

  </form>
</div>                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
                <div class="art-bar art-blockheader">
                    <h3 class="t">Partners</h3>
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <div>
                
  <p> </p>
  <ul>
    <li>
      <a href="http://www.swade.co.sz" title="Swaziland Water and Agricultural Development Enterprise">SWADE</a>
    </li>
    <li>
      <a href="http://www.gov.co.sz" title="Swaziland Ministry of Agriculture">Ministry of Agriculture</a>
    </li>
    <li>
      <a href="http://www.ifad.org" title="International Fund for Agricultural Development">IFAD</a>
    </li>
    <li>
      <a href="http://www.namboard.co.sz" title="National Agricultural Marketing Board">NAMBOARD</a>
    </li>
    <li>
      <a href="http://www.lulote.co.sz" title="Small Holder Irrigation Project">SHIP</a>
    </li>
    <li>
      <a href="http://www.lulote" title="LULOTE">LULOTE</a>
    </li>
  </ul>
</div>                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>
                                		<div class="cleared"></div>

                    </div>
                </div>

</div>
                                        
                    
<div class="art-footer" align="center">
    <!--<table cellspacing="20">
<td><a href="rssfeeds.jsp" class="art-rss-tag-icon" title="RSS"></a></td>
<td><a href="http://www.feeddude.com/feeds/-3212338428046193055.rss">CTA's ICT Update</a></td>
<td><a href="http://www.feeddude.com/feeds/-2811230554994382668.rss">E-Agriculture</a></td>
<td><a href="http://www.feeddude.com/feeds/1988585973493815697.rss">MobileActive</a></td>
<td><a href="http://www.feeddude.com/feeds/-4316459245381951162.rss">ICT-KM of CGIAR</a></td>
<td><a href="http://www.feeddude.com/feeds/1226026512795322507.rss">IICD</a></td>
</table
<a href="http://www.swade.co.sz" target="_blank"><img src="./images/swadelogo.JPG"></a>
<a href="http://www.gov.co.sz" target="_blank"><img src="./images/goslogo.JPG"></a>
<a href="http://www.ifad.org" target="_blank"><img src="./images/ifadlogo.JPG"></a>
<a href="http://www.swade.co.sz/index.php/ship" target="_blank"><img src="./images/shiplogo.JPG" width=120></a>
<a href="http://www.namboard.co.sz" target="_blank"><img src="./images/namboardlogo.JPG"></a>-->
                   
                <div class="art-footer-body">
                            <div class="art-footer-text">
<p><b>Copyright &copy SWADE 2013. All Rights Reserved.</b></p>
                                                            </div>
                    
                </div>
            </div>
<%} catch(Exception e) {}%>
</body>
</html>
