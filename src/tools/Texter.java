package tools;

import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Texter {

//    private static final String LOREM =
//            "Sed efficitur aliquam dolor " +
//            "lobortis aliquam lacus sollicitudin nec " +
//            "Suspendisse rhoncus eros sed risus laoreet " +
//            "vulputate tellus convallis Nunc lobortis quam turpis" +
//            "quis dignissim nunc vehicula " +
//            "Phasellus tortor sem Cras sodales pulvinar venenatis " +
//            "Donec viverra feugiat ex in dapibus " +
//            "Nulla eget leo quis orci aliquam In vitae tortor non ante condimentum " +
//            "libero quis elit tincidunt sagittis " +
//            "eget iaculis ligula faucibus tempus eleifend";

    private static ArrayList<String> sLoremList = new ArrayList<>();

    static {
//        sLoremList = new ArrayList<>(Arrays.asList(LOREM.split(" ")));
        sLoremList.add("Sed Tempus");
        sLoremList.add("Efficitur Odio");
        sLoremList.add("Cursus Arcu Elementum");
        sLoremList.add("Non Curabitur");
        sLoremList.add("Id Lorem");
        sLoremList.add("Pharetra");
        sLoremList.add("Quis Est");
        sLoremList.add("Justo");
        sLoremList.add("Quisque Eleifend Neque");
        sLoremList.add("Metus Ornare Iaculis");
        sLoremList.add("Donec Vestibulum");
        sLoremList.add("Tellus Id Ex");
        sLoremList.add("Sodales Feugia");
        sLoremList.add("Crci Aliquam Vulputate");
        sLoremList.add("Suspendisse potenti");
        sLoremList.add("Lobortis Quam Turpis");
        sLoremList.add("Curabitur ac Neque");
        sLoremList.add("ipsum sodales");
        sLoremList.add("Accumsan");
        sLoremList.add("Dictum");
        sLoremList.add("Ultricies");

    }

    /**
     * Get an random list of 1, 2, or 3 words (radom but no duplicates)
     * @param size Size of the return array
     * @return List<String>
     */
    public static List<String> getRandLoremList(int size) {
//        List<String> result = new ArrayList<>();
//        String lorem = LOREM;

        // Generate sequence from the start of Lorem list
        for (int i = 0; i < size; i++) {
            int nWords = Utils.randInt(1, 4); // 1, 2, or 3-word commands
//            result.add(lorem.su)

        }

        Collections.shuffle(sLoremList);

        if (size < sLoremList.size() - 1) return sLoremList.subList(0, size);
        else return new ArrayList<>();
    }

}
