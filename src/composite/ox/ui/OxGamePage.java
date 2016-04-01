package composite.ox.ui;

import composite.ox.game.GameController;
import composite.ox.game.GameEventListener;
import composite.ox.grid.Coords;
import composite.ox.game.GameGrid;
import composite.ox.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class OxGamePage extends OxPage implements GameEventListener {
    private final Grid<OxCellButton> cells;
    private final JComponent gameButtons;
    private final JButton mainMenuButton;
    private final JButton restartButton;
    private final GameController controller;

    OxGamePage(final OxGameForm form) {
        super(form);

        controller = new GameController();
        controller.addListener(this);

        setLayout(new BorderLayout());

        OxContainer leftPanel = new OxContainer();
        OxContainer centerPanel = new OxContainer();
        OxContainer rightPanel = new OxContainer();

        Dimension dim = new Dimension(150, getHeight());
        leftPanel.setPreferredSize(dim);
        rightPanel.setPreferredSize(dim);

        leftPanel.setLayout(new GridBagLayout());
        gameButtons = Box.createVerticalBox();
        leftPanel.add(gameButtons);

        centerPanel.setLayout(new GridBagLayout());

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        restartButton = new OxButton();
        restartButton.setText("Заново");

        mainMenuButton = new OxButton();
        mainMenuButton.setText("Главное меню");

        gameButtons.add(restartButton);
        gameButtons.add(Box.createVerticalStrut(5));
        gameButtons.add(mainMenuButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.selectPage(OxMainMenu.class);
            }
        });

        GameGrid grid = controller.getGrid();

        int rank = grid.getRank();
        cells = new Grid<>(rank);

        OxContainer gridPanel = new OxContainer();
        gridPanel.setLayout(new GridLayout(rank, rank, 5, 5));
        centerPanel.add(gridPanel);

        for (int y = 0; y < rank; ++y) {
            for (int x = 0; x < rank; ++x) {
                Coords coords = new Coords(x, y);
                OxCellButton cell = new OxCellButton(coords, controller);
                cells.set(cell, coords);
                gridPanel.add(cell);
            }
        }

        setVisible(true);

        controller.startGame();
    }

    @Override
    public void gameStarted(GameController controller) {
        gameButtons.removeAll();
        gameButtons.add(mainMenuButton);

        GameGrid grid = controller.getGrid();

        for (int y = 0; y < grid.getRank(); ++y) {
            for (int x = 0; x < grid.getRank(); ++x) {
                cells.get(x, y).setState(2);
            }
        }
    }

    @Override
    public void playerToggled(GameController controller) {

    }

    @Override
    public void moveMade(GameController controller, Coords coords) {
        cells.get(coords).setState(controller.getCurrentPlayer());
    }

    @Override
    public void gameEnded(GameController controller) {
        gameButtons.removeAll();
        gameButtons.add(restartButton);
        gameButtons.add(Box.createVerticalStrut(5));
        gameButtons.add(mainMenuButton);
    }

    @Override
    public void playerWon(GameController controller, ArrayList<Coords> winCombination) {

    }
}
