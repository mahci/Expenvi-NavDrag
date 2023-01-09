package views;

import models.Experiment;
import tools.Out;
import tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class NavPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    private List<JLabel> tabs = new ArrayList<>();

    public NavPanel(int nTabs) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set the background and the border
        setBackground(Experiment.NAV_PANEL_BG_COLOR);
        setBorder(BorderFactory.createLineBorder(Experiment.NAV_PANEL_BORDER_COLOR, 2));

        // Set the dimension
        int tabH = Utils.mm2px(Experiment.TABS_H_mm);
        int tabW = Utils.mm2px(Experiment.TABS_W_mm);
        int margin = Utils.mm2px(Experiment.TABS_MARGIN_mm);
        int twoMargin = 2 * margin;
        int gutter = Utils.mm2px(Experiment.TABS_GUTTER_mm);
        int allTabsH = nTabs * tabH + (nTabs - 1) * gutter;

        Dimension dim = new Dimension(
                twoMargin + tabW,
                twoMargin + allTabsH);

        Dimension tabDim = new Dimension(tabW, tabH);

        Out.d(TAG, dim);
        Out.d(TAG, "Tab Dim", tabW);
        setSize(dim);
//        setPreferredSize(dim);

        // Create tabs
        for (int i = 0; i < nTabs; i++) {
            JLabel tabLabel = new JLabel();
//            tabLabel.setPreferredSize(tabDim);
            tabLabel.setMaximumSize(tabDim);
            tabLabel.setOpaque(true);
            tabLabel.setBackground(Experiment.TAB_DEFAULT_COLOR);
            tabLabel.addMouseListener(new TabHandler());

            tabs.add(tabLabel);
        }

        // Put components together
        Component gutterSpace = Box.createRigidArea(new Dimension(0, gutter));
        Component marginSpace = Box.createRigidArea(new Dimension(0, margin));

        add(marginSpace);

        for (int i = 0; i < tabs.size() - 1; i++) {
            add(tabs.get(i));
            add(gutterSpace);
        }
        add(tabs.get(tabs.size() - 1)); // Last tab

        add(marginSpace);

    }

    // ----------------------------------------------------------------------
    private class TabHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Change the color...
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
