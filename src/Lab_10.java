import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MyShape {
    String type;
    Color color;
    int x, y;
    int width, height;

    public MyShape(String type, String colorName, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = parseColor(colorName);
    }

    private Color parseColor(String colorName) {
        switch (colorName.toLowerCase().trim()) {
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            case "green": return Color.GREEN;
            case "yellow": return Color.YELLOW;
            case "black": return Color.BLACK;
            default: return Color.GRAY;
        }
    }
}

class DrawingPanel extends JPanel {
    private List<MyShape> shapes;

    public DrawingPanel(List<MyShape> shapes) {
        this.shapes = shapes;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (MyShape shape : shapes) {
            g2d.setColor(shape.color);

            if (shape.type.equalsIgnoreCase("circle")) {
                g2d.fillOval(shape.x, shape.y, shape.width, shape.height);
            } else if (shape.type.equalsIgnoreCase("rectangle")) {
                g2d.fillRect(shape.x, shape.y, shape.width, shape.height);
            } else if (shape.type.equalsIgnoreCase("triangle")) {

                int[] xPoints = {
                        shape.x + shape.width / 2,
                        shape.x,
                        shape.x + shape.width
                };

                int[] yPoints = {
                        shape.y,
                        shape.y + shape.height,
                        shape.y + shape.height
                };

                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }
}


public class Lab_10 {
    public static void main(String[] args) {

        List<MyShape> shapes = new ArrayList<>();

        System.out.println("Режим: Open & Read. Відкриття файлу INPUT...");

        try (BufferedReader br = new BufferedReader(new FileReader("INPUT"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length == 6) {
                    String type = parts[0].trim();
                    String color = parts[1].trim();
                    int x = Integer.parseInt(parts[2].trim());
                    int y = Integer.parseInt(parts[3].trim());
                    int width = Integer.parseInt(parts[4].trim());
                    int height = Integer.parseInt(parts[5].trim());


                    if (x < 0 || y < 0 || width <= 0 || height <= 0) {
                        System.out.println("Пропущено некоректний рядок (координати або розміри не можуть бути від'ємними): " + line);
                        continue;
                    }


                    shapes.add(new MyShape(type, color, x, y, width, height));

                }
            }


        } catch (IOException e) {
            System.err.println("Помилка роботи з файлом: " + e.getMessage());
            System.err.println("Перевірте, чи файл INPUT створено в правильній папці!");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Лабораторна робота 10");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);

            DrawingPanel panel = new DrawingPanel(shapes);
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}