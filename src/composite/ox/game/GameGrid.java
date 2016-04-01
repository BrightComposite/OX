package composite.ox.game;

import composite.ox.grid.Coords;
import composite.ox.grid.Direction;
import composite.ox.grid.Grid;

import java.util.ArrayList;

public class GameGrid {
    private final GameController ctrl;
    private final Grid<Integer> cells;
    private final int cellsToWin;

    private int cellsToggled = 0;
    private ArrayList<Coords> winCombination = null;

    GameGrid(GameController ctrl, int size, int cellsToWin) {
        this.ctrl = ctrl;
        this.cells = new Grid<>(size);
        this.cellsToWin = cellsToWin;
    }

    GameGrid(GameController ctrl, int size) {
        this(ctrl, size, size);
    }

    public int getRank() {
        return cells.getRank();
    }

    boolean toggleCell(Coords coords) {
        if(getCell(coords) != 2)
            return false;

        setCell(coords, ctrl.getCurrentPlayer());
        ++this.cellsToggled;

        if(this.cellsToggled <= this.cellsToWin)
            return true;

        lookForWin(coords);
        return true;
    }

    void clear() {
        this.cellsToggled = 0;
        this.winCombination = null;

        this.cells.fill(2);
    }

    ArrayList<Coords> getWinCombination() {
        return this.winCombination;
    }

    boolean checkWin() {
        return this.winCombination != null;
    }

    boolean isClosed() {
        return this.winCombination != null || this.cellsToggled == this.cells.getRank() * this.cells.getRank();
    }

    /*------------------------------------------------------------------------------------------------------------*/

    private int getCell(Coords coords) {
        return this.cells.includes(coords) ? this.cells.get(coords) : -1;
    }

    private void setCell(Coords coords, int value) {
        if(this.cells.includes(coords))
            this.cells.set(value, coords);
    }

    /**
     * Checks all directions from given coordinates to find win combination
     */
    private void lookForWin(Coords coords)
    {
        ArrayList<Coords> cellsList = new ArrayList<>();

        for(Direction[] dirs : Direction.getPairs()) {
            cellsList.add(coords);

            for(int i = 0; i < 2; ++i) {
                if(collectCellsInDirection(cellsList, dirs[i], coords)) {
                    this.winCombination = cellsList; // we found the win combination!
                    return;
                }
            }

            cellsList.clear(); // erase all cells collected in this step
        }
    }

    /**
     * Moves coordinates in a given direction and adds cells to an array until a cell corresponds to a current player
     */
    private boolean collectCellsInDirection(ArrayList<Coords> cells, Direction dir, Coords coords)
    {
        while(true) {
            coords = coords.add(dir);

            if(getCell(coords) != ctrl.getCurrentPlayer()) // check the bounds and the cell state
                return false;

            cells.add(coords);

            if(cells.size() == this.cellsToWin)
                return true; // we found the win combination, so there is no need to continue
        }
    }
}
