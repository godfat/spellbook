
package org.godfat.sb

sealed abstract class Property{
  type This <: Property
  val pt: Int
  val create: Int => This

  def +(p: This): This = create(pt + p.pt)
  def -(p: This): This = create(pt - p.pt)
}
// hp
case class     Health      (val pt: Int) extends Property{
  type  This = Health
  val create = Health(_)
}
// mp
case class     Mana        (val pt: Int) extends Property{
  type  This = Mana
  val create = Mana(_)
}
// resource
case class     Energy      (val pt: Int) extends Property{
  type  This = Energy
  val create = Energy(_)
}
// action point
case class     Vigor       (val pt: Int) extends Property{
  type  This = Vigor
  val create = Vigor(_)
}
// p-atk
case class     Strength    (val pt: Int) extends Property{
  type  This = Strength
  val create = Strength(_)
}
// p-def
case class     Constitution(val pt: Int) extends Property{
  type  This = Constitution
  val create = Constitution(_)
}
// m-atk
case class     Imagination (val pt: Int) extends Property{
  type  This = Imagination
  val create = Imagination(_)
}
// m-def
case class     Will        (val pt: Int) extends Property{
  type  This = Will
  val create = Will(_)
}
// decides speed in vm
case class     Agility     (val pt: Int) extends Property{
  type  This = Agility
  val create = Agility(_)
}
