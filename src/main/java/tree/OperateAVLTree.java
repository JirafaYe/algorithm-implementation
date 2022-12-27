package tree;

import node.TreeNode;

public class OperateAVLTree {
    public int getHeight(TreeNode node) {
        if(node == null) return 0;
        return node.height;
    }

    public int getBalanceFactor(TreeNode root){
        if(root == null) return 0;
        return getHeight(root.left) - getHeight(root.right);
    }

    //判断一个树是否是一个平衡二叉树
    public boolean isBalance(TreeNode root){
        if(root == null) return true;
        int balanceFactor = Math.abs(getBalanceFactor(root.left) - getBalanceFactor(root.right));
        if(balanceFactor > 1) return false;
        return isBalance(root.left) && isBalance(root.right);
    }

    //左旋,并且返回新的根节点
    public TreeNode leftRotate(TreeNode node){
        System.out.println("leftRotate");
        TreeNode cur = node.right;
        node.right = cur.left;
        cur.left = node;
        //跟新node和cur的高度
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        cur.height = Math.max(getHeight(cur.left),getHeight(cur.right)) + 1;
        return cur;
    }

    //右旋，并且返回新的根节点
    public TreeNode rightRotate(TreeNode node){
        System.out.println("rightRotate");
        TreeNode cur = node.left;
        node.left = cur.right;
        cur.right = node;
        //跟新node和cur的高度
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        cur.height = Math.max(getHeight(cur.left),getHeight(cur.right)) + 1;
        return cur;
    }

    //添加元素
    public TreeNode add(TreeNode node, int value) {
        if (node == null) {
//            size++;
            return new TreeNode(value);
        }
        if(node.val>value)
            node.left= add(node.left,value);
        if(node.val<value)
            node.right= add(node.right,value);
        //跟新节点高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        //获取当前节点的平衡因子
        int balanceFactor = getBalanceFactor(node);
        //该子树不平衡且新插入节点(导致不平衡的节点)在左子树的左子树上，此时需要进行右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //该子树不平衡且新插入节点(导致不平衡的节点)在右子树子树的右子树上，此时需要进行左旋
        else if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //该子树不平衡且新插入节点(导致不平衡的节点)在左子树的右子树上，此时需要先对左子树左旋，在整个树右旋
        else if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //balanceFactor < -1 && getBalanceFactor(node.left) > 0
        //该子树不平衡且新插入节点(导致不平衡的节点)在右子树的左子树上，此时需要先对右子树右旋，再整个树左旋
        else if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //删除节点
//    public E remove(E value){
//        root = remove(root,value);
//        if(root == null){
//            return null;
//        }
//        return root.value;
//    }
    public TreeNode remove(TreeNode node, int value){
        TreeNode retNode = null;
        if(node == null)
            return retNode;
        if(node.val<value){
            node.right = remove(node.right,value);
            retNode = node;
        }
        else if(node.val>value){
            node.left = remove(node.left,value);
            retNode = node;
        }
        //value.compareTo(node.value) = 0
        else{
            //左右节点都为空，或者左节点为空
            if(node.left == null){
//                size--;
                retNode = node.right;
            }
            //右节点为空
            else if(node.right == null){
//                size--;
                retNode = node.left;
            }
            //左右节点都不为空
            else{
                TreeNode successor = new TreeNode();
                //寻找右子树最小的节点
                TreeNode cur = node.right;
                while(cur.left != null){
                    cur = cur.left;
                }
                successor.val  = cur.val;
                successor.right = remove(cur.right,value);
                successor.left = node.left;
                retNode = successor;
            }
            if(retNode == null)
                return null;
            //维护二叉树平衡
            //跟新height
            retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right));
        }
        int balanceFactor = getBalanceFactor(retNode);
        //该子树不平衡且新插入节点(导致不平衡的节点)在左子树的左子树上，此时需要进行右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        //该子树不平衡且新插入节点(导致不平衡的节点)在右子树子树的右子树上，此时需要进行左旋
        else if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        //该子树不平衡且新插入节点(导致不平衡的节点)在左子树的右子树上，此时需要先对左子树左旋，在整个树右旋
        else if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //该子树不平衡且新插入节点(导致不平衡的节点)在右子树的左子树上，此时需要先对右子树右旋，再整个树左旋
        else if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return  retNode;
    }


}
