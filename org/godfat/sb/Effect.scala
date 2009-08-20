
package org.godfat.sb

abstract class Effect
case class Ampify(val pt: Int) extends Effect
case class Absorb(val pt: Int) extends Effect
