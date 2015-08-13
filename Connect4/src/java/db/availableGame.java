/**
 * @nameAndExt availableGame.java
 * @date May 2, 2011
 * @author Kristin Ottofy
 */

package db;

/**
 *
 */
public class availableGame
{

    int id;
    String challenger;
    String responder;
    String nextMove;
    String dateTime;
    String message;

    public availableGame()
    {
        
    }

    public availableGame(int id, String challenger, String responder, String nextMove, String dateTime, String message)
    {
        this.id = id;
        this.challenger = challenger;
        this.responder = responder;
        this.nextMove = nextMove;
        this.dateTime = dateTime;
        this.message = message;
    }

    public String getChallenger()
    {
        return challenger;
    }

    public void setChallenger(String challenger)
    {
        this.challenger = challenger;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getNextMove()
    {
        return nextMove;
    }

    public void setNextMove(String nextMove)
    {
        this.nextMove = nextMove;
    }

    public String getResponder()
    {
        return responder;
    }

    public void setResponder(String responder)
    {
        this.responder = responder;
    }


}
