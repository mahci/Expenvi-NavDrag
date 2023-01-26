package views;

import configs.Consts.*;
import control.ExperimentFrame;
import models.Content;
import models.Experiment;
import models.Trial;
import tools.Out;
import tools.Sounder;
import tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class TrialPanel extends JLayeredPane {
    private final String TAG = this.getClass().getSimpleName();

    private NavPanel mNavPanel;
    private ContentPanel mContPanel;
    private ContextMenu mMenu;
    private DragObject mObject;
    private JPanel mColorIndic;

    private final MoPoint NAV_PANEL_POS = new MoPoint();
    private MoPoint mContPanelPos;

    private Point mGrabPos = new Point();
    private Point mCursorPos = new Point();

    MouseAdapter mMouseAdapter;

    //-- States
    private boolean mObjGrabbed, mObjCut, mObjPasted;

    //-- Actions
    private Action mCutAction, mPasteAction;

    // Experiment
    private final int mTargetTabIndex = Utils.randInt(0, Experiment.N_TABS);
    private final int mStartTabIndex = Utils.randIntDiffer(mTargetTabIndex, 0, Experiment.N_TABS);
    private Color mTargetColor;
    private Content[] contents;
    private int mActiveTabIndex;
    private int mUnderCursorTabIndex;
    private int mHoverTabIndex;
    private Timer tabTimer;
    private long dragStart;

    public TrialPanel(Trial trial) {
        setOpaque(true);
        setBackground(Color.WHITE);
        setLayout(null);

        setAdapters();
        setActions();

        addPanels();
        addObject();

        setTimers();

        dragStart = 0;
        mColorIndic.setVisible(false);
        mActiveTabIndex = mStartTabIndex;
        activateTabContent();
    }

    private void setAdapters() {
        mMouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                Out.d(TAG, "mousePressed", e.getSource().getClass());
                // Pressed on the Object
                if (e.getSource().getClass().equals(DragObject.class)) {
                    Out.d(TAG, "mousePressed", "Press");
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        grabObject();
                    }

                    if (e.getButton() == MouseEvent.BUTTON3) {
                        if (!mObjCut) showContextMenu(ContextMenuPanel.MENU_TYPE.CUT);
                        else showContextMenu(ContextMenuPanel.MENU_TYPE.PASTE);
                    }
                }

                // Pressed anywhere inside Nav
                if (e.getSource().getClass().equals(NavPanel.class)) {
                    // Check which tab sursor is over and activate it
                    mUnderCursorTabIndex = mNavPanel.getTabUnderCursor(getCursorPos());
                    if (mUnderCursorTabIndex != -1) {

                        mActiveTabIndex = mUnderCursorTabIndex;
                        activateTabContent();
                    }

                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {
//                Out.d(TAG, "mouseDragged", getLayer(mNavPanel));
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getSource().getClass().equals(DragObject.class)) {
                        dragObject();
                    }
                }

            }
        };
    }

    private void setActions() {
        mCutAction = new AbstractAction(STRINGS.CUT) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the color indicator
                mColorIndic.setBackground(mTargetColor);
                mColorIndic.setVisible(true);

                // Hide the object
                mObject.setVisible(false);
            }
        };
    }

    private void setTimers() {
        tabTimer = new Timer(Experiment.TIME_TAB_ACTIVATION, arg0 -> {
            if (mUnderCursorTabIndex == mHoverTabIndex) { // Check if still on the highlighted tab
                mActiveTabIndex = mHoverTabIndex;
                activateTabContent();
            }
        });
        tabTimer.setRepeats(false); // Only execute once
    }

    private void addPanels() {
        //-- Set random position for panels
        NAV_PANEL_POS.x = Utils.randInt(200, 1500);
        NAV_PANEL_POS.y = Utils.randInt(200, 800);

        //-- Add nav panel
        mNavPanel = new NavPanel(Experiment.N_TABS);
        Dimension navSize = mNavPanel.getPreferredSize();
        mNavPanel.setBounds(NAV_PANEL_POS.x, NAV_PANEL_POS.y, navSize.width, navSize.height);
        mNavPanel.addMouseListener(mMouseAdapter);
        add(mNavPanel, JLayeredPane.DEFAULT_LAYER);

        //-- Shuffle the list of colors and set start = target color
        ArrayList<Color> colors = new ArrayList<>(Experiment.COLOR_LIST);
        Collections.shuffle(colors);
        mTargetColor = colors.get(mStartTabIndex); // Random because of shuffle
        colors.set(mTargetTabIndex, mTargetColor);

        //-- Generate Contents
        contents = new Content[Experiment.N_TABS];
        for (int i = 0; i < contents.length; i++) {
            Out.d(TAG, i, colors.get(i));
            contents[i] = new Content(i == mStartTabIndex, colors.get(i));
        }

        //-- Add content panel
        mContPanelPos = NAV_PANEL_POS.trans(navSize.width, 0);
        mContPanel = new ContentPanel(navSize.height);
        mContPanel.setBounds(mContPanelPos.x, mContPanelPos.y, mContPanel.getWidth(), navSize.height);
        mContPanel.addMouseListener(mMouseAdapter);
        add(mContPanel, JLayeredPane.DEFAULT_LAYER);

        //-- TODO: Only add when in Mouse mode
        mMenu = new ContextMenu();
//        mMenu.setInvoker(this);
//        mContextMenu = new ContextMenuPanel();
//        mContextMenu.addMouseListener(mMouseAdapter);
//        mContextMenu.addMouseMotionListener(mMouseAdapter);

        // Set the initial tab

        //-- Set up the color indicator
        int clrIndH = Utils.mm2px(Experiment.COLOR_INDIC_H_mm);
        int clrIndMargin = Utils.mm2px(Experiment.TABS_GUTTER_mm);
        mColorIndic = new JPanel();
        mColorIndic.setBounds(mContPanelPos.x, mContPanelPos.y - (clrIndH + clrIndMargin),
                mContPanel.getWidth() / 4, clrIndH);
        add(mColorIndic);
    }

    private void addObject() {
        int objSize = Utils.mm2px(Experiment.CONT_PANEL_ITEM_SIZE_mm);

        // Find the position (contentPanel center)
        int objX = mContPanel.getX() + (mContPanel.getWidth() - objSize) / 2;
        int objY = mContPanel.getY() + (mContPanel.getHeight() - objSize) / 2;

        // Create and add the object
        mObject = new DragObject(objSize, mTargetColor, objX, objY);
        add(mObject, JLayeredPane.PALETTE_LAYER);

        mObject.addMouseListener(mMouseAdapter);
        mObject.addMouseMotionListener(mMouseAdapter);
        mObject.setVisible(false); // Default
    }

    /**
     * Activate the tab with the current index
     */
    private void activateTabContent() {
        Out.d(TAG, "Activate", mActiveTabIndex);
        if (mActiveTabIndex < contents.length) {
            Out.d(TAG, contents[mActiveTabIndex]);
            mContPanel.updateContent(contents[mActiveTabIndex]);
            mNavPanel.activateTab(mActiveTabIndex);

            // Show Object only on start tab index
            if (mActiveTabIndex == mStartTabIndex) mObject.setVisible(true);
            else mObject.setVisible(false);

            revalidate();
            repaint(); // Required always show the obj. on top of the tabs
        }

    }

    /**
     * Get the cursor position relative to the panel
     * @return MoPoint
     */
    protected Point getCursorPos() {
        Point result = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(result, this);

        return result;
    }

    public void move() {
//        if (mObject != null) {
//            Point mousePos = mObject.getMousePosition();
//            mCursorInObject = mousePos != null;
//            Out.d(TAG, "move", mObject.getLocation(), mousePos, mCursorInObject);
//        }
    }

    public void grabObject() {
        mGrabPos = getCursorPos();
        mObjGrabbed = true;

        mNavPanel.setTabActivation(true);

        mObject.setBorder(BorderFactory.createLineBorder(Experiment.OBJECT_BORDER__COLOR, 3));
    }

    public void dragObject() {
        // Start timing from the first drag
        if (dragStart == 0) dragStart = Utils.nowMillis();

        mCursorPos = getCursorPos();

        final int dX = mCursorPos.x - mGrabPos.x;
        final int dY = mCursorPos.y - mGrabPos.y;

//        Out.d(TAG, "Moving the object", dX, dY);
        mObject.changePosition(dX, dY);

        mGrabPos = mCursorPos;

        mUnderCursorTabIndex = mNavPanel.getTabUnderCursor(mCursorPos);
        if (mUnderCursorTabIndex != -1) {
            mHoverTabIndex = mUnderCursorTabIndex;
            tabTimer.restart(); // Go go go!
        }


        repaint();

    }

    public void release() {
        Out.d(TAG, mActiveTabIndex, mTargetTabIndex);
        if (mObjGrabbed) {
            if (mActiveTabIndex == mTargetTabIndex) {
                double trialTime = (Utils.nowMillis() - dragStart) / 1000.0;
                Out.d("Time", String.format("Trial time = %.3f", trialTime));
                Sounder.playHit();
                ExperimentFrame.get().nextTrial();
            } else {
                Sounder.playMiss();
                ExperimentFrame.get().nextTrial();
            }

            mObjGrabbed = false;
            mNavPanel.setTabActivation(false);
        }
    }

    public void navUp() {
        Out.d(TAG, "NavUP");
        if (mActiveTabIndex > 0) mActiveTabIndex--;
        else if (mActiveTabIndex == 0) mActiveTabIndex = Experiment.N_TABS - 1; // Start from the bottom

        activateTabContent();
        Out.d(TAG, mActiveTabIndex, mTargetTabIndex);
    }

    public void navDown() {
        Out.d(TAG, "NavUP");
        if (mActiveTabIndex < Experiment.N_TABS - 1) mActiveTabIndex++;
        else if (mActiveTabIndex == Experiment.N_TABS - 1) mActiveTabIndex = 0; // Start from the top

        activateTabContent();
        Out.d(TAG, mActiveTabIndex, mTargetTabIndex);
    }

    public void showContextMenu(ContextMenuPanel.MENU_TYPE type) {
        Out.d(TAG, "Showing context menu");
//        remove(mContextMenu);
//
//        Dimension menuDim = mContextMenu.getPreferredSize();
        Point curPos = getCursorPos();
//        mContextMenu.setBounds(curPos.x, curPos.y, menuDim.width, menuDim.height);
//        mContextMenu.create(type);
//
//        add(mContextMenu, JLayeredPane.POPUP_LAYER);
//
//        repaint();

//        mMenu.setLocation();
//        mMenu.show(this, curPos.x, curPos.y);

        mMenu.show(this, mCutAction, curPos);
    }

}
