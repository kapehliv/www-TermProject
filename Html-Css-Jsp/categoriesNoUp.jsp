<html>
<title>Find a Recipe</title>
	<head>
	
	<style type="text/css">
	body 
	{
		background-image:url('back2.png');
		background-repeat: no-repeat;
	}
	
	h1
	{
		font-family: Segoe Script;
		margin-top: 80px;
		margin-left:380px;
		font-size:50px;
		color:#302617;
	}
	
	h3
	{
		font-family: Segoe Script;
		margin-top: -50px;
		margin-left:750px;
		font-size:25px;
		color:#302617;


	}


	#f1
	{
		margin-top: 80px;
		width:250px;
		margin-left:420px;
	}

	#f1:hover
	{
		background:url(inghover.png) no-repeat;
		width:250px;
	} 

	
	#f2
	{
		margin-top: 50px;
		width:200px;
		margin-left:420px;
	}

	#f2:hover
	{
		background:url(winehover.png) no-repeat;
		width:250px;
	} 

	#uploadkoumpi
	{
				margin-left:950px;
				margin-top:90px;



	}
	#uploadkoumpi:hover
	{
		background:transparent url(uploadhover.png) no-repeat;
				margin-left:950px;
				margin-top:90px;



	}


	#logoutkoumpi
	{
				margin-left:950px;
				margin-top:-30px;


	}
	#logoutkoumpi:hover
	{
			background:transparent url(loghover.png) no-repeat;
			margin-left:950px;
			margin-top:-30px;


	}

	#welcome
	{
		margin-left:950px;
		margin-bottom:-80px;
		margin-top:100px;
		font-family: Georgia;
		font-size:15px;
		color:#302617;


		
	}

</style>
	
	
	
	
</head>
	
<body>
	
<h1>Find a Recipe</h1>
<h3>by..</h3>	
<form id="f1" action="ChooseIng.html">			
<input type="image" src="ing.png" value="Find me a recipe">
</form>
	
<form id="f2"  action="ChooseWine.html">
<input type="image" src="wines.png" value="Find me a recipe">
</form>


<%

if(session.getAttribute("Loged.in")!=null && session.getAttribute("Loged.in")!=""){
%>
<div id="welcome">
<%
out.println("Welcome: "+session.getAttribute("Loged.in"));
%>
</div>
<form id="uploadkoumpi" action="upload_recipe.jsp">
<input type="image" src="upload.png" value="" />
</form><br>
<form id="logoutkoumpi" action="logout.jsp" method="post">
<input  type="image" src="logout.png" value="" />
</form>
<%} %>
	
</body>
</html>
