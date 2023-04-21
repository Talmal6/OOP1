package src;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Monomial> monomials;

    // Constructor
    public Polynomial() {
        monomials = new ArrayList<Monomial>();
    }

    public static Polynomial build(String input) {
        Polynomial polynomial = new Polynomial();
        String[] monolist = input.split(" ");
        for (String str : monolist) {
            if (str.contains("/")) {
                // Handle fraction
                String[] fractionParts = str.split("/");
                Rational rational = new Rational(Integer.parseInt(fractionParts[0]),
                        Integer.parseInt(fractionParts[1]));
                Monomial monomial = new Monomial(rational, polynomial.monomials.size());
                polynomial.monomials.add(monomial);
            } else {
                // Handle integer
                Monomial monomial = new Monomial(new Integer(Integer.parseInt(str)), polynomial.monomials.size());
                polynomial.monomials.add(monomial);
            }
        }
        return polynomial;
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();

        int maxDegree = Math.max(this.size(), other.size());

        for (int i = 0; i < maxDegree; i++) {
            Monomial thisMonomial = (i < this.size()) ? this.monomials.get(i) : null;
            Monomial otherMonomial = (i < other.size()) ? other.monomials.get(i) : null;

            if (thisMonomial != null && otherMonomial != null) {
                result.monomials.add(thisMonomial.add(otherMonomial));
            } else if (thisMonomial != null) {
                result.monomials.add(thisMonomial);
            } else if (otherMonomial != null) {
                result.monomials.add(otherMonomial);
            }
        }
        return result;
    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();

        for (Monomial mono : monomials) {
            Monomial newMonomial = mono.derivative();
            result.setMono(newMonomial.getExponent(), newMonomial);
        }

        return result;
    }

    public Scalar evaluate(Scalar s) {
        Scalar result = new Rational(0, 1);
        for (Monomial mono : monomials) {
            result = result.add(mono.evaluate(s));
        }
        return result;
    }

    public Polynomial mul(Polynomial other) {
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(this.size(), other.size());

        for (int i = 0; i < maxDegree; i++) {
            Monomial thisMonomial = (i < this.size()) ? this.monomials.get(i) : null;
            Monomial otherMonomial = (i < other.size()) ? other.monomials.get(i) : null;
            Monomial newMonomial;

            if (thisMonomial != null && otherMonomial != null) {
                newMonomial = thisMonomial.mul(otherMonomial);
                result.setMono(newMonomial.getExponent(), newMonomial);
            }
        }
        return result;
    }

    public boolean equals(Object o) {
        return (o.toString() == this.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Monomial mono : monomials) {
            sb.append(mono.toString());
        }
        if (sb.length() == 0)
            return "0";
        if (sb.charAt(0) == '+') {
            sb.delete(0, 1);
        }

        return sb.toString();
    }

    public int size() {
        return monomials.size();
    }

    public ArrayList<Monomial> getMonos() {
        return monomials;
    }

    public void setMono(int exp, Monomial mono) {
        if (monomials.size() <= exp)
            for (int i = 0; i <= exp - monomials.size()+1; i++)
                monomials.add(new Monomial(new Integer(0), monomials.size()));
        monomials.set(exp, mono);
    }

}
