import control.ExperimentFrame;
import control.Server;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Server.get().start();
        SwingUtilities.invokeLater(() -> ExperimentFrame.get().startExperiment());
    }
}