public class 二叉树的最小深度111 {

        public int minDepth(TreeNode root) {
            if(root == null) return 0;
            int res1 = 1000000;
            int res2 = 1000000;
            if(root.left != null && root.right != null){
                res1 = minDepth(root.right)+1;
                res2 = minDepth(root.left)+1;
            }else if(root.left == null)   res1 = minDepth(root.right)+1;
            else if(root.right == null)  res2 = minDepth(root.left)+1;


            return Math.min(res1,res2);
        }


        // 是错的
        public int minDepth1(TreeNode root) {
            if(root == null)  return 0;

            int res1 = minDepth1(root.left);
            int res2 = minDepth1(root.right);

            return Math.min(res1,res2)+1;
        }

        // 每个子树的最小深度不代表树的最小深度
        // 而是 根节点到每一个叶子结点的最小深度，所以需要计算出到叶子节点的深度，避开中间节点有一个子节点为空的那个子节点，这种子节点不能遍历，不然所有子树的最小深度都是1
        // 例如
    //     3
    //  9     20
    //2      15 7
    // 如果用minDepth1 返回结果就是2，因为9的右子树是null，所以以9为根节点的子树“最小深度”为1，而正确结果是2.
    }
