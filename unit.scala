
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

sealed abstract class Property[T <: Property[T]]{
  val pt: Int
  val create: Int => T

  def +(p: T): T = create(pt + p.pt)
  def -(p: T): T = create(pt - p.pt)
}
case class      Health(val pt: Int) extends Property[Health]      // hp
{ val create = Health(_) }

case class        Mana(val pt: Int) extends Property[Mana]        // mp
{ val create = Mana(_) }

case class      Energy(val pt: Int) extends Property[Energy]      // resource
{ val create = Energy(_) }

case class       Vigor(val pt: Int) extends Property[Vigor]       // action point
{ val create = Vigor(_) }

case class    Strength(val pt: Int) extends Property[Strength]    // p-atk
{ val create = Strength(_) }

case class   Endurance(val pt: Int) extends Property[Endurance]   // p-def
{ val create = Endurance(_) }

case class Imagination(val pt: Int) extends Property[Imagination] // m-atk
{ val create = Imagination(_) }

case class        Will(val pt: Int) extends Property[Will]        // m-def
{ val create = Will(_) }

case class     Agility(val pt: Int) extends Property[Agility]     // decides speed in vm
{ val create = Agility(_) }

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
  def +[T <: Property[T]](property: T): State = property match{
    case h:      Health => State(_1 + h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:        Mana => State(_1, _2 + m, _3, _4, _5, _6, _7, _8, _9)
    case e:      Energy => State(_1, _2, _3 + e, _4, _5, _6, _7, _8, _9)
    case v:       Vigor => State(_1, _2, _3, _4 + v, _5, _6, _7, _8, _9)
    case s:    Strength => State(_1, _2, _3, _4, _5 + s, _6, _7, _8, _9)
    case e:   Endurance => State(_1, _2, _3, _4, _5, _6 + e, _7, _8, _9)
    case i: Imagination => State(_1, _2, _3, _4, _5, _6, _7 + i, _8, _9)
    case w:        Will => State(_1, _2, _3, _4, _5, _6, _7, _8 + w, _9)
    case a:     Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 + a)
  }

  def -[T <: Property[T]](property: T): State = property match{
    case h:      Health => State(_1 - h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:        Mana => State(_1, _2 - m, _3, _4, _5, _6, _7, _8, _9)
    case e:      Energy => State(_1, _2, _3 - e, _4, _5, _6, _7, _8, _9)
    case v:       Vigor => State(_1, _2, _3, _4 - v, _5, _6, _7, _8, _9)
    case s:    Strength => State(_1, _2, _3, _4, _5 - s, _6, _7, _8, _9)
    case e:   Endurance => State(_1, _2, _3, _4, _5, _6 - e, _7, _8, _9)
    case i: Imagination => State(_1, _2, _3, _4, _5, _6, _7 - i, _8, _9)
    case w:        Will => State(_1, _2, _3, _4, _5, _6, _7, _8 - w, _9)
    case a:     Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 - a)
  }
}

case class Unit(val     name: String,
                val elememts: List[Element],
                val    state: State)
{
  def +[T <: Property[T]](property: T): Unit = Unit(name, elememts, state + property)
  def -[T <: Property[T]](property: T): Unit = Unit(name, elememts, state - property)
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
println(Footman.state.health - Health(5))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
