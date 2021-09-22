object 两数之和1 {


  def main(args: Array[String]): Unit = {


    println("---")
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var i:Int=0
    var j = i
    var res = List[Int]()
    while(i<nums.length){
      j=i+1
      var other = target- nums(i)
      while(j<nums.length){

        if(other == nums(j))
          res = (i::j::Nil)
        j=j+1
      }
      i = i +1;
    }
    res.toArray
  }
}