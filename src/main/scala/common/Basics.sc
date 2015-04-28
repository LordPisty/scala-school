// simple expression
1 + 1

// simple val
val two = 1 + 1
// error: reassignment to val
// two = 4

// simple var
var name = "stefano"
// can change binding
name = "mark"

// simple function
def addOne(x: Int): Int = x + 1
// simple invocation
val three = addOne(2)

// no arguments ==> no parenthesis
def four() = 2 + 2
// valid invocations
four()
four

// anonymous function
(x: Int) => x + 1
// invocation
// res3(1)
// assign anonymous function to vals
val addOneAgain = (x: Int) => x + 1
//invocation
addOneAgain(1)

// multi expressions / {}
def timesTwo(i: Int): Int = {
  println("hello world")
  i * 2
}
// anonymous
{ (i: Int, j: Int) =>
  println(i)
  println(j)
  i + j
}

// partial application
def adder(m: Int, n: Int) = m + n
def add2(x: Int) = adder(2, _: Int)
def add3(x: Int) = adder(3, x)
add2(1)
add3(1)

// curry
def multiply(m: Int)(n: Int): Int = m * n
multiply(2)(3)
val timesTwo = multiply(2) _
// scala> def multiply(m: Int)(n: Int): Int = m*n
// multiply: (m: Int)(n: Int)Int

// scala> val timesTwo = multiply(2) _
// timesTwo: Int => Int = <function1>

// scala> timesTwo(3)
// res0: Int = 6

val curriedAdd = (adder _).curried
val addTwo = curriedAdd(2)
addTwo(4)

// vargargs
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}

capitalizeAll("rarity", "applejack")
capitalizeAll("a","v","c","k")

// classes
class Calculator {
  val brand: String = "HP"
  def add(m: Int, n: Int): Int = m + n
}

val calc = new Calculator
calc.add(1, 2)
calc.brand

// constructor
class CalculatorConst(brand: String) {
  /**
   * A constructor.
   */
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // An instance method.
  def add(m: Int, n: Int): Int = m + n
}

val calcConst = new CalculatorConst("HP")
calcConst.color

// functions VS methods
class C {
  var acc = 0
  def minc = { acc += 1 }
  val finc = { () => acc += 1 }
}

val c = new C
// calls c.minc()
c.minc
// function as value
c.finc

// inheritance
class ScientificCalculator(brand: String) extends CalculatorConst(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

// overload
class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}

// abstract class
abstract class Shape {
  def getArea():Int    // subclass should define this
}

class Circle(r: Int) extends Shape {
  def getArea():Int = { r * r * 3 }
}
// error: class Shape is abstract; cannot be instantiated
// val s = new Shape

val circle = new Circle(2)

// traits
trait Car {
  val brand: String
}

trait Shiny {
  val shineRefraction: Int
}

class BMW extends Car {
  val brand = "BMW"
}

class BMWS extends Car with Shiny {
  val brand = "BMW"
  val shineRefraction = 12
}

// type parameters
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
  // method introduces new type arg
  def newType[Y](arg: Y)
}