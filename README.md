# Projet_Genie_Logiciel

<h2>--------------- Run this project ---------------</h2>
<ol>
    <li>import the project as maven project in IDE</li>
    <li>lunch elastic search version 5.x.x <b>Check ES version first!</b> (In Dao, we used transport client which is deprecated in 7.0.0. The TransportClient is deprecated in favour of the Java High Level REST Client and will be removed in Elasticsearch 8.0. )</li>
    <li>run FillData.java in dao</li>
    <li> run JettyMain.java</li>
     <li>browser localhost:8081 or localhost:8081/index.html (I think index.html as welcome(start) page is better? )</li>
     <li>login with id=032,pwd=0 (a pilot account to see more info)</li>
     <li>login with id=all,pwd=9 (a client account to see other info)</li>
  </ol>
  
 <h2>----------Problems-----------</h2>
<p><b>  A little bit delay when log in / book a flight /aprouve a flight /disapprove a flight (Because of sending email/binding database.. ) , if it doesn't work , wait for ten seconds and click again!!!</b></p>
<p>A large delay when you delete a filght which contains a lot of reservations.(be patient! maybe 2 minutes because we need to send an email to each passenger.)  </p>
<p><b>todo: </b> add payment pages. test emailsender/remainder</p>
<br/>
<h3> <b>Here's sth you can do if you want to continue (not sure if needed)</b></h3>
<ul>
<li><p>In profile : upload and show image icon not done </p></li>
<li><p>designe a beautiful congratulation page</p></li>
<li><p><b>designe a little logo</b></p></li>
 <li><p>Thinkof a company name?</p></li>

<li><p>Improve css / html of all page</p></li>
<li><p>Modify account informations </p></li>
<li><p>Add more details to creat-flight : such as flight time </p></li>
<li><p>Passengers cancel his reservations </p></li>
<li><p>Add more pictures for other cities(for instance, only 5 or 6 cities available) </p></li>
<li><p>Web security </p></li>
<li><p>Test with more informations to find bugs  </p></li>
<li><p>More comments on code</p></li>
<li><p><b>A perfect Readme.md instead of this one</b></p></li>


</ul>
 


<h2>---------- New Changes from 2019/05/01(to be deleted from README.md)-----------</h2>
    <p>delete a flight and send email to all passengers who have reservation of this flight</p>
 <h2>---------- Changes from 2019/04/20(to be deleted from README.md)-----------</h2>
  <h3>DAO </h3>
 <ol>
    <li>Filldata.java: more data(for test)</li>
    <li>In flght entity: informationPrivate and trajet now are available</li>
    <li> Stucture of DAO: factoryDao and BasicDao(one client for all index)</li>
     <li>!!ActionDao : method search - add .from and . size (with size(), elasticsearch returns 10 data by default)</li>
  </ol>
  <h3>Entities</h3>
 <ol>
   Flight.java: <ol> <li>List of Passengers -> empty instead of null; </li>
    <li>attribut <trajet> to stock detail trajet( As the description of project)</li>
    <li>attrubut <info> to stock informations(description)</li>
    <li>attrubut <infoPrivate> to stock private informations (meetings)</li>
    </ol>
   </ol>
     
 <h3>Web services</h3>
 <ol>
    <li>Flight Service: filter ws and consult myflights ws </li>
    <li>consult myreservations WS; getByid WS; </li>
  </ol>
  
  <h3>JS---WEB PAGES</h3>
  <p>Attentions : I used my own pages when necessary .If you want to change, don't hesitate to do it or ask me if you need help</p>
  <p>I think almost all action goes well already. If there are some missing fonctionalities,don't hesitate to tell me or just do it </p>
  <p>Feel free to change any css/html directly if you want</p>
  <ol>
    <li> I chose registration.html for the signup page because it fits much more than the other sign up page (to simplify a little)</li>
    <li> I hid signup/login in header for many pages when client already login </li>
    <li> I modified a little for all pages( to give elements ids, to write js and so on )</li>
    <li> I didn't touch email/reminder part or the payment yet.</li>
    <li> I added much more details for edit_flight and create_flight pages to fill more information (only missing flight time now.) </li>
    <li> For the list of flights page, i would rather use mine because a lot of works to do if i change to another page(pagination,filtre....) </li>
  </ol>
 
<h2> Any other questions,pls contact me, nice day/night </h2>
