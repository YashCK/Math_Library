package highlevelmath.constructs.structures;

import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.UndefinedException;

public class Complex {
    
    private double real;
    private double imag;

    //Constructors
    public Complex(double a, double b){
        this.real = a;
        this.imag = b;
    }

    public Complex(String number) throws ConstructFormatException{
        number = number.replaceAll("\\s+", "");
        try{
            int ind = number.contains("+") ? number.indexOf("+") :  number.indexOf("-");
            if(ind == -1){
                if(number.charAt(number.length() - 1) == 'i'){
                    this.real = 0;
                    this.imag = Double.parseDouble(number.substring(0, number.length() - 1));
                } else {
                    this.real = Double.parseDouble(number);
                    this.imag = 0;
                }
            } else {
                this.real = Double.parseDouble(number.substring(0, ind));
                this.imag = Double.parseDouble(number.substring(ind, number.length() - 1));
            }
        } catch(NumberFormatException | StringIndexOutOfBoundsException e){
            throw new ConstructFormatException("One or more complex numbers were incorrectly forrmed");
        }
    }

    //Operations
    public static Complex add(Complex a, Complex b){
        Complex c = new Complex(a.getReal() + b.getReal(), a.getImag() + b.getImag());
        c.correctRounding();
        return c;
    }

    public static Complex subtract(Complex a, Complex b){
        Complex c = new Complex(a.getReal() - b.getReal(), a.getImag() - b.getImag());
        c.correctRounding();
        return c;
    }

    public static Complex mul(Complex a, Complex b){
        double real = a.getReal() * b.getReal() - a.getImag()*b.getImag();
        double im = a.getReal() * b.getImag() + a.getImag()*b.getReal();
        Complex c = new Complex(real, im);
        c.correctRounding();
        return c;
    }

    public static Complex div(Complex a, Complex b) throws UndefinedException{
        try {
            return Complex.mul(a, Complex.reciprocal(b));
        } catch (UndefinedException e) {
            throw new UndefinedException("Cannot divide by Complex number 0 + 0i.");
        }
    }

    public static double norm(Complex a){
        return Math.sqrt(a.getReal()*a.getReal() + a.getImag()*a.getImag());
    }

    public Complex scale(double val){
        Complex c = new Complex(getReal() * val, this.getImag() * val);
        c.correctRounding();
        return c;
    }

    public static Complex conjugate(Complex a){
        return new Complex(a.getReal(), -a.getImag());
    }

    public static Complex reciprocal(Complex a) throws UndefinedException{
        if(a.isZero()){
            throw new UndefinedException("There is no reciprocal for Complex number 0 + 0i");
        }
        double val = a.getReal()*a.getReal() + a.getImag()*a.getImag();
        Complex c = new Complex(a.getReal() / val, -a.getImag()/val);
        c.correctRounding();
        return c;
    }


    //Getters
    public double getImag() {
        return imag;
    }

    public double getReal() {
        return real;
    }

    //Setters
    public void setIm(double im) {
        this.imag = im;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public boolean isZero(){
        return this.equals(new Complex(0, 0));
    }

    //Other Methods
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append(truncateDecimal(this.real, 2));
        if(this.imag < 0) {
            bld.append(" - ");
        } else {
            bld.append(" + ");
        }
        bld.append(truncateDecimal(Math.abs(this.imag), 2) + "i");
        return bld.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        return this.getReal() == ((Complex)obj).getReal() && this.getImag() == ((Complex)obj).getImag();
    }

    private void correctRounding(){
        double threshold = 1E-2;
        if(Math.abs(Math.round(this.real) - this.real) < threshold){
            this.real = Math.round(this.real);
        }
        if(Math.abs(Math.round(this.imag) - this.imag) < threshold){
            this.imag = Math.round(this.imag);
        }
    }

    protected double truncateDecimal(double value, int places) {
        double powerOfTen = Math.pow(10, places);
        return Math.floor(value * powerOfTen) / powerOfTen;
    }

}
