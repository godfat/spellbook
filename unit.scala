
import scala.collection.immutable.TreeMap
import scala.collection.immutable.SortedMap

type ObjectId = Int
type Env = TreeMap[ObjectId, Unit]
type Loc = (Int, Int)

abstract class Effect
case class Ampify(val quantity: Int) extends Effect
case class Absorb(val quantity: Int) extends Effect

abstract class Buff
case class Enrage() extends Buff

abstract class Element{
  def apply(e: Element): Effect = (this, e) match{
    case (Fire(q0),  Fire(q1)) => Absorb(+(q0 + q1))
    case (Fire(q0), Water(q1)) => Ampify(-(q0 + q1))
    case _                     => Ampify(0)
  }
}

case class Earth(val quantity: Int) extends Element
case class Water(val quantity: Int) extends Element
case class  Fire(val quantity: Int) extends Element
case class   Air(val quantity: Int) extends Element
case class Light(val quantity: Int) extends Element

trait ElementTrait[T]{
  val create: Int => T         // abstract value
  lazy val Large = create(100) // lazy value
  lazy val Small = create(25)
}

object Earth extends ElementTrait[Earth]{
  override val create = Earth(_) // type constructor
}
object Water extends ElementTrait[Water]{
  override val create = Water(_)
}
object  Fire extends ElementTrait[Fire]{
  override val create = Fire(_)
}
object   Air extends ElementTrait[Air]{
  override val create = Air(_)
}

type Property = SortedMap[String, Int]
val  Property =   TreeMap[String, Int] _

case class Unit(    name: String,
                elememts: List[Element],
                property: Property,
                   state: Property)
{
  def this(name: String, elememts: List[Element], property: Property) =
      this(name, elememts, property, property)

  def hp_reduce(n: Int): Unit =
    Unit(name, elememts, property,
         state.update("hp", state("hp") - n))
}
abstract class Terrain(   name: String,
                      elememts: List[Element],
                      property: Property,
                         state: Property)
         extends Unit(name, elememts, property, state)
{
  def this(name: String, elements: List[Element]) =
      this(name, elements, Property(), Property())
}

case class   Road() extends Terrain(  "Road", Nil)
case class Forest() extends Terrain("Forest", List(Earth.Small, Earth.Small))
case class  River() extends Terrain( "River", List(Water.Small, Water.Small))
case class   Lava() extends Terrain(  "Lava", List( Fire.Small,  Fire.Small))
case class Plains() extends Terrain("Plains", List(  Air.Small,   Air.Small))

val Footman = new Unit( "Footman",
                        List(Fire.Large),
                        Property("hp" -> 200,
                                 "mp" -> 40))

println(Fire.Large(Water.Small))
println(Footman)
println(Footman.hp_reduce(10))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
