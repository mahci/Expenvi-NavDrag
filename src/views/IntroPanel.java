package views;

import control.ExperimentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static configs.Consts.*;
import static tools.Keys.KS_SPACE;

public class IntroPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    public IntroPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);

        add(Box.createRigidArea(new Dimension(0, 600)));

        // Instruction label
        JLabel instructLabel = new JLabel(STRINGS.INTRO_MSSG, JLabel.CENTER);
        instructLabel.setAlignmentX(CENTER_ALIGNMENT);
        instructLabel.setFont(new Font("Sans", Font.BOLD, 50));
        instructLabel.setForeground(COLORS.GRAY_900);
        add(instructLabel);

        add(Box.createRigidArea(new Dimension(0, 200)));

        getInputMap().put(KS_SPACE, KeyEvent.VK_SPACE);
        getActionMap().put(KeyEvent.VK_SPACE, ExperimentFrame.get().startTrialsAction);

    }

}
