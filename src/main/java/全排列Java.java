

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class 全排列Java {



    public static void main(String[] args) {

        int n = 3;
        全排列Java instance = new 全排列Java();
        instance.quanPaiLie(n);

        for (List<SingleInteger> solveNQueen : instance.res) {
            System.out.println(String.valueOf(solveNQueen));
        }
        System.out.println(instance.res.size());

        全排列Java instance1 = new 全排列Java();
        instance1.quanPaiLie1(n);

        for (List<SingleInteger> solveNQueen : instance1.res) {
            System.out.println(String.valueOf(solveNQueen));
        }
        System.out.println(instance1.res.size());
    }
    class SingleInteger{
        SingleInteger(int i){
            this.i = i;
        }
        int i=-1;

        @Override
        public String toString() {
            return String.valueOf(i);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SingleInteger that = (SingleInteger) o;
            return i == that.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i);
        }
    }

    List<SingleInteger> trace = new ArrayList<>();
    List<List<SingleInteger>> res = new ArrayList<>();
    ArrayList<SingleInteger> arr = new ArrayList<>();

    public  List<List<SingleInteger>> quanPaiLie(int n) {
        for (int i = 0; i < n; i++) {
            arr.add(new SingleInteger(i));
        }

        for (SingleInteger i : arr) {
            trace.add(i); // 第一次做选择
            backtrace(n);
            trace.remove(i);
        }

//        trace.add(new Tuple(0,0)); // 第一次做选择
//        backtrace(n);
        return res;
    }
    public  List<List<SingleInteger>> quanPaiLie1(int n) {
        for (int i = 0; i < n; i++) {
            arr.add(new SingleInteger(i));
        }
        trace.add(new SingleInteger(0));
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
                    if(!canDown(i)) continue;
//                    System.out.println(new SingleInteger(i));
//                    SingleInteger t = new SingleInteger(i);
//                    if (t .equals(new SingleInteger(2))){
//                        System.out.println("---");
//                    }
                    trace.add(new SingleInteger(i));
                    backtrace(n);
                    trace.remove(new SingleInteger(i));

        }

    }

    private boolean canDown(int i) {
        // 1. 在trace 各个节点上的 行上不可以
        for (SingleInteger x : trace) {
            if(x.i == i){
                return false;
            }
        }
        return true;
    }
}

