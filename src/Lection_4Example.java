import java.util.Scanner;
abstract class Plan{
    abstract void syuding();
    abstract void checkGrade(double  grade);
}
class AOP extends Plan{
    @Override
    void syuding() {
        System.out.println("Я навчаюся програмуванню на ");
    }

    @Override
    void checkGrade(double grade) {
        if (grade >= 60) {
            System.out.println("Засвоїв предмет");
        } else {
            System.out.println("Нескладено");
        }
    }
}
public class Lection_4Example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Plan myPlan = new AOP();
        myPlan.syuding();
        System.out.print("Введіть оцінку: ");
        double grade;
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка! Введіть ціле число.");
            scanner.next();
        }
        grade = scanner.nextDouble();
        myPlan.checkGrade(grade);
    }
}
