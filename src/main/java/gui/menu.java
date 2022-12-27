package gui;

import node.TreeNode;
import tree.OperateAVLTree;
import tree.OperateBST;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


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
                if(bst.isSelected()){
                    OperateBST operateBST = new OperateBST();
                    String[] split = instTxt.getText().split(",");
                    TreeNode root = new TreeNode(Integer.parseInt(split[0]));
                    for (int i = 1; i < split.length; i++) {
                        operateBST.add(root,Integer.parseInt(split[i]));
                    }
                    DrawTree drawTree = new DrawTree();
                    drawTree.getNodeList(root, 0, 0);
                    treePanel = new TreePanel(drawTree.getNodeList());

                }else{
                    OperateAVLTree operateAVLTree = new OperateAVLTree();
                    String[] split = instTxt.getText().split(",");
                    TreeNode root = new TreeNode(Integer.parseInt(split[0]));
                    for (int i = 1; i < split.length; i++) {
                        operateAVLTree.add(root,Integer.parseInt(split[i]));
                    }
                    DrawTree drawTree = new DrawTree();
                    drawTree.getNodeList(root, 0, 0);
                    treePanel = new TreePanel(drawTree.getNodeList());
                }
                JLabel delete = new JLabel("删除");
                JTextField del = new JTextField("",20);

                JButton confirm = new JButton("确定");
                JFrame tree = new JFrame();
                tree.add(treePanel);
                tree.setSize(700,700);
                tree.setLocation(400,300);
                tree.setVisible(true);
                tree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
