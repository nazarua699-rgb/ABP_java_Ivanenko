import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Lab_5 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Лабораторна робота 5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(242, 222, 201));
        GridBagConstraints gbs = new GridBagConstraints();
        gbs.insets = new Insets(10, 10, 10, 10);


        java.net.URL imgURL = Lab_5.class.getResource("/ed.png");
        JLabel iconLabel = new JLabel();


        ImageIcon originalIcon = new ImageIcon(imgURL);
        Image scaledImg = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImg));


        gbs.gridx = 0;
        gbs.gridy = 0;
        mainPanel.add(iconLabel, gbs);

        JPanel LoginPanel = new JPanel(new GridBagLayout());
        Border line = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY);
        TitledBorder titled = BorderFactory.createTitledBorder(line, "Ідентифікація");
        titled.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
        titled.setTitleColor(Color.BLACK);

        Border empty = BorderFactory.createEmptyBorder(20, 0, 20, 20);
        LoginPanel.setBorder(BorderFactory.createCompoundBorder(titled, empty));
        LoginPanel.setOpaque(false);

        GridBagConstraints innerGbs = new GridBagConstraints();
        innerGbs.insets = new Insets(5, 5, 5, 5);
        innerGbs.anchor = GridBagConstraints.WEST;


        innerGbs.gridx = 0; innerGbs.gridy = 0;
        LoginPanel.add(new JLabel("Користувач: "), innerGbs);

        innerGbs.gridx = 1; innerGbs.gridy = 0;
        LoginPanel.add(new JTextField(15), innerGbs);


        innerGbs.gridx = 0; innerGbs.gridy = 1;
        LoginPanel.add(new JLabel("Пароль: "), innerGbs);

        innerGbs.gridx = 1; innerGbs.gridy = 1;
        LoginPanel.add(new JPasswordField(15), innerGbs);


        innerGbs.gridx = 2; innerGbs.gridy = 0;
        innerGbs.gridheight = 2;
        JCheckBox rememberBox = new JCheckBox("Запам'ятати");
        rememberBox.setOpaque(false);
        rememberBox.setFont(new Font("Tahoma", Font.ITALIC, 12));
        LoginPanel.add(rememberBox, innerGbs);

        gbs.gridx = 0;
        gbs.gridy = 1;
        gbs.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(LoginPanel, gbs);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton button1 = new JButton("Увійти");
        button1.setBackground(new Color(128, 128, 128));
        button1.setForeground(Color.WHITE);
        button1.setFocusPainted(false);
        button1.setFont(new Font("Tahoma", Font.BOLD, 12));

        JButton button2 = new JButton("Скасувати");
        button2.setBackground(new Color(128, 128, 128));
        button2.setForeground(Color.WHITE);
        button2.setFocusPainted(false);
        button2.setFont(new Font("Tahoma", Font.BOLD, 12));

        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1.setForeground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button1.setForeground(Color.WHITE);
            }
        });


        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button2.setForeground(Color.BLACK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button2.setForeground(Color.WHITE);
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        gbs.gridx = 0;
        gbs.gridy = 2;
        gbs.fill = GridBagConstraints.NONE;
        mainPanel.add(buttonPanel, gbs);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}