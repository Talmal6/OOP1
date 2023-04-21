package calculator.src.test.java.com.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.Polynomial;

import static org.junit.Assert.assertEquals;

public class CalcTest {

    private Polynomial negP;
    private Polynomial rationalP;
    private Polynomial posP;
    private Polynomial randomP1;
    private Polynomial randomP2;

    @BeforeAll
    void setUp() {
        negP = Polynomial.build("-2 0 -4 -5");
        rationalP = Polynomial.build("2/3 -2/5 61/32 42/7 11/11");
        posP = Polynomial.build("3 4 12 4 6");
        randomP1 = Polynomial.build("1/4 4 -13/7 12 6 0 12 0 3/2 0 0 0 0 0 1324");
        randomP2 = Polynomial.build("4 2 1 -3 1/2 4 23 -42");
    }

    @Test
    void checkSums() {
        // add negP to posP
        assertEquals(negP.toString() + "+" + posP.toString() + "=", "1+4x+8x^2-x^3+6x^4", negP.add(posP).toString());

        // add rationalP to negp
        assertEquals(rationalP.toString() + "+a" + negP.toString() + "=", "-4/3+2/5x-67/32x^2+x^3+1/33x^4",
                rationalP.add(negP).toString());

        // add randomP1 to randomP2
        assertEquals(randomP1.toString() + "+" + randomP2.toString() + "=",
                "17/4+6x-6/7x^2+9x^3+13/2x^4+4x^5+35x^6-42x^7+3/2x^8+1324x^14", randomP1.add(randomP2).toString());

    }

    @Test
    void checkMul() {
        // Multiply negP by posP
        assertEquals(negP.toString() + "*" + posP.toString() + "=", "-6-48x^2-20x^3", negP.mul(posP).toString());

        // Multiply posP by RationalP
        assertEquals(posP.toString() + "*" + rationalP.toString() + "=", "2+8/5x+183/3x^2+36x^3",
                posP.mul(rationalP).toString());

        // Multiply randomP1 with randomP2
        assertEquals(randomP1.toString() + "*" + randomP2.toString() + "=", "1+8x-13/7x^2-36x^3+3x^4+276x^5",
                randomP1.mul(randomP2).toString());
    }
}