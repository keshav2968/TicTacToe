import java.util.*;

public class TicTacToe {

    static final char EMPTY = ' ';
    static final char HUMAN = 'X';
    static final char AI = 'O';
    static Scanner scanner = new Scanner(System.in);

    static char[] board = new char[9];

    public static void main(String[] args) {
        Arrays.fill(board, EMPTY);
        System.out.println("Tic-Tac-Toe - Human (X) vs AI (O)");
        printBoard();

        while (true) {
            humanMove();
            printBoard();
            if (checkGameOver(HUMAN)) break;

            System.out.println("AI is thinking...");
            int bestMove = minimax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true).index;
            board[bestMove] = AI;
            printBoard();
            if (checkGameOver(AI)) break;
        }
    }

    static void humanMove() {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < 9 && board[move] == EMPTY) {
                board[move] = HUMAN;
                break;
            }
            System.out.println("Invalid move. Try again.");
        }
    }

    static boolean checkGameOver(char player) {
        if (checkWinner(board, player)) {
            System.out.println(player + " wins!");
            return true;
        }
        if (isFull(board)) {
            System.out.println("It's a tie!");
            return true;
        }
        return false;
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i] == EMPTY ? (i + 1) : board[i]);
            if ((i + 1) % 3 == 0) System.out.println();
            else System.out.print(" | ");
        }
        System.out.println();
    }

    static boolean isFull(char[] b) {
        for (char c : b) if (c == EMPTY) return false;
        return true;
    }

    static boolean checkWinner(char[] b, char player) {
        int[][] winCombos = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] wc : winCombos) {
            if (b[wc[0]] == player && b[wc[1]] == player && b[wc[2]] == player)
                return true;
        }
        return false;
    }

    static class Move {
        int index;
        int score;

        Move(int i, int s) {
            index = i;
            score = s;
        }
    }

    static Move minimax(char[] b, int depth, int alpha, int beta, boolean isMax) {
        if (checkWinner(b, AI)) return new Move(-1, 10 - depth);
        if (checkWinner(b, HUMAN)) return new Move(-1, depth - 10);
        if (isFull(b)) return new Move(-1, 0);

        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (b[i] == EMPTY) available.add(i);

        Move bestMove = new Move(-1, isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        for (int i : available) {
            b[i] = isMax ? AI : HUMAN;
            Move sim = minimax(b, depth + 1, alpha, beta, !isMax);
            b[i] = EMPTY;
            sim.index = i;

            if (isMax) {
                if (sim.score > bestMove.score) bestMove = sim;
                alpha = Math.max(alpha, bestMove.score);
            } else {
                if (sim.score < bestMove.score) bestMove = sim;
                beta = Math.min(beta, bestMove.score);
            }

            if (beta <= alpha) break;
        }
        return bestMove;
    }
}
