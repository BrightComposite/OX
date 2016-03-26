package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import composite.ox.game.GameController;
import composite.ox.game.GameEventListener;
import composite.ox.game.grid.Coords;
import composite.ox.game.grid.GameGrid;

class OxGamePage extends OxPage implements GameEventListener {
    private JButton [][] cells;
    private JComponent gameButtons;
    private JButton mainMenuButton;
    private JButton restartButton;
    final private GameController controller;

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

        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                controller.start();
            }
        });

        mainMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                OxGamePage.this.form.selectPage(OxMainMenu.class);
            }
        });

        controller.start();

        GameGrid grid = controller.getGrid();
        cells = new OxCellButton[grid.getRank()][grid.getRank()];

        OxContainer gridPanel = new OxContainer();
        gridPanel.setLayout(new GridLayout(grid.getRank(), grid.getRank(), 5, 5));
        centerPanel.add(gridPanel);

        for(int y = 0; y < grid.getRank(); ++y) {
            for(int x = 0; x < grid.getRank(); ++x) {
                cells[y][x] = new OxCellButton();
                gridPanel.add(cells[y][x]);
            }
        }

        setVisible(true);
    }

    @Override
    public void gameStarted(GameController controller) {
        gameButtons.removeAll();
        gameButtons.add(mainMenuButton);
    }

    @Override
    public void playerToggled(GameController controller) {

    }

    @Override
    public void moveMade(GameController controller, Coords coords) {

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
