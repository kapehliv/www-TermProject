<html>

<%
if(!(session.getAttribute("Loged.in")!=null && session.getAttribute("Loged.in")!="")){

	response.sendRedirect("loginpage.html");
} %>




<title>Upload Recipe</title>
<script src="/wp-includes/js/addInput.js" language="Javascript" type="text/javascript"></script>
<link rel='stylesheet' href='recipe_upload.css' type='text/css'>
<body>
<h1>Upload your recipe..</h1>

<form name="form1" method="post" action="Upload">
<div id="title">
Recipe Title: <input type="text" name="title" maxlength="50" required>
</div>


<div id="veg_div">
<div style="font-family:Segoe Script; font-size:14px; color:#302617;" id="dynamicInput" >Vegetables</div>
<input id="button1" type="button" value="Add Vegetable" onClick="addInput('dynamicInput');">
</div>

<div id="herb_div">
<div style="font-family:Segoe Script; font-size:14px; color:#302617;"  id="dynamicInputH">Herbs</div>
<input id="button1" type="button" value="Add Herb" onClick="addInputH('dynamicInputH');">
</div>

<div id="meat_div">
<div style="font-family:Segoe Script; font-size:14px; color:#302617;"  id="dynamicInputM">Meats & Fishes</div>
<input id="button1" type="button" value="Add Meat" onClick="addInputM('dynamicInputM');">
</div>

<div id="fluid_div">
<div style="font-family:Segoe Script; font-size:14px; color:#302617;"  id="dynamicInputF">Fluids & Others</div>
<input id="button1" type="button" value="Add one" onClick="addInputF('dynamicInputF');">
</div>



<div id="step_div"> 
	<div style="font-family:Segoe Script; font-size:14px; color:#302617;"  id="dynamicInputStep">Step1<br> <textarea name="step" rows= "2" cols="40" maxlength="500" required></textarea><br></div>
	<input id="button1" type="button" value="Add Step" onClick="addInputStep('dynamicInputStep');">
</div>

<br><br>

<div style="font-family:Segoe Script; font-size:14px; color:#302617;"  id="wine" >Wines <br>
	<select name="Wine" >
	<option value="Agiorghitiko">Agiorghitiko</option>
	<option value="Xinomavro">Xinomavro</option>
	<option value="Mandilaria">Mandilaria</option>
	<option value="Mavrodaphne">Mavrodaphne</option>
	<option value="Kotsifali">Kotsifali</option>
	<option value="Assyrtiko">Assyrtiko</option>
	<option value="Athiri">Athiri</option>
	<option value="Debina">Debina</option>
	<option value="Lagorthi">Lagorthi</option>
	<option value="Malagousia">Malagousia</option>
	<option value="Moschofilero">Moschofilero</option>
	<option value="Retsina">Retsina</option>
	<option value="Robola">Robola</option>
	<option value="Roditis">Roditis</option>
	<option value="Savatiano">Savatiano</option>

	<option value="Chardonnay">Chardonnay</option>
	<option value="Chenin Blanc">Chenin Blanc</option>
	<option value="Gewurztraminer">Gewurztraminer</option>
	<option value="Pinot Gris">Pinot Gris</option>
	<option value="Riesling">Riesling</option>
	<option value="Sauvignon Blanc">Sauvignon Blanc</option>
	<option value="Semillon">Semillon</option>
	<option value="Viognier">Viognier</option>

	<option value="Barbera">Barbera</option>
	<option value="Brunello">Brunello</option>
	<option value="Cabernet Franc">Cabernet Franc</option>
	<option value="Cabernet Sauvignon">Cabernet Sauvignon</option>
	<option value="Dolcetto">Dolcetto</option>
	<option value="Gamay">Gamay</option>
	<option value="Grenache">Grenache</option>
	<option value="Malbec">Malbec</option>
	<option value="Merlot">Merlot</option>
	<option value="Mourvedre">Mourvedre</option>
	<option value="Nebbiolo">Nebbiolo</option>
	<option value="Pinot Noir">Pinot Noir</option>
	<option value="Sangiovese">Sangiovese</option>
	<option value="Syrah">Syrah</option>
	<option value="Tempranillo">Tempranillo</option>
	<option value="Zinfandel">Zinfandel</option>
	</select>
