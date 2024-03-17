import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
class FunctionNotFoundException extends Exception {
    public FunctionNotFoundException(String message) {
        super(message);
    }
}
class Task3 {
    static class MethodParameter {
        private final Class<?> type;
        private final Object value;

        public MethodParameter(Class<?> type, Object value) {
            this.type = type;
            this.value = value;
        }

        public Class<?> getType() {
            return type;
        }

        public Object getValue() {
            return value;
        }
    }
    public static void callMethod(Object obj, String methodName, MethodParameter... params) throws FunctionNotFoundException {
        Class<?>[] paramTypes = new Class<?>[params.length];
        Object[] paramValues = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            MethodParameter param = params[i];
            paramTypes[i] = param.getType();
            paramValues[i] = param.getValue();
        }

        try {
            Method method = obj.getClass().getMethod(methodName, paramTypes);
            Object result = method.invoke(obj, paramValues);
            System.out.println("Результат виклику методу " + methodName + ":" + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FunctionNotFoundException("Метод " + methodName + " не знайдено або не може бути викликаний.");
        }
    }

    public double func(double a) {
            return Math.exp(-Math.abs(a) * x) * Math.sin(x);
        }

        public double pow(double a, int b) {
            return Math.pow(a, b) + x;
        }

        private final double x = 1.0;
    }