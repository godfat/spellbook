
package org.godfat.sb

trait TraitProperty[T]{
  type This <: TraitProperty[T]
  val pt: Int
  val create: Int => This

  def +(p: This): This = create(pt + p.pt)
  def -(p: This): This = create(pt - p.pt)
}
