object 两数之和1V1 {


  def main(args: Array[String]): Unit = {


    println("---")
  }

  /**
   * 1. 守卫
   * 2. ListBuffer
   * 3. nums.indices 索引
   * @param nums
   * @param target
   * @return
   */

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val res =  collection.mutable.ListBuffer[Int]()
    for(i <- nums.indices){
      val other = -(nums(i) - target)
      for(j <- nums.indices if j > i && nums(j) == other){
            res += i
            res += j
      }
    }
    res.toArray

  }


  /***
   * 1. 双重循环
   * @param nums
   * @param target
   * @return
   */
  def twoSum1(nums: Array[Int], target: Int): Array[Int] = {
    val res =  collection.mutable.ListBuffer[Int]()
    for(i <- nums.indices;j <- nums.indices if j > i && nums(j) == -(nums(i) - target)){
        res += i
        res += j
      }
    res.toArray
  }

}