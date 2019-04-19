import java.util.Scanner;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int GAME_SIZE = 3;

    char[][] game = new char[3][3];

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void showGame() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void initBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }

    public Move readMove() {
        Scanner s = new Scanner(System.in);
        System.out.println("Make move");
        String myMove = s.nextLine();
        String[] mySplit = myMove.split("-");

        int myColumn = Integer.valueOf(mySplit[1]);
        int myLine = Integer.valueOf(mySplit[0]);

        Move move = new Move(myLine, myColumn);
        return move;
    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;
    }

    public boolean validateMove1(Move move) {
        boolean isValidInput = true;
        if (move.line >= GAME_SIZE || move.col >= GAME_SIZE) {
            isValidInput = false;
        }
        return isValidInput;
    }

    public boolean validateMove2(Move move) {
        boolean isValidMove = true;
        if (game[move.line][move.col] == SYMBOL_X || game[move.line][move.col] == SYMBOL_0) {
            isValidMove = false;
        }
        return isValidMove;
    }

    public boolean isWinLine(int line, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[line][i] != symbol) {
                isWin = false;

            }
            i++;
        }
        return isWin;
    }

    public boolean isWinCol(int col, char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][col] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag1(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][i] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWinDiag2(char symbol) {
        boolean isWin = true;
        int i = 0;
        while (i < GAME_SIZE && isWin == true) {
            if (game[i][GAME_SIZE - i - 1] != symbol) {
                isWin = false;
            }
            i++;
        }
        return isWin;
    }

    public boolean isWin(Move move, char symbol) {
        boolean isWin = false;

        //testez linii
        isWin = isWinLine(move.line, symbol);

        if (isWin == false) {
            //testez coloane
            isWin = isWinCol(move.col, symbol);
        }
        //testez diag1
        if (isWin == false && move.line == move.col) {
            isWin = isWinDiag1(symbol);
        }
        //testez diag2
        if (isWin == false && move.line == GAME_SIZE - move.col - 1) {
            isWin = isWinDiag2(symbol);
        }

        return isWin;
    }

    public void playGame() {
        initBoard();
        System.out.println("incepe jocul.");
        showGame();

        Player currentPlayer = player1;
        char currentSymbol = SYMBOL_X;
        int nrMoves = 0;
        boolean isWin = false;

        while (isWin == false && nrMoves < (GAME_SIZE * GAME_SIZE)) {

            //citesc mutarea
            Move move = readMove();
            System.out.println(move.line);
            System.out.println(move.col);

            //validez mutarea
            boolean moveIsValid = false;
            while (!moveIsValid) {
                moveIsValid = validateMove1(move);
                if (!moveIsValid) {
                    moveIsValid = validateMove2(move);
                    showGame();
                }
            }

            makeMove(move, currentSymbol);
            showGame();

            //numar mutarea
            nrMoves++;

            //testez daca avem starea de WIN
            if (nrMoves >= 2 * GAME_SIZE - 1) {
                isWin = isWin(move, currentSymbol);
            }

            //daca nu e WIN sau mai am mutari -- schimb jucatorul
            if (!isWin) {
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                    currentSymbol = SYMBOL_0;
                } else {
                    currentPlayer = player1;
                    currentSymbol = SYMBOL_X;

                }
            }
        }

            //afisez mesaj corespunzator
            if (isWin == true) {
                System.out.println("a castigat " + currentPlayer.name);

            } else System.out.println("Jocul nu a fost castigat de nici un jucator! ");
        }
    }


