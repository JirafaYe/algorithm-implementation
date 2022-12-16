

import org.junit.Test;

import javax.swing.*;

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
        JFrame jFrame = new JFrame();
        jFrame.setSize(400,300);
        jFrame.setLocation(400,300);
        jFrame.setTitle("hello");

        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
