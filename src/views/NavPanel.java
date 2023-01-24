package views;

import models.Experiment;
import tools.Out;
import tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class NavPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    private final List<Tab> mTabs = new ArrayList<>();
    private final int width, height;

    private boolean mActivatingTabs;

    private MouseAdapter mTabMouseAdapter;

    private int mTabUnderCursorId = -1;
    private int mAcitveTabIndex = -1;
    private int mHoverTabIndex = -1;

    public NavPanel(int nTabs) {
        setLayout(null);

        // Set the background and the border
        setBackground(Experiment.NAV_PANEL_BG_COLOR);
        setBorder(BorderFactory.createLineBorder(Experiment.NAV_PANEL_BORDER_COLOR, 1));

        // Set the dimension
        int tabH = Utils.mm2px(Experiment.TABS_H_mm);
        int tabW = Utils.mm2px(Experiment.TABS_W_mm);
        int margin = Utils.mm2px(Experiment.TABS_MARGIN_mm);
        int twoMargin = 2 * margin;
        int gutter = Utils.mm2px(Experiment.TABS_GUTTER_mm);
        int tabsH = nTabs * tabH + (nTabs - 1) * gutter;
        width = tabW + twoMargin;
        height = tabsH + twoMargin;

        setPreferredSize(new Dimension(width, height));

        //-- Create tabs
        for (int i = 0; i < nTabs; i++) {
            Tab tab = new Tab(i);

            tab.setOpaque(true);
            tab.setBackground(Experiment.TAB_DEFAULT_COLOR);

            mTabs.add(tab);
        }


        // Add tabs to the layout
        for (int i = 0; i < mTabs.size(); i++) {
            int y = margin + i * (tabH + gutter);
            mTabs.get(i).setBounds(margin, y, tabW, tabH);

            add(mTabs.get(i));
        }

    }

    public void setTabActivation(boolean status) {
        mActivatingTabs = status;
    }


    public void activateTab(int tabIndex) {
        mAcitveTabIndex = tabIndex;
        revalidate();
    }

    /**
     * Find out if there is a tab under the cursor
     * @param curPos Cursor position (rel. to the TrialPanel)
     * @return Tab index (-1 if none found)
     */
    public int getTabUnderCursor(Point curPos) {
        Point relPos = new Point(curPos.x - getX(), curPos.y - getY()); // Get the point relative to navPanel
        for (Tab tab : mTabs) {
            if (tab.getBounds().contains(relPos)) {
                mHoverTabIndex = tab.id;
                revalidate();
                return tab.id;
            }
        }

        return -1;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void revalidate() {
        super.revalidate();

        if (mTabs != null) {
            for (Tab tab : mTabs) {
                tab.setBackground(Experiment.TAB_DEFAULT_COLOR);
            }

            if (mAcitveTabIndex > -1) mTabs.get(mAcitveTabIndex).setBackground(Experiment.TAB_ACTIVE_COLOR);
            if (mHoverTabIndex > -1) mTabs.get(mHoverTabIndex).setBackground(Experiment.TAB_HIGHLIGHT_COLOR);
        }
    }

}
