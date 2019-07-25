// Copyright(C) 2018 - John A. De Goes. All rights reserved.

package net.degoes.essentials

object types {
  type ??? = Nothing

  //
  // EXERCISE 1
  //
  // List all values of the type `Unit`.
  //
  val UnitValues: Set[Unit] = Set(())

  //
  // EXERCISE 2
  //
  // List all values of the type `Nothing`.
  //
  val NothingValues: Set[Nothing] = Set()

  //
  // EXERCISE 3
  //
  // List all values of the type `Boolean`.
  //
  val BoolValues: Set[Boolean] = Set(true, false)

  //
  // EXERCISE 4
  //
  // List all values of the type `Either[Unit, Boolean]`.
  //
  val EitherUnitBoolValues: Set[Either[Unit, Boolean]] = Set(Left(()), Right(true), Right(false))

  //
  // EXERCISE 5
  //
  // List all values of the type `(Boolean, Boolean)`.
  //
  val TupleBoolBoolValues: Set[(Boolean, Boolean)] = Set((true, true), (true, false), (false, true), (false, false))

  //
  // EXERCISE 6
  //
  // List all values of the type `Either[Either[Unit, Unit], Unit]`.
  //
  val EitherEitherUnitUnitUnitValues: Set[Either[Either[Unit, Unit], Unit]] =
    Set(Left(Left(())), Left(Right(())), Right(()))

  //
  // EXERCISE 7
  //
  // Create a product type of `Int` and `String`, representing the age and
  // name of a person.
  //
  // type Person = (Int, String)
  case class Person(name: String, age: Int)

  //
  // EXERCISE 8
  //
  // Prove that `A * 1` is equivalent to `A` by implementing the following two
  // functions.
  //
  def to1[A](t: (A, Unit)): A = t._1
  def from1[A](a: A): (A, Unit) = (a, ())

  // assert(to1(from1(2)) == 3 )

  //
  // EXERCISE 9
  //
  // Prove that `A * 0` is equivalent to `0` by implementing the following two
  // functions.
  //
  def to2[A](t: (A, Nothing)): Nothing = ???
  def from2[A](n: Nothing): (A, Nothing) = ???

  //
  // EXERCISE 10
  //
  // Create a sum type of `Int` and `String` representing the identifier of
  // a robot (a number) or a person (a name).
  //
  type Identifier = Either[Int, String]

  //
  // EXERCISE 11
  //
  // Prove that `A + 0` is equivalent to `A` by implementing the following two
  // functions.
  //
  def to3[A](t: Either[A, Nothing]): A =
    t match {
      case Left(a) => a
      case Right(n) => n
    }
  def from3[A](a: A): Either[A, Nothing] = Left(a)

  //
  // EXERCISE 12
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // credit card, which has a number, an expiration date, and a security code.
  //
  // type CreditCard = ???
  import java.time.LocalDateTime
  // import scala.reflect.{ ClassTag, ClassTag }
  type CreditCardNumber
  type SecurityCode
  case class CreditCard(number: CreditCardNumber, expirationDate: LocalDateTime, securityCode: SecurityCode)

  //
  // EXERCISE 13
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // payment method, which could be a credit card, bank account, or
  // cryptocurrency.
  //
  // type PaymentMethod = ???

  sealed trait PaymentMethod
  object PaymentMethod {
    object CreditCardMethod extends PaymentMethod
    object BankAccountMethod extends PaymentMethod
    object CryptocurrencyMethod extends PaymentMethod
  }

  //
  // EXERCISE 14
  //
  // Create either a sum type or a product type (as appropriate) to represent an
  // employee at a company, which has a title, salary, name, and employment date.
  //
  type Salary
  type EmployerTitle
  type EmployeeName
  type EmploymentDate
  case class Employee1(salary: Salary, title: EmployerTitle, name: EmployeeName, startDate: EmploymentDate)
  type Employee = Employee1

