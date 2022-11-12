# Word relationship application
RESTful  application for storing and retrieving relationship between the words.

## Prerequisites
JAVA with minimal version of 11 is required to run the application.

## How to run
In the terminal (Windows CMD, PowerShell or Bash) navigate into the folder containing this file and run

`./mvnw spring-boot:run` - for bash
`.\mvnw spring-boot:run` - for Windows CMD and PowerShell
By default application is started on port 8080, you can change this by updating property `server.port` in the `src\main\resources\application.properties` file

## API
After application is started please refer to http://localhost:8080/swagger-ui/index.html for API details.
