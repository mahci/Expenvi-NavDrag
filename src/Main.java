import controllers.Experimenter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Experimenter.get().startExperiment());
    }
}