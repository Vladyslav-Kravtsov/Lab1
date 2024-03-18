import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

interface Evaluatable {
    double eval(double x);
}

class Function1 implements Evaluatable {
    public double eval(double x) {
        return Math.exp(-Math.abs(2.5 * x)) * Math.sin(x);
    }
}

class Function2 implements Evaluatable {
    public double eval(double x) {
        return x * x;
    }
}

class ProfilingInvocationHandler implements InvocationHandler {
    private final Object target;

    public ProfilingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();

        String methodName = "[" + target.getClass().getSimpleName() + "]." + method.getName();
        if (args != null && args.length > 0) {
            methodName += "(";
            for (int i = 0; i < args.length; i++) {
                methodName += args[i];
                if (i < args.length - 1) {
                    methodName += ", ";
                }
            }
            methodName += ")";
        }

        System.out.println(methodName + " took " + (endTime - startTime) + " ns");

        return result;
    }
}

class TracingInvocationHandler implements InvocationHandler {
    private final Object target;

    public TracingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = "[" + target.getClass().getSimpleName() + "]." + method.getName();
        if (args != null && args.length > 0) {
            methodName += "(";
            for (int i = 0; i < args.length; i++) {
                methodName += args[i];
                if (i < args.length - 1) {
                    methodName += ", ";
                }
            }
            methodName += ")";
        }

        System.out.println(methodName);

        Object result = method.invoke(target, args);

        if (!method.getName().equals("eval")) {
            System.out.println(methodName + " = " + result);
        }

        return result;
    }
}
