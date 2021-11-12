public class 相同的树100 {



    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) return true;
            if((p != null && q == null) || (p == null && q != null)) return false;
            if(p != null && q != null && p.val != q.val) return false;
            return isSameTree(p.left,q.left) &&
                    isSameTree(p.right,q.right);


            /*
            之前写成了这种情况，这种情况只是遍历树，及时中间返回false，最终永远会返回true
             */
//            isSameTree(p.left,q.left);
//            isSameTree(p.right,q.right);
//            return true;







        }
    }



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

}
