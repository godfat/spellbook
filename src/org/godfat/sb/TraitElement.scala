
package org.godfat.sb

trait TraitElement[T]{
  val create: Int => T         // abstract value
  lazy val Large = create(100) // lazy value
  lazy val Small = create(25)
}
