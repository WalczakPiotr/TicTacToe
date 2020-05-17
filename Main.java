package tictactoe;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Field {

    public static int[][] field = new int[3][3];
    public static int nextType = 1;

    public Field() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    public static void printBoard () {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + printCell(field[i][0]) + " " + printCell(field[i][1]) + " " + printCell(field[i][2]) + " |");
        }
        System.out.println("---------");
    }

    public static char printCell (int i) {
        if (i == 1) {
            return 'X';
        } else if (i == -1) {
            return 'O';
        } else {
            return ' ';
        }
    }

    public static void nextMove () {
        Scanner scanner = new Scanner(System.in);
        int loop = 0;
        while (loop == 0) {
            System.out.print("Enter the coordinates: ");
            String[] sam = scanner.nextLine().split(" ");
            if (sam[0].length() > 1 || sam[1].length() > 1) {
                System.out.println("You should enter numbers!");
            } else {
                int x = parseInt(sam[0]), y = parseInt(sam[1]);
                if (x > 3 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[3 - y][x - 1] == 0) {
                    field[3 - y][x - 1] = nextType;
                    changeType();
                    loop = 1;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        }
    }

    public static void changeType () {
        if (nextType == 1) {
            nextType = -1;
        } else {
            nextType = 1;
        }
    }

    public static int checkWin () {
        int[] sumRow = {0, 0, 0};
        int[] sumCol = {0, 0, 0};
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sumRow[i] += field[i][j];
                sumCol[j] += field[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (sumCol[i] == 3 || sumRow[i] == 3) {
                System.out.println("X wins");
                result++;
            } else if (sumCol[i] == -3 || sumRow[i] == -3) {
                System.out.println("O wins");
                result++;
            }
        }
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[1][1] == 1) {
            System.out.println("X wins");
            result++;
        } else if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[1][1] == -1) {
            System.out.println("O wins");
            result++;
        } else if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == 1) {
            System.out.println("X wins");
            result++;
        } else if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == -1) {
            System.out.println("O wins");
            result++;
        }
        if (result == 0) {
            if (fullField()) {
                System.out.println("Draw");
                result++;
            } else {
                System.out.println("Game not finished");
            }
        }
        return result;
    }

    public static boolean fullField () {
        int checkNull = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    checkNull++;
                }
            }
        }
        return checkNull == 0;
    }

}

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        int result = 0;
        while (result == 0) {
            field.printBoard();
            field.nextMove();
            field.printBoard();
            result = field.checkWin();
        }
    }
}
