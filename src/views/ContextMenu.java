package views;

import models.Experiment;
import tools.Fonter;
import tools.Texter;
import tools.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static configs.Consts.*;

public class ContextMenu extends JPopupMenu {

    private final int MARGIN = Utils.mm2px(1);

    private final List<String> mItemTexts;
    private List<JMenuItem> mItems = new ArrayList<>();

    private JMenuItem mCutItem, mPasteItem;

    public ContextMenu() {

        // Paddings
        setBorder(new EmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));

        //-- Random dummy item texts (replace cut/paste based on the type)
        mItemTexts = Texter.getRandLoremList(Experiment.N_MENU_ITMES);

        //-- Create items
        for (int i = 0; i < Experiment.N_MENU_ITMES; i++) {
            JMenuItem item = new JMenuItem(mItemTexts.get(i));
            item.setBorder(new EmptyBorder(MARGIN, -5, MARGIN, 0));
            item.setFont(Fonter.MENU);

            mItems.add(item);
        }

        //-- Create special items
        mCutItem = new JMenuItem("Cut");
        mCutItem.setBorder(new EmptyBorder(MARGIN, -5, MARGIN, 0));
        mCutItem.setFont(Fonter.MENU);
        mPasteItem = new JMenuItem("Paste");
        mPasteItem.setBorder(new EmptyBorder(MARGIN, -5, MARGIN, 0));
        mPasteItem.setFont(Fonter.MENU);

        int itemW = Utils.mm2px(Experiment.MENU_ITEM_W_mm);
        int itemH = Utils.mm2px(Experiment.MENU_ITEM_H_mm);
        int margin = Utils.mm2px(Experiment.MENU_ITEM_MARGIN_mm);

        int twoMargins = 2 * margin;

        int width = itemW + twoMargins;
        int height = Experiment.N_MENU_ITMES * itemH + twoMargins;

        setPreferredSize(new Dimension(width, height));
    }

    public void assignCutAction(Action cutAction) {
        mCutItem.setAction(cutAction);
    }

    public void assignPasteAction(Action pasteAction) {
        mPasteItem.setAction(pasteAction);
    }

    /**
     * Show the menu
     * @param invoker The component to show in relation to
     * @param location Coordinates on the screen to show the menu (in relation to the invker)
     */
    public void show(Component invoker, Action action, Point location) {
        // Replace with Cut or Paste + assing the action
        switch ((String) action.getValue(Action.NAME)) {

            case STRINGS.CUT -> {
                mCutItem.setAction(action);
                mItems.set(Experiment.INDEX_CUT_OPTION, mCutItem);
            }

            case STRINGS.PASTE -> {
                mPasteItem.setAction(action);
                mItems.set(Experiment.INDEX_PASTE_OPTION, mPasteItem);
            }
        }

        // Add the items to the menu
        for (JMenuItem item : mItems) {
            add(item);
        }

        // Show the menu
        show(invoker, location.x, location.y);
    }

}
