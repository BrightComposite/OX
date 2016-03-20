package composite.ox.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class OxMainMenuForm extends JFrame {
    private JPanel container;
    private JButton playTwoButton;
    private JButton exitButton;

    public void setup() {
        final OxMainMenuForm frame = this;
        container = new JPanel();

        playTwoButton = new JButton();
        playTwoButton.setText("Два игрока");

        exitButton = new JButton();
        exitButton.setText("Выход");

        container.add(playTwoButton);
        container.add(exitButton);

        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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

        add(container);
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
