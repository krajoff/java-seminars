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
            int[] turn;
            while (true) {
                printField();
                turn = humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "You win"))
                    break;
                aiTurn(turn);
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
        int tfx, tfy, tfdr, tfdl;
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                tfx = 0;
                tfy = 0;
                tfdr = 0;
                tfdl = 0;
                for (int k = 0; k < WIN_COUNT; k++) {
                    if (i < fieldSizeX - WIN_COUNT + 1)
                        tfx = field[i + k][j] == c ? tfx + 1 : 0;
                    if (j < fieldSizeY - WIN_COUNT + 1)
                        tfy = field[i][j + k] == c ? tfy + 1 : 0;
                    if (j < fieldSizeY - WIN_COUNT + 1 &&
                            i < fieldSizeX - WIN_COUNT + 1)
                        tfdr = field[i + k][j + k] == c ? tfdr + 1 : 0;
                    if (i < fieldSizeX - WIN_COUNT + 1 &&
                            j > fieldSizeY - WIN_COUNT + 1)
                        tfdl = field[i + k][j - k] == c ? tfdl + 1 : 0;
                }
                if (tfx == WIN_COUNT || tfy == WIN_COUNT || tfdr == WIN_COUNT || tfdl == WIN_COUNT)
                    return true;
            }
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    private static int[] humanTurn() {
        int x, y;
        int[] turn = new int[2];
        do {
            System.out.print("Input X & Y with whitespace: ");
            x = getScanner().nextInt() - 1;
            y = getScanner().nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
        turn[0] = x;
        turn[1] = y;
        return turn;
    }


    private static void aiTurn(int[] turn) {
        int x = turn[1];
        int y = turn[2];
        for (int k = 0; k < 7; k++) {
            if (x>0  )
            }



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

}
