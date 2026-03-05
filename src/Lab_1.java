import java.util.Scanner;
public class Lab_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Лабораторна робота 1 ");
        System.out.println("Оберіть завдання 1-5");
        int choise = new Scanner(System.in).nextInt();
        switch (choise) {
            case 1: {
                System.out.println("Ви вибрали завдання 1.Варіант№6.Елементарні оператори");
                double x1,x2,y1,y2;
                System.out.print("Введіть координату точки A x1: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                x1= scanner.nextDouble();
                System.out.print("Введіть координату точки A y1: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                y1= scanner.nextDouble();
                System.out.print("Введіть координату точки B x2: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                x2= scanner.nextDouble();
                System.out.print("Введіть координату точки B y2: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                y2= scanner.nextDouble();
                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                System.out.println("Відстань між точками А і В: " + distance);
                break;
            }
            case 2: {
                System.out.println("Ви вибрали завдання 2.Варіант№6.Умовні оператори");
                double x, y;
                System.out.print("Введіть координату x: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                x = scanner.nextDouble();
                System.out.print("Введіть координату y: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Помилка! Введіть число.");
                    scanner.next();
                }
                y = scanner.nextDouble();
                if (x * x + y * y <= 1) {
                    System.out.println("Точка (" + x + "; " + y + ")належить області");
                } else {
                    System.out.println("Точка (" + x + "; " + y + ") не належить області");
                }
                break;
            }
            case 3: {
                System.out.println("Ви вибрали завдання 3.Варіант№6.Цикли");
                long totalSum = 0;
                long factorial = 1;

                for (int i = 1; i <= 6; i++) {
                    factorial *= i;
                    totalSum += factorial;

                System.out.println(" " + i + "! = " + factorial + " Поточна сума S = " + totalSum);
            }
                System.out.println("Результат обчислення S = " + totalSum);
                break;
            }
            case 4: {
                System.out.println("Ви вибрали завдання 4.Варіант№6.Масиви");
                System.out.println("Введіть 18 дійсних чисел:");
                double[] numbers = new double[18];
                for (int i = 0; i < numbers.length; i++) {
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Помилка! Введіть число.");
                        scanner.next();
                    }
                    numbers[i] = scanner.nextDouble();
                }
                double max = numbers[0];
                double min = numbers[0];
                for (int i = 1; i < numbers.length; i++) {
                    if (numbers[i] > max) {
                        max = numbers[i];
                    }
                    if (numbers[i] < min) {
                        min = numbers[i];
                    }
                }
                System.out.println("Максимальне число: " + max);
                System.out.println("Мінімальне число: " + min);
                System.out.println("Різниця (max-min): " + (max - min));
                break;
            }
            case 5: {
                System.out.println("Завдання 5.Варіант№6.Методи:");
                double p1 = 0, p2 = 0, p3 = 0;

                for (int t = 1; t <= 3; t++) {
                    System.out.println("\nВведіть координати для трикутника №" + t);
                    double x1 = perevirka(scanner, "x1");
                    double y1 = perevirka(scanner, "y1");
                    double x2 = perevirka(scanner, "x2");
                    double y2 = perevirka(scanner, "y2");
                    double x3 = perevirka(scanner, "x3");
                    double y3 = perevirka(scanner, "y3");

                    double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                    double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
                    double c = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

                    double perimetr = a + b + c;
                    if (t == 1) p1 = perimetr;
                    else if (t == 2) p2 = perimetr;
                    else p3 = perimetr;
                }
                if (p1 < p2 && p1 < p3) {
                    System.out.println("Трикутник №1 має найменший периметр: " + p1);
                } else if (p2 < p1 && p2 < p3) {
                    System.out.println("Трикутник №2 має найменший периметр: " + p2);
                } else {
                    System.out.println("Трикутник №3 має найменший периметр: " + p3);
                }
                break;
            }
            default:
                System.out.println("Невірний вибір");

        }
    }

    public static double perevirka(Scanner scanner, String name) {
        System.out.print("Введіть " + name + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка! Введіть число.");
            scanner.next();
            System.out.print("Спробуйте ще раз для " + name + ": ");
        }
        return scanner.nextDouble();
    }
}