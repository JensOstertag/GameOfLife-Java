package de.jensostertag.gameoflife.main;

import java.util.Arrays;
import java.util.Scanner;

public class CellBoard {
    private final Scanner scanner;
    private int width, height;
    Cell[][] board;

    public CellBoard() {
        this.scanner = new Scanner(System.in);

        // Init board width and height
        initBoard();
        this.board = new Cell[width][height];

        // Fill the array with dead cells manually to have a single object for each field
        for(int w = 0; w < this.width; w++)
            for(int h = 0; h < this.height; h++)
                this.board[w][h] = new Cell();

        // Define alive cells
        predefineAliveCells();
    }

    private void initBoard() {
        int width, height;

        do {
            System.out.println("Please enter the game board width.");
            width = scanner.nextInt();
        } while(width <= 0);

        this.width = width;

        do {
            System.out.println("Please enter the game board height.");
            height = scanner.nextInt();
        } while(height <= 0);

        this.height = height;
    }

    private void predefineAliveCells() {
        boolean done = false;
        scanner.nextLine();
        while(!(done)) {
            System.out.println("Please enter the x and y coordinates of an alive cell or \"-1\" to continue.");
            String input = scanner.nextLine();
            if(!(input.equals("-1"))) {
                int x = Integer.parseInt(input.split(" ")[0]);
                int y = Integer.parseInt(input.split(" ")[1]);
                board[x][y].setAlive();
            } else {
                done = true;
            }
        }
    }

    public void nextIteration() {
        Cell[][] newBoard = new Cell[this.width][this.height];

        for(int w = 0;  w < this.board.length; w++) {
            for(int h = 0; h < this.board[w].length; h++) {
                Cell cell = getCellAtCoordinates(w, h);
                Cell[] neighbours = getNeighbours(w, h);

                int alive = 0;

                for(Cell neighbour : neighbours)
                    if(neighbour.getCellState() == CellState.ALIVE) alive++;

                Cell copiedCell = cell.copy();
                newBoard[w][h] = copiedCell;
                if(cell.getCellState() == CellState.ALIVE) {
                    if(alive < 2)
                        newBoard[w][h].setDead();
                    if(alive == 2 || alive == 3)
                        newBoard[w][h].setAlive();
                    if(alive > 3)
                        newBoard[w][h].setDead();
                } else if(cell.getCellState() == CellState.DEAD) {
                    if(alive == 3)
                        newBoard[w][h].setAlive();
                }
            }
        }

        board = newBoard;
    }

    private Cell getCellAtCoordinates(int x, int y) {
        if(x >= 0 && x < this.width && y >= 0 && y < this.height)
            return this.board[x][y];
        else
            return null;
    }

    private Cell[] getNeighbours(int x, int y) throws IllegalArgumentException {
        if(!(x >= 0 && x < this.width && y >= 0 && y < this.height)) throw new IllegalArgumentException();

        int neighboursAmount = 8;
        neighboursAmount -= (x == 0 || x == this.width - 1) ? 3 : 0;
        neighboursAmount -= (y == 0 || y == this.height - 1) ? 3 : 0;
        neighboursAmount += (neighboursAmount == 2) ? 1 : 0;

        Cell[] neighbours = new Cell[neighboursAmount];

        int i = 0;
        if(getCellAtCoordinates(x - 1, y - 1) != null) {
            neighbours[i] = getCellAtCoordinates(x - 1, y - 1);
            i++;
        }
        if(getCellAtCoordinates(x, y - 1) != null) {
            neighbours[i] = getCellAtCoordinates(x, y - 1);
            i++;
        }
        if(getCellAtCoordinates(x + 1, y - 1) != null) {
            neighbours[i] = getCellAtCoordinates(x + 1, y - 1);
            i++;
        }
        if(getCellAtCoordinates(x + 1, y) != null) {
            neighbours[i] = getCellAtCoordinates(x + 1, y);
            i++;
        }
        if(getCellAtCoordinates(x + 1, y + 1) != null) {
            neighbours[i] = getCellAtCoordinates(x + 1, y + 1);
            i++;
        }
        if(getCellAtCoordinates(x, y + 1) != null) {
            neighbours[i] = getCellAtCoordinates(x, y + 1);
            i++;
        }
        if(getCellAtCoordinates(x - 1, y + 1) != null) {
            neighbours[i] = getCellAtCoordinates(x - 1, y + 1);
            i++;
        }
        if(getCellAtCoordinates(x - 1, y) != null) {
            neighbours[i] = getCellAtCoordinates(x - 1, y);
            i++;
        }

        return neighbours;
    }

    public void printBoard() {
        for(int h = 0; h < this.height; h++) {
            if(h != 0) {
                for(int w = 0; w < this.width; w++) {
                    System.out.print("  ");
                }
            }
            System.out.println();
            for(int w = 0; w < this.width; w++) {
                System.out.print(this.board[w][h].getCellSymbol() + " ");
            }
            System.out.println();
        }
    }
}
