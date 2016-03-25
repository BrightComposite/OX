package composite.ox.launch;

import javax.swing.*;
import composite.ox.ui.OxGameForm;

public class Launch {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new OxGameForm().setup();
                    }
                }
        );
    }
}
