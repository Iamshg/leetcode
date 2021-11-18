/***
 * 和 对称二叉树101V1不同的是 ，这里的invertTree函数有返回值，所以需要将swap函数和递归揉在一起
 */

public class 翻转二叉树226 {
        public TreeNode invertTree(TreeNode p) {

            if(p == null) return null;
            TreeNode tmp = p.left;  // 需要有一个指针备份左节点

            p.left = invertTree(p.right);

            p.right =  invertTree(tmp);
            return p;

        }

        private  void swap(TreeNode p) {
            TreeNode tmp = p.left;
            p.left = p.right;
            p.right = tmp;
        }
    }
