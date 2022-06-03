import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) {

        try {
            String operator = getOperator(input);
            String[] operands = splitExpression(input, operator);

            String firstNumber = operands[0].trim();
            String secondNumber = operands[1].trim();


            String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String[] arabicNumerals = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            if (isRelevantNumbers(romanNumerals, firstNumber, secondNumber) && operands.length < 3) {
                String firstRomanNumber = romanToArabic(romanNumerals, arabicNumerals, firstNumber);
                String secondRomanNumber = romanToArabic(romanNumerals, arabicNumerals, secondNumber);
                int resultRomanOutput = result(firstRomanNumber, secondRomanNumber, operator);
                return romanResult(resultRomanOutput);
            } else if (isRelevantNumbers(arabicNumerals, firstNumber, secondNumber) && operands.length < 3) {
                return Integer.toString(result(firstNumber, secondNumber, operator));
            } else {
                return "trow Exeption";
            }
        } catch (Exception e) {
            return "trow Exeption";
        }
    }

    public static String getOperator(String input) {

        String plusSign = "+";
        String minusSign = "-";
        String divisionSign = "/";
        String multiplicationSign = "*";

        if (input.contains(plusSign)) {
            return "\\+";
        } else if (input.contains(minusSign)) {
            return "\\-";
        } else if (input.contains(divisionSign)) {
            return "\\/";
        } else if (input.contains(multiplicationSign)) {
            return "\\*";
        } else {
            return "throws Exception";
        }
    }

    public static String[] splitExpression(String input, String operator) {
        return input.split(operator);
    }

    public static boolean isRelevantNumbers(String[] strings, String x, String y) {
        return Arrays.asList(strings).contains(x) && Arrays.asList(strings).contains(y);
    }

    public static int result(String x, String y, String z) {
        int firstOperand = Integer.parseInt(x);
        int secondOperand = Integer.parseInt(y);
        int result = 0;

        switch (z) {
            case "\\+":
                result = firstOperand + secondOperand;
                break;
            case "\\-":
                result = firstOperand - secondOperand;
                break;
            case "\\/":
                result = firstOperand / secondOperand;
                break;
            case "\\*":
                result = firstOperand * secondOperand;
                break;
        }

        return result;
    }

    public static String romanToArabic(String[] roman, String[] arabic, String x) {
        int i = Arrays.asList(roman).indexOf(x);
        return arabic[i];
    }

    public static String romanResult(int arabicInt) {
        if (arabicInt > 0 && arabicInt <= 100) {
            String[] baseRomanNumbers = {"C", "XC", "L", "X", "IX", "V", "I"};
            int[] baseNumbers = {100, 90, 50, 10, 9, 5, 1};
            String resultRoman = "";

            for (int i = 0; i < baseNumbers.length; i++) {
                int numberInPlace = arabicInt / baseNumbers[i];
                if (numberInPlace == 0) continue;
                resultRoman += numberInPlace == 4 && i > 0 ? baseRomanNumbers[i] + baseRomanNumbers[i - 1] :
                        new String(new char[numberInPlace]).replace("\0", baseRomanNumbers[i]);
                arabicInt = arabicInt % baseNumbers[i];
            }
            return resultRoman;
        } else {
            return "trow Exeption";
        }
    }
}