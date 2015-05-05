// lists
val numbers = List(1,2,3,4)
numbers.size
// empty
val emptyList = List()
emptyList.size
val empty2 = Nil
empty2 == emptyList
empty2 eq emptyList
empty2 equals emptyList

// sets
val set = Set(1,2,3)

// tuple
val tuple = ("string", 80)
tuple._1
tuple._2
tuple match {
  case ("string", port) => println(port)
  case (host, port) => println(host + port)
}
// tuple of 2 values
1 -> 2

// maps
Map(1 -> 2)
Map("foo" -> "bar")
Map(1 -> "one", 2 -> "two")

// Option
/* trait Option[T] {
  def isDefined: Boolean
  def get: T
  def getOrElse(t: T): T
} */
// subclasses: Some[T] or None
val numbersMap = Map("one" -> 1, "two" -> 2)
val result = numbersMap.get("two")
val result1 = numbersMap.get("three")
result.isDefined
val result2 = result.getOrElse(0)
val result3 = result1.getOrElse(0)
val result4 = result1 match {
  case Some(n) => n * 2
  case None => 0
}


