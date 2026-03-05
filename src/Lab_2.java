import java.util.Scanner;

class Cube {
    public double side;
    // Конструктор для ініціалізації сторони куба
    public Cube(double side){
        this.side = side;
    }
    public double volume(){
        return Math.pow(side,3);
    }
    public double faceArea(){
        return 6* Math.pow(side, 2);
    }
    public double spaceDiagonal(){
        return side * Math.sqrt(3);
    }
}

public class Lab_2 {
    public static void main(String[] args){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Лабораторна робота 2");
        System.out.print("Введіть довжину ребра куба: ");
        double side;

        do {
            System.out.print("Введіть сторону куба: ");

            while (!scanner.hasNextDouble()) {
                System.out.println("Помилка! Введіть число.");
                scanner.next();
            }

            side = scanner.nextDouble();

            if (side < 0) {
                System.out.println("Число не може бути від'ємним.");
            }

        } while (side < 0);
        Cube cube = new Cube(side);
            System.out.printf("Об'єм куба: %.3f\n", cube.volume());
        System.out.printf("Площа грані куба: %.3f\n", cube.faceArea());
        System.out.printf("Довжина просторової діагоналі куба: %.3f\n", cube.spaceDiagonal());

    }
}
