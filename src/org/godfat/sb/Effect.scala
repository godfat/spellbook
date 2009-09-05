
package org.godfat.sb

abstract class Effect extends TraitAttribute[Effect]

case class Ampify(val pt: Int = 0) extends Effect{
  type  This = Ampify
  val create = Ampify(_)
}

case class Absorb(val pt: Int = 0) extends Effect{
  type  This = Absorb
  val create = Absorb(_)
}
