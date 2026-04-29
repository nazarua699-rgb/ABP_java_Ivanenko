import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Lab_6 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FontChangerApp app = new FontChangerApp();
            app.setVisible(true);
        });
    }
}
class FontChangerApp extends JFrame {


    private JLabel textLabel;
    private JButton changeTypeBtn, changeStyleBtn, resetBtn;


    private final String defaultFontFamily = "Arial";
    private final int defaultFontStyle = Font.PLAIN;
    private final int defaultFontSize = 24;


    private String currentFontFamily = defaultFontFamily;
    private int currentFontStyle = defaultFontStyle;


    private String[] fontFamilies = {"Arial", "Courier New", "Times New Roman", "Verdana"};
    private int fontIndex = 0;

    private int[] fontStyles = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};
    private int styleIndex = 0;

    public FontChangerApp() {

        setTitle("Редактор шрифту");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);


        textLabel = new JLabel("Факультет інформаційних технологій", SwingConstants.CENTER);
        textLabel.setFont(new Font(currentFontFamily, currentFontStyle, defaultFontSize));
        add(textLabel, BorderLayout.CENTER); // Додаємо надпис по центру


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.LIGHT_GRAY);


        changeTypeBtn = new JButton("Змінити тип шрифту");
        changeStyleBtn = new JButton("Змінити стиль шрифту");
        resetBtn = new JButton("Скасувати");
        changeTypeBtn.setBackground(Color.BLACK);
        changeTypeBtn.setForeground(Color.WHITE);

        changeStyleBtn.setBackground(Color.BLUE);
        changeStyleBtn.setForeground(Color.WHITE);

        resetBtn.setBackground(Color.RED);
        resetBtn.setForeground(Color.WHITE);


        buttonPanel.add(changeTypeBtn);
        buttonPanel.add(changeStyleBtn);
        buttonPanel.add(resetBtn);


        add(buttonPanel, BorderLayout.SOUTH);




        changeTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fontIndex = (fontIndex + 1) % fontFamilies.length;
                currentFontFamily = fontFamilies[fontIndex];
                updateFont();
            }
        });

        changeStyleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                styleIndex = (styleIndex + 1) % fontStyles.length;
                currentFontStyle = fontStyles[styleIndex];
                updateFont();
            }
        });


        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fontIndex = 0;
                styleIndex = 0;
                currentFontFamily = defaultFontFamily;
                currentFontStyle = defaultFontStyle;
                updateFont();
            }
        });
    }


    private void updateFont() {
        textLabel.setFont(new Font(currentFontFamily, currentFontStyle, defaultFontSize));
    }
}