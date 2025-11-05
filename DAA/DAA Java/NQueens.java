import java.util.Scanner;

public class NQueens {

    // Print the board
    static void printBoard(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if placing a queen at (row, col) is safe
    static boolean isSafe(int[][] board, int row, int col, int N) {
        // Check same column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // Upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    // Backtracking function to place queens
    static boolean solveNQueens(int[][] board, int row, int N) {
        if (row >= N) return true; // all queens placed

        // If this row already has a queen, skip to next row
        boolean rowHasQueen = false;
        for (int j = 0; j < N; j++) {
            if (board[row][j] == 1) {
                rowHasQueen = true;
                break;
            }
        }
        if (rowHasQueen) return solveNQueens(board, row + 1, N);

        for (int col = 0; col < N; col++) {
            if (board[row][col] == 0 && isSafe(board, row, col, N)) {
                board[row][col] = 1; // place queen
                if (solveNQueens(board, row + 1, N)) return true;
                board[row][col] = 0; // backtrack
            }
        }

        return false; // no valid position in this row
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of board (N): ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input for N.");
            sc.close();
            return;
        }
        int N = sc.nextInt();

        if (N <= 0) {
            System.out.println("Board size must be positive.");
            sc.close();
            return;
        }

        int[][] board = new int[N][N];

        System.out.print("Enter position of first queen (row and column, 0-based index): ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input for first row.");
            sc.close();
            return;
        }
        int firstRow = sc.nextInt();
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input for first column.");
            sc.close();
            return;
        }
        int firstCol = sc.nextInt();

        if (firstRow >= N || firstCol >= N || firstRow < 0 || firstCol < 0) {
            System.out.println("Invalid position!");
            sc.close();
            return;
        }

        board[firstRow][firstCol] = 1; // place first queen

        if (solveNQueens(board, 0, N)) {
            System.out.println("\nN-Queens solution:");
            printBoard(board, N);
        } else {
            System.out.println("No solution exists with the first queen at the given position.");
        }

        sc.close();
    }
}

