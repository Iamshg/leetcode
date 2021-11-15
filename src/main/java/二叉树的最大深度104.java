
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class 二叉树的最大深度104 {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;  // 一个根节点为null的子树当然也是0了
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);


    }
}