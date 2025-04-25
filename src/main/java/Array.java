package org;

import Lesson_12.MyArrayDataException;
import Lesson_12.MyArraySizeException;

public class Array {
    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4)
            throw new MyArraySizeException("Массив должен быть размером 4x4");
        int sum = 0;
        for (int a = 0; a < 4; a++) {
            if (array[a].length != 4)
                throw new MyArraySizeException("Массив должен быть размером 4x4");
            for (int b = 0; b < 4; b++) {
                try {
                    sum += Integer.parseInt(array[a][b]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + a + "][" + b + "]: " + array[a][b]);
                }
            }
        }
        return sum;
    }

}