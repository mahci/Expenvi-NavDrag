package tools;

import controllers.Experimenter;
import models.Experiment;

import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static configs.Consts.*;


public class Utils {
    private final static String NAME = "Utils/";
    /*-------------------------------------------------------------------------------------*/

    private final static List<Character> LETTERS = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z');

    /**
     * Returns a random int between the min (inclusive) and the bound (exclusive)
     * @param min Minimum (inclusive)
     * @param bound Bound (exclusive)
     * @return Random int
     */
    public static int randInt(int min, int bound) {
        if (bound <= min) return min;
        else return ThreadLocalRandom.current().nextInt(min, bound);
    }

    /**
     * Get a random element from any int array
     * @param inArray input int[] array
     * @return int element
     */
    public static int randElement(int[] inArray) {
        return inArray[randInt(0, inArray.length)];
    }

    /**
     * Get a random element from a List
     * @param inList List
     * @return Object element
     */
    public static Object randElement(List inList) {
        return inList.get(randInt(0, inList.size()));
    }

    /**
     * NOT on 0/1 (o => 1, 1 => 0)
     * @return Int
     */
    public static int intNOT(int i) {
        return (i == 1) ? 0 : 1;
    }

    /**
     * mm to pixel
     * @param mm - millimeters
     * @return equivalant in pixels
     */
    public static int mm2px(double mm) {
        String TAG = NAME + "mm2px";

        return (int) ((mm / DISP.MM_in_INCH) * Experimenter.DPI);
    }

    /**
     * mm to pixel
     * @param px - pixels
     * @return equivalant in mm
     */
    public static double px2mm(double px) {
        String TAG = NAME + "px2mm";

        return (px / Experimenter.DPI) * DISP.MM_in_INCH;
    }

    /**
     * Generate a random permutation of {0, 1, ..., len - 1}
     * @param len - length of the permutation
     * @return Random permutation
     */
    public static List<Integer> randPerm(int len) {
        String TAG = NAME + "randPerm";

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);

        return indexes;
    }

    /**
     * Get a random letter (always uppercase)
     * @return A, B, C, ..., Z
     */
    public static char randLetter() {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return abc.charAt(randInt(0, abc.length()));
    }

    /**
     * Get a permutation of letters with length, excluding a letter
     * @param len Lenght of the perm (< 25)
     * @param c Letter to exclude
     * @return List of char
     */
    public static List<Character> randLetterPermExcl(int len, char c) {
        List<Character> list = new ArrayList<>(LETTERS);
        list.remove((Object) c);
        Collections.shuffle(list);

        return list.subList(0, len); // List is shuffled, so this is random
    }

    /**
     * True -> 1, False -> 0
     * @param b Boolean
     * @return Int
     */
    public static int bool2Int(boolean b) {
        return b ? 1 : 0;
    }

    /**
     * Get the time in millis
     * @return Long timestamp
     */
    public static long nowMillis() {
//        return Calendar.getInstance().getTimeInMillis();
        return System.currentTimeMillis();
    }

    /**
     * Get the current date+time up to minutes
     * @return LocalDateTime
     */
    public static String nowDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_hh-mm");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String nowDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String str(Line2D.Double line) {
        return line.x1 + "," + line.y1 + "--" + line.x2 + "," + line.y2;
    }

    public static String str(Area area) {
        return "Area" + area.getBounds().toString();
    }


    public static <T> T last(List<T> list) {
        return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
    }

    /**
     * Print Path2D.Double coords
     * @param path Path2D.Double
     */
    private void printPath(Path2D.Double path) {
        final String TAG = NAME + "printPath";
        Out.d(TAG, "Printing Path...");
        double[] coords = new double[4];
        PathIterator pi = path.getPathIterator(null);
        Out.d(TAG, pi.isDone());
        while(!pi.isDone()) {
            pi.currentSegment(coords);
            Out.d(TAG, Arrays.toString(coords));
            pi.next();
        }
    }

    /**
     * Create a dir if it doesn't exist
     * @param dirPath Path to the dir
     * @return 0 if success, 1 if failed
     */
    public static void createDirIfNotExisted(Path dirPath) {

        if (!Files.isDirectory(dirPath)) {
            try {
                Files.createDirectory(dirPath);
                return;
            } catch (IOException ioe) {
//                Experimenter.get().showDialog(
//                        "Problem in creating directory: \n" +
//                        dirPath);
                ioe.printStackTrace();
            }
        }

    }

    /**
     * Check if a file is empty
     * @param file File
     * @return True (empty), False (not empty)
     */
    public static boolean isFileEmpty(File file) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the names of the class properties as a SP-delimeited String
     * @param clazz Class
     * @return String
     */
    public static String classPropsNames(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.joining(STRINGS.SP));
    }

}