  //
  // EXERCISE 15
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // piece on a chess board, which could be a pawn, rook, bishop, knight,
  // queen, or king.
  //

  sealed trait ChessPiece1
  object ChessPiece1 {
    case object Pawn extends ChessPiece1
    case object Rook extends ChessPiece1
    case object Bishop extends ChessPiece1
    case object Knight extends ChessPiece1
  }

  type ChessPiece = ChessPiece1

  //
  // EXERCISE 16
  //
  // Create an ADT model of a game world, including a map, a player, non-player
  // characters, different classes of items, and character stats.
  //
  /// John De Goes solution:
  // case class GameWorld(
  //     map     : GameMap,
  //     player  : Character,
  //     time    : Time)

  //   case class Character(
  //     name      : String,
  //     charClass : CharClass,
  //     position  : Cell,
  //     stats     : Stats,
  //     inventory : List[Item])

  //   case class Cell(
  //     name        : String,
  //     items       : List[Item],
  //     characters  : List[Character])

  //   case class GameMap(
  //     cells   : Set[Cell],
  //     routes  : Map[Cell, Set[Cell]])

  //   case class Stats(health: Int, stamina: Int)

  //   sealed trait CharClass
  //   object CharClass {
  //     case object Witch   extends CharClass
  //     case object Warlock extends CharClass
  //     case object Wizard  extends CharClass
  //     case object Human   extends CharClass
  //     case object Orc     extends CharClass
  //     case object Elf     extends CharClass
  //     case object Dwarf   extends CharClass
  // }
  type GameWorld = ???
  type Player
  type GameMap = (Int, Int)
  sealed trait NonPlayer

}

object functions {
  type ??? = Nothing
  type Foo = String

  // 1. Every function is total
  // for all x in the domain ,g f(x) is in the codomain
  // 2. Determinism
  // if x == y f(x) = f(y)
  // 3. free of  side effect
  //   the only effect of applying f to x is computing f(x)
  //
  // EXERCISE 1
  //
  // Convert the following non-function into a function.
  //
  // violate Totality
  def parseInt1(s: String): Int = s.toInt
  def parseInt2(s: String): Option[Int] = try {
    Some(s.toInt)
  } catch {
    case _: Exception => None
  }
  import scala.util.Try
  def parseInt3(s: String): Try[Int] = Try(s.toInt)

  //
  // EXERCISE 2
  //
  // Convert the following non-function into a function.
  //
  // non-deterministic, totality
  def arrayUpdate1[A](arr: Array[A], i: Int, f: A => A): Unit = ???
  // arr.update(i, f(arr(i)))
  import scala.util.Try
  // import scala.reflect.ClassTag

  // def arrayUpdate2[A: ClassTag](arr: Array[A], i: Int, f: A => A): Try[Array[A]] = Try { arr.updated(i, f(arr(i))).toArray }
  // // def arrayUpdate2[A : ClassTag](arr: Array[A], i: Int, f: A => A):Try[Array[A]] = Try{
  //   arr.updated(i, f(arr(i))).toArray
  // }
  //   Try {
  //     arr.updated(i, f(arr(i))).toArray
  //   }

  //
  // EXERCISE 3
  //
  // Convert the following non-function into a function.
  // totality
  def divide1(a: Int, b: Int): Int = a / b
  def divide2(a: Int, b: Int): Option[Int] = if (b == 0) None else Some(a / b)

  //
  // EXERCISE 4
  //
  // Convert the following non-function into a function.
  // side effect
  var id = 0
  def freshId1(): Int = {
    val newId = id
    id += 1
    newId
  }
  def freshId2(id: Int): (Int, Int) = (id, id + 1)

  //
  // EXERCISE 5
  //
  // Convert the following non-function into a function.
  // non deterministic
  import java.time.LocalDateTime
  def afterOneHour1: LocalDateTime = LocalDateTime.now.plusHours(1)
  def afterOneHour2(time: LocalDateTime): LocalDateTime = time.plusHours(1)

