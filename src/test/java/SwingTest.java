

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {

    @Test
    public void testSwing(){
        JFrame jFrame = new JFrame();
        jFrame.setSize(400,300);
        jFrame.setLocation(400,300);
        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        class test extends Canvas{
            @Override
            public void paint(Graphics g) {
                g.drawOval(100,100,50,50);
            }
        }
        test test = new test();
        test.setPreferredSize(new Dimension(300,300));
        JFrame jFrame = new JFrame();
        jFrame.setSize(400,300);
        jFrame.setLocation(400,300);
        jFrame.setTitle("经典算法的实现");
        jFrame.setVisible(true);
        jFrame.add(test);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
