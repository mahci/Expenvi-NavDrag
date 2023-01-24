package tools;

import java.util.ArrayList;
import java.util.List;

public class Out {

    private static boolean outAll = true;
    private static final List<String> toLogList = new ArrayList<>();

    static {
        toLogList.add("Main");
        toLogList.add("Time");
        toLogList.add("TrialPanel");
        toLogList.add("DO");
        toLogList.add("Listener");
        toLogList.add("NavPanel");
//        toLogList.add("ContentPanel");
//        toLogList.add("InRunnable");
    }

    /**
     * Show debug log
     * @param tag TAG
     * @param params Things to show
     */
    public static void d(String tag, Object... params) {
        final int pLen = params.length;

        if (toLogList.contains(tag)) {

            if (pLen > 0) {
                if (outAll) {
                    StringBuilder sb = new StringBuilder();
                    for (int oi = 0; oi < pLen - 1; oi++) {
                        sb.append(params[oi]).append(" | ");
                    }
                    sb.append(params[pLen - 1]);

                    System.out.println(tag + " >> " + sb);
                } else if (toShowTag(tag)) {
                    StringBuilder sb = new StringBuilder();
                    for (int oi = 0; oi < pLen - 1; oi++) {
                        sb.append(params[oi]).append(" | ");
                    }
                    sb.append(params[pLen - 1]);
                }
            }
        }
    }

    /**
     * Show error log
     * @param tag TAG
     * @param params Things to show
     */
    public static void e(String tag, Object... params) {
        final int pLen = params.length;
        if (pLen > 0 && toShowTag(tag)) {
            StringBuilder sb = new StringBuilder();
            for (int oi = 0; oi < pLen - 1; oi++) {
                sb.append(params[oi]).append(" | ");
            }
            sb.append(params[pLen - 1]);

            System.out.println(tag + " !! " + sb);
        }
    }

    private static boolean toShowTag(String tag) {
//        return toLogList.contains(tag.split("/")[0]);
        return toLogList.contains(tag);
    }

}