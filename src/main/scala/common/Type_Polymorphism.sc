// “A type system is a syntactic method for automatically checking the absence of certain erroneous behaviors by classifying program phrases according to the kinds of values they compute.” - Pierce

// polymorphism is achieved through specifying type variables.
def drop1[A](l: List[A]) = l.tail
drop1(List(1,2,3))

// rank-1 polymorphism
// that there are some type concepts you’d like to express in Scala that are “too generic” for the compiler to understand. Suppose you had some function
// ==> compilation error
// def foo[A, B](f: A => List[A], b: B) = f(b)

// type inference
// all type inference is local: scala considers one expression at a time
def id[T](x: T) = x
val x = id(322)
val y = id("hey")
// compilation error: missing parameter type
// { x => x}

// variance
//                Meaning	                          Scala notation
//covariant	      C[T’] is a subclass of C[T]	      [+T]
//contravariant	  C[T] is a subclass of C[T’]	      [-T]
//invariant	      C[T] and C[T’] are not related	  [T]

class Covariant[+A]
val cov: Covariant[AnyRef] = new Covariant[String] // OK
// KO: val cv: Covariant[String] = new Covariant[AnyRef]

class Contravariant[-A]
val contv: Contravariant[String] = new Contravariant[AnyRef]
// KO: val fail: Contravariant[AnyRef] = new Contravariant[String]

// contravariance
trait Function1 [-T1, +R] extends AnyRef
// bounds
class Animal { val sound = "rustle" }
class Bird extends Animal { override val sound = "call" }
class Chicken extends Bird { override val sound = "cluck" }
def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)
biophony(Seq(new Chicken, new Bird))

// lower bounds
// List defines ::[B >: T](x: B) which returns a List[B]
val flock = List(new Bird, new Bird)
new Animal :: flock // ==> List[Animal]

// wildcards
def count(l: List[_]) = l.size
def count2(l: List[T forSome { type T }]) = l.size
// loose type info
def drop1_2(l: List[_]) = l.tail
// drop1_2: drop1_2[](val l: List[_]) => List[Any]

// wildcards bounds
def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
// KO: primitive not converted to AnyRef => hashcodes(Seq(1,2,3))
hashcodes(Seq("one", "two", "three"))