package gui;

import node.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawTree {
    private ArrayList<HashMap<String, Integer>> nodeList;

    public DrawTree() {
        nodeList=new ArrayList<HashMap<String, Integer>>();
    }

    public HashMap<String,Integer> getNodeList(TreeNode root,int num,int height){
        HashMap<String, Integer> location = new HashMap<>();
        if (root.left != null) {
            HashMap<String, Integer> map = getNodeList(root.left, num, height + 1);
            location.put("leftx", map.get("x"));
            location.put("lefty", map.get("y"));
            num += getNums(root.left);
        }

        num++;
        location.put("x", num);
        location.put("y", height);
        location.put("val", root.val);
        if (root.right != null) {
            HashMap<String, Integer> map = getNodeList(root.right, num, height + 1);
            location.put("rightx", map.get("x"));
            location.put("righty", map.get("y"));
        }

        nodeList.add(location);
        return location;
    }

    private int getNums(TreeNode node) {
        int nums = 1;
        if (node.left != null) {
            nums += getNums(node.left);
        }

        if (node.right != null) {
            nums += getNums(node.right);
        }
        return nums;
    }

    public ArrayList<HashMap<String, Integer>> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<HashMap<String, Integer>> nodeList) {
        this.nodeList = nodeList;
    }
}
