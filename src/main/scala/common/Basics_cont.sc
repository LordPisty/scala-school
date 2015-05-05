// apply method
class Foo {}
object FooMaker {
  def apply() = new Foo
}
val newFoo = FooMaker()

class Barr {
  def apply() = 0
}
val barr = new Barr()
barr()

// objects: hold singleton / factories
object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}
Timer.currentCount()

class Bar(foo: String)
object Bar {
  def apply(foo: String) = new Bar(foo)
}
val bar = Bar("string")

// functions
object addOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}
addOne(2)

class AddOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}
val plusOne = new AddOne()
plusOne(3)
class MinusOne extends (Int => Int) {
  def apply(m: Int): Int = m - 1
}
val minusOne = new MinusOne();
minusOne(9)

// pattern matching
val times = 1
times match {
  case 1 => "one"
  case 2 => "two"
  case _ => "other"
}

// guards
times match {
  case i if i == 1 => "one"
  case i if i == 2 => "two"
  case _ => "other"
}

// match on type
def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i - 1
    case i: Int => i + 1
    case d: Double if d < 0.0 => d - 0.1
    case d: Double => d + 0.1
    case text: String => text + "s"
  }
}
bigger(-3)
bigger(3)
bigger("three")

// case classes
case class Calculator(brand: String, model: String)
val hp20b = Calculator("hp", "20b")
val hp30b = Calculator("hp", "30B")
def calcType(calc: Calculator) = calc match {
  case Calculator("hp", "20B") => "financial"
  case Calculator("hp", "48G") => "scientific"
  case Calculator("hp", "30B") => "business"
  // defaults
  case Calculator(ourBrand, ourModel) => "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
  case Calculator(_, _) => "Calculator of unknown type"
  case _ => "Calculator of unknown type"
  case c@Calculator(_, _) => "Calculator: %s of unknown type".format(c)
}

calcType(hp20b)
calcType(hp30b)
calcType(Calculator("x","y"))

// exceptions
class RemoteCalculatorService {
  def add(x: Int, y: Int): Int = {
    throw new IllegalArgumentException("arg 1 was wrong...")
    x + y
  }
  def close() = {
    println("closed the service")
  }
}
val remoteCalculatorService = new RemoteCalculatorService
try {
  remoteCalculatorService.add(1, 2)
} catch {
  case e: IllegalArgumentException => println(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
} finally {
  remoteCalculatorService.close()
}
// try-catch value
val result: Int = try {
  remoteCalculatorService.add(1, 2)
} catch {
  case e: IllegalArgumentException => {
    println(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
    0
  }
} finally {
  remoteCalculatorService.close()
}