package fractioncalculator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Enter first fraction in the format 'x/y' ");
            Fraction fraction1 = new Fraction(scanner.nextLine());
            if (!fraction1.getValid()) continue;
            System.out.println("Enter second fraction in the format 'x/y' ");
            Fraction fraction2 = new Fraction(scanner.nextLine());
            if (!fraction2.getValid()) continue;

            System.out.println("Would you like to add, subtract, multiply, or divide?");
            String operation = scanner.nextLine().toLowerCase();

            switch (operation) {
                case "add":
                case "+":
                    System.out.println(add(fraction1, fraction2, true));
                    break;
                case "subtract":
                case "-":
                    System.out.println(subtract(fraction1, fraction2));
                    break;
                case "multiply":
                case "*":
                    System.out.println(multiply(fraction1, fraction2, true));
                    break;
                case "divide":
                case "/":
                    System.out.println(divide(fraction1, fraction2));
                    break;
                default:
                    System.out.println("Please try again with a valid operation.");
                    break;
            }
            System.out.println("Would you like to continue using the calculator?");
            String quit = scanner.nextLine().toLowerCase();
            if(quit.equals("no") || quit.equals("n")) System.exit(0);
        }

    }

    public static String divide(Fraction num1, Fraction num2) {

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        int num2Numerator = num2.getNumerator();
        num2.setNumerator(num2.getDenominator());
        num2.setDenominator(num2Numerator);
        return multiply(num1, num2, true);
    }

    public static String multiply(Fraction num1, Fraction num2, boolean convert) {

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        int totalNumerator = num1.getNumerator() * num2.getNumerator();
        int commonDenominator = leastCommonMultiple(num1.getDenominator(), num2.getDenominator());
        if(num1.getDenominator() == num2.getDenominator() && num1.getDenominator() % totalNumerator != 0) commonDenominator = num1.getDenominator() * num2.getDenominator();


        if (convert) {
            return (convertToMixedNumber(new Fraction(totalNumerator + "/" + commonDenominator)));
        }
        return totalNumerator + "/" + commonDenominator;
    }

    public static String subtract(Fraction num1, Fraction num2) {

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        num2.setNumerator(num2.getNumerator() * -1);
        return add(num1, num2, true);
    }

    public static String add(Fraction num1, Fraction num2, boolean convert){

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        int commonDenominator = leastCommonMultiple(num1.getDenominator(), num2.getDenominator());

        num1.setNumerator(num1.getNumerator() * (commonDenominator/num1.getDenominator()));
        num2.setNumerator(num2.getNumerator() * (commonDenominator/num2.getDenominator()));
        int totalNumerator = num1.getNumerator() + num2.getNumerator();

        if (convert) {
            return (convertToMixedNumber(new Fraction(totalNumerator + "/" + commonDenominator)));
        }
        return (totalNumerator + "/" + commonDenominator);
    }

    public static String convertToMixedNumber(Fraction fraction){

        if(fraction.getNumerator() >= fraction.getDenominator()) {
            int remainder = fraction.getNumerator() % fraction.getDenominator();
            int wholeNum = (fraction.getNumerator() - remainder) / fraction.getDenominator();
            fraction.setNumerator(remainder);
            if(fraction.getNumerator() > 0){
                return wholeNum + " " + fraction.getNumerator() + "/" + fraction.getDenominator();
            }
            else {return Integer.toString(wholeNum);}

        }
        return fraction.getNumerator() + "/" + fraction.getDenominator();
    }

    public static int leastCommonMultiple(int num1, int num2) {
        int lcd = num1 * num2;
        for (int i=lcd-1; i>1; i--){
            if(lcd % i == 0 && (num1 % i == 0 && num2 % i == 0)) {
                lcd = lcd / i;
                break;
            }
        }
        return lcd;
    }

}