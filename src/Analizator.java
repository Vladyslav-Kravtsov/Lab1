import java.lang.reflect.*;
public class Analizator {
    //Поля
    public static String GetClassDescription(String typeName) throws ClassNotFoundException{
        Class <?> cls = Class.forName(typeName);
        return getClassDescription(cls);
    }

    private static String getClassDescription(Class <?> cls) {
        StringBuilder description = new StringBuilder();
        Package pkg = cls.getPackage();
        description.append("Package: ").append(pkg != null ? pkg.getName() : "No package").append("\n");

        int modifiers = cls.getModifiers();
        description.append("Modifiers: ").append(Modifier.toString(modifiers)).append("\n");

        description.append("Class Name: ").append(cls.getSimpleName()).append("\n");

        Class<?> superClass = cls.getSuperclass();
        description.append("Super Class: ").append(superClass != null ? superClass.getSimpleName() : "None").append("\n");

        Class<?> [] interfaces = cls.getInterfaces();
        description.append("Interfaces: ");
        if (interfaces.length > 0){
            for (Class<?> interf : interfaces) {
                description.append(interf.getSimpleName()).append(", ");
            }
            description.delete(description.length() - 2, description.length());
        } else {
            description.append("None");
        }
        description.append("\n");

        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        description.append("Constructors: ");
        if (constructors.length > 0) {
            for (Constructor<?> constructor : constructors) {
                description.append(constructor.getName()).append(", ");
            }
            description.delete(description.length() - 2, description.length());
        } else {
            description.append("None");
        }
        description.append("\n");

        Method[] methods = cls.getDeclaredMethods();
        description.append("Methods:\n");
        if (methods.length > 0) {
            for (Method method : methods) {
                description.append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("()\n ");
            }
            description.delete(description.length() - 2, description.length());
        } else {
            description.append("None");
        }
        description.append("\n");

        return description.toString();
    }
}
