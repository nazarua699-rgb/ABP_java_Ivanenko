import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class Lab_9 extends JFrame {
    private Connection connection;
    private Statement statement;
    private BanquetData banquetData;
    private Color bgColor = new Color(242, 222, 201);

    public Lab_9() {
        initConnection();
        buildUI();
        loadDataFromDB();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) { ex.printStackTrace(); }
                System.exit(0);
            }
        });
    }

    private void initConnection() {
        try {
            String url = "jdbc:ucanaccess://Banquethall.accdb";
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (Exception e) {
            showCustomMessage("Помилка", "Не вдалося підключитися до бази даних");
        }
    }

    private void buildUI() {
        setSize(1250, 600);
        setTitle("Лабораторна №9 - Банкетний зал");
        setLayout(new BorderLayout());
        getContentPane().setBackground(bgColor);

        banquetData = new BanquetData();
        JTable table = new JTable(banquetData);
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel controlPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        controlPanel.setBackground(bgColor);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = createSmartField("Назва заходу");
        JTextField dateField = createSmartField("Дата (YYYY-MM-DD)");
        JTextField priceField = createSmartField("Вартість");
        JTextField guestsField = createSmartField("Кількість гостей");

        JButton addButton = new JButton("Додати запис");
        JButton delButton = new JButton("Видалити обране");

        styleMyButton(addButton);
        styleMyButton(delButton);

        controlPanel.add(nameField);
        controlPanel.add(dateField);
        controlPanel.add(priceField);
        controlPanel.add(guestsField);
        controlPanel.add(addButton);
        controlPanel.add(delButton);

        add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            if (isDefault(nameField, "Назва заходу") || isDefault(priceField, "Вартість") ||
                    isDefault(guestsField, "Кількість гостей")) {
                showCustomMessage("Увага", "Спершу введіть реальні дані");
                return;
            }

            try {
                String sql = String.format(
                        "INSERT INTO Banquethall (client_name, event_date, guests_count, total_price) VALUES ('%s', '%s', %s, %s)",
                        nameField.getText(), dateField.getText(), guestsField.getText(), priceField.getText()
                );
                statement.executeUpdate(sql);
                loadDataFromDB();
            } catch (SQLException ex) {
                showCustomMessage("Помилка даних", "Число занадто велике або невірний формат!");
            }
        });

        delButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                try {
                    String id = banquetData.alData.get(row).id;
                    statement.executeUpdate("DELETE FROM Banquethall WHERE id = " + id);
                    loadDataFromDB();
                } catch (SQLException ex) {
                    showCustomMessage("Помилка", "Не вдалося видалити запис");
                }
            } else {
                showCustomMessage("Увага", "Виберіть рядок у таблиці");
            }
        });
    }

    private JTextField createSmartField(String hint) {
        JTextField field = new JTextField(hint);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(hint)) field.setText("");
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) field.setText(hint);
            }
        });
        return field;
    }

    private boolean isDefault(JTextField f, String hint) {
        return f.getText().trim().isEmpty() || f.getText().equals(hint);
    }

    private void styleMyButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.BLACK);
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });
    }

    private void showCustomMessage(String title, String message) {
        final JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(bgColor);
        JButton okBtn = new JButton("OK");
        styleMyButton(okBtn);
        okBtn.setPreferredSize(new Dimension(80, 30));
        okBtn.addActionListener(a -> dialog.dispose());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel(message), gbc);
        gbc.gridy = 1; gbc.insets = new Insets(10, 20, 20, 20);
        dialog.add(okBtn, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void loadDataFromDB() {
        this.banquetData.alData.clear();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Banquethall");
            while (rs.next()) {
                RowBanquet row = new RowBanquet(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)
                );
                this.banquetData.alData.add(row);
            }
            this.banquetData.fireTableDataChanged();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    class RowBanquet {
        String id, name, date, guests, price;
        public RowBanquet(String id, String name, String date, String guests, String price) {
            this.id = id; this.name = name; this.date = date;
            this.guests = guests; this.price = price;
        }
    }

    class BanquetData extends AbstractTableModel {

        String[] columns = {"№", "ID(номер з бази даних)", "Клієнт", "Дата", "Гості", "Ціна(грн)"};
        public List<RowBanquet> alData = new ArrayList<>();

        public int getColumnCount() { return columns.length; }
        public int getRowCount() { return alData.size(); }
        public String getColumnName(int col) { return columns[col]; }

        public Object getValueAt(int r, int c) {
            if (r < 0 || r >= alData.size()) return null;
            RowBanquet row = alData.get(r);
            switch (c) {
                case 0: return (r + 1);
                case 1: return row.id;     
                case 2: return row.name;
                case 3: return row.date;
                case 4: return row.guests;
                case 5: return row.price;
                default: return null;
            }
        }
    }

    public static void main(String[] args) {
        new Lab_9();
    }
}