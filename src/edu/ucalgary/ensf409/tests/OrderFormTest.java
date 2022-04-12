/**
 * @author Danielle Jourdain
 * @version 1.6
 * @since 1.0
 */

package edu.ucalgary.ensf409.tests;

import java.util.*;

import edu.ucalgary.ensf409.OrderForm;
import edu.ucalgary.ensf409.RequestForm;
import edu.ucalgary.ensf409.RequestFormDatabase;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;

public class OrderFormTest {
    /**
     * This test creates a OrderForm object and checks if the constructor creates
     * an object that is not null.
     */
    @Test
    public void testConstructor() {
        ArrayList<int[]> families = getSingleFamily();
        setupDatabase();
        RequestForm request = new RequestForm(families);
        OrderForm form = new OrderForm(request);

        assertNotNull("OrderForm constructor did not correctly create an OrderForm object.", form);
    }

    /**
     * This test creates an OrderForm object and checks if the getFilename method
     * returns the desired result.
     */
    @Test
    public void testGetFileName() {
        ArrayList<int[]> families = getSingleFamily();
        setupDatabase();
        RequestForm request = new RequestForm(families);
        OrderForm form = new OrderForm(request);

        String expectedName = getFilename();

        String actualName = form.getFilename();

        assertEquals("getFilename did not return expected value.", expectedName, actualName);
    }

    /**
     * This test creates an OrderForm object and checks if the createForm
     * creates a text file in the working directory with the correct name.
     */
    @Test
    public void testCreateForm() {
        ArrayList<int[]> families = getSingleFamily();
        setupDatabase();
        RequestForm request = new RequestForm(families);
        OrderForm form = new OrderForm(request);

        String filename = getFilename();
        form.createForm();

        boolean isFile = false;
        File file = new File(filename);
        if(file.isFile()) {
            isFile = true;
        }

        assertTrue("createForm method did not create a text file in the working directory.", isFile);
    }

    /**
     * Helper method for the tests. Finds what name should be used for the
     * next text file. The naming scheme goes "orderForm.txt" then "orderForm1.txt",
     * then "orderForm2.txt" and so on until an available name is found.
     * @return The filename to be used to create the order form.
     */
    public String getFilename() {
        String filename = "orderForm";
        File testFile = new File(filename + ".txt");
        int count = 1;
        String temp = filename;

        while(testFile.isFile()) {
            temp = filename + Integer.toString(count);
            testFile = new File(temp + ".txt");
            count++;
        }

        return (temp + ".txt");
    }

    /**
     * Helper method for the test. Creates a family made up of one of each
     * type of client.
     * @return An ArrayList of int[] with one item. The int array conatins
     * the values {1, 1, 1, 1}
     */
    public ArrayList<int[]> getSingleFamily() {
        ArrayList<int[]> result = new ArrayList<>();
        int[] family = {1, 1, 1, 1};
        result.add(family);
        return result;
    }

    @BeforeClass
    public static void setupDatabase() {
        RequestFormDatabase database = new RequestFormDatabase("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf" );
        database.initializeConnection();
		database.setFoodValues();
		database.setClientValues();
		database.close();
    }

    /*
     * OrderForm(RequestForm) is called with valid data.
     * The constructor creates an OrderForm object that is not null.
     
    @Test
    public void testConstructor() {
        RequestForm request = new RequestForm(goodRequest());
        OrderForm form = new OrderForm(request);

        assertNotNull("OrderForm constructor did not correctly create an OrderForm object.", form);
    }

    /**
     * OrderForm(RequestForm) is called with an invalid family.
     * The constructor should not create an OrderForm object
    
    @Test 
    public void testConstructorBad() {
        boolean result = false;
        try {
            RequestForm form = new RequestForm(badRequest());
            OrderForm orderForm = new OrderForm(form);
        }
        catch (IllegalArgumentException e) {
            result = true;
        }

        assertTrue("OrderForm created an object with an invalid RequestForm", result);
    }

    /**
     * Create an OrderForm objext, then call the createForm method. Check if a file was created in the working directory
     
    @Test
    public void testCreateFormWithUnsuccessfulHamper() {
        RequestForm request = new RequestForm(goodRequest());
        OrderForm form = new OrderForm(request);

        String fileName = "orderForm";
        File testFile = new File(fileName + ".txt");
        int count = 1;

        while(!testFile.isFile()) {
            fileName = fileName + Integer.toString(count);
            testFile = new File(fileName + ".txt");
            count++;
            //iterate through possible filenames until the first unused one is found
        }

        form.createForm();
        //create the order form

        boolean result = false;
        if(testFile.isFile()) {
            result = true;
            //check if a file was created with the correct name in the working directory
        }

        assertTrue("createForm did not create a file in the working directory.", result);
    }

    public ArrayList<int[]> goodRequest() {
        int[] family = {1, 1, 1, 1};
        ArrayList<int[]> result = new ArrayList<>();
        result.add(family);
        return result;
    }

    public ArrayList<int[]> badRequest() {
        int[] family = {1, 1, 1};
        ArrayList<int[]> result = new ArrayList<>();
        result.add(family);
        return result;
    }
    */
}
