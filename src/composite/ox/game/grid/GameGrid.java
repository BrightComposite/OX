package composite.ox.game.grid;

import composite.ox.game.GameController;

import java.util.ArrayList;

public class GameGrid {
    private GameController ctrl;
    private int[][] cells;
    private int size;
    private int cellsToWin;

    private int cellsToggled = 0;
    private ArrayList<Coords> winCombination = null;

    public GameGrid(GameController ctrl, int size, int cellsToWin) {
        this.ctrl = ctrl;
        this.cells = new int[size][size];
        this.size = size;
        this.cellsToWin = cellsToWin;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.cells[x][y] = 2;
            }
        }
    }

    public GameGrid(GameController ctrl, int size) {
        this(ctrl, size, size);
    }

    public int getCell(Coords coords) {
        return includes(coords) ? this.cells[coords.getX()][coords.getY()] : -1;
    }

    public boolean toggleCell(Coords coords) {
        if(getCell(coords) != 2)
            return false;

        setCell(coords, ctrl.getCurrentPlayer());
        ++this.cellsToggled;

        lookForWin(coords);
        return true;
    }

    public boolean includes(Coords coords) {
        return coords.getX() >= 0 && coords.getY() >= 0 && coords.getX() < size && coords.getY() < size;
    }

    public ArrayList<Coords> getWinCombination() {
        return this.winCombination;
    }

    public boolean checkWin() {
        return this.winCombination != null;
    }

    public boolean isClosed() {
        return this.winCombination != null || this.cellsToggled == this.size * this.size;
    }

    /*------------------------------------------------------------------------------------------------------------*/

    private void setCell(Coords coords, int value) {
        if(includes(coords))
            this.cells[coords.getX()][coords.getY()] = value;
    }

    /**
     * Checks all directions from given coordinates to find win combination
     */
    private void lookForWin(Coords coords)
    {
        ArrayList<Coords> cellsList = new ArrayList<>();

        for(Direction[] dirs : Direction.getPairs()) {
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
