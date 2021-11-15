


class 路径总和112 {
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


    // []
    // 0
    // 应该是false
    // 但是返回true
    // 这说明根节点为空的，直接就是错了，无论target是不是0，那什么条件为真呢？  就是两个节点的子节点都是null的时候，且targetnum对的上的时候

    // [1,2]
    // 1
    // 应该是false
    // 但是返回true
    public boolean hasPathSum1(TreeNode root, int targetSum) {

        if(root == null && targetSum == 0) return true;
        if(root == null && targetSum != 0) return false;


        return hasPathSum1(root.left,targetSum - root.val) ||
                hasPathSum1(root.right ,targetSum - root.val);



    }



    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // []
        // 0
        // 应该是false
        // 但是返回true
        // 这说明根节点为空的，直接就是错了，无论target是不是0，那什么条件为真呢？  就是两个节点的子节点都是null的时候，且targetnum对的上的时候
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}