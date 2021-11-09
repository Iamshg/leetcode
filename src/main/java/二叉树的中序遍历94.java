import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;


/**
 * helper 函数的意义，就是为了语法问题（例如return）而引入的，要配合上全局变量，
 *
 *
 */

public class 二叉树的中序遍历94 {

    public class TreeNode {
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

    /**
     * 必须使用helper方法，要不inorderTraversal方法无法即返回、又递归的遍历树形结构。
     * 1. 因为递归遍历有一个结束条件，这个结束条件一般是return 返回，
     *  xxx  但是中序遍历的第一次return 返回不代表遍历结束了，而只是到了最左边的一个叶子节点而已。
     * 此时返回List ，虽然是可以的，但是总是感觉浪费了空间，这个中间结果的List 不是最终的结果，但是返回了，就是为了保证语法正确而已。（浪费空间），代码不雅观
     * 2. 而且返回的List 必须是一个全局变量才可以，不然没有办法连续记录。
     *
     * 但是借助一个helper函数就可以解决问题1，helper函数可以返回void脱离inorderTraversal 必须返回 List的限制，而且也同样可以向全局变量List 中添加元素。
     *
     *
     *
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        helper(root);
        return res;
    }


    public void helper(TreeNode root) {
        if(root == null) return;

        helper(root.left);
        res.add(root.val);
        // System.out.println(root.val);
        helper(root.right);
    }




    public List<Integer> inorderTraversal1(TreeNode root) {
        if(root == null) return res;
        helper(root.left);
        res.add(root.val);
        // System.out.println(root.val);
        helper(root.right);
        return res;
    }
}