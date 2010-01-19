
package org.spbk.pure

trait TraitElement[T]{
  val create: Int => T            // abstract value
  lazy val Innate   = create(100) // lazy value
  lazy val Acquired = create(25)
}
