/**
 * @author Danielle Jourdain
 * @version 1.0
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;

public class OrderFormTest {
    String[][] goodFamily = {"1", "1", "1", "1"};
    String[][] badFamily = {"1", "0", "3"};
    private RequestForm goodRequest = new RequestForm(goodFamily);
    private RequestForm badRequest = new RequestForm(badFamily);

    /**
     * OrderForm(RequestForm) is called with valid data.
     * The constructor creates an OrderForm object that is not null.
     */
    @Test
    public void testConstructor() {
        OrderForm form = new OrderForm(goodRequest);

        assertNotNull(form, "OrderForm constructor did not correctly create an OrderForm object.");
    }

    /**
     * OrderForm(RequestForm) is called with an invalid family.
     * The constructor should not create an OrderForm object
     */
    @Test 
    public void testConstructorBad() {
        OrderForm form = new OrderForm(badRequest);

        assertNull(form, "OrderForm created a form for a bad RequestForm object");
    }

    /**
     * Create an OrderForm objext, then call the createForm method. Check if a file was created in the working directory
     */
    @Test
    public void testCreateFormWithUnsuccessfulHamper() {
        OrderForm form = new OrderForm(goodRequest);
        form.createForm("testFile.txt");

        File file = new File("testFile.txt");
        boolean result = file.isFile();

        assertTrue("createForm did not create a file in the working directory.", result);
    }
}
