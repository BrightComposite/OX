package composite.ox.game;

import java.util.ArrayList;

import composite.ox.game.grid.Coords;
import composite.ox.game.grid.GameGrid;

public class GameController {
    private int currentPlayer = 0;
    private GameGrid grid;
    private ArrayList<GameEventListener> listeners = new ArrayList<>();
    private GameState state;

    public GameController() {
        grid = new GameGrid(this, 3);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void makeMove(Coords coords) {
        if(state != GameState.IN_PROGRESS) {
            switch (state) {
                case ENDED:
                    return;
                case NOT_STARTED:
                    start();
                    break;
            }
        }

        if(!grid.toggleCell(coords))
            return;

        for(GameEventListener l : listeners)
            l.moveMade(this, coords);

        if(!grid.isClosed()) {
            togglePlayer();
            return;
        }

        if(grid.checkWin()) {
            for (GameEventListener l : listeners)
                l.playerWon(this, grid.getWinCombination());
        }

        end();
    }

    public void addListener(GameEventListener l) {
        listeners.add(l);
    }

    /*------------------------------------------------------------------------------------------------------------*/

    private void start() {
        state = GameState.IN_PROGRESS;

        for(GameEventListener l : listeners)
            l.gameStarted(this);
    }

    private void end() {
        state = GameState.ENDED;

        for(GameEventListener l : listeners)
            l.gameEnded(this);
    }

    private void togglePlayer() {
        currentPlayer ^= 1;

        for (GameEventListener l : listeners)
            l.playerToggled(this);
    }
}
