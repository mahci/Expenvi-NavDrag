package models;

import tools.Out;

import static configs.Consts.STRINGS.SP;

public class Memo {
    private final String TAG = this.getClass().getSimpleName();

    private String action;
    private String mode;
    private String value1 = "-";
    private String value2 = "-";

    /**
     * More general constructor
     * @param act Action
     * @param md Mode
     * @param values list of values (currently up to two is supported)
     */
    public Memo(String act, String md, Object... values) {
        action = act;
        mode = md;
        if (values.length == 1) value1 = String.valueOf(values[0]);
        if (values.length == 2) value2 = String.valueOf(values[1]);
    }

    /**
     * Basic consrtuctor
     */
    public Memo() {
        action = "";
        mode = "";
        value1 = "";
        value2 = "";
    }

    /**
     * Return action
     * @return String Action
     */
    public String getAction() {
        return action;
    }

    /**
     * Return mode
     * @return String Mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Get one of the values in String
     * @param valInd Index of the value
     * @return String
     */
    public String getStrValue(int valInd) {
        if (valInd == 1) return value1;
        if (valInd == 2) return value2;
        else return "";
    }

    /**
     * Get one of the values in Double
     * @param valInd Index of the value
     * @return Double
     */
    public Double getDblValue(int valInd) {
        if (valInd == 1) return Double.parseDouble(value1);
        if (valInd == 2) return Double.parseDouble(value2);
        else return 0.0;
    }

    /**
     * Get one of the values in Int
     * @param valInd Index of the value
     * @return Int
     */
    public int getIntValue(int valInd) {
        if (valInd == 1) return Integer.parseInt(value1);
        if (valInd == 2) return Integer.parseInt(value2);
        else return 0;
    }

    /**
     * Get the Memo from String
     * @param mssg String
     * @return Memo
     */
    public static Memo valueOf(String mssg) {
        Memo result = new Memo();

        if (mssg != null) {
            String[] parts = mssg.split(SP);

            if (parts.length == 4) {
                result.action = parts[0];
                result.mode = parts[1];
                result.value1 = parts[2];
                result.value2 = parts[3];
            } else {
                Out.e("Memo", "Memo string NOT 4 parts!");
            }
        }

        return result;
    }

    /**
     * Get the String equivaluent
     * @return String
     */
    @Override
    public String toString() {
        return action + SP + mode + SP + value1 + SP + value2;
    }
}
