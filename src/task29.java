import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class task29 extends JFrame {
    private JTextField inputField;
    private ResultForm13 resultForm;

    public task29() {
        setTitle("Задача 29: Форма 1 (Введення даних)");
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

            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Поле введення порожнє!", "Помилка", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (text.matches(".*[а-яА-ЯіІїЇєЄґҐ].*")) {
                JOptionPane.showMessageDialog(this, "Помилка: Українська мова неприпустима!Введіть будь ласка англійську", "Помилка введення", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (resultForm == null || !resultForm.isDisplayable()) {
                resultForm = new ResultForm13();
            }

            resultForm.setData(text);
            resultForm.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new task29().setVisible(true));
    }
}

class ResultForm13 extends JFrame {
    private String inputData;
    private int fResult;
    private String rawValueStr;
    private int shapeSize;

    private JLabel trueValueLabel;
    private JPanel drawPanel;

    public ResultForm13() {
        setTitle("Задача 29: Форма 2 (Відображення результату)");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(0, 255, 0, 128));

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int h = (int) (shapeSize * Math.sqrt(3) / 2);

                int[] xPoints = { centerX, centerX - shapeSize / 2, centerX + shapeSize / 2 };
                int[] yPoints = { centerY - h / 2, centerY + h / 2, centerY + h / 2 };

                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        };


        trueValueLabel = new JLabel("", SwingConstants.CENTER);
        trueValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        labelPanel.add(trueValueLabel, BorderLayout.CENTER);

        add(drawPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.SOUTH);
    }

    public void setData(String data) {
        this.inputData = data;
        calculateFunction();

        int modSize = fResult % 20;
        if (modSize < 0) modSize += 20;
        shapeSize = modSize * 10;

        if (shapeSize == 0) shapeSize = 30;


        trueValueLabel.setText("Справжнє (не модульне) значення функції: " + rawValueStr);

        drawPanel.repaint();
    }

    private void calculateFunction() {
        try {
            if (inputData.contains(".") || inputData.contains(",")) {
                double x = Double.parseDouble(inputData.replace(",", "."));
                double sinVal = Math.sin(55 * x);

                if (Math.abs(sinVal) < 1e-9) {
                    throw new ArithmeticException("Ділення на нуль! sin(55x) близький або рівний 0.");
                }

                double rawValue = 1.0 / sinVal;
                rawValueStr = String.format("%.4f", rawValue);

                int floorVal = (int) Math.floor(rawValue);
                int mod = floorVal % 329;
                if (mod < 0) mod += 329;
                fResult = mod;

            } else {
                try {
                    long x = Long.parseLong(inputData);

                    if (x >= 1) {
                        BigInteger factorial = BigInteger.ONE;
                        for (int i = 2; i <= x; i++) {
                            factorial = factorial.multiply(BigInteger.valueOf(i));
                        }
                        BigInteger trueVal = factorial.add(BigInteger.valueOf(x)).subtract(BigInteger.ONE);
                        rawValueStr = trueVal.toString();

                        fResult = trueVal.mod(BigInteger.valueOf(129)).intValue();

                    } else {
                        BigInteger trueVal = BigInteger.valueOf(x).pow(5);
                        rawValueStr = trueVal.toString();

                        BigInteger modVal = trueVal.mod(BigInteger.valueOf(229));
                        fResult = modVal.intValue();
                    }

                } catch (NumberFormatException e) {
                    int count = 0;
                    for (char c : inputData.toCharArray()) {
                        if (c >= 'A' && c <= 'Z') {
                            count++;
                        }
                    }
                    rawValueStr = String.valueOf(count);
                    fResult = count;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Помилка обчислення: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            rawValueStr = "Помилка через виняток";
            fResult = 0;
        }
    }
}