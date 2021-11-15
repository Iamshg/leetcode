


public class 将有序数组转换为二叉搜索树108 {
    static class TreeNode {
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
        
        // [-10,-3,0,5,9]
        System.out.println(sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }
    public static TreeNode sortedArrayToBST(int[] nums) {


        return binarySearch(nums,0,nums.length-1);
    }

    private static TreeNode binarySearch(int[] nums, int begine, int end) {
        if(end < begine) return null;  // 这个条件卡的。。。
        int mid = (begine+end)/2;
        TreeNode root = null;
        root = new TreeNode(nums[mid]);

        root.left =  binarySearch(nums,begine,mid-1);
        root.right =  binarySearch(nums,mid+1,end);

        return root;
    }
}