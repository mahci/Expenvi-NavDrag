package models;

import configs.Consts.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Experiment {

    // Configs -----------------------------------------------------------------
    //-- Sizes
//    public static final int NAV_PANEL_W_mm = 200; // Width of Nav panel (mm)
    public static final int TABS_H_mm = 6; // Tab height (mm)
    public static final int TABS_W_mm = 36; // Tab width (mm)
    public static final int TABS_GUTTER_mm = 1; // Gutter between tabs (mm)
    public static final int TABS_MARGIN_mm = 3; // All around margin of tabs (mm)
    public static final int MENU_ITEM_H_mm = 5; // Menu item height (mm) [macOS Finder]
    public static final int MENU_ITEM_W_mm = 50; // Menu item width (mm) [macOS Finder]
    public static final int MENU_ITEM_MARGIN_mm = 1; // L/R margin of menu items (mm) [macOS Finder]

//    public static final int CONT_PANEL_W_mm = 150; // Width of Content panel (mm)
    public static final int CONT_PANEL_ITEM_SIZE_mm = 14; // Size of (square) items (mm)
    public static final int ITEMS_GUTTER_mm = 5; // Gutter between tabs (mm)
    public static final int ITEMS_MARGIN_mm = 10; // All around margin of tabs (mm)

    //-- Times
    // Rouch est. from macOS
    public static final int TIME_TAB_ACTIVATION = 700; // Time to stay on the tab to activate it (ms)

    //-- Numbers & Indexes
    public static final int N_TABS = 10; // Number of 'tabs' (in the Nav panel)
    public static final int N_ITEMS_PER_ROW = 5; // Number of 'tabs' (in the Nav panel)
    public static final int N_CONTENT_ITEMS_MAX = 24; // Maximum number of items in the content panel
    public static final int N_MENU_ITMES = 18; // Total number of items in the context menu
    public static final int INDEX_CUT_OPTION = 8; // Index of "Cut" option in the context menu (begin from top = 0)
    public static final int INDEX_PASTE_OPTION = 1; // Index of "Paste" item in the context menu (begin from top = 0)

    //-- Colors
    public static Color NAV_PANEL_BG_COLOR = COLORS.BLUE_GRAY_50;
    public static Color NAV_PANEL_BORDER_COLOR = COLORS.GRAY_800;
    public static Color CONT_PANEL_BG_COLOR = COLORS.GRAY_100;
    public static Color MENU_PANEL_BG_COLOR = NAV_PANEL_BG_COLOR;
    public static Color TAB_DEFAULT_COLOR = COLORS.BLUE_GRAY_100;
    public static Color TAB_ACTIVE_COLOR = COLORS.BLUE_GRAY_500;
    public static Color TAB_HIGHLIGHT_COLOR = COLORS.BLUE_700;
    public static Color OBJECT_BORDER__COLOR = COLORS.GRAY_900;
    public static List<Color> COLOR_LIST = new ArrayList<>();

    //-- Actions
    public static enum ACTION {
        CUT, PASTE
    }

    // -------------------------------------------------------------------------

    public Experiment() {
        // Add N_TABS distincitve colors to the list
        COLOR_LIST.add(COLORS.RED_300);
        COLOR_LIST.add(COLORS.PINK_900);
        COLOR_LIST.add(COLORS.PURPLE_300);
        COLOR_LIST.add(COLORS.INDIGO_300);
        COLOR_LIST.add(COLORS.BLUE_300);
        COLOR_LIST.add(COLORS.CYAN_300);
        COLOR_LIST.add(COLORS.TEAL_900);
        COLOR_LIST.add(COLORS.LIME_600);
        COLOR_LIST.add(COLORS.ORANGE_300);
        COLOR_LIST.add(COLORS.BROWN_300);
    }


}
