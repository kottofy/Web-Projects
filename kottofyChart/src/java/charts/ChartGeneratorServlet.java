/*
 *
 * ChartGeneratorServlet.java
 * Author: Kristin Ottofy
 * Last Edited: February 14, 2011
 */

package charts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.googlecode.charts4j.*;

/**
 * Initializes storage for title, data and label values from client
 */
public class ChartGeneratorServlet extends HttpServlet
{
      private ArrayList data;
      private ArrayList label;
      PieChart pieChart;
      BarChart barChart;
      LineChart lineChart;
      String pieChartURL, lineChartURL, barChartURL;
      BarChartPlot barChartPlot;
      Plot lineChartPlot;
      String titleOne, titleTwo, radio;
      String labelOne, labelTwo, labelThree, labelFour;
      String dataOne, dataTwo, dataThree, dataFour;
      double dataOneDouble, dataTwoDouble, dataThreeDouble, dataFourDouble;
      Integer dataOneInteger, dataTwoInteger, dataThreeInteger, dataFourInteger;
      int min = 0, sum;
      int dataOnePercent, dataTwoPercent, dataThreePercent, dataFourPercent;
      static final int DEFAULT = 0;

     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        data = new ArrayList();
        label = new ArrayList();

        if (request.getParameter("titleOne") != null)
            titleOne = request.getParameter("titleOne").toString();

        if (request.getParameter("titleTwo") != null)
            titleTwo = request.getParameter("titleTwo");

        if (request.getParameter("labelOne") != null)
        {
            labelOne = request.getParameter("labelOne");
            label.add(labelOne);
        }

        if (request.getParameter("dataOne") != null)
        {
            dataOne = request.getParameter("dataOne");
            if (isNum(dataOne) == true)
            {
                dataOneDouble = Double.parseDouble(dataOne);
                data.add(Double.valueOf(dataOneDouble));
                sum += dataOneDouble;
                if (dataOneDouble > min)
                    min = Integer.parseInt(dataOne);
            }
            else
                dataOneDouble = DEFAULT;
        }

        if (request.getParameter("labelTwo") != null)
        {
            labelTwo = request.getParameter("labelTwo");
            label.add(labelTwo);
        }

        if (request.getParameter("dataTwo") != null)
        {
            dataTwo = request.getParameter("dataTwo");
            if (isNum(dataTwo) == true)
            {
                dataTwoDouble = Double.parseDouble(dataTwo);
                data.add(Double.valueOf(dataTwoDouble));
                sum += dataTwoDouble;
                if (dataTwoDouble > min)
                    min = Integer.parseInt(dataTwo);
            }
            else
                dataTwoDouble = DEFAULT;
        }

        if (request.getParameter("labelThree") != null)
        {
            labelThree = request.getParameter("labelThree");
            label.add(labelThree);
        }

        if (request.getParameter("dataThree") != null)
        {
            dataThree = request.getParameter("dataThree");
            if (isNum(dataThree) == true)
            {
                dataThreeDouble = Double.parseDouble(dataThree);
                data.add(Double.valueOf(dataThreeDouble));
                sum += dataOneDouble;
                if (dataThreeDouble > min)
                    min = Integer.parseInt(dataThree);
            }
            else
                dataThreeDouble = DEFAULT;
        }

        if (request.getParameter("labelFour") != null)
        {
            labelFour = request.getParameter("labelFour");
            label.add(labelFour);
        }

        if (request.getParameter("dataFour") != null)
        {
            dataFour = request.getParameter("dataFour");
            if (isNum(dataFour) == true)
            {
                dataFourDouble = Double.parseDouble(dataFour);
                data.add(Double.valueOf(dataFourDouble));
                sum += dataFourDouble;
                if (dataFourDouble > min)
                    min = Integer.parseInt(dataFour);
            }
            else
               dataFourDouble = DEFAULT;
        }

        if (request.getParameter("radios") != null)
            radio = request.getParameter("radios");
        else
        {
            response.sendRedirect("kottofyChart");
        }

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Chart Generator</title>");
            out.println("</head>");
            out.println("<body>");

