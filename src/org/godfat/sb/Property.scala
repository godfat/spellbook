
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
