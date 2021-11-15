public class 平衡二叉树110 {

    public boolean isBalanced(TreeNode root) {
        if(root == null)  return true;
        // 只不过递归结束条件，是这样的而已
        if(Math.abs(maxDepth(root.left) - maxDepth(root.right))>1 ) return false;

        // 下面是为了递归遍历
        boolean res1 = isBalanced(root.left);
        boolean res2 = isBalanced(root.right);
        return  res1 && res2;
    }

    public boolean isBalanced1(TreeNode root) {
        if(root == null)  return true;

        boolean res = Math.abs(maxDepth(root.left) - maxDepth(root.right))<=1;
        // 下面是为了递归遍历,上面res才是中间子树递归判断的中间结果，
        // 返回结果的时候，要把中间结果给体现出来， 所以res && res1 && res2;
        boolean res1 = isBalanced1(root.left);
        boolean res2 = isBalanced1(root.right);

        //
        return res && res1 && res2;
    }

    public int maxDepth(TreeNode root) {
        if(root == null)  return 0;

        int res1 = maxDepth(root.left);
        int res2 = maxDepth(root.right);

        return Math.max(res1,res2)+1;


    }

}