        if (radio.matches("bar"))
        {
            toBarChart();
            response.sendRedirect(barChartURL);
        }
        if (radio.matches("pie"))
        {
            toPieChart();
            response.sendRedirect(pieChartURL);
        }
        if (radio.matches("line"))
        {
            toLineChart();
            response.sendRedirect(lineChartURL);
        }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
/**
 *
 */
    private void toBarChart()
    {
        Plot plot = Plots.newPlot(Data.newData(data), Color.YELLOW);
        Plot[] plots = new Plot[1];
        plots[0] = plot;

        barChart = GCharts.newBarChart(plots);

        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.WHITE, 20, AxisTextAlignment.LEFT);
        AxisLabels left = AxisLabelsFactory.newAxisLabels(titleOne, 1);
        left.setAxisStyle(axisStyle);
        AxisLabels right = AxisLabelsFactory.newAxisLabels(titleTwo, 2);
        right.setAxisStyle(axisStyle);
        barChart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(label));

        barChart.addXAxisLabels(left);
        barChart.addYAxisLabels(right);

        barChart.setGrid(10, 10, 10, 10);
        barChart.setSize(500, 500);
        barChart.setBarWidth(100);
        barChart.setSpaceBetweenGroupsOfBars(20);
        barChart.setDataStacked(true);
        barChart.setTitle("Bar Chart", Color.WHITE, 40);
        barChart.setGrid(100, 10, 3, 2);
        barChart.setBackgroundFill(Fills.newSolidFill(Color.LIGHTSEAGREEN));
        barChart.setAreaFill(Fills.newSolidFill(Color.HOTPINK));
        barChartURL = barChartURL();
    }

    private void toLineChart()
    {
        Plot plot = Plots.newPlot(Data.newData(data), Color.YELLOW);
        Plot[] plots = new Plot[1];
        plots[0] = plot;

        lineChart = GCharts.newLineChart(plots);
        lineChart.setBackgroundFill(Fills.newSolidFill(Color.LIGHTSEAGREEN));
        lineChart.setAreaFill(Fills.newSolidFill(Color.HOTPINK));
        lineChart.setTitle("Line Chart", Color.WHITE, 40);
        lineChart.setSize(500, 500);
        lineChart.setSparkline(true);
        lineChart.addTopAxisLabels(AxisLabelsFactory.newAxisLabels(label));

        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.WHITE, 20, AxisTextAlignment.LEFT);
        AxisLabels left = AxisLabelsFactory.newAxisLabels(titleOne, 1);
        left.setAxisStyle(axisStyle);
        AxisLabels right = AxisLabelsFactory.newAxisLabels(titleTwo, 2);
        right.setAxisStyle(axisStyle);

        lineChart.addXAxisLabels(left);
        lineChart.addYAxisLabels(right);

        lineChartURL = lineChartURL();
    }

    private void toPieChart()
    {
        ArrayList slices = new ArrayList();
        try
        {
            slices = slices(data, label);
        }
        catch (Exception e)
        {

        }

        pieChart = GCharts.newPieChart(slices);
        pieChart.setTitle("Pie Chart", Color.WHITE, 40);
        pieChart.setSize(500, 200);
        pieChart.setThreeD(true);
        pieChart.setBackgroundFill(Fills.newSolidFill(Color.LIGHTSEAGREEN));

        pieChartURL = pieChartURL("Pie Chart", slices);
    }

    /**
     * Returns the URL of the pie chart generated from the data in this object
     * @param title
     * @param sliceList
     * @return
     */
    public String pieChartURL(String title, ArrayList sliceList)
    {
        return pieChart.toURLString();
    }

    /**
     * Returns the URL of the line chart generated from the data in this object
     * @return
     */
    public String lineChartURL()
    {
        return lineChart.toURLString();
    }

    /**
     * Returns the URL of the bar chart generated from the data in this object
     * @return
     */
    public String barChartURL()
    {
        return barChart.toURLString();
    }

    /**
     * Return true if 'numString' is non-null and encodes a number
     * @param numString
     * @return
     */
    protected boolean isNum(String numString)
    {
        try
        {
            Integer.parseInt(numString);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * Convert 'data' and 'labels' to pie slices, converting negative data values to positive and normalizing slice values to sum to nearly 100
     * @param data
     * @param label
     * @return
     * @throws Exception if sum of data values is less than 100 times the smallest Double value
     */
    protected ArrayList slices(ArrayList data, ArrayList label)
            throws Exception
    {
        double sliceInt1 = ((Double)data.get(0)).intValue();
        double sliceInt2 = ((Double)data.get(1)).intValue();
        double sliceInt3 = ((Double)data.get(2)).intValue();
        double sliceInt4 = ((Double)data.get(3)).intValue();

        if (((Integer)data.get(0)).intValue() < 0)
            sliceInt1 = -1*((Double)data.get(0)).intValue();
        if (((Integer)data.get(1)).intValue() < 0)
            sliceInt1 = -1*((Double)data.get(0)).intValue();
        if (((Integer)data.get(2)).intValue() < 0)
            sliceInt1 = -1*((Double)data.get(0)).intValue();
        if (((Integer)data.get(3)).intValue() < 0)
            sliceInt1 = -1*((Double)data.get(0)).intValue();

        double summation = sliceInt1 + sliceInt2 + sliceInt3 + sliceInt4;
        double minimum = summation;

        if (sliceInt1 < minimum)
            minimum = sliceInt1;
        if (sliceInt2 < minimum)
            minimum = sliceInt2;
        if (sliceInt3 < minimum)
            minimum = sliceInt3;
        if (sliceInt4 < minimum)
            minimum = sliceInt4;

        if (minimum < (summation/100))
            throw new Exception();

        ArrayList sliceList = new ArrayList();

        Slice slice1 = Slice.newSlice((int)sliceInt1, Color.HOTPINK, label.get(0).toString());
        Slice slice2 = Slice.newSlice((int)sliceInt2, Color.GREENYELLOW, label.get(1).toString());
        Slice slice3 = Slice.newSlice((int)sliceInt3, Color.AQUAMARINE, label.get(2).toString());
        Slice slice4 = Slice.newSlice((int)sliceInt4, Color.ORCHID, label.get(3).toString());
        sliceList.add(slice1);
        sliceList.add(slice2);
        sliceList.add(slice3);
        sliceList.add(slice4);
        return sliceList;
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Redirects the request to a url for a generated chart conforming to the 'data', 'label', 'title', and 'chartType' request parameters. If a data value cannot be parsed into a number or the data values add up to zero, display an error message instead.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
