public class Monomial {
    private int exponent;
    private Scalar coefficient;


    Monomial(Scalar coefficient, int exponent){
        this.exponent = exponent;
        this.coefficient = coefficient;
    }
    public Monomial add(Monomial other){
        if (other.getExponent() != getExponent())
            return null;
        return(new Monomial( coefficient.add(other.getCoefficient()), exponent));
    }

    public Monomial mul(Monomial other){
        return new Monomial(coefficient.mul(other.getCoefficient()), exponent+other.getExponent());
    }

    public Scalar evaluate(Scalar s){
        return coefficient.mul(s.power(exponent));
    }
    public Monomial derivative(){
        Scalar temp = new Integer(exponent);
        return new Monomial(temp.mul(coefficient), exponent-1);
    }

    public int sign(){
        return coefficient.sign();
    }

    public Scalar getCoefficient(){
        return coefficient;
    }

    public int getExponent(){
        return exponent;
    }

    public String toString(){
        if(coefficient.equals(0)){
            return "";
        }
        if(exponent == 0)
            return coefficient.toString();
        if (exponent == 1){
            return "+" + coefficient.toString() + "x";
        }
        if(coefficient.equals(1)){
            return "x"+"^"+ String.valueOf(exponent);
        }
        if(coefficient.equals(-1)){
            return "-x"+"^"+ String.valueOf(exponent);
        }

        return "+" + coefficient.toString() + "x" + "^" + String.valueOf(exponent);
    }
}
