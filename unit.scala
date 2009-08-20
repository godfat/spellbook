
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
  val create = Earth(_) // type constructor
}
object Water extends ElementTrait[Water]{
  val create = Water(_)
}
object  Fire extends ElementTrait[Fire]{
  val create = Fire(_)
}
object   Air extends ElementTrait[Air]{
  val create = Air(_)
}

abstract class Property{
  val pt: Int

  def +(p: Property): Int = pt + p.pt
  def +(i: Int):      Int = pt + i

  def -(p: Property): Int = pt - p.pt
  def -(i: Int):      Int = pt - i
}
case class      Health(val pt: Int) extends Property // hp
case class        Mana(val pt: Int) extends Property // mp
case class      Energy(val pt: Int) extends Property // resource
case class       Vigor(val pt: Int) extends Property // action point
case class    Strength(val pt: Int) extends Property // p-atk
case class   Endurance(val pt: Int) extends Property // p-def
case class Imagination(val pt: Int) extends Property // m-atk
case class        Will(val pt: Int) extends Property // m-def
case class     Agility(val pt: Int) extends Property // decides speed in vm

case class State( val health: Health, val mana: Mana, val energy: Energy, val vigor: Vigor,
                  val strength: Strength, val endurance: Endurance,
                  val imagination: Imagination, val will: Will,
                  val agility: Agility ) extends
                 (Health, Mana, Energy, Vigor,
                  Strength, Endurance, Imagination, Will,
                  Agility)(
                      health, mana, energy, vigor,
                      strength, endurance, imagination, will,
                      agility)
{
  def +(property: Property): State = apply((_: Property) + (_: Int), property)
  def -(property: Property): State = apply((_: Property) - (_: Int), property)

  def apply(f: Function2[Property, Int, Int], property: Property): State = property match{
    case      Health(pt) => State(Health(f(_1, pt)), _2, _3, _4, _5, _6, _7, _8, _9)
    case        Mana(pt) => State(_1, Mana(f(_2, pt)), _3, _4, _5, _6, _7, _8, _9)
    case      Energy(pt) => State(_1, _2, Energy(f(_3, pt)), _4, _5, _6, _7, _8, _9)
    case       Vigor(pt) => State(_1, _2, _3, Vigor(f(_4, pt)), _5, _6, _7, _8, _9)
    case    Strength(pt) => State(_1, _2, _3, _4, Strength(f(_5, pt)), _6, _7, _8, _9)
    case   Endurance(pt) => State(_1, _2, _3, _4, _5, Endurance(f(_6, pt)), _7, _8, _9)
    case Imagination(pt) => State(_1, _2, _3, _4, _5, _6, Imagination(f(_7, pt)), _8, _9)
    case        Will(pt) => State(_1, _2, _3, _4, _5, _6, _7, Will(f(_8, pt)), _9)
    case     Agility(pt) => State(_1, _2, _3, _4, _5, _6, _7, _8, Agility(f(_9, pt)))
  }
}

case class Unit(val     name: String,
                val elememts: List[Element],
                val    state: State)
{
  def +(property: Property): Unit = Unit(name, elememts, state + property)
  def -(property: Property): Unit = Unit(name, elememts, state - property)
}

abstract class Terrain(     name: String,
                        elememts: List[Element],
                           state: State)
         extends Unit(name, elememts, state)
{
  def this(name: String, elements: List[Element]) =
      this(name, elements,
             State(Health(999), Mana(0), Energy(0), Vigor(0),
                   Strength(0), Endurance(99), Imagination(0), Will(99),
                   Agility(0)))
}

case class   Road() extends Terrain(  "Road", Nil)
case class Forest() extends Terrain("Forest", List(Earth.Small, Earth.Small))
case class  River() extends Terrain( "River", List(Water.Small, Water.Small))
case class   Lava() extends Terrain(  "Lava", List( Fire.Small,  Fire.Small))
case class Plains() extends Terrain("Plains", List(  Air.Small,   Air.Small))

val Footman = new Unit( "Footman",
                        List(Fire.Large),
                        State(Health(100), Mana(10), Energy(50), Vigor(50),
                              Strength(40), Endurance(20), Imagination(10), Will(10),
                              Agility(15)))

println(Fire.Large(Water.Small))
println(Footman)
println(Footman - Health(10))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
