package gui;

import node.TreeNode;
import tree.OperateAVLTree;
import tree.OperateBST;

import javax.swing.*;
import java.awt.*;


public class menu {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(400,300);
        jFrame.setLocation(400,300);
        jFrame.setTitle("经典算法的实现");

        jFrame.add(new JLabel("请选择对以下哪种树进行操作"));

        JRadioButton bst = new JRadioButton("二叉搜索树");
        JRadioButton avl = new JRadioButton("二叉平衡树");
        ButtonGroup buttonGroup = new ButtonGroup();
        JButton next = new JButton("下一步");

        next.addActionListener(e -> {
            JFrame jFrame1 = new JFrame();
            jFrame1.setSize(300,300);
            jFrame1.setLocation(400,300);
            jFrame1.setVisible(true);
            jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel inst = new JLabel("插入");
//            JLabel delete = new JLabel("删除");
            JTextField instTxt = new JTextField("",20);
//            JTextField deleteTxt = new JTextField("",20);

            JButton next1 = new JButton("下一步");

            jFrame1.add(inst);
            jFrame1.add(instTxt);
//            jFrame1.add(delete);
//            jFrame1.add(deleteTxt);
            jFrame1.add(next1);

            jFrame1.setLayout(new FlowLayout());

            next1.addActionListener(b -> {
                TreePanel treePanel;
                TreeNode root;
                if(bst.isSelected()){
                    OperateBST operateBST = new OperateBST();
                    String[] split = instTxt.getText().split(",");
                    root = new TreeNode(Integer.parseInt(split[0]));
                    for (int i = 1; i < split.length; i++) {
                        operateBST.add(root,Integer.parseInt(split[i]));
                    }
                    DrawTree drawTree = new DrawTree();
                    drawTree.getNodeList(root, 0, 0);
                    treePanel = new TreePanel(drawTree.getNodeList());
                }else{
                    OperateAVLTree operateAVLTree = new OperateAVLTree();
                    String[] split = instTxt.getText().split(",");
                    root = new TreeNode(Integer.parseInt(split[0]));
                    for (int i = 1; i < split.length; i++) {
                        root=operateAVLTree.add(root,Integer.parseInt(split[i]));
                    }
                    DrawTree drawTree = new DrawTree();
                    drawTree.getNodeList(root, 0, 0);
                    treePanel = new TreePanel(drawTree.getNodeList());
                }
                JFrame tree = new JFrame();
                tree.add(treePanel);
                tree.setSize(700,700);
                tree.setLocation(400,300);
                tree.setVisible(true);
                tree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel delete = new JLabel("删除");
                JTextField del = new JTextField("",20);
                JLabel add = new JLabel("插入");
                JTextField addText = new JTextField("",20);
                JButton confirm = new JButton("确定");
                TreeNode finalRoot = root;
                confirm.addActionListener(e2->{
                    OperateBST operateBST = new OperateBST();
                    OperateAVLTree operateAVLTree = new OperateAVLTree();
                    TreeNode tmp = finalRoot;
                    if(bst.isSelected()) {
                        if (!del.getText().isEmpty()) {
                            tmp = operateBST.delete(tmp, Integer.parseInt(del.getText()));
                        } else {
                            operateBST.add(tmp, Integer.parseInt(addText.getText()));
                        }
                    }else {
                        if (!del.getText().isEmpty()) {
                            tmp = operateAVLTree.remove(tmp, Integer.parseInt(del.getText()));
                        } else {
                            tmp=operateAVLTree.add(tmp, Integer.parseInt(addText.getText()));
                        }
                    }

                    DrawTree drawTree = new DrawTree();
                    drawTree.getNodeList(tmp, 0, 0);
                    TreePanel panel = new TreePanel(drawTree.getNodeList());
                    JFrame refresh = new JFrame();
                    refresh.add(panel);
                    refresh.setSize(700,700);
                    refresh.setLocation(400,300);
                    refresh.setVisible(true);
                    refresh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                });

                JFrame operate = new JFrame();
                operate.add(delete);
                operate.add(del);
                operate.add(add);
                operate.add(addText);
                operate.add(confirm);
                operate.setLayout(new FlowLayout(FlowLayout.LEFT));
                operate.setSize(300,300);
                operate.setLocation(400,300);
                operate.setVisible(true);
                operate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            });
        });


        buttonGroup.add(bst);
        buttonGroup.add(avl);
        jFrame.add(avl);
        jFrame.add(bst);
        jFrame.add(next);

        jFrame.setLayout(new FlowLayout());


        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
