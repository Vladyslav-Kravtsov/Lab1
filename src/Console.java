public class Console {
    public static void printClassDescription(String typeName) {
        try {
            Task1 analizator = new Task1(typeName);
            System.out.println(analizator);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
