package de.jensostertag.gameoflife.main;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CellBoard board = new CellBoard();
        board.printBoard();

        Thread.sleep(2500);

        for(int i = 0; i < 50; i++) {
            board.nextIteration();
            board.printBoard();
            Thread.sleep(2500);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}
