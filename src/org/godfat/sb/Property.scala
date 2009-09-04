
package org.godfat.sb

sealed abstract class Property extends TraitAttribute[Property]

// hp
case class     Health      (pt: Int) extends Property{
  type  This = Health
  val create = Health(_)
}
// mp
case class     Mana        (pt: Int) extends Property{
  type  This = Mana
  val create = Mana(_)
}
// resource
case class     Energy      (pt: Int) extends Property{
  type  This = Energy
  val create = Energy(_)
}
// action point
case class     Vigor       (pt: Int) extends Property{
  type  This = Vigor
  val create = Vigor(_)
}
// p-atk
case class     Strength    (pt: Int) extends Property{
  type  This = Strength
  val create = Strength(_)
}
// p-def
case class     Constitution(pt: Int) extends Property{
  type  This = Constitution
  val create = Constitution(_)
}
// m-atk
case class     Imagination (pt: Int) extends Property{
  type  This = Imagination
  val create = Imagination(_)
}
// m-def
case class     Will        (pt: Int) extends Property{
  type  This = Will
  val create = Will(_)
}
// decides speed in vm
case class     Agility     (pt: Int) extends Property{
  type  This = Agility
  val create = Agility(_)
}
