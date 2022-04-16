Compiling
To compile the program, run the following command while in the same folder as the package:
javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar edu/ucalgary/ensf409/*.java

Testing
To test the program, run the following command while in the same folder as the classes:
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.

Running
To run the program, run the following command while in the same folder as the classes:
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar edu.ucalgary.ensf409.RequestFormGUI