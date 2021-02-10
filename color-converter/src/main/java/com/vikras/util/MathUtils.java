package com.vikras.util;

public class MathUtils {
    /**
     * find minimal value
     * @param a value1
     * @param b value2
     * @param c value3
     * @return minimal value
     */
    public static double getMinThree(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * find maximal value
     * @param a value1
     * @param b value2
     * @param c value3
     * @return maximal value
     */
    public static double getMaxThree(double a, double b, double c) {
        return Math.max(a, Math.max(b, c));
    }
}
