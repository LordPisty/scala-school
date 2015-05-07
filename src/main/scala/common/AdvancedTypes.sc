// implicit function
implicit def strToInt(x: String) = x.toInt
"123"
val y: Int = "123"
math.max("123",111)

// view bounds
class Container[A <% Int] { def addIt(x: A) = 123 + x }
(new Container[String]).addIt("123")
(new Container[Int]).addIt(123)

implicit def floatToInt(x: Float) = x.toInt
(new Container[Float]).addIt(123.2F)

// type classes ==> implicit parameters ==> e.g. Numeric / List.sum
case class Alcohol(liters:Double)
case class Water(liters:Double)

case class Fire(heat:Double)
// trait
trait Flammable[A] {
  def burn(fuel:A): Fire
}
//implicit object - type class
implicit object AlcoholIsFlammable extends Flammable[Alcohol] {
  def burn(fuel:Alcohol) = Fire(120.0)
}

def setFire[T](fuel:T)(implicit f:Flammable[T]) = f.burn(fuel)

setFire(Alcohol(1.0)) // OK
// KO setFire(Water(1.0))

// evidence - type bounds
class Container2[A](value: A) { def addIt(implicit evidence: A =:= Int) = 123 + value }
(new Container2(123)).addIt
// not enough arguments for method addIt: (implicit evidence: =:=[String,Int])Int.
// (new Container2("123")).addIt

// generic with views
// min on Seq[]
/*def min[B >: A](implicit cmp: Ordering[B]): A = {
  if (isEmpty)
    throw new UnsupportedOperationException("empty.min")

  reduceLeft((x, y) => if (cmp.lteq(x, y)) x else y)
}*/

// shorthand - implicitly
def foo[A](implicit x: Ordered[A]) {}
def fooSame[A : Ordered] {}
implicitly[Ordering[Int]]
// higher kinded types - ad-hoc polymorphism
trait Container3[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }
val container = new Container3[List] { def put[A](x: A) = List(x); def get[A](m: List[A]) = m.head }
container.put("hey")
container.put(123)
trait Container4[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }
implicit val listContainer = new Container4[List] { def put[A](x: A) = List(x); def get[A](m: List[A]) = m.head }
implicit val optionContainer = new Container4[Some] { def put[A](x: A) = Some(x); def get[A](m: Some[A]) = m.get }
def tupleize[M[_]: Container4, A, B](fst: M[A], snd: M[B]) = {
  val c = implicitly[Container4[M]]
  c.put(c.get(fst), c.get(snd))
}
tupleize(Some(1), Some(2))
tupleize(List(1), List(2))
// KO tupleize(Some(1), List(2))
tupleize(Some(1), Some("2")) // OK
// F-bounded polymorphism
trait Container5[A <: Container5[A]] extends Ordered[A]
class MyContainer extends Container5[MyContainer] {
  def compare(that: MyContainer) = 0
}
List(new MyContainer, new MyContainer, new MyContainer)
List(new MyContainer, new MyContainer, new MyContainer).min
class YourContainer extends Container5[YourContainer] {
  def compare(that: YourContainer) = 0
}
List(new MyContainer, new MyContainer, new MyContainer, new YourContainer)

// structural types: type reqs expressed by interface structure rather than concrete type
def foo(x: { def get: Int }) = 123 + x.get
foo(new { def get = 10 })
// abstract type members ==> dependency injection
trait Foo { type A; val x: A; def getX: A = x }
(new Foo { type A = Int; val x = 123 }).getX
(new Foo { type A = String; val x = "hey" }).getX

// abstract type variable ==> #
trait Foo2[M[_]] { type t[A] = M[A] }
val x: Foo2[List]#t[Int] = List(1)

// type erasure - manifests
class MakeFoo[A](implicit manifest: Manifest[A]) { def make: A = manifest.erasure.newInstance.asInstanceOf[A] }
(new MakeFoo[String]).make