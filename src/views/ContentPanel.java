package views;

import models.Content;
import models.Experiment;
import tools.Out;
import tools.Utils;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private final String TAG = this.getClass().getSimpleName();

    private Dimension mDim;

    private int mItemSize;
    private int mMargin, mGutter;

    private Content mContent;

    /**
     * Constructor
     * @param height = NavPanel height
     */
    public ContentPanel(int height) {
        setLayout(null);

        // Set the background
        setBackground(Experiment.CONT_PANEL_BG_COLOR);
        setBorder(BorderFactory.createLineBorder(Experiment.NAV_PANEL_BORDER_COLOR, 1));

        //-- Set the size
        mItemSize = Utils.mm2px(Experiment.CONT_PANEL_ITEM_SIZE_mm);
        mMargin = Utils.mm2px(Experiment.ITEMS_MARGIN_mm);
        mGutter = Utils.mm2px(Experiment.ITEMS_GUTTER_mm);

        int width =
                (Experiment.N_ITEMS_PER_ROW * mItemSize)
                + ((Experiment.N_ITEMS_PER_ROW - 1) * mGutter)
                + (2 * mMargin);
        mDim = new Dimension(width, height);

        setPreferredSize(mDim);
    }

    public void updateContent(Content content) {
        mContent = content;
        setItems();

//        revalidate();
    }

    private void setItems() {
        removeAll();
        int nItems = mContent.nItems;
        Color itemColor = mContent.itemsColor;
        if (nItems > 0) {
            MoPoint itemPos = new MoPoint(mMargin, mMargin); // Initial pos
            int rowCount = 0;
            int rowItemCount = 0;
            while (nItems > 0) {
                JLabel itemLabel = new JLabel(" ");
                itemLabel.setOpaque(true);
                itemLabel.setBackground(itemColor);
                Out.d(TAG, itemColor);
                itemLabel.setBounds(itemPos.x, itemPos.y, mItemSize, mItemSize);

                add(itemLabel);
                rowItemCount++;

                nItems--;

                // Next pos
                if (rowItemCount == 5) { // row is full => next row
                    rowCount++;
                    int rowH = mItemSize + mGutter;
                    itemPos = new MoPoint(mMargin, mMargin + rowH);
                    rowItemCount = 0;
                } else {
                    itemPos.translate(mItemSize + mGutter, 0);
                }
            }
        }
    }

    /**
     * Add 1 item to the center
     */
    public void addCenterItem(Color itemColor) {
        int x = (mDim.width - mItemSize) / 2;
        int y = (mDim.height - mItemSize) / 2;

        JLabel itemLabel = new JLabel(" ");
        itemLabel.setOpaque(true);
        Out.d(TAG, mContent.itemsColor);
        itemLabel.setBackground(itemColor);
        itemLabel.setBounds(x, y, mItemSize, mItemSize);

//        add(itemLabel);
    }

    public Point getCenter() {
        return new Point((mDim.width - mItemSize) / 2, (mDim.height - mItemSize) / 2);
    }

    public int getWidth() {
        return mDim.width;
    }


}
