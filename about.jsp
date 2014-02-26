<%@ page import="java.util.*,java.io.*, java.sql.*, Load.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <!--
    Created by Artisteer v3.1.0.46558
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>About Swaziland Farming</title>

    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>

</head>
<body>
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
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1">
<div class="art-box art-vmenublock">
    <div class="art-box-body art-vmenublock-body">
                <div class="art-bar art-vmenublockheader">
                    <h3 class="t"></h3>
                </div>
                <div class="art-box art-vmenublockcontent">
                    <div class="art-box-body art-vmenublockcontent-body">
                <ul class="art-vmenu">
    <%
    String uName = Load.getUsername(request, response);

    out.println("<li><a href=\"about.jsp?uname=" + uName + "\" class=active><b>About</b></a></li><li>");
    out.println("<a href=\"login.jsp?uname=" + uName + "\"><b>Login</b></a></li><li>");
    out.println("<a href=\"index.jsp?uname=" + uName + "\"><b>Home</b></a></li><li>");
    out.println("<a href=\"farmers.jsp?uname=" + uName + "\"><b>Farmers</b></a></li><li>");
	out.println("<a href=\"markets.jsp?uname=" + uName + "\"><b>Markets</b></a></li><li>");
    out.println("<a href=\"produce.jsp?uname=" + uName + "\"><b>Produce</b></a></li><li>");
    out.println("<a href=\"soil.jsp?uname=" + uName + "\"><b>Soil</b></a></li><li>");
    out.println("<a href=\"reports.jsp?uname=" + uName + "\"><b>Reports</b></a></li><li>");
    out.println("<a href=\"inputsuppliers.jsp?uname=" + uName + "\"><b>Input Suppliers</b></a></li><li>");
    out.println("<a href=\"maps.jsp?uname=" +  uName  + "\"><b>Maps</b></a></li><li>");
    out.println("<a href=\"contactus.jsp?uname=" +  uName  + "\"><b>Contact Us</b></a></li><li>");%>
</b>
</ul>
                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
                <div class="art-bar art-blockheader">
                    <h3 class="t"></h3>
                        
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <p> </p>                
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
                                <h2 class="art-postheader">About</h2>
                                
                                <div class="art-postcontent">
<p>The Swaziland Farming Market Intelligence System began as a concept of the Swaziland Water and Agricultural Development Enterprise (SWADE) several years ago.</p>
                                
<p>It's main purpose is to link Farmers to Markets (vice versa), to enable Farmers to market and sell their produce to the markets, and to enable markets to view the entire 
farming process, from planting to harvesting and availability, of produce they procure.</p>

<p>The system requirements were compiled by Mr Sandile Bhembe while he worked at SWADE, and the system was developed by Wandile Nxumalo at SWADE.</p>

<p>The system is open to entire public for;</p>
    <ul>
    <li> farmers to keep records the ongoings of their farms eg. farm details, planting, harvesting
    <li> farmers to market their produce to markets
    <li> markets to monitor progress of farms
    <li> markets to purchase produce from farms
    </ul>

<p>NB: The use of the system can assist in the reduction of costs for both markets and farmers by the provision of a cost effective communication platform, as opposed to 
    telephone calls and travelling to and from markets.</p>
                </div>
                <div class="cleared"></div>
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
  <p></p>
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
      <a href="http://www.swade.co.sz/index.php/ship" title="Small Holder Irrigation Project">SHIP</a>
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
<div class="art-box art-block">
    <div class="art-box-body art-block-body">
                <div class="art-bar art-blockheader">
                    <h3 class="t"></h3>
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <div>
  
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
                    <h3 class="t"></h3>
                </div>
                <div class="art-box art-blockcontent">
                    <div class="art-box-body art-blockcontent-body">
                <div>
  
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
            </div>
            <div class="cleared"></div>
            <div class="art-footer">
                <div class="art-footer-body">
                    <!--<a href="#" class="art-rss-tag-icon" title="RSS"></a-->
                            <div class="art-footer-text">
                                <p> </p>

<p>Copyright &copy SWADE 2013. All Rights Reserved.</p>
                                                            </div>
                    <div class="cleared"></div>
                </div>
            </div>
    		<div class="cleared"></div>
        </div>
    </div>
    <div class="cleared"></div>
    <p class="art-page-footer"></p>
    <div class="cleared"></div>
</div>

</body>
</html>
