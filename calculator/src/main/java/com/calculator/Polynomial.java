package calculator.src.main.java.com.calculator;

import java.util.HashMap;

public class Polynomial {
    private HashMap<java.lang.Integer, Monomial> monomials;

    // Constructor
    public Polynomial() {
        monomials = new HashMap<>();
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
                polynomial.monomials.put(monomial.getExponent(), monomial);
            } else {
                // Handle integer
                Monomial monomial = new Monomial(new Integer(Integer.parseInt(str)), polynomial.monomials.size());
                polynomial.monomials.put(monomial.getExponent(), monomial);
            }
        }
        return polynomial;
    }

    private void addMonomial(Monomial mono){
        int exponent = mono.getExponent();
        if (monomials.containsKey(exponent)) {
            Monomial existingMonomial = monomials.get(exponent);
            Monomial sum = existingMonomial.add(mono);
            if (sum.getCoefficient().equals(0)) {
                monomials.remove(exponent);
            } else {
                monomials.put(exponent, sum);
            }
        } else {
            monomials.put(exponent, mono);
        }
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();

        for (HashMap.Entry<java.lang.Integer, Monomial> mono : this.monomials.entrySet()) {
            result.addMonomial(mono.getValue());
        }

        for (HashMap.Entry<java.lang.Integer, Monomial> mono : other.monomials.entrySet()) {
            result.addMonomial(mono.getValue());
        }

        return result;
    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();

        for (HashMap.Entry<java.lang.Integer, Monomial> mono : monomials.entrySet()) {
            Monomial newMonomial = mono.getValue().derivative();
            result.setMono(newMonomial.getExponent(), newMonomial);
        }

        return result;
    }

    public Scalar evaluate(Scalar s) {
        Scalar result = new Rational(0, 1);
        for (HashMap.Entry<java.lang.Integer, Monomial> mono : monomials.entrySet()) {
            result = result.add(mono.getValue().evaluate(s));
        }
        return result;
    }

    public Polynomial mul(Polynomial other) {
        Polynomial result = Polynomial.build("0");
        for (HashMap.Entry<java.lang.Integer, Monomial> m1 : this.monomials.entrySet()) {
            for (HashMap.Entry<java.lang.Integer, Monomial> m2 : other.monomials.entrySet()) {
                Monomial product = m1.getValue().mul(m2.getValue());
                result.addMonomial(product);
            }
        }
        return result;
    }

    public boolean equals(Object o) {
        return (o.toString().equals(this.toString()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<java.lang.Integer, Monomial> mono : monomials.entrySet()) {
            if(!mono.getValue().getCoefficient().equals(0)){
                sb.append(mono.getValue().sign() == -1 ? " - " : " + ");
                sb.append(mono.getValue().toString());
            }
        }

        if (sb.length() == 0)
            return "0";

        return (sb.charAt(1) == '-' ? sb.substring(2) : sb.substring(3));
    }

    private void setMono(int exp, Monomial mono) {
        monomials.put(exp, mono);
    }

}
