package calculator.src.test.java.com.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.Monomial;
import src.Polynomial;
import src.Rational;

public class CalcTest {

    private static Polynomial poly;
    private static Monomial mono;
    private static src.Integer i;
    private static Rational r;

    @BeforeAll
    static void setUp() {
        i = new src.Integer(1);
        r = new Rational(2, 3);
        mono = new Monomial(i, 4);
        poly = Polynomial.build("5 6 7 8 9");
    }

    @Test
    void checkIntegers() {
        // test adds
        Assertions.assertEquals("2", i.add(i).toString());
        Assertions.assertEquals("2", i.addInteger(i).toString());
        Assertions.assertEquals("5/3", i.addRational(r).toString());

        //test mults
        Assertions.assertEquals("1", i.mul(i).toString());
        Assertions.assertEquals("1", i.mulInteger(i).toString());
        Assertions.assertEquals("2/3", i.mulRational(r).toString());

        Assertions.assertEquals("-1", i.neg().toString());
        Assertions.assertEquals("1", i.power(2).toString());
        Assertions.assertEquals(1, i.sign());
    }

    @Test
    void checkRationals() {
        // test adds
        Assertions.assertEquals("4/3", r.add(r).toString());
        Assertions.assertEquals("5/3", r.addInteger(i).toString());
        Assertions.assertEquals("4/3", r.addRational(r).toString());

        //test mults
        Assertions.assertEquals("2/3", r.mul(i).toString());
        Assertions.assertEquals("2/3", r.mulInteger(i).toString());
        Assertions.assertEquals("4/9", r.mulRational(r).toString());
    }

    @Test
    void checkMonomials() {
        Assertions.assertEquals("2x^4", mono.add(mono).toString());
        Assertions.assertEquals("x^8", mono.mul(mono).toString());
        Assertions.assertEquals("16/81", mono.evaluate(r).toString());
        Assertions.assertEquals("4x^3", mono.derivative().toString());
        Assertions.assertEquals(1, mono.sign());
    }

    @Test
    void checkPolynomials() {
        Assertions.assertEquals("10 + 12x + 14x^2 + 16x^3 + 18x^4", poly.add(poly).toString());
        Assertions.assertEquals("6 + 14x + 24x^2 + 36x^3", poly.derivative().toString());
        Assertions.assertEquals("1", poly.evaluate(i).toString());
        Assertions.assertEquals("25 + 60x + 106x^2 + 164x^3 + 235x^4 + 220x^5 + 190x^6 + 144x^7 + 81x^8", poly.mul(poly).toString());
    }
}