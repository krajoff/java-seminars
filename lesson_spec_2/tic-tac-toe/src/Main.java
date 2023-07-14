import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = ' ';
    private static Scanner scanner;
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static Random random = new Random();

    private static Scanner getScanner() {
        if (scanner == null)
            scanner = new Scanner(System.in);
        return scanner;
    }

    public static void main(String[] args) {
        while (true) {
            initialize();
            while (true) {
                printField();
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "You win"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "AI win"))
                    break;
            }
            System.out.println("One more time (Y)?");
            if (!getScanner().next().equalsIgnoreCase("Y"))
                break;
        }

    }

    private static void initialize() {
        fieldSizeX = 11;
        fieldSizeY = 10;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        String space = " ";
        int SizeX = (int) Math.log10(fieldSizeX) + 1;
        int SizeTemp;
        System.out.print(space.repeat(SizeX));
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? " " : i / 2 + 1);
        }
        System.out.println();
        for (int x = 0; x < fieldSizeX; x++) {
            SizeTemp = (int) Math.log10(x + 1) + 1;
            System.out.print(x + 1 +
                    space.repeat(SizeX - SizeTemp) + "|");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    /**
     * Проверка победы
     *
     * @param c фишка игрока
     * @return результат проверки
     */
    static boolean checkWin(char c) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                boolean tfx = true;
                boolean tfy = true;
                boolean tfdr = true;
                boolean tfdl = true;
                for (int k = 0; k < WIN_COUNT; k++) {
                    if (i < fieldSizeX - WIN_COUNT) {
                        tfx = tfx && field[i + k][j] == c;
                    }
                    if (j < fieldSizeY - WIN_COUNT) {
                        tfy = tfy && field[i][j + k] == c;
                    }
                    if (j < fieldSizeY - WIN_COUNT &&
                            i < fieldSizeX - WIN_COUNT) {
                        tfdr = tfdr && field[i + k][j + k] == c;
                    }

                    if (i < fieldSizeX - WIN_COUNT && j >= WIN_COUNT-1) {
                        tfdl = tfdl && field[i - k][j - k] == c;
                    }
                }
            if (tfx || tfy || tfdr || tfdl == true)
                return true;
            }
        }
        return false;
    }

    static boolean checkWin1(char c, int x, int y, int win) {
        return true;
    }

    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }


    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Input X & Y with whitespace: ");
            x = getScanner().nextInt() - 1;
            y = getScanner().nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }


    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }


    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

//    private static boolean checkPont(char c, int x, int y) {
//        int cnt = 0;
//        for (int )
//        while (condition == f)
//        (int i = 0; i < fieldSizeY; i++){
//            if(field[x][i] == c) {
//            }
//                break;
//            if(field[x][i] != c)
//                break;
//            if(i == fieldSizeY-1){
//                //report win for s
//            }
//        }
//
//        //check row
//        for(int i = 0; i < n; i++){
//            if(board[i][y] != s)
//                break;
//            if(i == n-1){
//                //report win for s
//            }
//        }
//
//        //check diag
//        if(x == y){
//            //we're on a diagonal
//            for(int i = 0; i < n; i++){
//                if(board[i][i] != s)
//                    break;
//                if(i == n-1){
//                    //report win for s
//                }
//            }
//        }
//
//        //check anti diag (thanks rampion)
//        if(x + y == n - 1){
//            for(int i = 0; i < n; i++){
//                if(board[i][(n-1)-i] != s)
//                    break;
//                if(i == n-1){
//                    //report win for s
//                }
//            }
//        }
//
//        //check draw
//        if(moveCount == (Math.pow(n, 2) - 1)){
//            //report draw
//        }
//    }
//    }

}
