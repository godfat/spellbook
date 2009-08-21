
package org.godfat.sb

sealed abstract class Property{
  type T <: Property
  val pt: Int
  val create: Int => T

  def +(p: T): T = create(pt + p.pt)
  def -(p: T): T = create(pt - p.pt)
}
// hp
case class     Health      (val pt: Int) extends Property{
  type     T = Health
  val create = Health(_)
}
// mp
case class     Mana        (val pt: Int) extends Property{
  type     T = Mana
  val create = Mana(_)
}
// resource
case class     Energy      (val pt: Int) extends Property{
  type     T = Energy
  val create = Energy(_)
}
// action point
case class     Vigor       (val pt: Int) extends Property{
  type     T = Vigor
  val create = Vigor(_)
}
// p-atk
case class     Strength    (val pt: Int) extends Property{
  type     T = Strength
  val create = Strength(_)
}
// p-def
case class     Constitution(val pt: Int) extends Property{
  type     T = Constitution
  val create = Constitution(_)
}
// m-atk
case class     Imagination (val pt: Int) extends Property{
  type     T = Imagination
  val create = Imagination(_)
}
// m-def
case class     Will        (val pt: Int) extends Property{
  type     T = Will
  val create = Will(_)
}
// decides speed in vm
case class     Agility     (val pt: Int) extends Property{
  type     T = Agility
  val create = Agility(_)
}
