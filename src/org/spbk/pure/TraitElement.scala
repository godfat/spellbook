
package org.spbk.pure

trait TraitElement[T]{
  val create: Int => T            // abstract value
  lazy val innate   = create(100) // lazy value
  lazy val acquired = create(25)
}
