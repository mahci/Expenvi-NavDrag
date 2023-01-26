package tools;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonter {

    // Fonts
//    public static Font SERIF = Font.getFont(Font.SERIF);
//    public static Font DIALOG = new Font(Font.DIALOG,  Font.PLAIN, 12);
    public static Font MENU;
//    public static Font STATUS = new Font(Font.DIALOG,  Font.PLAIN, 18);
//    public static Font NUMBER_FONT = new Font(Font.DIALOG,  Font.PLAIN, 60);
//    public static Font BUTTON = new Font(Font.SERIF,  Font.PLAIN, 14);
//    public static Font BUTTON_BOLD = new Font(Font.SERIF,  Font.BOLD, 18);

    // Sizes and spacings
    public static final float TEXT_FONT_SIZE = 20.5f;
    public static final float TEXT_LINE_SPACING = 0.193f;

    // Run-time
        static {
            try {
                File sfRegFile = new File("./res/SF-Regular.ttf");
                File sfLightFile = new File("./res/SF-Light.ttf");

                MENU = Font.createFont(Font.TRUETYPE_FONT, sfRegFile).deriveFont(14f);

            } catch (FontFormatException | IOException e) {
                Out.d("FONTS", "Can't load the font file!");
                e.printStackTrace();
            }
        }
}
