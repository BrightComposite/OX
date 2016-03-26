package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class OxGameForm extends JFrame {
    private OxPage currentPage = null;

    public OxGameForm() {}

    public void setup() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(500, 400);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(Color.black);

        selectPage(OxMainMenu.class);
        setVisible(true);
    }

    void selectPage(Class<? extends OxPage> pageClass) {
        if(pageClass.isInstance(currentPage))
            return;

        OxPage page;

        try {
            page = pageClass.getDeclaredConstructor(OxGameForm.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        this.currentPage = page;
        setContentPane(this.currentPage);
        validate();
        repaint();
    }
}
