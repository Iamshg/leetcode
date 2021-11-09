import java.util.ArrayList;
import java.util.List;


/**
 * helper 函数的意义，就是为了语法问题（例如return）而引入的，要配合上全局变量，
 *
 *
 */

public class 不同的二叉搜索树95 {

    public class Tuple {
        int val;
        int position;
        Tuple() {}
        Tuple(int val, int position) { 
            this.val = val; 
            this.position = position;
        }
    }
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
    List<TreeNode> trace = new ArrayList<>();
    List<List<Tuple>> res = new ArrayList<>();
    ArrayList<Tuple> board = new ArrayList<>();



    public  List<List<Tuple>> solveNQueens1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                board.add(new Tuple(i,j));
            }
        }

        backtrace(n);
        return res;
    }

    private  void backtrace(int n) {
        if(trace.size() == n){
//            System.out.println("结束一次");
            res.add(new ArrayList<>(trace));
//            System.out.println(trace);
//            trace.clear();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int pos = 0; pos < 2; pos++) {
                if(!isBinarySearchTree(i,pos)) continue;
//                    System.out.println(new Tuple(i, j));
//                    Tuple t = new Tuple(i, j);
//                    if (t.equals(new Tuple(3,1))){
//                        System.out.println("---");
//                    }
                if(pos == 0)
                    trace.add(new TreeNode(i,pos));
                else
                    trace.add(new TreeNode(i,null,new TreeNode(i)))
                backtrace(n);
                trace.remove(new TreeNode(i,pos));
            }
        }



    }


    private boolean isBinarySearchTree(int i, int j) {
        // 1. 在trace 各个节点上的 行上不可以
        for (Tuple tuple : trace) {
            int x = tuple.i;
            int y = tuple.j;
            if(x == i
                    ||  y == j         // 列不可以
                    ||  (i == (x + 1) && j == (y+1))         // 周围不可以
                    ||  (i == (x - 1) && j == (y-1))
                    ||  (i == (x - 1) && j == (y+1))         // 周围不可以
                    ||  (i == (x + 1) && j == (y-1))
            )
                return false;
        }
        return true;
    }
}