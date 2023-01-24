package models;

import tools.Utils;

import java.awt.*;

public class Content {
    public int nItems;
    public Color itemsColor;
    public boolean isEmpty; // Is this the content with only 1 item?

    /**
     * Constructor
     * @param itemsColor Color of the items
     */
    public Content(boolean isEmpty, Color itemsColor) {
        this.isEmpty = isEmpty;

        if (isEmpty) nItems = 0;
        else nItems = Utils.randInt(1, Experiment.MAX_N_ITEMS);

        this.itemsColor = itemsColor;
    }

    @Override
    public String toString() {
        return "Content{" +
                "nItems=" + nItems +
                ", itemsColor=" + itemsColor +
                ", singleItemed=" + isEmpty +
                '}';
    }
}
