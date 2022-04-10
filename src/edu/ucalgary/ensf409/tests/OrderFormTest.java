/**
 * @author Danielle Jourdain
 * @version 1.3
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;

public class OrderFormTest {
    /**
     * OrderForm(RequestForm) is called with valid data.
     * The constructor creates an OrderForm object that is not null.
     */
    @Test
    public void testConstructor() {
        RequestForm request = new RequestForm(goodRequest());
        OrderForm form = new OrderForm(request);

        assertNotNull("OrderForm constructor did not correctly create an OrderForm object.", form);
    }

    /**
     * OrderForm(RequestForm) is called with an invalid family.
     * The constructor should not create an OrderForm object
     */
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
     */
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
}
