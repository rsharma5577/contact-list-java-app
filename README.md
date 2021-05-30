# contact-list-java-app

# Instruction to run the application (Springboot)
 
 1. Go to initilizer java file ("src/main/java/com/rs/contactlist/COntactListApplication.java") and run it as Java Application
 2. This application uses a JSON file as a data source
 3. Create a file name  - "ContactList.json" in local machine
 4. Give the path of ContactList.json to "file.path" property in application.properties
 5. Recommendation : run application on  "http://localhost:8080"
 
# Minimum requirement to run the application

 1. JDK 11
 2. Eclipse/STS or any other IDE
 3. Lombok installed in IDE
 
# Endpoints supported

	/api/contacts
	
	1. GET
	2. POST 
			Payload : {
        			"firstName": "userFirstName",
        			"lastName": "userLastName",
        			"email": "user@gmail.com",
        			"phoneNumber": "00888000000"
    			}
    			
    3. DELETE
    		Path Variable : /{contactId}
    		
    4. PUT
    		Path Variable : /{contactId}
    		Payload : {
        			"firstName": "userFirstName",
        			"lastName": "userLastName",
        			"email": "user@gmail.com",
        			"phoneNumber": "00888000000"
    			}
    		