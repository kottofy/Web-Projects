/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package charts;

import com.googlecode.charts4j.*;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author kottofy
 */
public class ChartGeneratorServletTest extends TestCase {

    ArrayList data;
    ArrayList label;

    protected void setUp() throws Exception {
        data = new ArrayList();
        label = new ArrayList();
        data.add("20");
        data.add("26");
        data.add("-5");
        data.add("110");
        label.add("Label One");
        label.add("Label Two");
        label.add("Label Three");
        label.add("Label Four");
    }

    protected void tearDown() throws Exception {
        data.clear();
        label.clear();
    }

    /**
     * Test of isNum method, of class ChartGeneratorServlet.
     */
    public void testIsNum() {
        String numString = "twelve";
        ChartGeneratorServlet instance = new ChartGeneratorServlet();
        boolean expResult = false;
        boolean result = instance.isNum(numString);
        assertEquals(expResult, result);
    }

        /**
     * Test of isNum method, of class ChartGeneratorServlet.
     */
    public void testIsNum2() {
        String numString = "12";
        ChartGeneratorServlet instance = new ChartGeneratorServlet();
        boolean expResult = true;
        boolean result = instance.isNum(numString);
        assertEquals(expResult, result);
    }

    /**
     * Test of slices method, of class ChartGeneratorServlet.
     */
/*    public void testSlices() throws Exception {
        
        ChartGeneratorServlet instance = new ChartGeneratorServlet();

        ArrayList sliceList = new ArrayList();
        Slice slice1 = Slice.newSlice(40, Color.HOTPINK, label.get(0).toString());
        Slice slice2 = Slice.newSlice(26, Color.GREENYELLOW, label.get(1).toString());
        Slice slice3 = Slice.newSlice(5, Color.AQUAMARINE, label.get(2).toString());
        Slice slice4 = Slice.newSlice(110, Color.ORCHID, label.get(3).toString());
        sliceList.add(slice1);
        sliceList.add(slice2);
        sliceList.add(slice3);
        sliceList.add(slice4);

        ArrayList expResult = sliceList;
        ArrayList result = instance.slices(data, label);
        assertEquals(expResult, result);
    } */
        /**
     * Test of slices method, of class ChartGeneratorServlet.
     */
    public void testSlices2() throws Exception {
        ChartGeneratorServlet instance = new ChartGeneratorServlet();
        data.clear();
        data.add("5");
        data.add("100");
        data.add("200");
        data.add("300");

        ArrayList result = new ArrayList();

        try
        {
            result = instance.slices(data, label);
            fail ("Exception expected.");
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }
}
