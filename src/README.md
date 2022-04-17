# ENSF 409 FINAL PROJECT GROUP 60

## Compiling
To compile the program, run the following command while in the same folder as the package (outside the edu folder):
javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar edu/ucalgary/ensf409/\*.java
   
   - note: if using Mac, use the command: javac -cp .:lib/mysql-connector-java-8.0.23.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar edu/ucalgary/ensf409/\*.java

## Testing
To test the program, run the following command while in the same folder as the package (outside the edu folder):
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.TEST_FILE_NAME

## Running
To run the program, run the following command while in the same folder as the package (outside the edu folder):
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar edu.ucalgary.ensf409.RequestFormGUI

## General Notes:
   
   - the order form text files will be created in the working directory. They should appear in the same directory as
        the edu folder if the commands were run correctly
   - ensure the database is reset before running each test file. Several of the tests access the database,
        and if it not refreshed, this will cause some tests to fail
   - jar files and all necessary library files should be stored in a lib folder in the same directory as the edu folder and this
        README file
