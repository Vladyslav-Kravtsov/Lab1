import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new GUI(); // Запуск GUI
        System.out.print("Введіть клас: ");
        String Class = scanner.next();
        Console.printClassDescription(Class); // Виклик аналізу через консоль
    }
}