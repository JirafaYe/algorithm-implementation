package tree;

import node.TreeNode;

public class OperateAVLTree {
    //获取节点的高度
    public int getHeight(TreeNode p){
        return p == null ? -1 : p.height; // 空树的高度为-1
    }

    // AVL树的插入方法
    public TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }
        if (data <= root.val) { // 插入到其左子树上
            root.left = insert(root.left, data);
            //平衡调整
            if (getHeight(root.left) - getHeight(root.right) > 1) {
                if (data <= root.left.val) { // 插入节点在失衡结点的左子树的左边
                    System.out.println("RR旋转");
                    root = RRRotate(root); // RR旋转调整
                }else{ // 插入节点在失衡结点的左子树的右边
                    System.out.println("LR旋转");
                    root = LRRotate(root);
                }
            }
        }else{ // 插入到其右子树上
            root.right = insert(root.right, data);
            //平衡调整
            if(getHeight(root.right) - getHeight(root.left) > 1){
                if(data <= root.right.val){//插入节点在失衡结点的右子树的左边
                    System.out.println("RL旋转");
                    root = RLRotate(root);
                }else{
                    System.out.println("LL旋转");//插入节点在失衡结点的右子树的右边
                    root = LLRotate(root);
                }
            }
        }
        //重新调整root节点的高度值
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }
    // LR旋转
    public TreeNode LRRotate(TreeNode p){
        p.left = LLRotate(p.left); // 先将失衡点p的左子树进行LL旋转
        return RRRotate(p); // 再将失衡点p进行LL平衡旋转并返回新节点代替原失衡点p

    }
    // RL平衡旋转
    public TreeNode RLRotate(TreeNode p){
        p.right = RRRotate(p.right); // 先将失衡点p的右子树进行RR平衡旋转
        return LLRotate(p); // 再将失衡点p进行LL平衡旋转并返回新节点代替原失衡点p
    }

    // RR旋转
    public TreeNode RRRotate(TreeNode p){ // 30为失衡点
        TreeNode lsubtree = p.left;   //失衡点的左子树的根结点20作为新的结点
        p.left = lsubtree.right; //将新节点的右子树25成为失衡点30的左子树
        lsubtree.right = p; // 将失衡点30作为新结点的右子树
        // 重新设置失衡点30和新节点20的高度
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        lsubtree.height = Math.max(getHeight(lsubtree.left), p.height) + 1;
        return lsubtree; // 新的根节点取代原失衡点的位置
    }

    public TreeNode LLRotate(TreeNode p){ // 20为失衡点
        TreeNode rsubtree = p.right;  //失衡点的右子树的根结点30作为新的结点
        p.right = rsubtree.left; //将新节点的左子树25成为失衡点20的右子树
        rsubtree.left = p; // 将失衡点20作为新结点的左子树
        // 重新设置失衡点20和新节点30的高度
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        rsubtree.height = Math.max(getHeight(rsubtree.left), getHeight(rsubtree.right)) + 1;
        return rsubtree; // 新的根节点取代原失衡点的位置
    }
}
