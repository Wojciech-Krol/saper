package saper;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private int width, height;

    private Pole[][] board;

    Scanner scanner = new Scanner(System.in);

    private int allbombs;
    public Board(int height, int width, int bombs) {
        this.width = width;
        this.height = height;
        this.board = new Pole[height][width];
        this.allbombs = bombs;

    }

    public void fillBoard() {
        Random random = new Random();
        int bombs = allbombs;
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                board[j][i] = new Pole(false);

        while (bombs > 0) {
            int x = random.nextInt(0, height - 1);
            int y = random.nextInt(0, width - 1);
            if (!board[x][y].getBomba())  {
                board[x][y].setCzyBomba(true);
                bombs--;
            }

        }
    }

    public void printBoard() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (!(board[j][i].getBomba()) && board[j][i].getCzyOdkryte()) {
                    System.out.print(countNeighbours(j, i) + "  ");
                }
                else if(board[j][i].getCzyFlaga())
                    System.out.println("F  ");
                else if (!board[j][i].getCzyOdkryte())
                    System.out.print("X  ");
                else {
                    System.out.print("B  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void kliknijPole(int x, int y) {

        if (!(board[x][y].getCzyOdkryte())) {
            if (board[x][y].getBomba()) {
                System.out.println("Koniec gry, trafiłeś bombę");
                odkryjPlansze();
                printBoard();
                System.exit(0);
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
                if (i < 0 || i > height - 1 || j < 0 || j > width - 1) {
                } else if (i == w && j == k) {
                } else if (board[i][j].getBomba()) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public void graj() {
        int x;
        int y;
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        int odp;

        fillBoard();

        while (liczNieOdkrytePola()!= allbombs) {
            System.out.println("Flaga? (1 - tak; 0 - nie) ");
            odp = scanner.nextInt();
            System.out.println("Podaj współrzędną x: ");
            x = scanner.nextInt() - 1;
            System.out.println("Podaj współrzędną y:  ");
            y = scanner.nextInt() - 1;
            if (sprawdźCzyPoprawneWspół(x, y)) {
                if (odp != 1) {
                    n++;
                    kliknijPole(x, y);
                }
                else if (odp == 1)
                    board[x][y].setFlaga();
            }
            else
                System.out.println("Podane współrzędne są poza planszą");
        }
        System.out.println("Brawo wygrałeś w "+ n + " ruchach");
    }

    public void odkrywanieInnych(int w, int k) {
        if (countNeighbours(w, k) == 0) {
            for (int i = w - 1; i < w + 2; i++) {
                for (int j = k - 1; j < k + 2; j++) {
                    if (i < 0 || i > height - 1 || j < 0 || j > width - 1) {
                    } else if (i == w && j == k) {
                    } else if (board[i][j].getCzyOdkryte()) {
                    } else if (countNeighbours(i, j) == 0) {
                        board[i][j].setCzyOdkryte(true);
                        odkrywanieInnych(i, j);
                    } else
                        board[i][j].setCzyOdkryte(true);
                }
            }
        }
    }

    public void odkryjPlansze() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++)
                board[j][i].setCzyOdkryte(true);
        }
    }

    public int liczNieOdkrytePola() {
        int n = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (!board[j][i].getCzyOdkryte())
                    n++;
            }
        }
        return n;
    }
    public boolean sprawdźCzyPoprawneWspół(int x , int y)
    {
        return x < height && y < width && x >= 0 && y >= 0;
    }
}