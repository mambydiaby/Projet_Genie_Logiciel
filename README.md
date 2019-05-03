# Projet_Genie_Logiciel

## How to run this project 

### Important ! Check ElasticSearch version first!  it has to be elastic search version 5.x.x 
 <ul>
    <li>In Dao, we used transport client which is deprecated in 7.0.0. the TransportClient is deprecated in favour of the Java High Level REST Client and will be removed in Elasticsearch 8.0.  
        <br/>For more informations check https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html</li>
     <li> We chose to stock different types of informations in the same index(flight_sharing) which is only supported in version 5 .In fact, indices created in 6.x only allow a single-type per index. Any name can be used for the type, but there can be only one. 
         <br/>More info pls check https://www.elastic.co/guide/en/elasticsearch/reference/current/removal-of-types.html</li>
</ul>

### Steps to run this project
   <ol>
    <li>lunch elastic search version 5.x.x </li>
    <li>import the project as maven project in IDE</li>
    <li>run FillData.java in dao</li>
    <li> run JettyMain.java</li>
    <li>browser localhost:8081 or localhost:8081/index.html </li>
  </ol>
  
  
  ### Potential problems and solutions
  
   - Class not found error : make sure you set up java SE 8 in the environment, then create a new folder src/main/test 
   
   - DataBase error: Check if you have run elasticsearch 5.x.x and run FillData.java to fill the database
   
   - Server error : Run maven install on this project (right-click on pom.xml and run maven install) 
   
   - Address in use problem : stop the program and kill the running server program in port 8081, then run again.
   
   - Make sure you are in Branche Master 
 

  
## Program Delay
   
   - There is a little bit delay when log in / book a flight /aprouve a flight /disapprove a flight (Because of sending email/binding database.. ) , if it doesn't work , wait and click again!!!<b> Deleting a flight can cause one minute.</b>





 

