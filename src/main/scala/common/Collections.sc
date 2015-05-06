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

// functional combinators
// map
numbers.map((i: Int) => i * 2)
def timesTwo(i: Int): Int = i * 2
numbers map timesTwo

// foreach !!!SIDE EFFECTS ONLY => :Unit !!!
numbers.foreach((i: Int) => i * 2)
numbers foreach timesTwo

// filter
numbers.filter((i: Int) => i % 2 == 0)
def even(i: Int): Boolean = i % 2 == 0
numbers filter even
numbers.filter(even _)
numbers.filter(_ % 2 == 1)

// zip
List(1, 2, 3).zip(List("a", "b", "c"))

// partition
val numbers2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
numbers2.partition(_ % 2 == 0)

// find :Option => first match
numbers2.find((i: Int) => i > 5)

// drop dropWhile
numbers2.drop(5)
numbers2.dropWhile(_ % 2 != 0)

// foldLeft: zero-0 accumulator-m
numbers2.foldLeft(0)((m: Int, n: Int) => m + n)
numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

// foldRight
numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

// flatten: collapse one level of nested structure
List(List(1, 2), List(3, 4)).flatten

// flatMap: takes a function that works on the nested lists and then concatenates the results back together
val nestedNumbers = List(List(1, 2), List(3, 4))
nestedNumbers.flatMap(x => x.map(_ * 2))
nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten

// every combinator rewritable using only fold
def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
  numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
    fn(x) :: xs
  }
}
ourMap(numbers,timesTwo)

def ourFilter(numbers: List[Int], fn: Int => Boolean): List[Int] = {
  numbers.foldLeft(List[Int]()) { (xs: List[Int], x: Int) =>
    x match {
      case i if fn(i) => xs :+ x
      case _ => xs
    }
  }
}
ourFilter(numbers, _ % 2 == 0)

// combinators work on maps too
val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
extensions.filter({case (name, extension) => extension < 200})