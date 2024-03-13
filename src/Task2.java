import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

class Task2 {
    public static void inspect(Object obj) {
        System.out.println("Стан об'єкту:");
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getType().getName() + " " + field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Список відкритих методів:");
        Method[] methods = cls.getDeclaredMethods();
        int count = 1;
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                System.out.println(count + ") " + method.toString());
                count++;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть порядковий номер методу [1, " + (count - 1) + "]: ");
        int choice = scanner.nextInt();
        if (choice >= 1 && choice < count) {
            Method selectedMethod = methods[choice - 1];
            selectedMethod.setAccessible(true);
            try {
                Object result = selectedMethod.invoke(obj);
                System.out.println("Результат виклику методу: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Неправильний вибір методу.");
        }
    }
}

class Check {
    private double x;
    private double y;

    public Check(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double Dist() {
        return Math.sqrt(x * x + y * y);
    }

    public void setRandomData() {
        this.x = Math.random() * 10;
        this.y = Math.random() * 10;
    }

    @Override
    public String toString() {
        return "Check{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}