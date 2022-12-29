package highlevelmath.constructs;

public class Complex {
    
    private double real;
    private double im;

    //Constructors
    public Complex(double a, double b){
        this.real = a;
        this.im = b;
    }

    public Complex(String number) throws ConstructFormatException{
        number = number.replaceAll("\\s+", "");
        try{
            int ind = number.contains("+") ? number.indexOf("+") :  number.indexOf("-");
            this.real = Integer.parseInt(number.substring(0, ind));
            this.im = Integer.parseInt(number.substring(ind, number.length()));
        } catch(NumberFormatException | StringIndexOutOfBoundsException e){
            throw new ConstructFormatException("One or more complex numbers were incorrectly forrmed");
        }
    }

    //Operations
    public static Complex add(Complex a, Complex b){
        Complex c = new Complex(a.getReal() + b.getReal(), a.getIm() + b.getIm());
        c.correctRounding();
        return c;
    }

    public static Complex sub(Complex a, Complex b){
        Complex c = new Complex(a.getReal() - b.getReal(), a.getIm() - b.getIm());
        c.correctRounding();
        return c;
    }

    public static Complex mul(Complex a, Complex b){
        double real = a.getReal() * b.getReal() - a.getIm()*b.getIm();
        double im = a.getReal() * b.getReal() + a.getIm()*b.getIm();
        Complex c = new Complex(real, im);
        return c;
    }

    public static Complex div(Complex a, Complex b){
        return Complex.mul(a, Complex.reciprocal(b));
    }

    public Complex scale(double val){
        Complex c = new Complex(getReal() * val, this.getIm() * val);
        c.correctRounding();
        return c;
    }

    public static Complex conjugate(Complex a){
        return new Complex(a.getReal(), -a.getIm());
    }

    public static Complex reciprocal(Complex a){
        double val = a.getReal()*a.getReal() + a.getIm()*a.getIm();
        Complex c = new Complex(a.getReal() / val, -a.getIm()/val);
        c.correctRounding();
        return c;
    }


    //Getters
    public double getIm() {
        return im;
    }

    public double getReal() {
        return real;
    }

    //Setters
    public void setIm(double im) {
        this.im = im;
    }

    public void setReal(double real) {
        this.real = real;
    }

    //Other Methods
    @Override
    public String toString() {
        return this.real + " + " + this.im + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        return this.getReal() == ((Complex)obj).getReal() && this.getIm() == ((Complex)obj).getIm();
    }

    private void correctRounding(){
        double threshold = 1E-2;
        if(Math.abs(Math.round(this.real) - this.real) < threshold){
            this.real = Math.round(this.real);
        }
        if(Math.abs(Math.round(this.im) - this.im) < threshold){
            this.im = Math.round(this.im);
        }
    }

}
