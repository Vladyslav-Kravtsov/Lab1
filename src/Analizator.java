import java.lang.reflect.*;

public class Analizator {
    private final String packageName;
    private final String modifiers;
    private final String className;
    private final String interfaces;
    private final String Fields;
    private final String constructors;
    private final String methods;

    public Analizator(String typeName) throws ClassNotFoundException {
        Class<?> cls = Class.forName(typeName);
        packageName = (cls.getPackage() != null) ? cls.getPackage().getName() : "No package";

        modifiers = Modifier.toString(cls.getModifiers());
        className = cls.getSimpleName();

        Class<?>[] interfaceList = cls.getInterfaces();
        if (interfaceList.length > 0) {
            StringBuilder interfaceNames = new StringBuilder();
            for (Class<?> interf : interfaceList) {
                interfaceNames.append(interf.getSimpleName()).append(", ");
            }
            interfaceNames.delete(interfaceNames.length() - 2, interfaceNames.length());
            interfaces = interfaceNames.toString();
        } else {
            interfaces = "None";
        }

        Field [] FieldsList = cls.getFields();
        if (FieldsList.length > 0){
            StringBuilder FieldsNames = new StringBuilder();
            for (Field field : FieldsList) {
                FieldsNames.append(field.getName()).append("\n");
            }
            FieldsNames.delete(FieldsNames.length() - 2, FieldsNames.length());
            Fields = FieldsNames.toString();
        } else Fields = "None";

        Constructor<?>[] constructorList = cls.getDeclaredConstructors();
        if (constructorList.length > 0) {
            StringBuilder constructorNames = new StringBuilder();
            for (Constructor<?> constructor : constructorList) {
                String constructorParams = getConstructorParams(constructor);
                constructorNames.append("public ").append(className).append("(").append(constructorParams).append(");\n");
            }
            constructorNames.delete(constructorNames.length() - 2, constructorNames.length());
            constructors = constructorNames.toString();
        } else constructors = "None";

        Method[] methodList = cls.getDeclaredMethods();
        if (methodList.length > 0) {
            StringBuilder methodNames = new StringBuilder();
            for (Method method : methodList) {
                methodNames.append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("()\n");
            }
            methodNames.delete(methodNames.length() - 2, methodNames.length());
            methods = methodNames.toString();
        } else {
            methods = "None";
        }
    }

    private String getConstructorParams(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        StringBuilder params = new StringBuilder();
        for (Parameter param : parameters) {
            String paramType = param.getType().getSimpleName();
            String paramName = param.getName();
            params.append(paramType).append(" ").append(paramName).append(", ");
        }
        if (params.length() > 0) {
            params.delete(params.length() - 2, params.length());
        }
        return params.toString();
    }

    @Override
    public String toString() {
        return "Package: " + packageName + "\n\n" +
                modifiers + " " + className + " implements " + interfaces + "{" +
                "\n   //Поля\n" +
                Fields +
                "\n   //Конструктори\n" +
                constructors +
                "\n   //Методи\n" +
                methods + "}";
    }
}
