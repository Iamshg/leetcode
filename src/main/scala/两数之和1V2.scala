object 两数之和1V2 {


  def main(args: Array[String]): Unit = {


    println("---")
  }

  /**
   * 1. map.get(other)返回的是不是Int，只有map(other)才是Int
   * 2. Map +=  后面是  —>  这个符号
   * 3. Array[Int](i,map(other)) 和 Array(i,map(other)) 不一样，第一个是初始化，第二个是apply方法。
   * @param nums
   * @param target
   * @return
   */

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val map =  collection.mutable.Map[Int,Int]()
    for(i <- nums.indices){
      val other = -(nums(i) - target)
      if(!map.contains(other)) {
        map += (nums(i) -> i)
      }else{
        return Array(i,map(other))
      }
    }
    null
  }


}