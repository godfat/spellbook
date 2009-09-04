
package org.godfat.sb

sealed abstract class Effect extends TraitProperty[Effect]

case class Ampify(val pt: Int) extends Effect{
  type  This = Ampify
  val create = Ampify(_)
}

case class Absorb(val pt: Int) extends Effect{
  type  This = Absorb
  val create = Absorb(_)
}