  //
  // EXERCISE 6
  //
  // Convert the following non-function into function.
  // side effect in the form of output to stdout
  // totality - is not defined on an empty list
  def head1[A](as: List[A]): A = {
    if (as.length == 0) println("Oh no, it's impossible!!!")
    as.head
  }
  def head2[A](as: List[A]): Either[String, A] =
    if (as.length == 0)
      Left("Oh no, it's impossible!!!")
    else
      Right(as.head)

  //
  // EXERCISE 7
  //
  // Convert the following non-function into a function.
  //
  trait Account
  trait Processor {
    def charge(account: Account, amount: Double): Unit
  }
  case class Coffee() {
    val price = 3.14
  }
  def buyCoffee1(processor: Processor, account: Account): Coffee = {
    val coffee = Coffee()
    processor.charge(account, coffee.price)
    coffee
  }
  final case class Charge(amount: Double)
  def buyCoffee2(account: Account): (Coffee, Charge) = {
    ???
  }

  //
  // EXERCISE 8
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def printLine(line: String): Unit = ???

  //
  // EXERCISE 9
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def readLine: String = ???

  //
  // EXERCISE 10
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def systemExit(code: Int): Unit = ???

  //
  // EXERCISE 11
  //
  // Rewrite the following non-function `printer1` into a pure function, which
  // could be used by pure or impure code.
  //
  def printer1(): Unit = {
    println("Welcome to the help page!")
    println("To list commands, type `commands`.")
    println("For help on a command, type `help <command>`")
    println("To exit the help page, type `exit`.")
  }
  def printer2[A](println: String => A, combine: (A, A) => A): A =
    ???

  //
  // EXERCISE 12
  //
  // Create a purely-functional drawing library that is equivalent in
  // expressive power to the following procedural library.
  //
  trait Draw {
    def goLeft(): Unit
    def goRight(): Unit
    def goUp(): Unit
    def goDown(): Unit
    def draw(): Unit
    def finish(): List[List[Boolean]]
  }
  def draw1(size: Int): Draw = new Draw {
    val canvas = Array.fill(size, size)(false)
    var x = 0
    var y = 0

    def goLeft(): Unit = x -= 1
    def goRight(): Unit = x += 1
    def goUp(): Unit = y += 1
    def goDown(): Unit = y -= 1
    def draw(): Unit = {
      def wrap(x: Int): Int =
        if (x < 0) (size - 1) + ((x + 1) % size) else x % size

      val x2 = wrap(x)
      val y2 = wrap(y)

      canvas.updated(x2, canvas(x2).updated(y2, true))
    }
    def finish(): List[List[Boolean]] =
      canvas.map(_.toList).toList
  }
  def draw2(size: Int /* ... */ ): ??? = ???
}

object higher_order {
  //
  // EXERCISE 1
  //
  // Implement the following higher-order function.
  //
  def fanout[A, B, C](f: A => B, g: A => C): A => (B, C) =
    ???

  //
  // EXERCISE 2
  //
  // Implement the following higher-order function.
  //
  def cross[A, B, C, D](f: A => B, g: C => D): (A, C) => (B, D) =
    ???

  //
  // EXERCISE 3
  //
  // Implement the following higher-order function.
  //
  def either[A, B, C](f: A => B, g: C => B): Either[A, C] => B =
    ???

  //
  // EXERCISE 4
  //
  // Implement the following higher-order function.
  //
  def choice[A, B, C, D](f: A => B, g: C => D): Either[A, C] => Either[B, D] =
    ???

  //
  // EXERCISE 5
  //
  // Implement the following higer-order function.
  //
  def compose[A, B, C](f: B => C, g: A => B): A => C =
    ???

  //
  // EXERCISE 6
  //
  // Implement the following higher-order function. After you implement
  // the function, interpret its meaning.
  //
  def alt[E1, E2, A, B](l: Parser[E1, A], r: E1 => Parser[E2, B]): Parser[E2, Either[A, B]] = ???

