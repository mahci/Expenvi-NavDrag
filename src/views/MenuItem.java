package views;

import models.Experiment;
import tools.Out;
import tools.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuItem extends JLabel {

    private final int LR_PADDING = Utils.mm2px(2); // 2 mm padding on L/R
    private final int TB_PADDING = Utils.mm2px(5); // 1 mm padding on T/B

    public MenuItem(String text) {
        super(text);
        setOpaque(true); // Default (set to true for highlight)
//        setBackground(Experiment.TAB_HIGHLIGHT_COLOR);
        Out.d("Item", TB_PADDING);
        setBorder(new EmptyBorder(TB_PADDING, LR_PADDING, TB_PADDING, LR_PADDING));
    }

    public void setHighlight(boolean enable) {
        if (enable) {
            setBackground(Experiment.TAB_HIGHLIGHT_COLOR);
            setForeground(Color.WHITE);
        } else {
            setBackground(null);
            setForeground(Color.BLACK);
        }
    }
}
