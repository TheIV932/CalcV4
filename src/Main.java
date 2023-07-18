import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String input = scanner.nextLine();
        String calc();
        System.out.println("Результат: ");
    }

    public static String calc(String input) {
        String[] operands = input.split(" ");
        if (operands.length != 3) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }
        String aStr = operands[0];
        String operator = operands[1];
        String bStr = operands[2];

        boolean isRoman = false;
        int a;
        int b;

        // Проверяем, являются ли оба числа арабскими
        boolean isArabic = isArabicNumber(aStr) && isArabicNumber(bStr);

        // Проверяем, являются ли оба числа римскими
        boolean isRomanNumeral = isRomanNumeral(aStr) && isRomanNumeral(bStr);

        if (isArabic && isRomanNumeral) {
            throw new IllegalArgumentException("Нельзя использовать одновременно арабские и римские числа");
        } else if (!isArabic && !isRomanNumeral) {
            throw new IllegalArgumentException("Числа должны быть либо арабскими, либо римскими");
        } else if (isRomanNumeral) {
            a = Roman.RomanConverter.romanToArabic(aStr);
            b = Roman.RomanConverter.romanToArabic(bStr);
            isRoman = true;
        } else {
            a = Integer.parseInt(aStr);
            b = Integer.parseInt(bStr);
        }

        int maxNumber = isRoman ? 10 : 10;
        if (a < 1 || a > maxNumber || b < 1 || b > maxNumber) {
            throw new IllegalArgumentException("Числа должны быть от 1 " + maxNumber + " включительно");
        }

        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        };

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат работы калькулятора с римскими числами не может быть меньше единицы");
            }
            String romanResult = Roman.RomanConverter.arabicToRoman(result);
            System.out.println(romanResult);
        } else {
            System.out.println(result);
        }
        return aStr;
    }

    // Добавлены проверки на арабские и римские числа
    static boolean isArabicNumber(String number) {
        try {
            int arabic = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isRomanNumeral(String number) {
        String[] romanArray = Roman.romanArray;
        for (String roman : romanArray) {
            if (roman.equals(number)) {
                return true;
            }
        }
        return false;
    }

    static class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        static class RomanConverter {
            public static int romanToArabic(String romanNumber) {
                // Реализация конвертации римских чисел в арабские
                for (int i = 0; i < romanArray.length; i++) {
                    if (romanNumber.equals(romanArray[i])) {
                        return i;
                    }
                }
                return -1;
            }

            public static String arabicToRoman(int arabian) {
                // Реализация конвертации арабских чисел в римские
                return romanArray[arabian];
            }
        }
    }
}