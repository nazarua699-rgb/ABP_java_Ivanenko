import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Lab_8 extends JFrame {
    private JLabel mainLabel;
    private JLabel infoLabel;

    private final String[] fontTypes = {"Segoe UI", "Serif", "Monospaced", "Verdana", "Arial"};
    private final int[] styles = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};
    private int currentTypeIdx = 0;
    private int currentStyleIdx = 0;

    private final Color bgColor = new Color(35, 35, 35);
    private final Color barColor = new Color(45, 45, 45);
    private final Color accentColor = new Color(100, 200, 255);

    public Lab_8() {
        setTitle(" Lab 8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(bgColor);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        topPanel.setOpaque(false);

        infoLabel = new JLabel("Інформація: Segoe UI");
        infoLabel.setForeground(new Color(180, 180, 180));
        infoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        topPanel.add(infoLabel);
        add(topPanel, BorderLayout.NORTH);


        mainLabel = new JLabel("ASUS Vivobook", SwingConstants.CENTER);
        mainLabel.setFont(new Font(fontTypes[0], Font.PLAIN, 32));
        mainLabel.setForeground(Color.WHITE);
        add(mainLabel, BorderLayout.CENTER);


        setupBottomMenu();


        setupHotkeys();
    }

    private void setupBottomMenu() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 12));
        bottomPanel.setBackground(barColor);
        bottomPanel.setPreferredSize(new Dimension(0, 55));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        JLabel menuTitle = new JLabel("Меню:");
        menuTitle.setForeground(accentColor);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bottomPanel.add(menuTitle);

        bottomPanel.add(createMenuButton("Змінити тип шрифту (Ctrl+T)", e -> toggleType()));
        bottomPanel.add(createMenuButton("Змінити стиль (Ctrl+B)", e -> toggleStyle()));
        bottomPanel.add(createMenuButton("Скасувати (Ctrl+Z)", e -> resetAll()));

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.addActionListener(action);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setForeground(accentColor); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setForeground(Color.WHITE); }
        });

        return btn;
    }

    private void setupHotkeys() {
        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK), "changeType");
        root.getActionMap().put("changeType", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { toggleType(); }
        });

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), "changeStyle");
        root.getActionMap().put("changeStyle", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { toggleStyle(); }
        });

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "reset");
        root.getActionMap().put("reset", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { resetAll(); }
        });
    }

    private void toggleType() {
        currentTypeIdx = (currentTypeIdx + 1) % fontTypes.length;
        updateUI();
        infoLabel.setText("Інформація: тип змінено на " + fontTypes[currentTypeIdx]);
    }

    private void toggleStyle() {
        currentStyleIdx = (currentStyleIdx + 1) % styles.length;
        updateUI();
        infoLabel.setText("Інформація: змінено стиль шрифту");
    }

    private void resetAll() {
        currentTypeIdx = 0;
        currentStyleIdx = 0;
        updateUI();
        infoLabel.setText("Інформація: скинуто");
    }

    private void updateUI() {
        mainLabel.setFont(new Font(fontTypes[currentTypeIdx], styles[currentStyleIdx], 32));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new Lab_8().setVisible(true));
    }
}