import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву класу: ");
        String Class = scanner.next();
        String description = Analizator.GetClassDescription(Class);
        System.out.println(description);
    }
}