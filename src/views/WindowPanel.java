package views;

import configs.Consts.*;
import models.Experiment;
import tools.Out;

import javax.swing.*;
import java.awt.*;

/**
 * NavPanel + ContentPanel
 */
public class WindowPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    private NavPanel mNavPanel;
    private ContentPanel mContPanel;

    public WindowPanel() {
//        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        setOpaque(true);
//        setBackground(COLORS.GRAY_900);
//
//        // Create and add the NavPanel
//        mNavPanel = new NavPanel(Experiment.N_TABS);
//        Out.d(TAG, mNavPanel.getPreferredSize());
//        add(mNavPanel);
//
//        // Create and add the ContentPanel
//        mContPanel = new ContentPanel(mNavPanel.getHeight());
//        Out.d(TAG, mContPanel.getPreferredSize());
////        mContPanel.addCenterItem(Experiment.ITEM_COLOR);
//        add(mContPanel);

    }

    public Point getContentPanelCenter() {
        return mContPanel.getCenter();
    }

}
