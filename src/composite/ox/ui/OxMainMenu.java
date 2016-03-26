package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

class OxMainMenu extends OxPage {
    private JButton playTwoButton;
    private JButton exitButton;

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

        playTwoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                OxMainMenu.this.form.selectPage(OxGamePage.class);
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                OxMainMenu.this.form.dispatchEvent(new WindowEvent(OxMainMenu.this.form, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}
