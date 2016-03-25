package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class OxMainMenu extends JPanel {
    private JButton playTwoButton;
    private JButton exitButton;

    public OxMainMenu() {
        setBackground(new Color(255, 255, 255));

        playTwoButton = new OxButton();
        playTwoButton.setText("Два игрока");
        playTwoButton.setBounds(0, 0, 200, 50);

        exitButton = new OxButton();
        exitButton.setText("Выход");
        exitButton.setBounds(250, 0, 200, 50);

        add(playTwoButton);
        add(exitButton);

        setVisible(true);

        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window window = SwingUtilities.getWindowAncestor(OxMainMenu.this);
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
}
