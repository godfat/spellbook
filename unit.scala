
import scala.collection.immutable.TreeMap
import scala.collection.immutable.SortedMap

type ObjectId = Int
type Env = TreeMap[ObjectId, Unit]
type Loc = (Int, Int)

abstract class Effect
case class Ampify(val pt: Int) extends Effect
case class Absorb(val pt: Int) extends Effect

abstract class Buff
case class Enrage() extends Buff

abstract class Element{
  def apply(e: Element): Effect = (this, e) match{
    case (Fire(p0),  Fire(p1)) => absorb(p0, p1)
    case (Fire(p0), Water(p1)) => weaken(p0, p1)
    case _                     => ampify( 0,  0)
  }

  def absorb(p0: Int, p1: Int) = Absorb(+(p0 + p1))
  def ampify(p0: Int, p1: Int) = Ampify(+(p0 + p1))
  def weaken(p0: Int, p1: Int) = Ampify(-(p0 + p1))
}

case class Earth(val pt: Int) extends Element
case class Water(val pt: Int) extends Element
case class  Fire(val pt: Int) extends Element
case class   Air(val pt: Int) extends Element
case class Light(val pt: Int) extends Element

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

abstract class Unit(    name: String,
                    elememts: List[Element],
                    property: Property,
                       state: Property) // primary constructor
{
  def this(name: String, elememts: List[Element], property: Property) =
      this(name, elememts, property, property)
}

case class Creature(     name: String,
                     elememts: List[Element],
                     property: Property,
                        state: Property)
     extends Unit(name, elememts, property, state)
{
  def this(name: String, elememts: List[Element], property: Property) =
      this(name, elememts, property, property)

  def hp_reduce(n: Int): Creature =
    Creature(name, elememts, property,
             state.update("Health", state("Health") - n))
}

abstract class Terrain(     name: String,
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

val Footman = new Creature( "Footman",
                            List(Fire.Large),
                            Property("Health"      -> 100, // hp
                                     "Mana"        ->  50, // mp
                                     "Energy"      ->  50, // resource
                                     "Vigor"       ->  50, // action point
                                     "Strength"    ->  50, // p-atk
                                     "Endurance"   ->  25, // p-def
                                     "Imagination" ->  10, // m-atk
                                     "Will"        ->  10, // m-def,
                                     "Agility"     ->  15)) // decides speed in vm

println(Fire.Large(Water.Small))
println(Footman)
println(Footman.hp_reduce(10))
// println(Footman - Health(10))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
