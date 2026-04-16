import javax.swing.*;
import java.awt.*;

public class Lab_4 {
    public static void main(String [] args){
        JFrame frame = new JFrame("Лабораторна робота 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        DrawPanel panel = new DrawPanel();
        frame.add(panel);
        frame .setVisible(true);
    }
}
class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int [] incomes = {1000, 1500, 2000, 2500,};
        int total = 0;
        Color [] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE,};
        int totalIncome = 0;
        Font myFont= new Font ("SansSerif", Font.BOLD | Font.ITALIC, 18);
         int baseY=350;
        for (int i = 0; i < 4; i++) {
            int x= 50+(i*100);
            int height= incomes [i]/10;
            int y= baseY-height;
            g.setColor(colors[i]);
            g.fillRect(x, y, 80, height);
            g.setColor(Color.PINK);
            g.setFont(myFont);
            g.drawString("Дохід "+(i+1), x, baseY +25);
             totalIncome += incomes[i];
            g.drawString(String.valueOf(incomes[i]), x, y - 10);
        }
    }
}

