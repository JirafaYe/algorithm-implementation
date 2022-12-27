package tree;

import node.TreeNode;

public class OperateAVLTree {
    int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }


    int getBalanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    TreeNode L(TreeNode oldRoot) {
//        保留两个节点引用
        TreeNode newRoot = oldRoot.right;
        TreeNode rightLeft = oldRoot.right.left;
//        改变node的右子树和right的左子树
        newRoot.left = oldRoot;
        oldRoot.right = rightLeft;
//        自下向上修改高度
        oldRoot.height = Math.max(getHeight(oldRoot.left), getHeight(oldRoot.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
        return newRoot;
    }

    TreeNode R(TreeNode oldRoot) {
//        保留两个节点引用
        TreeNode newRoot = oldRoot.left;
        TreeNode leftRight = oldRoot.left.right;
//        改变node的左子树和left的右子树
        newRoot.right = oldRoot;
        oldRoot.left = leftRight;
//        自下向上修改高度
        oldRoot.height = Math.max(getHeight(oldRoot.left), getHeight(oldRoot.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
        return newRoot;
    }

    TreeNode rotate(TreeNode node) {
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) > 0) {
            return R(node);
        }
        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) < 0) {
            return L(node);
        }
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) <= 0) {
            node.left = L(node.left);
            return R(node);
        }
        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) >= 0) {
            node.right = R(node.right);
            return L(node);
        }
        return node;
    }


    public TreeNode add(TreeNode node, int n) {
        if (node == null)
            return new TreeNode(n);
        if (n < node.val)
            node.left = add(node.left, n);
        else if (n > node.val)
            node.right = add(node.right, n);
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return rotate(node);
    }

    public TreeNode remove(TreeNode node, int n) {
        if (node == null)
            return null;
        if (n < node.val) {
            node.left = remove(node.left, n);
//            return node;
        } else if (n > node.val) {
            node.right = remove(node.right, n);
//            return node;
        } else {
//            要删除的节点无子树或者只有一颗子树
            if (node.left == null) {
                TreeNode right = node.right;
                node.right = null;
                node = right;
            } else if (node.right == null) {
                TreeNode left = node.left;
                node.left = null;
                node = left;
            } else {
                TreeNode newNode = node.left;
                remove(newNode, newNode.val);
                node.val = newNode.val;
            }
        }
//       旋转处理,注意node可能为null
        if (node == null) return null;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return rotate(node);
    }
}
