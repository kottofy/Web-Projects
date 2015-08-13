
/**
 * @nameAndExt DBHelper.java
 * @date May 2, 2011
 * @author Kristin Ottofy
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class DBHelper
{
    PreparedStatement issueChallenge;
    PreparedStatement addCell;
    PreparedStatement addWinner;
    PreparedStatement getAvailableGames;
    String dbURL = "jdbc:mysql://zion.cs.uga.edu/lab";
    String user = "lab";
    String password = "wonkyWizard53";
    Connection conn = null;

    public DBHelper()
    {
        System.out.println("DBHelper");
    }

    public void addWinner(String challenger, String responder, String winner)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, user, password);
            System.out.println("Established DB Connection!");
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Instantiated Driver!");
            addWinner = conn.prepareStatement("INSERT into connect4Wins(challenger, responder, winner) VALUES (?,?,?)");

            addWinner.setString(1, challenger);
            addWinner.setString(2, responder);
            addWinner.setString(3, winner);

            addWinner.execute();
            conn.close();

        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void issueChallenge(String challenger, String responder, String nextMove, String message)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, user, password);
            System.out.println("Established DB Connection!");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Instantiated Driver!");

            issueChallenge = conn.prepareStatement("INSERT into connect4Game(challenger, responder, nextMove, gameStarted, message) VALUES(?,?,?,NOW(),?)");

            issueChallenge.setString(1, challenger);
            issueChallenge.setString(2, responder);
            issueChallenge.setString(3, nextMove);
            issueChallenge.setString(4, message);

            issueChallenge.execute();
            conn.close();
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCell(int row, int col, int gameId, String cellColor)
    {
        try
        {
            conn = DriverManager.getConnection(dbURL, user, password);
            System.out.println("Established DB Connection!");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Instantiated Driver!");

            addCell = conn.prepareStatement("INSERT into connect4Cell(row, col, gameId, cellColor, moveTime) VALUES(?,?,?,?,NOW())");

            addCell.setInt(1, row);
            addCell.setInt(2, col);
            addCell.setInt(3, gameId);
            addCell.setString(4, cellColor);

            addCell.execute();
            conn.close();
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList getAvailableGames()
    {
        System.out.println("getAvailableGames()");
        ResultSet rs;
        ArrayList<availableGame> games = new ArrayList();
        ArrayList<availableGame> lastThreeAvailableGames = new ArrayList();
        int id = -1;
        String challenger = "";
        String responder = "";
        String nextMove = "";
        String gameStarted = "";
        String message = "";
        availableGame game = null;

        try
        {
            conn = DriverManager.getConnection(dbURL, user, password);
            System.out.println("Established DB Connection!");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Instantiated Driver!");

            getAvailableGames = conn.prepareStatement("SELECT * from connect4Game where responder is NULL and nextMove=\"notStarted\"");
            rs = getAvailableGames.executeQuery();

            //add the entire list of available games to "games"
            while (rs.next())
            {
                id = rs.getInt("id");
                if (rs.getString("challenger") != null)
                {
                    challenger = rs.getString("challenger");
                }
                if (rs.getString("responder") != null)
                {
                    responder = rs.getString("responder");
                }
                if (rs.getString("nextMove") != null)
                {
                    nextMove = rs.getString("nextMove");
                }
                if (rs.getString("gameStarted") != null)
                {
                    gameStarted = rs.getString("gameStarted");
                }
                if (rs.getString("message") != null)
                {
                    message = rs.getString("message");
                }

                game = new availableGame(id, challenger, responder, nextMove, gameStarted, message);

                games.add(game);

                challenger = "";
                responder = "";
                nextMove = "";
                gameStarted = "";
                message = "";
            }

            //add last 3 available games to a list, return this list
            lastThreeAvailableGames.add((availableGame) games.get(games.size() - 3));
            lastThreeAvailableGames.add((availableGame) games.get(games.size() - 2));
            lastThreeAvailableGames.add((availableGame) games.get(games.size() - 1));

            conn.close();

        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastThreeAvailableGames;
    }
}
