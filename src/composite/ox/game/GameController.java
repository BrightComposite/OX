package composite.ox.game;

import java.util.ArrayList;

import composite.ox.game.grid.Coords;
import composite.ox.game.grid.GameGrid;

public class GameController {
    private int currentPlayer = 0;
    private GameState state = GameState.NOT_STARTED;
    private GameGrid grid = null;
    private ArrayList<GameEventListener> listeners = new ArrayList<>();

    public GameController() {
        grid = new GameGrid(this, 3);}

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void makeMove(Coords coords) {
        if(state != GameState.IN_PROGRESS)
            return;

        if(!grid.toggleCell(coords))
            return;

        for(GameEventListener l : listeners)
            l.moveMade(this, coords);

        if(!grid.isClosed()) {
            currentPlayer ^= 1;

            for (GameEventListener l : listeners)
                l.playerToggled(this);

            return;
        }

        if(grid.checkWin()) {
            for (GameEventListener l : listeners)
                l.playerWon(this, grid.getWinCombination());
        }

        state = GameState.ENDED;

        for(GameEventListener l : listeners)
            l.gameEnded(this);
    }

    public void addListener(GameEventListener l) {
        listeners.add(l);
    }

    public void startGame() {
        grid.clear();
        state = GameState.IN_PROGRESS;

        currentPlayer = 0;

        for(GameEventListener l : listeners)
            l.gameStarted(this);
    }
}
