package tools;

import java.util.ArrayList;
import java.util.List;

public class Texter {

    private static final String LOREM =
            "Sed tempus efficitur odio quis cursus arcu elementum non Curabitur " +
            "id lorem quis est pharetra euismod quis eget justo " +
            "Quisque eleifend neque et metus ornare iaculis";

    private static final List<String> sLoremList;

    static {
        sLoremList = List.of(LOREM.split(" "));
    }

    /**
     * Get an array of size of Lorem Ipsum words (constant for now)
     * @param size Size of the return array
     * @return String[]
     */
    public static List<String> getLoremArray(int size) {
        if (sLoremList != null && size < sLoremList.size() - 1) return sLoremList.subList(0, size);
        else return new ArrayList<>();
    }

}
