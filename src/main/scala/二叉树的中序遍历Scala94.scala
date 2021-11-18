



object 二叉树的中序遍历Scala94 {



  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }


  def inorderTraversal(root: TreeNode): List[Int] = {
    if(root == null) return List.empty[Int]
    inorderTraversal(root.left) ::: List(root.value) ::: inorderTraversal(root.right)
  }


}