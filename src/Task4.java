import java.lang.reflect.Array;
import java.util.Arrays;

public class Task4 {

    public static <T> T[] createArray(Class<T> clazz, int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, length);
        return array;
    }

    public static <T> T[][] createMatrix(Class<T> clazz, int rows, int cols) {
        @SuppressWarnings("unchecked")
        T[][] matrix = (T[][]) Array.newInstance(clazz, rows, cols);
        return matrix;
    }

    public static <T> T[] resizeArray(T[] array, int newLength) {
        array = Arrays.copyOf(array, newLength);
        return array;
    }

    public static <T> T[][] resizeMatrix(T[][] matrix, int newRows, int newCols) {
        T[][] resizedMatrix = Arrays.copyOf(matrix, newRows);
        for (int i = 0; i < newRows; i++) {
            if (i < matrix.length) {
                resizedMatrix[i] = Arrays.copyOf(matrix[i], newCols);
            } else {
                resizedMatrix[i] = (T[]) Array.newInstance(matrix.getClass().getComponentType().getComponentType(), newCols);
            }
        }
        return resizedMatrix;
    }

    public static String arrayToString(Object array) {
        return Arrays.deepToString((Object[]) array);
    }
}

