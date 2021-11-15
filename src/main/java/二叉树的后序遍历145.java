import java.util.ArrayList;
import java.util.List;

public class 二叉树的后序遍历145 {




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



        List<Integer> res= new ArrayList<>();
        public List<Integer> postorderTraversal(TreeNode root) {
            if(root == null ) return res ; // 这个是为了保证， 输入是 [] ,输出也是 一个空链表， 而不是null。
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
            return res;

        }
    }