package src;

public class Rational implements Scalar {

    private int numerator;
    private int denominator;

    Rational(int numer, int denom) {
        numerator = numer;
        denominator = denom;
    }

    @Override
    public Scalar add(Scalar other) {
        return (other.addRational(this));
    }

    @Override
    public Scalar mul(Scalar other) {
        return other.mulRational(this);
    }

    @Override
    public Scalar neg() {
        return new Rational(numerator * -1, denominator);
    }

    @Override
    public Scalar power(int exponent) {
        return (new Rational((int) Math.pow(numerator, exponent), (int) Math.pow(denominator, exponent)));
    }

    @Override
    public int sign() {
        if (numerator == 0)
            return 0;
        else
            return (numerator * denominator / Math.abs(denominator * numerator));
    }

    Rational reduce() {
        for (int i = denominator; i > 1; i--)
            if (denominator % i == 0 && numerator % i == 0)
                return new Rational(numerator / i, denominator / i);
        return this;
    }

    int getNumer() {
        return numerator;
    }

    int getDenom() {
        return denominator;
    }

    void setNumer(int numer) {
        numerator = numer;
    }

    void setDenom(int denom) {
        denominator = denom;
    }

    @Override
    public Scalar addRational(Rational other) {
        return new Rational(other.getNumer() * denominator + numerator * other.getDenom(),
                denominator * other.getDenom());
    }

    @Override
    public Scalar addInteger(Integer other) {
        return new Rational(numerator + other.getNumber() * denominator, denominator);
    }

    @Override
    public Scalar mulInteger(Integer other) {
        return new Rational(numerator * other.getNumber(), denominator);
    }

    @Override
    public Scalar mulRational(Rational other) {
        return new Rational(numerator * other.getNumer(), denominator * other.getDenom());
    }

    public String toString() {
        Rational temp = reduce();
        if (temp.getDenom() == 1) {
            return String.valueOf(temp.getNumer());
        }
        if ((temp.getNumer() < 0 && temp.getDenom() < 0) || (temp.getNumer() > 0 && temp.getDenom() < 0)) {
            temp.setDenom(denominator * -1);
            temp.setNumer(numerator * -1);
        }
        return String.valueOf(temp.getNumer()) + "/" + String.valueOf(temp.getDenom());
    }

    public boolean equals(float i) {
        return (numerator / denominator == i);
    }

}
