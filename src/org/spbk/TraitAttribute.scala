
package org.spbk

trait TraitAttribute[T]{
  type This <: TraitAttribute[T]
  val pt: Int
  val create: Int => This

  def +(p: This): This = create(pt + p.pt)
  def -(p: This): This = create(pt - p.pt)
}
