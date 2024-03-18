import javax.swing.*;
import java.awt.*;
public class GUI {
    private final JTextField inputField;
    private final JTextArea outputArea;

    public GUI() {
        JFrame frame = new JFrame("Class Analyzer");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Використовуємо FlowLayout для розміщення кнопок в один ряд
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 30));

        JButton analyzeButton = new JButton("Аналіз");
        JButton clearButton = new JButton("Очистити");

        // Додаємо кнопки до панелі з використанням FlowLayout
        inputPanel.add(inputField);
        inputPanel.add(analyzeButton);
        inputPanel.add(clearButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);

        analyzeButton.addActionListener(e -> {
            String className = inputField.getText();
            try {
                Task1 analyzer = new Task1(className);
                outputArea.setText(analyzer.toString());
            } catch (ClassNotFoundException ex) {
                outputArea.setText("Class not found");
                ex.printStackTrace();
            }
        });

        clearButton.addActionListener(e -> {
            outputArea.setText(null);
            inputField.setText(null);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}