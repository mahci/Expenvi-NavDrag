package views;

import models.Experiment;
import models.Trial;
import tools.Out;

import javax.swing.*;
import java.awt.*;

public class TrialPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    NavPanel mNavPanel;

    public TrialPanel(Trial trial) {
        setOpaque(true);
        setBackground(Color.WHITE);
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(null);

        mNavPanel = new NavPanel(Experiment.N_TABS);
        mNavPanel.setLocation(200, 100);
//        mNavPanel.setBounds(200, 100, 500, 400);

//        add(Box.createHorizontalGlue());
//        add(Box.createRigidArea(new Dimension(200, 0)));
        add(mNavPanel);
//        add(Box.createHorizontalGlue());
    }
}
