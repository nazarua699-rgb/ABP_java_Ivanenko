import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lab_7 extends JFrame {
    private JTextField nameField, priceField, quantityField;
    private JButton calculateButton;

    private static final Color BG_COLOR = new Color(242, 222, 201);

    public Lab_7() {

        setTitle("Магазин - Введення даних");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));


        getContentPane().setBackground(BG_COLOR);


        add(new JLabel(" Назва товару:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel(" Ціна за шт:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel(" Кількість (шт):"));
        quantityField = new JTextField();
        add(quantityField);


        calculateButton = new JButton("Розрахувати суму");
        add(new JLabel(""));
        add(calculateButton);


        applyButtonStyle(calculateButton);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    double total = price * quantity;


                    SecondForm secondForm = new SecondForm(total);
                    secondForm.setVisible(true);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Помилка! Введіть числові значення для ціни та кількості.",
                            "Помилка введення",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        setLocationRelativeTo(null);
    }


    private void applyButtonStyle(JButton button) {
        // Початковий стиль: проста кнопка
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);

        button.setContentAreaFilled(true);


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.BLACK);
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Lab_7().setVisible(true);
        });
    }
}


class SecondForm extends JFrame {
    // Такий же колір фону
    private static final Color BG_COLOR = new Color(242, 222, 201);
    private JButton okButton;

    public SecondForm(double totalSum) {
        setTitle("Загальна сума покупки");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        getContentPane().setBackground(BG_COLOR);


        JLabel resultLabel = new JLabel("До сплати: " + totalSum + " грн", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(resultLabel, BorderLayout.CENTER);

        okButton = new JButton("Зрозуміло");
        okButton.addActionListener(e -> dispose());
        add(okButton, BorderLayout.SOUTH);


        applyButtonStyle(okButton);

        setLocationRelativeTo(null);
    }


    private void applyButtonStyle(JButton button) {
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setContentAreaFilled(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.BLACK);
            }
        });
    }
}