  case class Parser[+E, +A](
    run: String => Either[E, (String, A)])
  object Parser {
    final def fail[E](e: E): Parser[E, Nothing] =
      Parser(input => Left(e))

    final def point[A](a: => A): Parser[Nothing, A] =
      Parser(input => Right((input, a)))

    final def char[E](e: E): Parser[E, Char] =
      Parser(input =>
        if (input.length == 0) Left(e)
        else Right((input.drop(1), input.charAt(0))))
  }
}

object poly_functions {
  //
  // EXERCISE 1
  //
  // Create a polymorphic function of two type parameters `A` and `B` called
  // `snd` that returns the second element out of any pair of `A` and `B`.
  //
  object snd {
    ???
  }
  // snd((1, "foo")) // "foo"

  //
  // EXERCISE 2
  //
  // Create a polymorphic function called `repeat` that can take any
  // function `A => A`, and apply it repeatedly to a starting value
  // `A` the specified number of times.
  //
  object repeat {
    ???
  }
  // repeat[   Int](100)( 0, _ +   1) // 100
  // repeat[String]( 10)("", _ + "*") // "**********"

  //
  // EXERCISE 3
  //
  // Count the number of unique implementations of the following method.
  //
  def countExample1[A, B](a: A, b: B): Either[A, B] = ???
  val countExample1Answer = ???

  //
  // EXERCISE 4
  //
  // Count the number of unique implementations of the following method.
  //
  def countExample2[A, B](f: A => B, g: A => B, a: A): B = ???
  val countExample2Answer = ???

  //
  // EXERCISE 5
  //
  // Implement the function `groupBy`.
  //
  val Data =
    "poweroutage;2018-09-20;level=20" :: Nil
  val By: String => String =
    (data: String) => data.split(";")(1)
  val Reducer: (String, List[String]) => String =
    (date, events) =>
      "On date " +
        date + ", there were " +
        events.length + " power outages"
  val Expected =
    Map("2018-09-20" ->
      "On date 2018-09-20, there were 1 power outages")
  def groupBy1(
    l: List[String],
    by: String => String)(
    reducer: (String, List[String]) => String): Map[String, String] = ???
  // groupBy1(Data, By)(Reducer) == Expected

  //
  // EXERCISE 6
  //
  // Make the function `groupBy1` as polymorphic as possible and implement
  // the polymorphic function. Compare to the original.
  //
  object groupBy2 {
    ???
  }
}

object higher_kinded {
  type ?? = Nothing
  type ???[A] = Nothing
  type ????[A, B] = Nothing
  type ?????[F[_]] = Nothing

  trait `* => *`[F[_]]
  trait `[*, *] => *`[F[_, _]]
  trait `(* => *) => *`[T[_[_]]]

  //
  // EXERCISE 1
  //
  // Identify a type constructor that takes one type parameter of kind `*`
  // (i.e. has kind `* => *`), and place your answer inside the square brackets.
  //
  type Answer1 = `* => *`[???]

  //
  // EXERCISE 2
  //
  // Identify a type constructor that takes two type parameters of kind `*` (i.e.
  // has kind `[*, *] => *`), and place your answer inside the square brackets.
  //
  type Answer2 = `[*, *] => *`[????]

  //
  // EXERCISE 3
  //
  // Create a new type called `Answer3` that has kind `*`.
  //
  trait Answer3 /*[]*/

  //
  // EXERCISE 4
  //
  // Create a trait with kind `[*, *, *] => *`.
  //
  trait Answer4 /*[]*/

  //
  // EXERCISE 5
  //
  // Create a new type that has kind `(* => *) => *`.
  //
  type NewType1 /* ??? */
  type Answer5 = `(* => *) => *`[?????]

  //
  // EXERCISE 6
  //
  // Create a trait with kind `[* => *, (* => *) => *] => *`.
  //
  trait Answer6[F[_], G[_[_]]] /*[]*/

