import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Клас для виконання операцій над матрицями, включаючи додавання
 * та спеціальні обчислення суми елементів.
 */
public class Main {

    /**
     * Головний метод для виконання програми.
     *
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        // Оголошення змінних
        Scanner scanner = new Scanner(System.in);
        int rows = 0;
        int cols = 0;
        double[][] matrixA = null;
        double[][] matrixB = null;
        double[][] matrixC = null;

        try {
            // Введення розмірів матриць
            System.out.print("Введіть кількість рядків матриць: ");
            rows = scanner.nextInt();

            System.out.print("Введіть кількість стовпців матриць: ");
            cols = scanner.nextInt();

            // Перевірка на коректність розмірів
            if (rows <= 0 || cols <= 0) {
                throw new IllegalArgumentException("Розміри матриці повинні бути додатніми.");
            }

            // Ініціалізація матриць
            matrixA = new double[rows][cols];
            matrixB = new double[rows][cols];
            matrixC = new double[rows][cols];

            // Введення елементів першої матриці
            System.out.println("Введіть елементи матриці A:");
            fillMatrix(scanner, matrixA);

            // Введення елементів другої матриці
            System.out.println("Введіть елементи матриці B:");
            fillMatrix(scanner, matrixB);

            // Додавання матриць
            addMatrices(matrixA, matrixB, matrixC);

            // Виведення результуючої матриці
            System.out.println("Результуюча матриця C (A + B):");
            printMatrix(matrixC);

            // Обчислення сум
            double sumOddColsMax = calculateOddColumnMaxSum(matrixC);
            double sumEvenColsMin = calculateEvenColumnMinSum(matrixC);

            // Виведення результатів
            System.out.printf(
                    "Сума найбільших елементів у стовпцях з непарними номерами: %.2f%n",
                    sumOddColsMax
            );
            System.out.printf(
                    "Сума найменших елементів у стовпцях з парними номерами: %.2f%n",
                    sumEvenColsMin
            );
        } catch (InputMismatchException e) {
            System.err.println("Помилка вводу: очікувалося число. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Некоректні дані: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Заповнює матрицю значеннями, введеними користувачем.
     *
     * @param scanner Об'єкт Scanner для зчитування введених даних.
     * @param matrix  Матриця для заповнення.
     */
    private static void fillMatrix(Scanner scanner, double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("matrix[%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    /**
     * Виконує додавання двох матриць і зберігає результат у третій матриці.
     *
     * @param matrixA Перша матриця.
     * @param matrixB Друга матриця.
     * @param matrixC Матриця для збереження результату.
     */
    private static void addMatrices(double[][] matrixA, double[][] matrixB, double[][] matrixC) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
    }

    /**
     * Виводить матрицю на екран.
     *
     * @param matrix Матриця для виведення.
     */
    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%.2f ", value);
            }
            System.out.println();
        }
    }

    /**
     * Обчислює суму найбільших елементів у стовпцях з непарними номерами.
     *
     * @param matrix Матриця для обчислення.
     * @return Сума найбільших елементів.
     */
    private static double calculateOddColumnMaxSum(double[][] matrix) {
        double sum = 0;
        for (int j = 0; j < matrix[0].length; j += 2) { // Непарні стовпці
            double max = Double.NEGATIVE_INFINITY;
            for (double[] row : matrix) {
                max = Math.max(max, row[j]);
            }
            sum += max;
        }
        return sum;
    }

    /**
     * Обчислює суму найменших елементів у стовпцях з парними номерами.
     *
     * @param matrix Матриця для обчислення.
     * @return Сума найменших елементів.
     */
    private static double calculateEvenColumnMinSum(double[][] matrix) {
        double sum = 0;
        for (int j = 1; j < matrix[0].length; j += 2) { // Парні стовпці
            double min = Double.POSITIVE_INFINITY;
            for (double[] row : matrix) {
                min = Math.min(min, row[j]);
            }
            sum += min;
        }
        return sum;
    }
}
