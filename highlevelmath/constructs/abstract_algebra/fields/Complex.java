package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.ConstructFormatException;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.UndefinedException;

import java.util.Objects;

public class Complex implements Field<Complex> {

    private double real;
    private double imag;

    //Constructors
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex(String number) throws ConstructFormatException {
        number = number.replaceAll("\\s+", "");
        try {
            int ind = number.contains("+") ? number.lastIndexOf("+") : number.lastIndexOf("-");
            if (ind == -1 || ind == 0) {
                if (number.charAt(number.length() - 1) == 'i') {
                    this.real = 0;
                    String imCoefficient = number.substring(0, number.length() - 1);
                    this.imag = switch (imCoefficient) {
                        case "" -> this.imag = 1;
                        case "-" -> this.imag = -1;
                        default -> Double.parseDouble(imCoefficient);
                    };
                } else {
                    this.real = Double.parseDouble(number);
                    this.imag = 0;
                }
            } else {
                this.real = Double.parseDouble(number.substring(0, ind));
                String imCoefficient = number.substring(ind, number.length() - 1);
                this.imag = switch (imCoefficient) {
                    case "+" -> this.imag = 1;
                    case "-" -> this.imag = -1;
                    default -> Double.parseDouble(imCoefficient);
                };
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new ConstructFormatException("One or more complex numbers were incorrectly formed");
        }
    }

    @Override
    public void add(Complex element) {
        real += element.real();
        imag += element.imag();
        correctRounding();
    }

    @Override
    public Complex getZero() {
        return new Complex(0, 0);
    }

    @Override
    public Complex negate() {
        return new Complex(-real, -imag);
    }

    @Override
    public void subtract(Complex element) {
        real -= element.real();
        imag -= element.imag();
        correctRounding();
    }

    @Override
    public void multiply(Complex element) {
        real = real * element.real() - imag * element.imag();
        imag = real * element.imag() + imag * element.real();
        correctRounding();
    }

    @Override
    public Complex getOne() {
        return new Complex(1, 0);
    }

    @Override
    public Complex invert() throws NotInvertibleException {
        if (isZero()) {
            throw new NotInvertibleException("There is no reciprocal for Complex number 0 + 0i");
        }
        double val = real * real + imag * imag;
        Complex c = new Complex(real / val, -imag / val);
        c.correctRounding();
        return c;
    }

    @Override
    public void divide(Complex b) throws UndefinedException {
        try {
            multiply(b.invert());
        } catch (NotInvertibleException e) {
            throw new UndefinedException("Cannot divide by Complex number 0 + 0i.");
        }
    }

    public double norm() {
        return Math.sqrt(real * real + imag * imag);
    }

    public void scale(double val) {
        real *= val;
        imag *= val;
        correctRounding();
    }

    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    public double real() {
        return real;
    }

    public double imag() {
        return imag;
    }

    public void setImag(double im) {
        this.imag = im;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public boolean isZero(){
        return this.equals(new Complex(0, 0));
    }

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
    public boolean equals(Object o) {
        if(o instanceof Complex c){
            return real == c.real() && imag == c.imag();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
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
