package configs;

import java.awt.*;

public class Consts {

    //-- Display and related
    public static class DISP {
        public final static int MACBOOK_PPI = 127;
        public final static int APPLE_DISP_PPI = 109;
        public final static int BENQ_PPI = 89;

        public final static double MM_in_INCH = 25.4;
    }

    //-- Colors and related
    public static class COLORS {
        public final static Color GRAY_200 = Color.decode("#EEEEEE");
        public final static Color GRAY_400 = Color.decode("#BDBDBD");
        public final static Color GRAY_500 = Color.decode("#9E9E9E");
        public final static Color GRAY_800 = Color.decode("#424242");
        public final static Color GRAY_900 = Color.decode("#212121");

        public final static Color BLUE_50 = Color.decode("#E3F2FD");
        public final static Color BLUE_100 = Color.decode("#BBDEFB");
        public final static Color BLUE_900 = Color.decode("#0D47A1");
        public final static Color BLUE_900_ALPHA = new Color(13, 71, 161, 170);

        public final static Color PURPLE_100 = Color.decode("#E1BEE7");
        public final static Color PURPLE_900 = Color.decode("#4A148C");

        public final static Color INDIGO_900 = Color.decode("#1A237E");

        public final static Color GREEN_100 = Color.decode("#C8E6C9");
        public final static Color GREEN_200 = Color.decode("#A5D6A7");
        public final static Color GREEN_400 = Color.decode("#66BB6A");
        public final static Color GREEN_700 = Color.decode("#388E3C");
        public final static Color GREEN_900 = Color.decode("#1B5E20");
        public final static Color GREEN_A400 = Color.decode("#00E676");

        public final static Color YELLOW_100 = Color.decode("#FFF9C4");
        public final static Color YELLOW_800 = Color.decode("#F9A825");
        public final static Color YELLOW_900 = Color.decode("#F57F17");

        public final static Color ORANGE_400 = Color.decode("#FFA726");
        public final static Color ORANGE_200 = Color.decode("#FFCC80");
    }


    //-- Strings and related
    public static class STRINGS {
        public final static String SP = ";";
        public final static String MSP = "&";
        public static final String INTRO_MSSG = "Press SPACE to begin";
        public static final String END_MSSG = "Trials finished";
        public static final String MOOSE = "MOOSE";
        public final static String TECH = "TECH";
        public final static String CONFIG = "CONFIG";
        public final static String CONNECTION = "CONNECTION";
        public final static String LOG = "LOG";
        public final static String EXP_ID = "EXPID"; // Id for an experiment
        public final static String GENLOG = "GENLOG";
        public final static String BLOCK = "BLOCK";
        public final static String TRIAL = "TRIAL";
        public final static String TSK = "TASK"; // TSK to not confuse with TASK
        public final static String P_INIT = "P";

        public final static String GRAB = "GRAB";
        public final static String DRAG = "DRAG";
        public final static String RELEASE = "RELEASE";
        public final static String REVERT = "REVERT";

        public final static String DEMO_TITLE = "Welcome to the scrolling experiment!";
        public final static String DEMO_NEXT = "First, let's have a demo >";

        public final static String SHORT_BREAK_TEXT =
                "<html>Time for a quick break! To continue, press <B>ENTER</B>.</html>";

        public static final String DLG_BREAK_TITLE  = "Time for a break!";
        public static final String DLG_BREAK_TEXT   =
                "<html>When ready, press <B>BLUE + RED</B> keys to start the next block</html>";

        public final static String EXP_START_MESSAGE =
                "To begin the experiment, press SPACE.";
        public final static String END_EXPERIMENT_MESSAGE =
                "All finished! Thank you for participating in this experiment!";

        public final static String TRIAL_BEGIN = "Entered to Side Panel";
        public final static String TRIAL_END = "SPACE in Side Panel";

    }

}
