package views;

import models.Experiment;
import tools.Utils;

import javax.swing.*;
import java.awt.*;

public class ContextMenuPanel extends JPanel {

    private final int width, height;

    public ContextMenuPanel() {
        setLayout(null);

        // Set the background and the border
        setBackground(Experiment.MENU_PANEL_BG_COLOR);
//        setBorder(BorderFactory.createLineBorder(Experiment.NAV_PANEL_BORDER_COLOR, 1));

        // Set the dimension
        int menuW = Utils.mm2px(Experiment.MENU_W_mm);
        int itemH = Utils.mm2px(Experiment.MENU_ITEM_H_mm);
        int itemMargin = Utils.mm2px(Experiment.MENU_ITEM_MARGIN_mm);

        int twoMargins = 2 * itemMargin;

        width = menuW + twoMargins;
        height = Experiment.N_MENU_ITMES * itemH + twoMargins;

        setPreferredSize(new Dimension(width, height));
    }

}
