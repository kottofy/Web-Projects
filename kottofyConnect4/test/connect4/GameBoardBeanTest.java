
package connect4;

import junit.framework.TestCase;

/**
 *
 * @author kottofy
 */
public class GameBoardBeanTest extends TestCase {

    int currentPlayer = RED_CHECKER, gameStatus = 1, winner = -1, column;
    String reset;
    public static final int N_ROWS = 6;
    public static final int N_COLS = 7;
    public static final int OFF_BOARD = -1;
    public static final int UNOCCUPIED = 1;
    public static final int RED_CHECKER = 2;
    public static final int BLACK_CHECKER = 3;
    int[][] testBoard = new int[N_ROWS][N_COLS];
    GameBoardBean instance;

    protected void setUp() throws Exception {
        
        instance = new GameBoardBean();
        testBoard = new int[N_ROWS][N_COLS];
        currentPlayer = RED_CHECKER;
        gameStatus = 1;
        winner = -1;
        column = -1;
    }

    protected void tearDown() throws Exception {
    
        instance = null;
        testBoard = null;
        currentPlayer = -5;
        gameStatus = -5;
        winner = -5;
        column = -5;
    }

    public void testRow() {
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(1, RED_CHECKER);
        instance.addChecker(2, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        int rowTest = instance.getWinner();
        assertEquals(rowTest, RED_CHECKER);
    }

    public void testColumn() {
        instance.addChecker(3, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        int colTest = instance.getWinner();
        assertEquals(colTest, RED_CHECKER);
    }

    public void testDiagonal() {
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(1, RED_CHECKER);
        instance.addChecker(2, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        instance.addChecker(1, RED_CHECKER);
        instance.addChecker(2, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        instance.addChecker(2, RED_CHECKER);
        instance.addChecker(3, RED_CHECKER);
        int diagTest = instance.getWinner();
        assertEquals(diagTest, RED_CHECKER);
    }

    public void testOffBoard() {
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        int offBoardTest = instance.getWinner();
        assertEquals(offBoardTest, RED_CHECKER);
    }

    public void testWinner() {
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        int winnerTest = instance.getWinner();
        assertEquals(winnerTest, RED_CHECKER);
    }

    public void testReset() {
        int[][] instanceTemp = instance.getBoard();
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);
        instance.addChecker(0, RED_CHECKER);

        instance.setReset("reset");
        int[][] resetTest = instance.getBoard();
        assertEquals(instanceTemp, resetTest);
    }
}
