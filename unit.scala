
// type ObjectId = Int
// type Env = TreeMap[ObjectId, Unit]
// type Loc = (Int, Int)

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
// hp
case class       Health(val pt: Int) extends Property[      Health]{ val create =       Health(_) }
// mp
case class         Mana(val pt: Int) extends Property[        Mana]{ val create =         Mana(_) }
// resource
case class       Energy(val pt: Int) extends Property[      Energy]{ val create =       Energy(_) }
// action point
case class        Vigor(val pt: Int) extends Property[       Vigor]{ val create =        Vigor(_) }
// p-atk
case class     Strength(val pt: Int) extends Property[    Strength]{ val create =     Strength(_) }
// p-def
case class Constitution(val pt: Int) extends Property[Constitution]{ val create = Constitution(_) }
// m-atk
case class  Imagination(val pt: Int) extends Property[ Imagination]{ val create =  Imagination(_) }
// m-def
case class         Will(val pt: Int) extends Property[        Will]{ val create =         Will(_) }
// decides speed in vm
case class      Agility(val pt: Int) extends Property[     Agility]{ val create =      Agility(_) }

case class State( val health: Health, val mana: Mana, val energy: Energy, val vigor: Vigor,
                  val strength: Strength, val constitution: Constitution,
                  val imagination: Imagination, val will: Will,
                  val agility: Agility ) extends
                 (Health, Mana, Energy, Vigor,
                  Strength, Constitution, Imagination, Will,
                  Agility)(
                      health, mana, energy, vigor,
                      strength, constitution, imagination, will,
                      agility)
{
  def +[T <: Property[T]](property: T): State = property match{
    case h:       Health => State(_1 + h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:         Mana => State(_1, _2 + m, _3, _4, _5, _6, _7, _8, _9)
    case e:       Energy => State(_1, _2, _3 + e, _4, _5, _6, _7, _8, _9)
    case v:        Vigor => State(_1, _2, _3, _4 + v, _5, _6, _7, _8, _9)
    case s:     Strength => State(_1, _2, _3, _4, _5 + s, _6, _7, _8, _9)
    case c: Constitution => State(_1, _2, _3, _4, _5, _6 + c, _7, _8, _9)
    case i:  Imagination => State(_1, _2, _3, _4, _5, _6, _7 + i, _8, _9)
    case w:         Will => State(_1, _2, _3, _4, _5, _6, _7, _8 + w, _9)
    case a:      Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 + a)
  }

  def -[T <: Property[T]](property: T): State = property match{
    case h:       Health => State(_1 - h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:         Mana => State(_1, _2 - m, _3, _4, _5, _6, _7, _8, _9)
    case e:       Energy => State(_1, _2, _3 - e, _4, _5, _6, _7, _8, _9)
    case v:        Vigor => State(_1, _2, _3, _4 - v, _5, _6, _7, _8, _9)
    case s:     Strength => State(_1, _2, _3, _4, _5 - s, _6, _7, _8, _9)
    case c: Constitution => State(_1, _2, _3, _4, _5, _6 - c, _7, _8, _9)
    case i:  Imagination => State(_1, _2, _3, _4, _5, _6, _7 - i, _8, _9)
    case w:         Will => State(_1, _2, _3, _4, _5, _6, _7, _8 - w, _9)
    case a:      Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 - a)
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
                   Strength(0), Constitution(99), Imagination(0), Will(99),
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
                              Strength(40), Constitution(20), Imagination(10), Will(10),
                              Agility(15)))

println(Fire.Large(Water.Small))
println(Footman)
println(Footman - Health(10))
println(Footman.state.health - Health(5))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
