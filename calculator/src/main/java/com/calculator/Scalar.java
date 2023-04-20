package com.calculator;

public interface Scalar {

    //accepts a scalar argument and returns a new scalar which is the sum of he current scalar and the argument.
    Scalar add(Scalar s);
    // for double dispatch
    Scalar addInteger(Integer other);
    
    Scalar addRational(Rational other);

    //accepts a scalar argument and returns a new scalar which is the multipli acation of the current scalar and the argument.
    Scalar mul(Scalar s);
    // for double dispatch
    Scalar mulInteger(Integer other);

    Scalar mulRational(Rational other);

    //returns a new scalar which is the multiplication of the current scalar with (-1).
    Scalar neg();

    // accepts a scalar argument and returns a new scalar which is the scalar raised to the power of the exponent argument.
    Scalar power(int exponent);
    
    // returns 1 for positive scalar, -1 for negative and 0 for 0.
    int sign();

    boolean equals(float i);



    
}
