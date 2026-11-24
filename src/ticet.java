import javax.swing.*;
import java.awt.*;

public class ticet extends JFrame {
    private JTextField inputField;

    public ticet() {
        setTitle("Білет 2: Форма 1 (Введення)");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        inputField = new JTextField(20);
        JButton btnCalc = new JButton("Обчислити");

        add(new JLabel("Вхідні дані:"));
        add(inputField);
        add(btnCalc);

        btnCalc.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                new ResultForm20(text).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ticet().setVisible(true));
    }
}

class ResultForm20 extends JFrame {
    private String inputData;
    private double fResult;
    private int shapeSize;

    public ResultForm20(String data) {
        this.inputData = data;
        setTitle("Білет 20: Форма 2 (Результат)");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        calculateFunction();

        // Розмір квадрата: (f(x) mod 20) * 10
        shapeSize = Math.abs((int) fResult % 20) * 10;
        if (shapeSize == 0) shapeSize = 20;

        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Червоний колір, 50% прозорості (альфа = 128)
                g2d.setColor(new Color(255, 0, 0, 128));

                int x = (getWidth() - shapeSize) / 2;
                int y = (getHeight() - shapeSize) / 2;
                g2d.fillRect(x, y, shapeSize, shapeSize);
            }
        };

        JLabel resultLabel = new JLabel("Значення f(x) = " + fResult, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(drawPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private void calculateFunction() {
        try {
            if (inputData.contains(".") || inputData.contains(",")) {
                // Дійсне число
                double x = Double.parseDouble(inputData.replace(",", "."));
                if (Math.sin(x) == 0) throw new ArithmeticException("Ділення на нуль (sin(x) = 0)");
                fResult = Math.floor(Math.exp(1.0 / Math.sin(x))) % 302;
            } else {
                try {
                    int val = Integer.parseInt(inputData);
                    if (val >= 0) {
                        // Натуральне число
                        fResult = factorial(val) % 202;
                    } else {
                        // Від'ємне ціле
                        fResult = (Math.abs(val) * 7 + Math.pow(val, 3)) % 202;
                    }
                } catch (NumberFormatException e) {
                    // Рядок: рахуємо голосні англійські літери
                    fResult = inputData.toLowerCase().replaceAll("[^aeiouy]", "").length();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Помилка: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            fResult = 0;
        }
    }

    private double factorial(int n) {
        double res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }
}