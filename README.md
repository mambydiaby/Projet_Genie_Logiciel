# Projet_Genie_Logiciel

## Introduction 

As part of the "Génie Logiciel Avancé" course, we plan to carry out the development of a flight sharing web-based platform. This platform will connect certified pilots with passengers wishing to book one of their flight. The purpose of this website, and therefore the outcome of the project, is to allow clients to book seats for a flight of their choice.

The purpose of the project is to create a performing platform that is easy to use, both for the pilots and the aspiring passengers. We will make all information about the available flights and their details accessible for anyone to browse through. Once logged, a passenger will be able to book a flight. The pilot will then be able to accept or refuse their request through an email. In case of acceptance by the pilot, the passengers will have access to all the private information of the flight.






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
    <li>run JettyMain.java</li>
    <li>browser localhost:8081 or localhost:8081/index.html </li>
  </ol>
  
  
  ### Potential problems and solutions
  
   - Class not found error : make sure you set up java SE 8 in the environment, then create a new folder src/main/test 
   
   - DataBase error: Check if you have run elasticsearch 5.x.x and run FillData.java to fill the database
   
   - Server error : Run maven install on this project (right-click on pom.xml and run maven install) 
   
   - Address in use problem : stop the program and kill the running server program in port 8081, then run again.
   
   - Make sure you are in Branche Master 
 

  
## Delay
   
   - There is a little bit delay when log in / book a flight /aprouve a flight /disapprove a flight (Because of sending email/binding database.. ) , if it doesn't work , wait and click again!!!<b> Deleting a flight can cause one minute.</b>

## Details of this project

### fillData.java
 
  - Some prefill data just to test. If you don't want it, you can run Jettymain.java directly.


### Search flight policy

  - It's a flight-sharing platform. At first, withou login, users can search flights with a departure and/or a date or nothing (all flights). In the departure field, user can type a short version of a city (like p/pa/par/pari/paris for paris and it's not case sensetive)
  
  - The system will show all the flights available with their basic information and users can filter the result with other fields.Also, Users can sort the results by all the fields.
  
  - Users can read more informations (public informations/pilot ..etc ) without login. But once a user login as passenger/pilot, he can book the filght.
  
 ### Reservation policy
 
  - Once you login (as pilot or passenger), you can book any flight you want if there is a seat available. But the pilot has the right to accpet or deny the reservation.

  
### Private information policy

 - Each flight has its own private information (meeting day/place defined by the pilot), only the pilot and the passengers of the flight can read it.

### Email policy

 - Once user books a flight, the pilot will receive an email 
 
 - Once Pilot comfirms/denies a reservation, the passsenger will receive an email
 
 - Once Pilot cancels a flight, passengers who book this flight will receive an email
 
 - 24 hours before a flight, the pilot and the passengers receive a reminder for the flight
 

### Changes FLight policy

 - Pilot can change anything of his flight, he just need to click on edit.
 
 - Piolt can delete his flight, if he does this , all the reservations on this flight will be canceled.





 

