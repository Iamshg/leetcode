import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * helper 函数的意义，就是为了语法问题（例如return）而引入的，要配合上全局变量，
 *
 *
 */

public class 不同的二叉搜索树95 {

    public class Tuple {

        int currNode ;
        int position;
        int willAddNode ;
        Tuple() {}
        Tuple(int currNode, int position, int willAddNode ) { 
            this.willAddNode = willAddNode; 
            this.position = position;
            this.currNode = currNode;
        }

        @Override
        public String toString() {
            String s = null;
            if(position == 0){
                s = "左边";
            }else {
                s = "右边";
            }
            return "Tuple{" +
                    "currNode=" + currNode +
                    ", s=" + s +
                    ", willAddNode=" + willAddNode +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return willAddNode == tuple.willAddNode && currNode == tuple.currNode && position == tuple.position;
        }

        @Override
        public int hashCode() {
            return Objects.hash(willAddNode, currNode, position);
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
     */
    LinkedList<Tuple> trace = new LinkedList<>();
    List<List<Tuple>> res = new LinkedList<>();
    LinkedList<Tuple> board = new LinkedList<>();



    public  List<List<Tuple>> solveNQueens1(int n) {


        for (int i = 0; i < n; i++) {
            trace.addLast(new Tuple(i,-1,-1)); // 路径不应该在这里添加了，应该在回溯函数中，配合选择列表canAddtoCurrBinaryTree添加
            backtrace(n);
            trace.removeLast();
        }
        
        return res;
    }

    private  void backtrace(int n) {
        if(trace.size() == n){
//            System.out.println("结束一次");
            res.add(new LinkedList<>(trace));
//            System.out.println(trace);
//            trace.clear();
            return;
        }

        for (int willAddNode = 0; willAddNode < n; willAddNode++) {
            for (int j = 0; j < trace.size(); j++) {
                for (int pos = 0; pos < 2; pos++) {
                    int currNode = trace.get(j).currNode;
                    Tuple willAdd = new Tuple(currNode,pos,willAddNode);
                    if(canAddtoCurrBinaryTree(willAdd,trace)) {
                        trace.addLast(new Tuple(willAddNode,pos,willAddNode));
                        if(new Tuple(willAddNode,pos,willAddNode).toString().equals("Tuple{currNode=2, s=右边, willAddNode=2}")){
                            System.out.println(1);
                        }
                        backtrace(n);
                        trace.removeLast();
                    }
                }
            }
        }



    }


    private boolean canAddtoCurrBinaryTree(Tuple willAddto, List<Tuple> currTrace) {
        // 1. 在trace 各个节点上的 行上不可以
        // TreeNode t = buildTree(currTrace);

        for (Tuple currOnTree : currTrace) {

            if(currOnTree.currNode == willAddto.currNode){
                if( (willAddto.position == 0 && willAddto.willAddNode > currOnTree.currNode) ||
                        (willAddto.position == 1 && willAddto.willAddNode < currOnTree.currNode)
                )
                    return  false;
                if(currOnTree.position != -1){
                    if(// 一个左右节点都是唯一的
                            (willAddto.position == 0 && willAddto.willAddNode < currOnTree.currNode && currOnTree.position == 0  )  ||  // 左节点已经有了
                                    (willAddto.position == 1 && willAddto.willAddNode > currOnTree.currNode && currOnTree.position == 1  ) // 右节点有了
                    )
                        return  false;
                }

                if(currOnTree.position == -1){ // 如果第一次，就看第二个元素的位置pos

                    if(currTrace.size() > 1){
                        Tuple secOnTree = currTrace.get(1);
                        if(// 一个左右节点都是唯一的
                                (willAddto.position == 0 && willAddto.willAddNode < currOnTree.currNode && secOnTree.position == 0  )  ||  // 左节点已经有了
                                        (willAddto.position == 1 && willAddto.willAddNode > currOnTree.currNode && secOnTree.position == 1  ) // 右节点有了
                        )
                            return  false;
                    }
                }
            }
            if(willAddto.willAddNode == currOnTree.currNode){ // 如果当前在树上的节点  等于   要加入的节点   ，这个节点一定不能加进来
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        int n = 3;
        System.out.println(new 不同的二叉搜索树95().solveNQueens1(n).size());
        for (List<Tuple> tuples : new 不同的二叉搜索树95().solveNQueens1(n)) {
            System.out.println(tuples);
        }
    }
}