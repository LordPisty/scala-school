// list
List(1, 2, 3)
// cons
1 :: 2 :: 3 :: Nil

// set ==> no duplicates
Set(1, 1, 2)
// def contains(key: A): Boolean
// def +(elem: A): Set[A]
// def -(elem: A): Set[A]

// seq ==> order
Seq(1, 1, 2)

// map
Map('a' -> 1, 'b' -> 2)
// -> is a method that returns a Tuple.
"a" -> 2
"a".->(2)
Map.empty ++ List(("a", 1), ("b", 2), ("c", 3))

// Traversable ==> combinators through foreach
// def head : A
// def tail : Traversable[A]

// Iterable ==> iterator()
// def hasNext(): Boolean
// def next(): A

// def groupBy [K] (f: (A) => K) : Map[K, Traversable[A]]
val a = List(1,1,2,3,4,5,5,6)
a.groupBy((x: Int) => 'a' * x)

// conversion
/*def toArray : Array[A]
def toArray [B >: A] (implicit arg0: ClassManifest[B]) : Array[B]
def toBuffer [B >: A] : Buffer[B]
def toIndexedSeq [B >: A] : IndexedSeq[B]
def toIterable : Iterable[A]
def toIterator : Iterator[A]
def toList : List[A]
def toMap [T, U] (implicit ev: <:<[A, (T, U)]) : Map[T, U]
def toSeq : Seq[A]
def toSet [B >: A] : Set[B]
def toStream : Stream[A]
def toString () : String
def toTraversable : Traversable[A]*/

// range
for (i <- 1 to 3) { println(i) }
// with combinators
(1 to 3).map { i => i }

// default implementations
Iterable(1, 2)
Seq(1, 2)
Set(1, 2)

// mutable
// HashMap defines getOrElseUpdate, +=
val numbers = collection.mutable.Map(1 -> 2)
numbers.get(1)
numbers.getOrElseUpdate(2, 3)
numbers
numbers += (4 -> 1)

// java conversions
// asScala / asJava
import scala.collection.JavaConverters._
val sl = new scala.collection.mutable.ListBuffer[Int]
val jl : java.util.List[Int] = sl.asJava
val sl2 : scala.collection.mutable.Buffer[Int] = jl.asScala
assert(sl eq sl2)

// 2-way conversions
/*scala.collection.Iterable <=> java.lang.Iterable
scala.collection.Iterable <=> java.util.Collection
scala.collection.Iterator <=> java.util.{ Iterator, Enumeration }
scala.collection.mutable.Buffer <=> java.util.List
scala.collection.mutable.Set <=> java.util.Set
scala.collection.mutable.Map <=> java.util.{ Map, Dictionary }
scala.collection.mutable.ConcurrentMap <=> java.util.concurrent.ConcurrentMap*/

