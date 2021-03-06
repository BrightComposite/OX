package composite.ox.ui;

import composite.ox.game.GameController;
import composite.ox.grid.Coords;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OxCellButton extends OxButton {
    private static ActionListener cellListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OxCellButton btn = (OxCellButton)e.getSource();
            btn.controller.makeMove(btn.coords);
        }
    };

    private final Coords coords;
    private final GameController controller;

    OxCellButton(final Coords coords, final GameController controller) {
        this.coords = coords;
        this.controller = controller;

        setText(null);
        setPreferredSize(new Dimension(50, 50));
        addActionListener(cellListener);
    }

    void setState(int state) {
        setText(state == 0 ? "X" : state == 1 ? "O" : "");
    }
}
