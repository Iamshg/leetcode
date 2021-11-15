public class 对称二叉树101 {




    public static class TreeNode {
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



    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.left.left = new TreeNode(3);
        p.left.right = new TreeNode(4);

        p.right = new TreeNode(2);
        p.right.right = new TreeNode(3);
        p.right.left = new TreeNode(4);
//        p.right.left = new TreeNode(6);

//        trave(p);
//
//        System.out.println("------------");
//         trave(p);
//        System.out.println("------------");

        TreeNode root = deepCopy(p);
        new 对称二叉树101().fanzhuan(root);
        System.out.println(isEqu(p,root));

    }
        public boolean isSymmetric(TreeNode p) {
            TreeNode root = deepCopy(p);
            fanzhuan(root);
            return isEqu(p,root);

        }

        static boolean isEqu(TreeNode p,TreeNode q){
            if(p == null && q == null) return true; // 这句话可以不要
            if(p == null && q != null || p != null && q == null) return false;
            if(p.val != q.val)  return false;
            return isEqu(p.left,q.left) && isEqu(p.right, q.right);
        }
        private static TreeNode deepCopy(TreeNode p){
            if(p == null) return null;
            TreeNode tmp = new TreeNode(p.val);
            tmp.left = deepCopy(p.left);
            tmp.right = deepCopy(p.right);
            return tmp;
        }
        static void fanzhuan(TreeNode p)
        {
            if(p == null) return;
            swap(p);// 递归的主要目的是记住父节点，（栈结构），和父节点下面的子节点顺序无关系，
            // 而且必须是先序遍历、或者后序遍历， 因为在没有访问子节点或完全访问完子节点之后才会子节点的变化才不会影响

            fanzhuan(p.left);

            fanzhuan(p.right);

        }

        private static void swap(TreeNode p) {
            TreeNode tmp = p.left;
            p.left = p.right;
            p.right = tmp;
        }

}