  //
  // EXERCISE 7
  //
  // Create an implementation of the trait `CollectionLike` for `List`.
  //
  trait CollectionLike[F[_]] {
    def empty[A]: F[A]

    def cons[A](a: A, as: F[A]): F[A]

    def uncons[A](as: F[A]): Option[(A, F[A])]

    final def singleton[A](a: A): F[A] =
      cons(a, empty[A])

    final def append[A](l: F[A], r: F[A]): F[A] =
      uncons(l) match {
        case Some((l, ls)) => append(ls, cons(l, r))
        case None => r
      }

    final def filter[A](fa: F[A])(f: A => Boolean): F[A] =
      bind(fa)(a => if (f(a)) singleton(a) else empty[A])

    final def bind[A, B](fa: F[A])(f: A => F[B]): F[B] =
      uncons(fa) match {
        case Some((a, as)) => append(f(a), bind(as)(f))
        case None => empty[B]
      }

    final def fmap[A, B](fa: F[A])(f: A => B): F[B] = {
      val single: B => F[B] = singleton[B](_)

      bind(fa)(f andThen single)
    }
  }
  val ListCollectionLike: CollectionLike[List] =
    ???

  //
  // EXERCISE 8
  //
  // Implement `Sized` for `List`.
  //
  trait Sized[F[_]] {
    // This method will return the number of `A`s inside `fa`.
    def size[A](fa: F[A]): Int
  }
  val ListSized: Sized[List] = ???

  //
  // EXERCISE 9
  //
  // Implement `Sized` for `Map`, partially applied with its first type
  // parameter to `String`.
  //
  val MapStringSized: Sized[Map[String, ?]] = ???

  //
  // EXERCISE 9
  //
  // Implement `Sized` for `Map`, partially applied with its first type
  // parameter to a user-defined type parameter.
  //
  def MapSized2[K]: Sized[Map[K, ?]] = ???

  //
  // EXERCISE 10
  //
  // Implement `Sized` for `Tuple3`.
  //
  def Tuple3Sized: ?? = ???
}

object typeclasses {
  /**
   * {{
   * Reflexivity:   a ==> equals(a, a)
   *
   * Transitivity:  equals(a, b) && equals(b, c) ==>
   *                equals(a, c)
   *
   * Symmetry:      equals(a, b) ==> equals(b, a)
   * }}
   */
  trait Eq[A] {
    def equals(l: A, r: A): Boolean
  }
  object Eq {
    def apply[A](implicit eq: Eq[A]): Eq[A] = eq

    implicit val EqInt: Eq[Int] = new Eq[Int] {
      def equals(l: Int, r: Int): Boolean = l == r
    }
    implicit def EqList[A: Eq]: Eq[List[A]] =
      new Eq[List[A]] {
        def equals(l: List[A], r: List[A]): Boolean =
          (l, r) match {
            case (Nil, Nil) => true
            case (Nil, _) => false
            case (_, Nil) => false
            case (l :: ls, r :: rs) =>
              Eq[A].equals(l, r) && equals(ls, rs)
          }
      }
  }
  implicit class EqSyntax[A](val l: A) extends AnyVal {
    def ===(r: A)(implicit eq: Eq[A]): Boolean =
      eq.equals(l, r)
  }

  //
  // Scalaz 7 Encoding
  //
  sealed trait Ordering
  case object EQUAL extends Ordering
  case object LT extends Ordering
  case object GT extends Ordering
  object Ordering {
    implicit val OrderingEq: Eq[Ordering] = new Eq[Ordering] {
      def equals(l: Ordering, r: Ordering): Boolean =
        (l, r) match {
          case (EQUAL, EQUAL) => true
          case (LT, LT) => true
          case (GT, GT) => true
          case _ => false
        }
    }
  }

  trait Ord[A] {
    def compare(l: A, r: A): Ordering
  }
  object Ord {
    def apply[A](implicit A: Ord[A]): Ord[A] = A

