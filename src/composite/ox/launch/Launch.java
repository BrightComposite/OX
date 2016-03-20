package composite.ox.launch;

import javax.swing.*;
import composite.ox.ui.OxMainMenuForm;

public class Launch {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        OxMainMenuForm form = new OxMainMenuForm();
                        form.setup();
                    }
                }
        );
    }
}
