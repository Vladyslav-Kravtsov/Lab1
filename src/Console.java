public class Console {
    public static void printClassDescription(String typeName) {
        try {
            Analizator analizator = new Analizator(typeName);
            System.out.println(analizator);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        printClassDescription("java.lang.String");
    }
}
