
package org.godfat.sb

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
