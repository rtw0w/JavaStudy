package lesson_4;


import java.util.Arrays;


public class lesson_4 {


    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        amountInBounds(7, 5);
        amountInBounds(5, 16);
        determiningMethod(-3);
        determiningMethod(5);
        determiningMethod(0);
        negativeMethod(-8);
        negativeMethod(0);
        negativeMethod(19);
        stringRepeatText("Nissan Silvia S15", 3);
        checkLeapYear(2025); // не могу понять почему не выводит int year
        arrayMassive();
        emptyMassive();
        x2Massive();
        diagonalMassive();
        argumentsMethod(4, 15);
    }


    public static void printThreeWords() {
        System.out.println("Orange \nBanana \nApple");
    }

    public static void checkSumSign() {
        int a = 15;
        int b = 30;
        if (a + b >= 0)
            System.out.println("Сумма положительная");
        else if (a + b <= 0)
            System.out.println("Сумма отрицательная");
    }

    public static void printColor() {
        int value = 100;
        if (value <= 0)
            System.out.println("Красный");
        if (value > 0 && value <= 100)
            System.out.println("Желтый");
        else if (value > 100)
            System.out.println("Зеленый");
    }

    public static void compareNumbers() {
        int a = 40;
        int b = 50;
        if (a >= b)
            System.out.println("a >= b");
        else if (a < b)
            System.out.println("a < b");
    }

    public static boolean amountInBounds(int a, int b) {
        int amount = a + b;
        return amount >= 10 && amount <= 20;
    }

    public static void determiningMethod(int number) {
        if (number >= 0) {
            System.out.println("Positive number (incl zero)");
        } else {
            System.out.println("Negative number");
        }
    }

    public static boolean negativeMethod(int number) {
        return number < 0;
    }

    public static void stringRepeatText(String text, int count) {
        if (count <= 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static boolean checkLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }

    public static void arrayMassive() {
        int[] arr10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr10.length; i++) {
            if (arr10[i] == 0) {
                arr10[i] = 1;
            } else {
                arr10[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr10));
    }

    public static void emptyMassive() {
        int[] arr11 = {100};
        for (int i = 0; i < arr11.length; i++) {
            arr11[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr11));
    }

    public static void x2Massive() {
        int[] arr12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr12.length; i++) {
            if (arr12[i] < 6) {
                arr12[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr12));
    }

    public static void diagonalMassive() {
        int conter = 5;
        int[][] table = new int[conter][conter];
        for (int i = 0; i < conter; i++) {
            table[i][i] = 1;
            table[i][conter - 1 - i] = 1;
        }
        System.out.println(Arrays.toString(table));
    }

    public static int[] argumentsMethod(int len, int initialValue) {
        int[] arg1 = new int[len];
        for (int i = 0; i < len; i++) {
            arg1[i] = initialValue;
        }
        return arg1;
    }

}































