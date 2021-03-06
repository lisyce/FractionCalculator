package fractioncalculator;

public class Fraction {
    private int numerator;
    private int denominator;
    private boolean valid;

    public Fraction(String stringFraction) {
        try {
            numerator = numeratorFromString(stringFraction);
            denominator = denomFromString(stringFraction);
            valid = true;
        } catch(Exception e){
            System.out.println("Invalid fraction entered. Please try again.");
            valid = false;
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int newNumerator) {
        numerator = newNumerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int newDenominator) {
        denominator = newDenominator;
    }

    public boolean getValid() {
        return valid;
    }

    private int denomFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        return Integer.parseInt(fraction.substring(slashIndex + 1));
    }

    private int numeratorFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        return Integer.parseInt(fraction.substring(0, slashIndex));
    }

}