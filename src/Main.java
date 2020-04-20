

import java.util.Random;
import java.util.Scanner;
    public class Main {
        public static int SIZE = 3;
        public static int DOTS_TO_WIN = 3;
        public static final char DOT_EMPTY = '•';
        public static final char DOT_X = 'X';
        public static final char DOT_O = 'O';
        public static char[][] map;
        public static Scanner sc = new Scanner(System.in);
        public static Random rand = new Random();
        public static int i = 0;
        public static int j = 0;
        public static void main(String[] args) {
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил человек");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    System.out.println("Победил Искуственный Интеллект");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
            }
            System.out.println("Игра закончена");
        }
        public static boolean checkWin(char c) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (checkLine(i, j, 0, 1, c)) return true;
                    if (checkLine(i, j, 1, 1, c)) return true;
                    if (checkLine(i, j, 1, 0, c)) return true;
                    if (checkLine(i, j, -1, 1, c)) return true;
                }
            }
            return false;
        }
        public static boolean checkLine(int y, int x, int vy, int vx, char symb) {
            int winX = x + (DOTS_TO_WIN - 1) * vx;
            int winY = y + (DOTS_TO_WIN - 1) * vy;
            if (winX < 0 || winY < 0 || winX > SIZE - 1 || winY > SIZE - 1) return false;
            for (int i = 0; i < DOTS_TO_WIN; i++) {
                int Y = y + i * vy;
                int X = x + i * vx;
                if (map[Y][X] != symb) return false;
            }
            return true;
        }

        public static boolean isMapFull() {
            for (i = 0; i < SIZE; i++) {
                for (j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) return false;
                }
            }
            return true;
        }
        public static void aiTurn() {
                for (int i = 0; i < SIZE; i++)
                    for (int j = 0; j < SIZE; j++) {
                        if (isCellValid(i, j)) {
                            map[i][j] = DOT_X;
                            if (checkWin(DOT_X)) {
                                map[i][j] = DOT_O;
                                return;
                            }
                            map[i][j] = DOT_EMPTY;
                        }
                    }
                int x, y;
                do {
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE);
                } while (!isCellValid(x, y));
                System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
                map[y][x] = DOT_O;
            }

        public static void humanTurn() {
            int x, y;
            do {
                System.out.println("Введите координаты в формате X Y");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isCellValid(x, y));
            map[y][x] = DOT_X;
        }
        public static boolean isCellValid(int x, int y) {
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }
        public static void initMap() {
            map = new char[SIZE][SIZE];
            for (i = 0; i < SIZE; i++) {
                for (j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        public static void printMap() {
            for (i = 0; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }


