

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class N皇后51Java {



    public static void main(String[] args) {

        int n= 6;

//        for (List<Tuple> solveNQueen : instance.res) {
//            System.out.printf(String.valueOf(solveNQueen));
//        }
        List<List<Tuple>> lists = new N皇后51Java().solveNQueens(n);
        System.out.println(lists.size());
        List<List<Tuple>> lists1 = new N皇后51Java().solveNQueens1(n);
        System.out.println(lists1.size());

    }
    class Tuple{
        Tuple(int i,int j){
            this.i = i;
            this.j = j;
        }
        int i=-1;
        int j=-1;

        @Override
        public String toString() {
            return "Tuple{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return i == tuple.i && j == tuple.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    List<Tuple> trace = new ArrayList<>();
    List<List<Tuple>> res = new ArrayList<>();
    ArrayList<Tuple> board = new ArrayList<>();

    public  List<List<Tuple>> solveNQueens(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board.add(new Tuple(i,j));
            }
        }

        for (Tuple tuple : board) {
            trace.add(tuple); // 第一次做选择
            backtrace(n);
            trace.remove(tuple);
        }

        return res;
    }

    public  List<List<Tuple>> solveNQueens1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
                for (int j = 0; j < n; j++) {
                    if(!canDown(i,j)) continue;
//                    System.out.println(new Tuple(i, j));
//                    Tuple t = new Tuple(i, j);
//                    if (t.equals(new Tuple(3,1))){
//                        System.out.println("---");
//                    }
                    trace.add(new Tuple(i,j));
                    backtrace(n);
                    trace.remove(new Tuple(i,j));
                }
            }


    }

    private boolean canDown(int i, int j) {
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

