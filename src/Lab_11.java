import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

public class Lab_11 {


    public static double task1FindDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }



    public static boolean task2IsInsideUnitCircle(double x, double y) {
        return (x * x + y * y) <= 1.0;
    }


    public static long task3CalculateSum() {
        long sum1 = 0;
        long sum2 = 0;
        long fact = 1;

        for (int i = 1; i <= 6; i++) {
            sum1 += (long) Math.pow(2, i);
            fact *= i;
            sum2 += (long) (fact - Math.pow(2, i));
        }
        return sum1 + sum2;
    }


    public static double task4FindMinMaxDifference(double[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Масив порожній");
        }
        double min = array[0], max = array[0];
        for (double val : array) {
            if (val < min) min = val;
            if (val > max) max = val;
        }
        return max - min;
    }


    public static int task5FindTriangleWithMinPerimeter(double[][] t1, double[][] t2, double[][] t3) {
        double p1 = getPerimeter(t1);
        double p2 = getPerimeter(t2);
        double p3 = getPerimeter(t3);

        if (p1 <= p2 && p1 <= p3) return 0;
        if (p2 <= p1 && p2 <= p3) return 1;
        return 2;
    }

    private static double getPerimeter(double[][] v) {
        double s1 = task1FindDistance(v[0][0], v[0][1], v[1][0], v[1][1]);
        double s2 = task1FindDistance(v[1][0], v[1][1], v[2][0], v[2][1]);
        double s3 = task1FindDistance(v[2][0], v[2][1], v[0][0], v[0][1]);
        return s1 + s2 + s3;
    }


    @Nested
    public class Lab1Tests {

        @Test
        public void testTask1() {
            System.out.println("-> Старт тесту 1: Перевірка відстані між точками (0,0) та (3,4)...");
            double res = task1FindDistance(0, 0, 3, 4);
            System.out.println("   Результат програми: " + res + " (Очікувалось: 5.0)");
            assertEquals(5.0, res, 0.0001);
            System.out.println("[OK] Тест 1 пройдено успішно!\n");
        }

        @Test
        public void testTask2() {
            System.out.println("-> Старт тесту 2: Перевірка точок всередині та ззовні кола...");
            boolean inside = task2IsInsideUnitCircle(0.5, 0.5);
            boolean outside = task2IsInsideUnitCircle(2.0, 2.0);
            System.out.println("   Точка (0.5, 0.5) всередині? " + inside + " (Очікувалось: true)");
            System.out.println("   Точка (2.0, 2.0) всередині? " + outside + " (Очікувалось: false)");
            assertTrue(inside);
            assertFalse(outside);
            System.out.println("[OK] Тест 2 пройдено успішно!\n");
        }

        @Test
        public void testTask3() {
            System.out.println("-> Старт тесту 3: Обчислення математичної суми рядів (1!+2!+...+6!)...");
            long sum = task3CalculateSum();
            System.out.println("   Результат програми: " + sum + " (Очікувалось: 873)");
            assertEquals(873, sum);
            System.out.println("[OK] Тест 3 пройдено успішно!\n");
        }

        @Test
        public void testTask4() {
            System.out.println("-> Старт тесту 4: Пошук різниці між max та min елементами масиву...");
            double[] arr = {10.0, -5.0, 15.0, 0.0};
            double diff = task4FindMinMaxDifference(arr);
            System.out.println("   Результат програми: " + diff + " (Очікувалось: 20.0)");
            assertEquals(20.0, diff, 0.0001);
            System.out.println("[OK] Тест 4 пройдено успішно!\n");
        }

        @Test
        public void testTask5() {
            System.out.println("-> Старт тесту 5: Визначення трикутника з найменшим периметром...");
            double[][] tr1 = {{0,0}, {0,3}, {4,0}}; // P = 12
            double[][] tr2 = {{0,0}, {0,1}, {1,0}}; // P = 3.41 (Найменший)
            double[][] tr3 = {{0,0}, {0,5}, {5,0}}; // P = 17.07

            int bestTriangle = task5FindTriangleWithMinPerimeter(tr1, tr2, tr3);
            System.out.println("   Індекс найменшого трикутника: " + bestTriangle + " (Очікувалось: 1)");
            assertEquals(1, bestTriangle);
            System.out.println("[OK] Тест 5 пройдено успішно!\n");
        }
    }
}