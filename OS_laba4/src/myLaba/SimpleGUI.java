package myLaba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGUI extends JFrame {

    private JButton button = new JButton("Нажми на меня");

    public SimpleGUI() {
        super("Задание 2");
        this.setBounds(250,250,250,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(0, 1, 1, 1));
        button.addActionListener(new ButtonEvetListener());
        container.add(button);
        while (!button.isEnabled()) {

        }
    }

    class ButtonEvetListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            My pott1 = new My();
            Drawing hi1 = new Drawing();
            pott1.Set(hi1);
            pott1.start();
        }
    }
}

class Drawing extends JFrame {

    public Drawing () {
        super("Задание 2");
        this.setBounds(250,250,510,540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MyComponents());
    }

    class MyComponents extends JComponent {

        protected void paintComponent (Graphics q) {
            super.paintComponent(q);
            Random rand = new Random();
            float r, g, b;
            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 10; i++) {
                    r = rand.nextFloat();
                    g = rand.nextFloat();
                    b = rand.nextFloat();
                    q.setColor(new Color(r, g, b));
                    q.fillOval(j * 50, i * 50, 50, 50);
                    if (i == 0 || j == 9) {
                        Font font = new Font("Arial", Font.BOLD, 10);
                        q.setFont(font);
                        q.setColor(Color.BLACK);
                        q.drawString(String.valueOf((j + 1) * (i + 1)), j * 50 + 25, i * 50 + 25);
                    }
                }
            }
        }
    }
}


