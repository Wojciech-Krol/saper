package saper;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private int width, height;

    private Pole[][] board;

    Scanner scanner = new Scanner(System.in);

    public Board(int height, int width) {
        this.width = width;
        this.height = height;
        this.board = new Pole[width][height];

    }

    public void fillBoard(int intBombs) {
        boolean randomizedValue;
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                randomizedValue = random.nextBoolean();
                if (randomizedValue == true && intBombs > 0) {
                    board[i][j] = new Pole(true);
                    intBombs--;
                } else {
                    board[i][j] = new Pole(false);
                }
            }

        }
    }

    public void printBoard() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (!(board[i][j].getBomba()) && board[i][j].getCzyOdkryte()) {
                    System.out.print(countNeighbours(i, j) + " ");
                } else if (!board[i][j].getCzyOdkryte())
                    System.out.print("X ");
                else {
                    System.out.print("B ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void kliknijPole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj współrzędną x: ");
        int x = scanner.nextInt();
        System.out.println("Podaj współrzędną y:  ");
        int y = scanner.nextInt();

        if (!(board[x][y].getCzyOdkryte())) {
            if (board[x][y].getBomba()) {
                System.out.println("Koniec gry");
                board[x][y].setCzyOdkryte(true);
            } else {
                board[x][y].setCzyOdkryte(true);
                odkrywanieInnych(x, y);

            }
        } else {
            System.out.println("Pole jest już odkryte");
        }
        printBoard();

    }

    private int countNeighbours(int w, int k) {
        int neighbours = 0;
        for (int i = w - 1; i < w + 2; i++) {
            for (int j = k - 1; j < k + 2; j++) {
                if (i < 0 || i > width - 1 || j < 0 || j > height - 1) {
                } else if (i == w && j == k) {
                } else if (board[i][j].getBomba()) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public void graj(int x) {
        while (x > 0) {
            kliknijPole();
            x--;
        }
    }

    public void odkryjPlansze(){
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                board[i][j].setCzyOdkryte(true);
            }
        }
    }

    public void odkrywanieInnych(int w, int k) {
        if (countNeighbours(w, k) == 0) {
            for (int i = w - 1; i <= w + 1; i++) {
                for (int j = k - 1; j <= k + 1; j++) {
                    if (i < 0 || i > width - 1 || j < 0 || j > height - 1) {
                    } else if (i == w && j == k) {
                    } else if (board[i][j].getCzyOdkryte()) {
                    } else if (countNeighbours(i, j) == 0) {
                        board[i][j].setCzyOdkryte(true);
                        odkrywanieInnych(i, j);
                    } else {
                        board[i][j].setCzyOdkryte(true);
                    }
                }
            }
        }

    }
}