package connect4;

/**
 * http://zion.cs.uga.edu:8080/drdanConnect4/
 * bean knows what board looks like
 * @author Kristin Ottofy
 */
public class GameBoardBean {

    private int currentPlayer = RED_CHECKER, gameStatus = 1, winner = -1, column;
    String reset;
    public static final int N_ROWS = 6;
    public static final int N_COLS = 7;
    public static final int OFF_BOARD = -1;
    public static final int UNOCCUPIED = 1;
    public static final int RED_CHECKER = 2;
    public static final int BLACK_CHECKER = 3;
    int[][] board = new int[N_ROWS][N_COLS];

    public GameBoardBean() {
        for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                board[row][col] = UNOCCUPIED;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCell(int row, int column) {
        return board[row][column];

    }

    public void setColumn(int column) {
        this.column = column;
        if (currentPlayer == BLACK_CHECKER) {
            if (addChecker(column, BLACK_CHECKER) == 1) {
                currentPlayer = RED_CHECKER;
            }
        } else if (currentPlayer == RED_CHECKER) {
            if (addChecker(column, RED_CHECKER) == 1) {
                currentPlayer = BLACK_CHECKER;
            }
        }
    }

    public int getColumn() {
        return column;
    }

    public int addChecker(int col, int checker) {
        if (col < N_COLS) {
            int row = N_ROWS-1;
            while (board[row][col] != 1 ) {
                row--;
                if (row == -1)
                    break;
            }
            if (row >= 0) {
                board[row][col] = checker;
                return 1;
            }
        }
        return -1;
    }

    public void setReset(String reset) {
        this.reset = reset;
                for (int row = 0; row < N_ROWS; row++) {
            for (int col = 0; col < N_COLS; col++) {
                board[row][col] = UNOCCUPIED;
            }
        }
        setGameStatus(1);
        winner = -1;
        currentPlayer = RED_CHECKER;
    }

    public String getReset() {
        return this.reset;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;

    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setReset(char reset) {
        for (int i = 0; i < N_ROWS; i++) {
            for (int j = 0; j < N_COLS; j++) {
                board[i][j] = UNOCCUPIED;
            }
        }
        currentPlayer = RED_CHECKER;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        //check diagonals
        if ((board[2][0] != 1) && (board[2][0] == board[3][1]) && (board[2][0] == board[4][2]) && (board[2][0] == board[5][3])) {
            winner = board[2][0];
        } else if ((board[1][0] != 1) && (board[1][0] == board[2][1]) && (board[1][0] == board[3][2]) && (board[1][0] == board[4][3])) {
            winner = board[1][0];
        } else if ((board[2][1] != 1) && (board[2][1] == board[3][2]) && (board[2][1] == board[4][3]) && (board[2][1] == board[5][4])) {
            winner = board[2][1];
        } else if ((board[0][0] != 1) && (board[0][0] == board[1][1]) && (board[0][0] == board[2][2]) && (board[0][0] == board[3][3])) {
            winner = board[0][0];
        } else if ((board[1][1] != 1) && (board[1][1] == board[2][2]) && (board[1][1] == board[3][3]) && (board[1][1] == board[4][4])) {
            winner = board[1][1];
        } else if ((board[2][2] != 1) && (board[2][2] == board[3][3]) && (board[2][2] == board[4][4]) && (board[2][2] == board[5][5])) {
            winner = board[2][2];
        } else if ((board[0][1] != 1) && (board[0][1] == board[1][2]) && (board[0][1] == board[2][3]) && (board[0][1] == board[3][4])) {
            winner = board[0][1];
        } else if ((board[1][2] != 1) && (board[1][2] == board[2][3]) && (board[1][2] == board[3][4]) && (board[1][2] == board[4][5])) {
            winner = board[1][2];
        } else if ((board[2][3] != 1) && (board[2][3] == board[3][4]) && (board[2][3] == board[4][5]) && (board[2][3] == board[5][6])) {
            winner = board[2][3];
        } else if ((board[0][2] != 1) && (board[0][2] == board[1][3]) && (board[0][2] == board[2][4]) && (board[0][2] == board[3][5])) {
            winner = board[0][2];
        } else if ((board[1][3] != 1) && (board[1][3] == board[2][4]) && (board[1][3] == board[3][5]) && (board[1][3] == board[4][6])) {
            winner = board[1][3];
        } else if ((board[0][3] != 1) && (board[0][3] == board[1][4]) && (board[0][3] == board[2][5]) && (board[0][3] == board[3][6])) {
            winner = board[0][3];
        } else if ((board[3][0] != 1) && (board[3][0] == board[2][1]) && (board[3][0] == board[1][2]) && (board[3][0] == board[0][3])) {
            winner = board[3][0];
        } else if ((board[4][0] != 1) && (board[4][0] == board[3][1]) && (board[4][0] == board[2][2]) && (board[4][0] == board[1][3])) {
            winner = board[4][0];
        } else if ((board[3][1] != 1) && (board[3][1] == board[2][2]) && (board[3][1] == board[1][3]) && (board[3][1] == board[0][4])) {
            winner = board[3][1];
        } else if ((board[5][0] != 1) && (board[5][0] == board[4][1]) && (board[5][0] == board[3][2]) && (board[5][0] == board[2][3])) {
            winner = board[5][0];
        } else if ((board[4][1] != 1) && (board[4][1] == board[3][2]) && (board[4][1] == board[2][3]) && (board[4][1] == board[1][4])) {
            winner = board[4][1];
        } else if ((board[3][2] != 1) && (board[3][2] == board[2][3]) && (board[3][2] == board[1][4]) && (board[3][2] == board[0][5])) {
            winner = board[3][2];
        } else if ((board[5][1] != 1) && (board[5][1] == board[4][2]) && (board[5][1] == board[3][3]) && (board[5][1] == board[2][4])) {
            winner = board[5][1];
        } else if ((board[4][2] != 1) && (board[4][2] == board[3][3]) && (board[4][2] == board[2][4]) && (board[4][2] == board[1][5])) {
            winner = board[4][2];
        } else if ((board[3][3] != 1) && (board[3][3] == board[2][4]) && (board[3][3] == board[1][5]) && (board[3][3] == board[0][6])) {
            winner = board[3][3];
        } else if ((board[5][2] != 1) && (board[5][2] == board[4][3]) && (board[5][2] == board[3][4]) && (board[5][2] == board[2][5])) {
            winner = board[5][2];
        } else if ((board[4][3] != 1) && (board[4][3] == board[3][4]) && (board[4][3] == board[2][5]) && (board[4][3] == board[1][6])) {
            winner = board[4][3];
        } else if ((board[5][3] != 1) && (board[5][3] == board[4][4]) && (board[5][3] == board[3][5]) && (board[5][3] == board[2][6])) {
            winner = board[5][3];
        }

        //check rows
        for (int i = 0; i < N_ROWS; i++) {
            if ((board[i][0] != 1) && (board[i][0] == board[i][1]) && (board[i][0] == board[i][2]) && (board[i][0] == board[i][3])) {
                winner = board[i][0];
            } else if ((board[i][1] != 1) && (board[i][1] == board[i][2]) && (board[i][1] == board[i][3]) && (board[i][1] == board[i][4])) {
                winner = board[i][1];
            } else if ((board[i][2] != 1) && (board[i][2] == board[i][3]) && (board[i][2] == board[i][4]) && (board[i][2] == board[i][5])) {
                winner = board[i][2];
            } else if ((board[i][3] != 1) && (board[i][3] == board[i][4]) && (board[i][3] == board[i][5]) && (board[i][3] == board[i][6])) {
                winner = board[i][3];
            }
        }

        //check columns
        for (int i = 0; i < N_COLS; i++) {
            if ((board[0][i] != 1) && (board[0][i] == board[1][i]) && (board[0][i] == board[2][i]) && (board[0][i] == board[3][i])) {
                winner = board[0][i];
            } else if ((board[1][i] != 1) && (board[1][i] == board[2][i]) && (board[1][i] == board[3][i]) && (board[1][i] == board[4][i])) {
                winner = board[1][i];
            } else if ((board[2][i] != 1) && (board[2][i] == board[3][i]) && (board[2][i] == board[4][i]) && (board[2][i] == board[5][i])) {
                winner = board[2][i];
            }
        }

        if (winner == 2 || winner == 3)
            gameStatus = 0;

        return winner;
    }
}
