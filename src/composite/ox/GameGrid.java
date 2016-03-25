package composite.ox;


/**
 * Created by yura on 21.03.16.
 */
class GameGrid {

    int[][] cells;

    GameGrid(int countOfRows, int countOfCols){

        cells = new int[countOfRows][countOfCols];

        for (int i = 0; i < countOfRows; i++) {
            for (int j = 0; j < countOfCols; j++) {
                cells[i][j] = 2;
            }
        }
    }

    void toggleCell(){

    }

    protected void makeMove(){

    }

    int[][] getWinCombination(){


    }

    protected boolean checkWin(){
        return false;
    }

    protected boolean checkGridFull(){

        return false;
    }
    
    }
