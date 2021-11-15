

class 对称二叉树101V1 {

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
    public boolean isSymmetric(TreeNode p) {
        return isMirror(p,p);
    }

    static boolean isMirror(TreeNode p,TreeNode q){
        if(p == null && q == null) return true; // 这句话可以不要
        if(p == null && q != null || p != null && q == null) return false;
        if(p.val != q.val)  return false;
        return isMirror(p.left,q.right) && isMirror(p.right, q.left);
    }

}