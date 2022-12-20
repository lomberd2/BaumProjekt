package Polynom;

import java.util.ArrayList;
import java.util.Stack;

public class Polynom {
    private final double[] coefficients;

    /**
     * Constructor for the Polynom class.
     * @param coefficients, max length 5, max degree 4
     */
    public Polynom(double[] coefficients) {
        if (coefficients.length > 5) {
            throw new IllegalArgumentException("The maximum length of the coefficients array is 5.");
        }
        this.coefficients = coefficients;
    }

    /**
     * Returns the value of the polynom for the given x.
     * @param x the given value for x
     * @return double result of the polynom for the given x
     */
    public double getValue(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    /**
     * Returns the degree of the polynom when the highest coefficient is not 0.
     * Lowest degree is i = 0.
     * @return int degree of the polynom, returns 0 if all coefficients are 0
     */
    public int getDegree() {
        int degree = 0;
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (this.coefficients[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Returns if the polynom is point symmetric.
     * @return boolean true if the polynom is point symmetric, false if not
     */
    public boolean isPointSymmetric() {
        var tempDegrees = new Stack<Integer>();
        for (int i = 0; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0) {
                tempDegrees.push(i);
            }
        }

        // Check if all degrees in tempDegrees are uneven, if one is even, return false
        while (!tempDegrees.empty()) {
            if (tempDegrees.pop() % 2 == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns if the polynom is axis symmetric.
     * @return boolean true if the polynom is axis symmetric, false if not
     */
    public boolean isAxisSymmetric() {
        var tempDegrees = new Stack<Integer>();
        for (int i = 0; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0) {
                tempDegrees.push(i);
            }
        }

        // Check if all degrees in tempDegrees are even, if one is uneven return false
        while (!tempDegrees.empty()) {
            if (tempDegrees.pop() % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the calculated Zeros of the polynom.
     * The max degree of the polynom is 2.
     * @return ArrayList<Double> all Zeroes of the polynom
     */
    public ArrayList<Double> calculateZeros() {
        if (this.getDegree() > 2) {
            throw new IllegalArgumentException("The maximum degree of the polynom is 2.");
        }

        // If the degree is 0, return an empty ArrayList
        var zeros = new ArrayList<Double>();

        switch (this.getDegree()) {
            case 1 -> {
                zeros.add(-this.coefficients[0] / this.coefficients[1]);
                return zeros;
            }
            case 2 -> {
                double a = this.coefficients[2];
                double b = this.coefficients[1];
                double c = this.coefficients[0];
                double d = Math.pow(b, 2) - 4 * a * c;
                if (d < 0) {
                    return zeros;
                }
                zeros.add((-b + Math.sqrt(d)) / (2 * a));
                zeros.add((-b - Math.sqrt(d)) / (2 * a));
                return zeros;
            }
            default -> {
                return zeros;
            }
        }
    }


    /**
     * Returns the polynom as a string. Leaves out all coefficients that are 0.
     * @return String polynom as a string
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0) {
                if (i == 0) {
                    result += this.coefficients[i];
                } else if (i == 1) {
                    result += this.coefficients[i] + "x";
                } else {
                    result += this.coefficients[i] + "x^" + i;
                }
                if (i != this.coefficients.length - 1) {
                    result += " + ";
                }
            }
        }
        return result;
    }
}
