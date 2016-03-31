package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OxMainMenu extends OxPage {
    private final JButton playTwoButton;
    private final JButton exitButton;

    OxMainMenu(final OxGameForm form) {
        super(form);

        setLayout(new GridBagLayout());

        playTwoButton = new OxButton();
        playTwoButton.setText("Два игрока");

        exitButton = new OxButton();
        exitButton.setText("Выход");

        Box container = Box.createVerticalBox();

        container.add(playTwoButton);
        container.add(Box.createVerticalStrut(5));
        container.add(exitButton);

        add(container);

        setVisible(true);

        playTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OxMainMenu.this.form.selectPage(OxGamePage.class);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OxMainMenu.this.form.dispatchEvent(new WindowEvent(OxMainMenu.this.form, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}