</div>
<div id="total_time">
Total Time: <input type="text" name="time" maxlength="3" size="1" required > min<br></div>
	<div id="continue_div"><input id="button1" type="submit" value="Continue" onclick="submitForms()">

</div>
</form>
</body>

<script>
var counter = 0;
var limit = 20;
var counterH = 0;
var limitH = 20;
var counterM = 0;
var limitM = 20;
var counterS = 0;
var limitS = 20;
var counterF = 0;
var limitF = 20;

function addInput(divName){
     if (counter == limit)  {
          alert("You have reached the limit of adding " + counter + " inputs");
     }
     else {
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "Vegetable " + (counter + 1) + " <br><select name='Vegetable' ><option value='Artichoke'>Artichoke</option><option value='Asparagus'>Asparagus</option><option value='Aubergine'>Aubergine</option><option value='Beet'>Beet</option><option value='Broccoflower'>Broccoflower</option><option value='Broccoli'>Broccoli</option><option value='Brussels sprout'>Brussels sprout</option><option value='Cabbage'>Cabbage</option><option value='Capsicum'>Capsicum</option><option value='Carrot'>Carrot</option><option value='Cauliflower'>Cauliflower</option><option value='Chili pepper'>Chili pepper</option><option value='Cucumber'>Cucumber</option><option value='Ginger'>Ginger</option><option value='Legume'>Legume</option><option value='Lettuce'>Lettuce</option><option value='Maize'>Maize</option><option value='Mushroom'>Mushroom</option><option value='Okra'>Okra</option><option value='Onion'>Onion</option><option value='Potato'>Potato</option><option value='Pumpkin'>Pumpkin</option><option value='Radish'>Radish</option><option value='Spinach'>Spinach</option><option value='Sweet potato'>Sweet potato</option><option value='Tomato'>Tomato</option><option value='Turnip'>Turnip</option><option value='Zucchini'>Zucchini</option></select> <input type='text' name='vegquantity' maxlength='5' size='5' required> g";
          document.getElementById(divName).appendChild(newdiv);
          counter++;
     }
}

function addInputH(divName){
     if (counterH == limitH)  {
          alert("You have reached the limit of adding " + counterH + " inputs");
     }

     else {
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "Herb " + (counterH + 1) + " <br><select name='Herb' ><option value='Anise'>Anise</option><option value='Basil'>Basil</option><option value='Caraway'>Caraway</option><option value='Celery'>Celery</option><option  value='Chives'>Chives</option><option value='Coriander'>Coriander</option><option value='Chamomile'>Chamomile</option><option value='Dill'>Dill</option><option value='Fennel'>Fennel</option><option  value='Garlic'>Garlic</option><option value='Green pepper'>Green pepper</option><option value='Lavender'>Lavender</option><option value='Lemon Grass'>Lemon Grass</option><option value='Marjoram'>Marjoram</option><option value='Oregano'>Oregano</option><option value='Paprika'>Paprika</option><option value='Parsley'>Parsley</option><option value='Rosemary'>Rosemary</option><option value='Thyme'>Thyme</option><option value='Wasabi'>Wasabi</option><option value='Watercress'>Watercress</option></select> <input type='text' name='herbquantity' maxlength='5' size='5' required> g";
          document.getElementById(divName).appendChild(newdiv);
          counterH++;
     }
}

function addInputM(divName){
     if (counterM == limitM)  {
          alert("You have reached the limit of adding " + counterM + " inputs");
     }
     else {
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "Meat & Fish " + (counterM + 1) + "<br><select name='Meat'><option value='Chicken'>Chicken</option><option value='Chicken Leg'>Chicken Leg</option><option value='Chicken Wing'>Chicken Wing</option><option value='Chicken Breast'>Chicken Breast</option><option value='Beef'>Beef</option><option value='Beef Steak'>Beef Steak</option><option value='Chuck Steak'>Chuck Steak</option><option value='Blade Steak'>Blade Steak</option><option value='Stewin Steak'>Stewin Steak</option><option value='Sirloin Steak'>Sirloin Steak</option><option value='Bon fillet'>Bon fillet</option><option value='Rib Eye'>Rib Eye</option><option value='Lean Mins'>Lean Mins</option><option value='Ham'>Ham</option><option value='Bacon'>Bacon</option><option value='Ham Rib Roast'>Ham Rib Roast</option><option value='Pork Loin Back Ribs'>Pork Loin Back Ribs</option><option value='Salmon'>Salmon</option><option value='Crab'>Crab</option><option value='Lobster'>Lobster</option><option value='Gilthead'>Gilthead</option><option value='Lavraki'>Lavraki</option><option value='Sardelis'>Sardelis</option></select><input type='text' name='meatquantity' maxlength='5' size='5' required> g ";
          document.getElementById(divName).appendChild(newdiv);
          counterM++;
     }
}

