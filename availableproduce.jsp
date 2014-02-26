<%@ page import="java.util.*,java.io.*,java.sql.*,Connections.*, AvailableProduce.*,Load.*,Planting.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <!--
    Created by Artisteer v3.1.0.46558
    Base template (without users data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Available Produce</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <script language="javascript" type="text/javascript" src="datetimepicker.js"></script>

</head>
<body width=800 height=600>
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
    out.println("<a href=\"index.jsp?uname=" + uName + "\"><b>Home</b></a></li><li>");
    out.println("<a href=\"farmers.jsp?uname=" + uName + "\" class=active><b>Farmers</b></a></li><li>");
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
<h3>AVAILABLE PRODUCE</h3>

<%

boolean proceed = false;
boolean proceed1 = false;          

if (uName.equals("null")) {
    
    ResultSet cfs = AvailableProduce.loadAvailableProduce(request, response);

    out.println("<p><b>" + request.getParameter("fname") + "</b></p>");
    out.println("<table border=1><tr><td><b>Available From</b></td><td><b>Available To</b></td><td><b>Produce Type</b></td><td width=100><b>Description</b></td><td><b>Quantity</b></td><td><b>Variety</b></td><td><b>Packaging</b></td><td><b>Area Planted</b></td><td><b>Unit</b></td><td><b>Unit Price</b></td></tr>");

    while (cfs.next()) {

        proceed = true;

        out.println("<tr><td>" + cfs.getString(4) + "</td><td>" + cfs.getString(5) + "</td><td>" + cfs.getString(6) + "</td><td>" +  cfs.getString(7) + "</td><td>" +  cfs.getString(8) + "</td><td>" +  cfs.getString(9) + "</td><td>" +  cfs.getString(10) + "</td><td>" +  cfs.getString(11) + "</td><td>" +  cfs.getString(12) + "</td><td>" +  cfs.getString(13) + "</td><td></tr>");

    }

    out.println("</table>");

    if (proceed == false) {

        out.println("<b>No available produce available</b>");

    }

    Connections.closeCon();

} else {
    
    
    if (AvailableProduce.getUserView1(request, response) != null) {

        ResultSet cfs = AvailableProduce.loadAvailableProduce(request, response);

        out.println("<p><b>" + AvailableProduce.getFName(request, response) + "</b></p>");
        out.println("<table border=1><tr><td><b>ID</b></td><td><b>AVAILABLE FROM</b></td><td><bAVAILABLE TO<b></td><td><b>PRODUCE TYPE</b></td><td><b>DESCRIPTION</b></td></tr>");

        while (cfs.next()) {

            proceed = true;

            out.println("<tr><td>" + cfs.getString(1) +"</td>" + "<td>" + cfs.getString(4) +"</td>" + "<td>" + cfs.getString(5) +"</td>" + "<td>" + cfs.getString(6) + "</td>" + 
            "<td>" + cfs.getString(7) +"</td><td><a href=\"smsavailableprod.jsp?uname=" + uName + "&fname=" + AvailableProduce.getFName(request, response) + "&from=" + cfs.getString(4) + "&to=" + cfs.getString(5) + "&ptype=" + cfs.getString(6) + "&pdesc=" + cfs.getString(7) + "\">send</a></td></tr>");

        }

        out.println("</table>");

        if (proceed == false) {

            out.println("<b>No availableproduce data available</b>");                    

        }

        Connections.closeCon();

        out.println("<form method=POST action=\"availableproduce.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&fname=" + AvailableProduce.getFName(request, response) + "&reguserview2=true\">");%>
        <p class="smallText">
        <input type="radio" name="group1" value="Delete" CHECKED> Delete
        <input type="radio" name="group1" value="Edit" CHECKED> Edit
        </p>
        <p>Available Produce ID<br>
        <input type=text name=apid size=40></p>
        <p>
        <input type="submit" value="Next" name="B1">
        <input type="hidden" name="id">
        </p>
        </form>
        <%

    } else if (AvailableProduce.getUserView2(request, response) != null) {

        proceed1 = false;

        if (AvailableProduce.getGroup(request, response).equals("Edit")) {

            proceed1 = false;

            ResultSet cfs = AvailableProduce.getAPID(request, response);

                while (cfs.next()) {

                    out.println("<form name=frm method=POST action=\"availableproducepost.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&fname=" + AvailableProduce.getFName(request, response) + "&reguserview3=true&apid=" + AvailableProduce.getHAProduceID(request, response) + "\">");
                    out.println("<p>Available From<br>");
                    %>
                    <input type="Text" id="demo2" maxlength="25" size="25" name="afrom" <%out.println("value=\"" + cfs.getString(4) + "\">");%><a href="javascript:NewCal('demo2','yyyymmdd',false,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></p>
                    <%    
                    out.println("<p>Available To<br>");
                    %>
                    <input type="Text" id="demo2" maxlength="25" size="25" name="ato" <%out.println("value=\"" + cfs.getString(5) + "\">");%><a href="javascript:NewCal('demo2','yyyymmdd',false,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></p>
                    <%    
                    out.println("<p>Produce Type<br>");
                    out.println("<select name=ptype class=styled value=\"" + cfs.getString(6) + "\">");
                    out.println("<option value=Fruit>Fruit</option>");
                    out.println("<option value=Vegetable>Vegetable</option>");
                    out.println("<option value=Livestock>Livestock</option>");                    
                    out.println("</select></p>");
                    out.println("<p>Description<br>");
                    out.println("<input type=text name=desc size=40 value=\"" + cfs.getString(7) + "\"></p>");
                    out.println("<p>Quantity<br>");
                    out.println("<input type=text name=quantity size=40 value=\"" + cfs.getString(8) + "\"></p>");
                    out.println("<p>Variety<br>");
                    out.println("<input type=text name=variety size=40 value=\"" + cfs.getString(9) + "\"></p>");
                    out.println("<p>Packaging<br>");
                    out.println("<input type=text name=packaging size=40 value=\"" + cfs.getString(10) + "\"></p>");
                    out.println("<p>Area Planted (ha)<br>");
                    out.println("<input type=text name=aplanted size=40 value=\"" + cfs.getString(11) + "\"></p>");
                    out.println("<p>Unit<br>");
                    out.println("<input type=text name=unit size=40 value=\"" + cfs.getString(12) + "\"></p>");
                    out.println("<p>Unit Price<br>");
                    out.println("<input type=text name=uprice size=40 value=\"" + cfs.getString(13) + "\"></p>");
                    out.println("<p>Quality<br>");
                    out.println("<input type=text name=quality size=40 value=\"" + cfs.getString(14) + "\"></p>");
                    out.println("<p><input type=submit value=Save name=B1>");
                    out.println("<input type=reset value=Reset name=Reset>");
                    out.println("<input type=hidden name=id></p>");
                    out.println("</form>");

            }

        } else if (AvailableProduce.getGroup(request, response).equals("Delete")) {
            
            AvailableProduce.deleteAvailableProduce(request, response);

            out.println("Available Produce Deleted!");

            proceed1 = false;
        }

        Connections.closeCon();

    } else {
%>
        <%out.println("<form method=POST action=\"availableproduce.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&fname=" + AvailableProduce.getFName(request, response) + "&reguserview1=true\">");%>
        <p class="smallText">
        <input type="radio" name="group1" value="view" checked> View
        <!--<input type="radio" name="group1" value="get"> Get-->
        </p>
        <p>
        <input type="submit" value="Next" name="B1">
        <input type="hidden" name="id"></td></tr>

        </form>

        <%proceed1 = true;
  }
}

if (proceed1 == true) {
    String pID = "";
    String cDesc = "";
    String aPlanted = "";

    out.println("<p align=left><a href=\"availableproduce.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&fname=" + AvailableProduce.getFName(request, response) + "&getplanting=true\">LINK TO HARVESTING</a></p>");    

    try {

        AvailableProduce.getPlanting(request, response).equals("true");

        ResultSet cfs = AvailableProduce.getHarvesting(request, response);

        out.println("<p><b>" + AvailableProduce.getFName(request, response) + "- HARVESTING</b></p>");
        out.println("<table border=1><tr><td><b>ID</b></td><td><b>Start Date</b></td><td><b>End Date</b></td><td width=300><b>Crop Description</b></td><td><b>Actual Yields</b></td></tr>");

        while (cfs.next()) {

            proceed = true;

            out.println("<tr><td>" + cfs.getString(1) + "</td><td>" + cfs.getString(3) + "</td><td>" + cfs.getString(4) + "</td><td>" + cfs.getString(6) + "</td><td>" + cfs.getString(9) + "</td></tr>");

        }

        if (proceed == false) {

            out.println("<b>No farm harvesting available</b>");                    

        }

        out.println("</table><form method=POST action=\"availableproduce.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&fname=" + AvailableProduce.getFName(request, response) + "&getplanting1=true\">");
        out.println("<p>Enter Harvesting ID<br>");
        out.println("<input type=text name=pid size=40></p>");
        out.println("<p><input type=submit value=Get name=B1>");
        out.println("<input type=reset value=Reset name=Reset></p>");
        out.println("<input type=hidden name=id></form>");

        Connections.closeCon();

    } catch (NullPointerException npe) {
        
        String[] entryFields = { "pid"};
        String[] entry = new String[1];
/*
        try {        
            
            for (int i = 0; i < entryFields.length; i++) {
                entry[i] = Guestbook.filterString(request.getParameter(entryFields[i]));
            }
            
        } catch (NullPointerException npe3) {}  
*/
        try {

            AvailableProduce.getPlanting1(request, response);

            ResultSet cfs = AvailableProduce.getHarvestingByIDs(request, response);

            while (cfs.next()) {

                pID = cfs.getString(1);
                cDesc = cfs.getString(6);
                aPlanted = cfs.getString(9);
                    
                proceed1 = true;                    
            }

            Connections.closeCon();

        } catch (NullPointerException npe1) {
            
        }catch (Exception npe1) {
            
        }
    }

    if (proceed1 == true) {
        if (pID.equals("")) {
            out.println("<p>Not linked to any harvesting</p>");
            out.println("<form name=frm method=POST action=\"availableproducepost.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&pid=0" + "\">");
        } else {
            out.println("<p>Linked to Harvesting # " + pID + "</p>");
            out.println("<p></p><form name=frm method=POST action=\"availableproducepost.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "&pid=" + pID + "\">");
        }
    } else {
        out.println("<form name=frm method=POST action=\"availableproducepost.jsp?uname=" + uName + "&fid=" + AvailableProduce.getFID(request, response) + "\">");
    }
    
        out.println("<p>Available From<br>");%>
        <input type="Text" id="demo1" maxlength="25" size="25" name="afrom"><a href="javascript:NewCal('demo1','yyyymmdd',false,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></p><%
        out.println("<p>Available To<br>");%>
        <input type="Text" id="demo2" maxlength="25" size="25" name="ato"><a href="javascript:NewCal('demo2','yyyymmdd',false,24)"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></p><%
    
        String pType = "";
        String pDesc = "";
        String pVariety = "";

        ResultSet cfs = AvailableProduce.getHID(request, response);

        while (cfs.next()) {

            pID = cfs.getString(1);
            pType = cfs.getString(4);
            pDesc = cfs.getString(6);
            pVariety = cfs.getString(5);

        }

        out.println("<p>Produce Type<br>");
        out.println("<select name=ptype class=styled>");
        out.println("<option value=Fruit>Fruit</option>");
        out.println("<option value=Vegetable>Vegetable</option>");
        out.println("<option value=Livestock>Livestock</option>");                    
        out.println("</select></p>");                    

        try {
            AvailableProduce.getPlanting1(request, response).equals("true");

            out.println("<p>Description<br>");
            out.println("<input type=text name=desc size=40 value=" +  pDesc + "></p>");

        } catch (Exception e) {
            out.println("<p>Description<br>");
            out.println("<input type=text name=desc size=40></p>");
        }

        out.println("<p>Quantity<br>");
        out.println("<input type=text name=quantity size=40></p>");

        try {
            AvailableProduce.getPlanting1(request, response).equals("true");

            out.println("<p>Variety<br>");
            out.println("<input type=text name=variety size=40></p>");

        } catch (Exception e) {
            out.println("<p>Variety<br>");
            out.println("<input type=text name=variety size=40></p>");
        }

        out.println("<p>Packaging<br>");
        out.println("<input type=text name=packaging size=40 value=></p>");
        out.println("<p>Area Planted (ha)<br>");
        out.println("<input type=text name=aplanted size=40 value=></p>");                    
        out.println("<p>Unit Quantity<br>");
        out.println("<input type=text name=unit size=40 value=></p>");                    
        out.println("<p>Unit Price<br>");
        out.println("<input type=text name=uprice size=40 value=></p>");                                        
        out.println("<p>Quality<br>");
        out.println("<input type=text name=quality size=40 value=></p>");                                        
        out.println("<p><input type=submit value=Save name=B1>");
        out.println("<input type=reset value=Reset name=Reset></p>");
        out.println("<input type=hidden name=id>");
        out.println("</form>");

        Connections.closeCon();
}
%>
         
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
                                		<div class="cleared"></div>

                    </div>
                </div>

<div class="art-footer">
                <div class="art-footer-body">
                    <!-- <a href="#" class="art-rss-tag-icon" title="RSS"></a-->
                            <div class="art-footer-text">
<p>Copyright &copy SWADE 2012. All Rights Reserved.</p>
                                                            </div>
                    
                </div>
            </div>

</body>
</html>
