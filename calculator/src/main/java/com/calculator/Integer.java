package calculator.src.main.java.com.calculator;

public class Integer implements Scalar {
    private int number;

    public Integer(int number){
        this.number = 
        number;
    }

    
    public static int parseInt(String str) {
        int result = 0;
        boolean isNegative = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && c == '-') {
                isNegative = true;
            } else if (c < '0' || c > '9') {
                throw new NumberFormatException("Invalid input string: " + str);
            } else {
                int digitValue = c - '0';
                result = result * 10 + digitValue;
            }
        }
        return isNegative ? -result : result;
    }
    

    
    @Override
    public Scalar add(Scalar other) {
        return other.addInteger(this);
    }

    public Scalar addInteger(Integer other){
        return(new Integer(other.getNumber()+getNumber()));

    }

    public Scalar addRational(Rational other){
        return (new Rational(other.getDenom()*number+other.getNumer(),other.getDenom()));

    }
    @Override
    public Scalar mul(Scalar other) {
        return(other.mulInteger(this));
    }

    @Override
    public Scalar neg() {
        return new Integer(number*-1);
    }

    @Override
    public Scalar power(int exponent) {
       return new Integer((int)Math.pow(number, exponent));
    }

    @Override
    public int sign() {
        if (number == 0)
            return 0;
        else{
            return(number/Math.abs(number));
        }
    }
    public int getNumber(){
        return number;
    }


    @Override
    public Scalar mulInteger(Integer other) {
        return(new Integer(other.getNumber()*getNumber()));
    }

    @Override
    public Scalar mulRational(Rational other) {
        return new Rational(this.getNumber()*other.getNumer(),other.getDenom());
    }

    public String toString(){
        return String.valueOf(number);
    }

    public boolean equals(float i){
        return (number == i);
    }



}
