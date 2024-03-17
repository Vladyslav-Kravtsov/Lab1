import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Завдання №1");
        new GUI();
        System.out.print("Введіть клас: ");
        String Class = scanner.next();
        Console.printClassDescription(Class); // Виклик аналізу через консоль
        System.out.println("\n\n");

        System.out.println("Завдання №2");
        Check obj2 = new Check(3.0, 4.0);
        Task2.inspect(obj2);
        System.out.println("\n\n");

        System.out.println("Завдання №3");
        Task3 obj3 = new Task3();
        try {
            Task3.callMethod(obj3, "func", new Task3.MethodParameter(double.class, 1.0));
            Task3.callMethod(obj3, "pow", new Task3.MethodParameter(double.class, 1.0),
                    new Task3.MethodParameter(int.class, 0));
        } catch (FunctionNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n\n");

    }
}