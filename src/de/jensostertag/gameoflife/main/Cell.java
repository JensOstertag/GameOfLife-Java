package de.jensostertag.gameoflife.main;

public class Cell {
    private CellState state;

    public Cell() {
        this.state = CellState.DEAD;
    }

    public CellState getCellState() {
        return this.state;
    }

    public String getCellSymbol() {
        return this.state.label;
    }

    public void setDead() {
        this.state = CellState.DEAD;
    }

    public void setAlive() {
        this.state = CellState.ALIVE;
    }

    public Cell copy() {
        Cell copyCell = new Cell();
        if(this.state == CellState.ALIVE)
            copyCell.setAlive();

        return copyCell;
    }
}
