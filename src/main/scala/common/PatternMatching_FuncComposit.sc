// functional composition
def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"

// compose
// f(g())
val fComposeG = f _ compose g _
fComposeG("yay")

// andThen
// g(f())
val fAndThenG = f _ andThen g _
fAndThenG("yay")

// partial function
val one: PartialFunction[Int, String] = { case 1 => "one" }
one.isDefinedAt(1)
one.isDefinedAt(2)
one(1)
// composed
val two: PartialFunction[Int, String] = { case 2 => "two" }
val three: PartialFunction[Int, String] = { case 3 => "three" }
val wildcard: PartialFunction[Int, String] = { case _ => "something else" }
val partial = one orElse two orElse three orElse wildcard
partial(5)
partial(3)
partial(2)
partial(1)
partial(0)

// case class as partial function ==> subtype of Function
case class PhoneExt(name: String, ext: Int)
val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
extensions.filter { case PhoneExt(name, extension) => extension < 200 }
