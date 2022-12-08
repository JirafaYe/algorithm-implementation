package binarySearchTree;

import node.TreeNode;


public class OperateBST {
    public TreeNode insertBST(TreeNode root,int val){
        if(root==null)
            return new TreeNode(val);

        if(root.val>val)
            root.left=insertBST(root.left,val);
        if(root.val<val)
            root.right=insertBST(root.right,val);

        return root;
    }

    public TreeNode deleteBST(TreeNode root,int val){
        if (root == null) return null;

        if (root.val > val) {
            root.left = deleteBST(root.left,val);
        } else if (root.val < val) {
            root.right = deleteBST(root.right,val);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode tmp = root.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            root.val = tmp.val;
            root.right = deleteBST(root.right,tmp.val);
        }
        return root;
    }

    private TreeNode searchBST(TreeNode root,int val){
        TreeNode result;
        if(root==null||root.val==val)
            return root;

        result= val>=root.val?searchBST(root.right,val):
                searchBST(root.left,val);

        return result;
    }
}
