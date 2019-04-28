# Projet_Genie_Logiciel

<h2>--------------- Run this project ---------------</h2>
<ol>
    <li>run FillData.java in dao</li>
    <li> run JettyMain.java</li>
     <li>browser localhost:8081</li>
     <li>login with id=032,pwd=0 (a pilot account to watch more info)</li>
  </ol>
 <h2>----------Problems-----------</h2>
<p><b> Always a little bit delay when log in / book a flight , if it doesn't work , wait for ten seconds and click again!!!</b></p>
<p><b>todo: </b>remainder and email part and payment not done</p>
<p>profile: upload and images not done </p>
<p>if this doesn't work, try 'tryThis' branche</p>

 <h2>---------- Difference with master -----------</h2>
  <h3>DAO </h3>
 <ol>
    <li>Filldata.java: more data(for test)</li>
    <li> Stucture of DAO: factoryDao and BasicDao(one client for all index)</li>
     <li>!!ActionDao : method search - add .from and . size (with size(), elasticsearch returns 10 data by default)</li>
  </ol>
  <h3>Entities</h3>
 <ol>
   Flight.java:  List of Passenger -> empty instead of null; attrubut <info> to stock informations(description) 
   </ol>
     
 <h3>Web services</h3>
 <ol>
    <li>Flight Service: filter ws and consult myflights ws </li>
    <li> ReservationService: result.equals("CREATED") ;consult myreservations WS; getByid WS; </li>
  </ol>
  
  <h3>JS---WEB PAGES</h3>
  <p>Attentions : I used my own pages when necessary .If you want to change, don't hesitate to tell me or just do it </p>
  <p>I think almost all action goes well already. If there are some missing fonctionalities,don't hesitate to tell me or just do it </p>
  <p>feel free to change any css/html directly in this branch if you want</p>
  <ol>
    <li> I chose registration.html for the signup page because it fits much more than the other sign up page (to simplify a little)</li>
    <li> I hide signup/login in header for many pages when client already login </li>
    <li> I modify a little for all pages( to give elements ids, to write js and so on )</li>
    <li> I didn't touch email/reminder part or the payment yet.</li>
    <li> For the list of flights page, i would rather use mine because a lot of works to do if i change to another page</li>
  </ol>

<h2> Any other questions pls contact me, Nice day/night </h2>
