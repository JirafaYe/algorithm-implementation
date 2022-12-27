package gui;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TreePanel extends Canvas {
    private ArrayList<HashMap<String, Integer>> nodeList;
    private static final int X = 40;
    private static final int Y = 80;
    private static final int CIRCLE_LEN = 40;
    private static final int STRING_SIZE = 20;

    public TreePanel(ArrayList<HashMap<String, Integer>> nodeList){
        this.nodeList=nodeList;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Iterator it = this.nodeList.iterator();

        while(it.hasNext()) {
            HashMap<String, Integer> map = (HashMap<String, Integer>) it.next();
            int x = map.get("x") * X;
            int y = map.get("y") * Y;

            g2.setColor(Color.yellow);
            Shape circle = new Ellipse2D.Double(x, y, CIRCLE_LEN, CIRCLE_LEN);
            g2.fill(circle);

            int val = map.get("val");
            String str = String.valueOf(val);
            g2.setColor(Color.black);
            Font font = new Font("宋体", 1, STRING_SIZE);
            g2.setFont(font);
            g2.drawString(str, x + CIRCLE_LEN / 2 - 10, y + CIRCLE_LEN / 2 + 8);
//            g2.setColor(Color.yellow);
            int rightx;
            int righty;
            Line2D.Double line;
            if (map.get("leftx") != null) {
                rightx = map.get("leftx") * X + CIRCLE_LEN / 2;
                righty = map.get("lefty") * Y+ CIRCLE_LEN / 2;
                line = new Line2D.Double((x + CIRCLE_LEN / 2), (y + CIRCLE_LEN / 2), rightx, righty);
                g2.draw(line);
            }

            if (map.get("rightx") != null) {
                rightx = map.get("rightx") * X + CIRCLE_LEN / 2;
                righty = map.get("righty") * Y + CIRCLE_LEN / 2;
                line = new Line2D.Double((x + CIRCLE_LEN / 2), (y + CIRCLE_LEN / 2), rightx, righty);
                g2.draw(line);
            }
        }
    }
}
