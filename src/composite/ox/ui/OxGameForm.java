package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class OxGameForm extends JFrame {
    private JPanel currentPage = null;

    public OxGameForm() {}

    public void setup() {
        selectPage(OxMainMenu.class);

        setLayout(new BorderLayout());
        setBackground(Color.black);

        setSize(500, 400);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void selectPage(Class<? extends JPanel> pageClass) {
        if(pageClass.isInstance(currentPage))
            return;

        JPanel page;

        try {
            page = pageClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        this.currentPage = page;
        setContentPane(this.currentPage);
    }
}
