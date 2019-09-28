# ApiTestingFramework
This is an API testing framework to implement tests against an open API weather station.
The open weather map API allows the user to manage their personal weather stations.
The user needs to create an account in members area https://home.openweathermap.org/users/sign_up.
They will then receive a key to access the API methods https://openweathermap.org/appid.

## **Technologies**
The project has been built using Java, Maven, JUnit, Rest Assured and the Gherkin - Behaviour Driven Development (BDD) approach has been used for writing the API tests and Serenity for reporting

## **Pre-requisites**
* The user must register with the open weather map website to generate their own api key.
* Install Java and set path
* Install Maven and set path
* Install Eclipse IDE
* Install Eclipse Plugins
  - Maven
  - Cucumber
 * mvn Command Line Interface(CLI)

## **Setup**
* Clone repository or download zip.
* Go to the project directory.
* Replace the APIKey in the test.properties file located in src/test/resources with the user's generated api key.

## **Writing the tests**
* New tests can be written in src/test/resources/features with appropriate tags. 

## **Running the tests**
* Use "mvn test" or "mvn clean install" to run the tests from the command line. These will be used for CI/CD pipeline implementation.

## **Reporting**
* The serenity reports will be generated inside target/site/serenity/index.htlm