function addInputF(divName){
     if (counterF == limitF)  {
          alert("You have reached the limit of adding " + counterF + " inputs");
     }
     else {
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "Fluid/Other " + (counterF + 1) + "<br><select name='Fluids'><option value='Water'>Water</option><option value='Olive Oil'>Olive Oil</option><option value='Corn Oil'>Corn Oil</option><option value='Maize Oil'>Maize Oil</option><option value='Butter'>Butter</option><option value='Margarine'>Margarine</option><option value='Lemon Juice'>Lemon Juice</option><option value='Lime Juice'>Lime Juice</option><option value='Orange Juice'>Orange Juice</option><option value='Lemon Zime'>Lemon Zime</option><option value='Orage Zime'>Orage Zime</option><option value='Lime Zime'>Lime Zime</option><option value='Bread'>Bread</option><option value='Flour'>Flour</option><option value='Kotopoulo'>Corn Flour</option><option value='Salt'>Salt</option><option value='Yogurt'>Yogurt</option><option value='Milk'>Milk</option><option value='Sour Cream'>Sour Cream</option><option value='Red Wine Sweet'>Red Wine Sweet</option><option value='Red Wine Dry'>Red Wine Dry</option><option value='White Wine Sweet'>White Wine Sweet</option><option value='White Wine Dry'>White Wine Dry</option><option value='Vinegar'>Vinegar</option><option value='Basmati Rice'>Basmati Rice</option><option value='Rice'>Rice</option><option value='Chicken Broth'>Chicken Broth</option><option value='Beef Broth'>Beef Broth</option><option value='Vegetable Broth'>Vegetable Broth</option><option value='Spaghetti'>Spaghetti</option><option value='Fusilli'>Fusilli</option><option value='Perciatelli'>Perciatelli</option><option value='Noodle'>Noodle</option><option value='Lasagne'>Lasagne</option><option value='Fettuce'>Fettuce</option><option value='Kous-kous'>Kous-kous</option><option value='Tagliatelle'>Tagliatelle</option><option value='Penne'>Penne</option><option value='Rigatoni'>Rigatoni</option><option value='Conchiglie'>Conchiglie</option><option value='Farfalle'>Farfalle</option><option value='Torteglini'>Torteglini</option><option value='Feta Cheese'>Feta Cheese</option><option value='Orzo'>Orzo</option><option value='Bree'>Bree</option><option value='Parmigiano'>Parmigiano</option><option value='Kaseri'>Kaseri</option><option value='Katiki'>Katiki</option><option value='Metsovone'>Metsovone</option><option value='Myzithra'>Myzithra</option><option value='Kefalotyri'>Kefalotyri</option><option value='Kefalograviera'>Kefalograviera</option><option value='Graviera'>Graviera</option><option value='Mascarpone'>Mascarpone</option><option value='Ricotta'>Ricotta</option><option value='Mozzarella'>Mozzarella</option><option value='Gorgonzola'>Gorgonzola</option><option value='Graviera'>Graviera</option><option value='Edam'>Edam</option><option value='Roquefort'>Roquefort</option><option value='Gouda'>Gouda</option><option value='Leerdammer'>Leerdammer</option><option value='Blue Cheese'>Blue Cheese</option></select><input type='text' name='fluidquantity' maxlength='5' size='5' required> g";
          document.getElementById(divName).appendChild(newdiv);
          counterF++;
     }
}

function addInputStep(divName){
     if (counterS == limitS)  {
          alert("You have reached the limit of adding " + counterS + " inputs");
     }
     else {
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "Step " + (counterS + 2) + "<br><textarea name='step' rows= '2' cols='40' maxlength='500' required></textarea><br>";
          document.getElementById(divName).appendChild(newdiv);
          counterS++;
     }
}

submitForms = function(){
    if ((counterH+counterM+counterF+counter)==0)  {
          alert("You have to choose some ingredients first..");
     }

else{

    document.forms["form1"].submit();
}

}




</script>

</html>



