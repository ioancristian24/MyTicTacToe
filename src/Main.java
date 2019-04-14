import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("numele primului jucator ");
        String player1Name = scanner.nextLine();
        System.out.println("numele celui de-al doilea jucator ");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        MyTicTacToe myTicTacToe = new MyTicTacToe(player1,player2);
        myTicTacToe.playGame();

    }
}
