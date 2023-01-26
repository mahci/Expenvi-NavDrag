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
        public final static Color GRAY_50 = Color.decode("#FAFAFA");
        public final static Color GRAY_100 = Color.decode("#F5F5F5");
        public final static Color GRAY_200 = Color.decode("#EEEEEE");
        public final static Color GRAY_400 = Color.decode("#BDBDBD");
        public final static Color GRAY_500 = Color.decode("#9E9E9E");
        public final static Color GRAY_800 = Color.decode("#424242");
        public final static Color GRAY_900 = Color.decode("#212121");

        public final static Color BLUE_GRAY_50 = Color.decode("#ECEFF1");
        public final static Color BLUE_GRAY_100 = Color.decode("#CFD8DC");
        public final static Color BLUE_GRAY_200 = Color.decode("#B0BEC5");
        public final static Color BLUE_GRAY_300 = Color.decode("#90A4AE");
        public final static Color BLUE_GRAY_500 = Color.decode("#607D8B");

        public final static Color BLUE_50 = Color.decode("#E3F2FD");
        public final static Color BLUE_100 = Color.decode("#BBDEFB");
        public final static Color BLUE_200 = Color.decode("#90CAF9");
        public final static Color BLUE_300 = Color.decode("#64B5F6");
        public final static Color BLUE_500 = Color.decode("#2196F3");
        public final static Color BLUE_700 = Color.decode("#1976D2");
        public final static Color BLUE_800 = Color.decode("#1565C0");
        public final static Color BLUE_900 = Color.decode("#0D47A1");
        public final static Color BLUE_900_ALPHA = new Color(13, 71, 161, 170);

        public final static Color PURPLE_100 = Color.decode("#E1BEE7");
        public final static Color PURPLE_300 = Color.decode("#BA68C8");
        public final static Color PURPLE_900 = Color.decode("#4A148C");

        public final static Color PINK_900 = Color.decode("#880E4F");

        public final static Color INDIGO_300 = Color.decode("#7986CB");
        public final static Color INDIGO_900 = Color.decode("#1A237E");

        public final static Color GREEN_100 = Color.decode("#C8E6C9");
        public final static Color GREEN_200 = Color.decode("#A5D6A7");
        public final static Color GREEN_300 = Color.decode("#81C784");
        public final static Color GREEN_400 = Color.decode("#66BB6A");
        public final static Color GREEN_700 = Color.decode("#388E3C");
        public final static Color GREEN_900 = Color.decode("#1B5E20");
        public final static Color GREEN_A400 = Color.decode("#00E676");

        public final static Color YELLOW_100 = Color.decode("#FFF9C4");
        public final static Color YELLOW_300 = Color.decode("#FFF176");
        public final static Color YELLOW_800 = Color.decode("#F9A825");
        public final static Color YELLOW_900 = Color.decode("#F57F17");

        public final static Color LIME_600 = Color.decode("#C0CA33");
        public final static Color LIME_900 = Color.decode("#827717");

        public final static Color ORANGE_300 = Color.decode("#FFB74D");
        public final static Color ORANGE_400 = Color.decode("#FFA726");

        public final static Color AMBER_900 = Color.decode("#FF6F00");

        public final static Color RED_300 = Color.decode("#E57373");

        public final static Color DEEP_PURPLE_300 = Color.decode("#9575CD");

        public final static Color CYAN_300 = Color.decode("#4DD0E1");

        public final static Color TEAL_300 = Color.decode("#4DB6AC");
        public final static Color TEAL_900 = Color.decode("#004D40");

        public final static Color BROWN_300 = Color.decode("#A1887F");

    }


    //-- Strings and related
    public static class STRINGS {
        public final static String SP = ";";
//        public final static String MSP = "&";

        public static final String INTRO = "INTRO";
        public final static String END = "END";

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

        //-- Actions
        public final static String DRAG = "DRAG";
        public final static String GRAB = "GRAB";
        public final static String RELEASE = "RELEASE";
        public final static String REVERT = "REVERT";
        public final static String NAV = "NAV";
        public final static String UP = "UP";
        public final static String DOWN = "DOWN";

        public final static String CUT = "Cut";
        public final static String PASTE = "Past";

        //-- Messages
        public static final String INTRO_MSSG = "Press SPACE to begin";
        public static final String END_MSSG = "Trials finished";

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
