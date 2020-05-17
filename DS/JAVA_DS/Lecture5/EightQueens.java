package Lecture5;

public class EightQueens {
    static final int BOARD_SIZE = 8;
    private boolean [][]board;

    public EightQueens() {
        this.board = new boolean[BOARD_SIZE][BOARD_SIZE]; // default: null
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = false;
            }
        }

        // Start to place from leftmost
        placeQueens(0);

    }

    private boolean placeQueens(int col) {
        // 이전 col까지 잘 놓여있다고 가정해도 된다.
        // Return : true(if a solution is found) / false(if there is no solution)
        if (col == BOARD_SIZE) {
            return true;
        } else {
            boolean queenPlaced = false;
            int row = 0; // start from top
            while (!queenPlaced && (row < BOARD_SIZE)) {
                if (isUnderAttack(row, col)) {
                    ++row; // consider next square
                } else { // found valid square(Not under attack)
                    setQueen(row, col); // 놓고
                    queenPlaced = placeQueens(col + 1); // 다음열에 시킨 뒤 결과를 기다린다.
                    if (!queenPlaced) {
                        removeQueen(row, col);
                        ++row;
                    }
                }
            } // end while

            return queenPlaced;
        }
    }

    private boolean isUnderAttack(int row, int col) {
        // Check previous squres
        // vertical
        for (int i = 0; i < row; i++) {
            if (this.board[i][col]) return true;
        }

        // Horizontal
        for (int i = 0; i < col; i++) {
            if (this.board[row][i]) return true;
        }

        // Diagonal; up & left
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (this.board[i][j]) return true;
        }
        // Diagonal; down&left
        for (int i = row + 1, j = col - 1; i < BOARD_SIZE && j >= 0; i++, j--) {
            if (this.board[i][j]) return true;
        }

        // Arriving here means; No any attack.
        return false;
    }

    private void setQueen(int row, int col) { // setBoard()
        this.board[row][col] = true;
    }

    private void removeQueen(int row, int col) {
        this.board[row][col] = false;
    }

    public void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (this.board[row][col]) {
                    System.out.print("|Q");
                } else {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }

        System.out.println("----------------");
    }


    public static void main(String args[]) {
        EightQueens eightQueen = new EightQueens();
        eightQueen.printBoard();
    }


}
