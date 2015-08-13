
package servlets;

import db.DBHelper;
import db.availableGame;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kottofy
 */
public class Connect4Servlet extends HttpServlet
{

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        DBHelper myDBHelper = new DBHelper();

        String player = (String) session.getAttribute("player");
        String challenger0 = (String) session.getAttribute("challenger0");

        System.out.println("PLAYER: " + player);
        System.out.println("CHALLENGER: " + challenger0);

        out.println("<xml>");
        out.println("<challenger0Name>" + challenger0 + "</challenger0Name>");
        out.println("</xml>");

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        DBHelper myDBHelper = new DBHelper();

        //player becomes player since that is how it is on the myDBHelper
        String player = request.getParameter("player");
        //System.out.println("player: " + player);

        session.setAttribute("player", player);

        //DEBUGGGGGGGG
        //myDBHelper.issueChallenge(player, null, "notStarted", player);

        ArrayList availGames = myDBHelper.getAvailableGames();

        String challenger0 = ((availableGame) availGames.get(0)).getChallenger();
        String challenger1 = ((availableGame) availGames.get(1)).getChallenger();
        String challenger2 = ((availableGame) availGames.get(2)).getChallenger();
        int challenger0Id = ((availableGame) availGames.get(0)).getId();
        int challenger1Id = ((availableGame) availGames.get(1)).getId();
        int challenger2Id = ((availableGame) availGames.get(2)).getId();

        session.setAttribute("challenger0", challenger0);
        session.setAttribute("challenger1", challenger1);
        session.setAttribute("challenger2", challenger2);
        session.setAttribute("challenger0Id", challenger0Id);
        session.setAttribute("challenger1Id", challenger1Id);
        session.setAttribute("challenger2Id", challenger2Id);

        System.out.println("challenger0: " + session.getAttribute("challenger0") + ", id: " + session.getAttribute("challenger0Id"));
        System.out.println("challenger1: " + session.getAttribute("challenger1") + ", id: " + session.getAttribute("challenger1Id"));
        System.out.println("challenger2: " + session.getAttribute("challenger2") + ", id: " + session.getAttribute("challenger2Id"));

        out.println("<xml>");
        out.println("<playerName>" + player + "</playerName>");
        out.println("<challenger0Name>" + challenger0 + "</challenger0Name>");
        out.println("<challenger1Name>" + challenger1 + "</challenger1Name>");
        out.println("<challenger2Name>" + challenger2 + "</challenger2Name>");
        out.println("</xml>");
    }
}
