import java.util.*;
public class TicTacToe {
    
    public static void startGame(char player1, char player2) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose character for Player 1 -> X or O : ");
        player1 = sc.nextChar();
        if (player1 != 'X' || player1 != 'O') {
            System.out.println("Invalid!");
            return;
        }
        player2 = player1 == 'X' ? 'O' : 'X';
        return;
    }

    public static char[][] enterVal(char[][] arr, char player) {
        
        return arr;
    }

    public static char hasWon(char[][] arr){
        boolean ans = (arr[0][0] != 0 && arr[0][0] == arr[0][1] && arr[0][1] == arr[0][2]) || (arr[1][0] != 0 && arr[1][0] == arr[1][1] && arr[1][1] == arr[1][2]) || (arr[2][0] != 0 && arr[2][0] == arr[2][1] && arr[2][1] == arr[2][2]) || (arr[0][0] != 0 && arr[0][0] == arr[1][0] && arr[1][0] == arr[2][0]) || (arr[0][1] != 0 && arr[0][1] == arr[1][1] && arr[1][1] == arr[2][1]) || (arr[0][2] != 0 && arr[0][2] == arr[1][2] && arr[1][2] == arr[2][2]) || (arr[0][0] != 0 && arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) || (arr[0][2] != 0 && arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]);
        if(!ans) return 'N';
        return ans ? (arr[1][1] != 0 ? arr[1][1] : (arr[0][0] != 0 && arr[0][0] == arr[0][1] ? arr[0][0] : arr[0][2])) : ' ';
    }

    public static void main(String[] args){
        char[][] arr = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {' ', ' ', 'X'}
        };
        System.out.println(hasWon(arr));
    }
}