package composite.ox.game.grid;

import composite.ox.game.GameController;

public class GameGrid {
    private int[][] cells;
    private int[][] winCombination = null;
    private int cellsCount;
    private int cellsToggled = 0;

    public GameGrid(int rows, int cols) {
        cells = new int[rows][cols];
        cellsCount = rows * cols;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                cells[x][y] = 2;
            }
        }
    }

    public boolean toggleCell(int x, int y) {
        if(cells[x][y] != 2 || cells.length <= y || cells[0].length <= x)
            return false;

        cells[x][y] = GameController.getCurrentPlayer();
        ++cellsToggled;

        lookForWin(x, y);
        return true;
    }

    protected void makeMove() {

    }

    public int[][] getWinCombination() {
        return winCombination;
    }

    protected boolean checkWin() {
        return winCombination != null;
    }

    protected boolean checkGridFull() {
        return cellsToggled == cellsCount;
    }

    /*------------------------------------------------------------------------------------------------------------*/

    private void lookForWin(int x, int y)
    {
        boolean foundWin = false;

        while(!foundWin)
        {

            foundWin = true;
        }
    }
}
