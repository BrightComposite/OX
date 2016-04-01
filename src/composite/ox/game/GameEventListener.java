package composite.ox.game;

import composite.ox.grid.Coords;

import java.util.ArrayList;

public interface GameEventListener {
    void gameStarted(GameController controller);
    void playerToggled(GameController controller);
    void moveMade(GameController controller, Coords coords);
    void gameEnded(GameController controller);
    void playerWon(GameController controller, ArrayList<Coords> winCombination);
}
