package views;

import models.Experiment;
import tools.Out;
import tools.Texter;
import tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContextMenuPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    private int width, height;
    private final List<String> itemTexts;
    private List<MenuItem> items = new ArrayList<>();

    //-- Sizes
    private int margin, itemW, itemH;

    public static enum MENU_TYPE {
        CUT, PASTE
    }

    public ContextMenuPanel() {
        setLayout(null);

        // Set the background and the border
        setBackground(Experiment.MENU_PANEL_BG_COLOR);
        setBorder(BorderFactory.createLineBorder(Experiment.NAV_PANEL_BORDER_COLOR, 1));

        //-- Set the dimension
        itemW = Utils.mm2px(Experiment.MENU_ITEM_W_mm);
        itemH = Utils.mm2px(Experiment.MENU_ITEM_H_mm);
        margin = Utils.mm2px(Experiment.MENU_ITEM_MARGIN_mm);

        int twoMargins = 2 * margin;

        width = itemW + twoMargins;
        height = Experiment.N_MENU_ITMES * itemH + twoMargins;

        setPreferredSize(new Dimension(width, height));

        //-- Random dummy item texts (replace cut/paste based on the type)
        itemTexts = Texter.getRandLoremList(Experiment.N_MENU_ITMES);
    }

    public void create(MENU_TYPE type) {
        if (type == MENU_TYPE.CUT) itemTexts.set(Experiment.INDEX_CUT_OPTION, "Cut");
        if (type == MENU_TYPE.PASTE) itemTexts.set(Experiment.INDEX_PASTE_OPTION, "Paste");

        //-- Create and show menu items
        for (int i = 0; i < Experiment.N_MENU_ITMES; i++) {
            MenuItem mi = new MenuItem(itemTexts.get(i));
            int y = margin + i * itemH;
            mi.setBounds(margin, y, itemW, itemH);
            items.add(mi);
//            add(mi);
        }

        revalidate();

    }

    public void highlightItems(Point curPos) {
//        Out.d(TAG, "highlightItems", curPos);
        Point relPos = new Point(curPos.x - getX(), curPos.y - getY()); // Get the point relative to navPanel
        for (MenuItem item : items) {
            item.setHighlight(item.getBounds().contains(relPos));
        }

        revalidate();
    }

    @Override
    public void revalidate() {
        super.revalidate();

        // Add items
        if (items != null) {
            for (MenuItem item : items) {
                add(item);
            }
        }
    }
}
