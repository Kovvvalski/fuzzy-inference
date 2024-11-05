package by.kovalski.fuzzyinference.util;

public class Util {
    public static double lukasiewiczTNorm(double x, double y) {
        return Math.max(x + y - 1, 0);
    }

    public static double minTNorm(double x, double y) {
        return Math.min(x, y);
    }

    public static double lukasiewiczImplication(double x, double y) {
        double result = Math.min(1 - x + y, 1);
        return Math.round(result * 100.0) / 100.0;
    }

    public static double godelImplication(double x, double y) {
        return x <= y ? 1 : y;
    }
}
