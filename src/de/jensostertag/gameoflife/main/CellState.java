package de.jensostertag.gameoflife.main;

public enum CellState {
    DEAD("-"), ALIVE("O");

    public final String label;
    CellState(String label) {
        this.label = label;
    }
}
