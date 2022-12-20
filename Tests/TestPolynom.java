import BinaryTrees.*;
import Polynom.Polynom;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPolynom {
    @Test
    void TestPolynome() {
        // 2x−8 || Nullstellen: x = 4
        double[] coefficients = {-8, 2};
        Polynom polynom = new Polynom(coefficients);

        var expectedResults = new ArrayList<Double>();
        expectedResults.add(4.0);

        assertArrayEquals(expectedResults.toArray(), polynom.calculateZeros().toArray());

        // -x^2-7x-10 || Nullstellen: x = -5, x = -2
        double[] coefficients2 = {-10, -7, -1};
        Polynom polynom2 = new Polynom(coefficients2);

        var expectedResults2 = new ArrayList<Double>();
        expectedResults2.add(-5.0);
        expectedResults2.add(-2.0);

        assertArrayEquals(expectedResults2.toArray(), polynom2.calculateZeros().toArray());

        // 2x^2+5x+3 || Nullstellen: x = -1.5, x = -1
        double[] coefficients3 = {3, 5, 2};
        Polynom polynom3 = new Polynom(coefficients3);

        var expectedResults3 = new ArrayList<Double>();
        expectedResults3.add(-1.0);
        expectedResults3.add(-1.5);

        assertArrayEquals(expectedResults3.toArray(), polynom3.calculateZeros().toArray());

        // 4x^3+3x^2+2x+1 || expectedResults: error to high degree
        /*double[] coefficients4 = {1, 2, 3, 4};
        Polynom polynom4 = new Polynom(coefficients4);

        assertThrows(IllegalArgumentException.class, (Executable) polynom4.calculateZeros(), "The maximum degree of the polynom is 2.");*/

        // 0x+12 || Nullstellen: Zero
        double[] coefficients5 = {12, 0};
        Polynom polynom5 = new Polynom(coefficients5);

        assertArrayEquals(new ArrayList<Double>().toArray(), polynom5.calculateZeros().toArray());
    }
}