    implicit val OrdInt: Ord[Int] = new Ord[Int] {
      def compare(l: Int, r: Int): Ordering =
        if (l < r) LT else if (l > r) GT else EQUAL
    }
  }
  implicit class OrdSyntax[A](val l: A) extends AnyVal {
    def =?=(r: A)(implicit A: Ord[A]): Ordering =
      A.compare(l, r)

    def <(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), LT)

    def <=(r: A)(implicit A: Ord[A]): Boolean =
      (l < r) || (this === r)

    def >(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), GT)

    def >=(r: A)(implicit A: Ord[A]): Boolean =
      (l > r) || (this === r)

    def ===(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), EQUAL)

    def !==(r: A)(implicit A: Ord[A]): Boolean =
      !Eq[Ordering].equals(A.compare(l, r), EQUAL)
  }
  case class Person(age: Int, name: String)
  object Person {
    implicit val OrdPerson: Ord[Person] = new Ord[Person] {
      def compare(l: Person, r: Person): Ordering =
        if (l.age < r.age) LT else if (l.age > r.age) GT
        else if (l.name < r.name) LT else if (l.name > r.name) GT
        else EQUAL
    }
    implicit val EqPerson: Eq[Person] = new Eq[Person] {
      def equals(l: Person, r: Person): Boolean =
        l == r
    }
  }

  //
  // EXERCISE 1
  //
  // Write a version of `sort1` called `sort2` that uses the polymorphic `List`
  // type constructor, and which uses the `Ord` type class, including the
  // compare syntax operator `=?=` to compare elements.
  //
  def sort1(l: List[Int]): List[Int] = l match {
    case Nil => Nil
    case x :: xs =>
      val (lessThan, notLessThan) = xs.partition(_ < x)

      sort1(lessThan) ++ List(x) ++ sort1(notLessThan)
  }
  def sort2[A: Ord](l: List[A]): List[A] = ???

  //
  // EXERCISE 2
  //
  // Create a data structure and an instance of this type class for the data
  // structure.
  //
  trait PathLike[A] {
    def child(parent: A, name: String): A

    def parent(node: A): Option[A]

    def root: A
  }
  sealed trait MyPath
  implicit val MyPathPathLike: PathLike[MyPath] = ???

  //
  // EXERCISE 3
  //
  // Create an instance of the `PathLike` type class for `java.io.File`.
  //
  implicit val FilePathLike: PathLike[java.io.File] = ???

  //
  // EXERCISE 4
  //
  // Create two laws for the `PathLike` type class.
  //
  object path_like_laws {
    ???
  }

  //
  // EXERCISE 5
  //
  // Create a syntax class for path-like values with a `/` method that descends
  // into the given named node.
  //
  implicit class PathLikeSyntax[A](a: A) {
    ???
  }

  //
  // EXERCISE 6
  //
  // Create an instance of the `Filterable` type class for `List`.
  //
  trait Filterable[F[_]] {
    def filter[A](fa: F[A], f: A => Boolean): F[A]
  }
  implicit val FilterableList: Filterable[List] = ???

  //
  // EXERCISE 7
  //
  // Create a syntax class for `Filterable` that lets you call `.filter` on any
  // type for which there exists a `Filterable` instance.
  //
  implicit class FilterableSyntax[F[_], A](fa: F[A]) {
    ???
  }

  //
  //
  // EXERCISE 8
  //
  // Create an instance of the `Collection` type class for `List`.
  //
  trait Collection[F[_]] {
    def empty[A]: F[A]
    def cons[A](a: A, as: F[A]): F[A]
    def uncons[A](fa: F[A]): Option[(A, F[A])]
  }
  object Collection {
    def apply[F[_]](implicit F: Collection[F]): Collection[F] = F
  }
  implicit val ListCollection: Collection[List] = ???

  val example = Collection[List].cons(1, Collection[List].empty)
}
