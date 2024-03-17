import java.lang.reflect.Proxy;
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


        System.out.println("Завдання №4");
        // Створення масиву типу int
        Integer[] intArray = Task4.createArray(Integer.class, 2);
        System.out.println("int[] = " + Task4.arrayToString(intArray));

        // Створення масиву типу String
        String[] stringArray = Task4.createArray(String.class, 3);
        System.out.println("String[] = " + Task4.arrayToString(stringArray));

        // Створення масиву типу double
        Double[] doubleArray = Task4.createArray(Double.class, 5);
        System.out.println("double[] = " + Task4.arrayToString(doubleArray));

        // Створення матриці типу int
        Integer[][] intMatrix = Task4.createMatrix(Integer.class, 3, 5);
        System.out.println("int[][] = " + Task4.arrayToString(intMatrix));

        //Зміна розмірів масиву типу String
        stringArray = Task4.resizeArray(stringArray, 2);
        System.out.println("String[] = " + Task4.arrayToString(stringArray));

        // Зміна розмірів матриці
        intMatrix = Task4.resizeMatrix(intMatrix, 4, 6);
        System.out.println("int[][] = " + Task4.arrayToString(intMatrix));

        // Зміна розмірів матриці
        intMatrix = Task4.resizeMatrix(intMatrix, 3, 7);
        System.out.println("int[][] = " + Task4.arrayToString(intMatrix));

        // Задання деяких значень матриці
        intMatrix[0][0] = 0;
        intMatrix[0][1] = 1;
        intMatrix[1][0] = 10;
        intMatrix[1][1] = 11;
        System.out.println("int[][] = " + Task4.arrayToString(intMatrix));
        System.out.println("\n\n");

        System.out.println("Завдання №5");
        Evaluatable f1 = new Function1();
        Evaluatable f2 = new Function2();

        Evaluatable f1Proxy = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new ProfilingInvocationHandler(f1));

        Evaluatable f2Proxy = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new ProfilingInvocationHandler(f2));

        f1Proxy = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new TracingInvocationHandler(f1Proxy));

        f2Proxy = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[]{Evaluatable.class},
                new TracingInvocationHandler(f2Proxy));

        System.out.println("[Exp(-|2.5| * x) * sin(x)].eval(1.0) = " + f1Proxy.eval(1.8));
        System.out.println("\n");
        System.out.println("[x * x].eval(1.0) = " + f2Proxy.eval(1.0));
    }
}