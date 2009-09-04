
package org.godfat.sb

object StateUnit extends State(Health(0), Mana(0), Energy(0), Vigor(0),
                               Strength(0), Constitution(0), Imagination(0), Will(0),
                               Agility(0))

abstract class Unit
{
  type This <: Unit
  val create: State => This

  val     name: String
  val elements: List[Element]
  val    state: State

  def +(property: Property): This = create(state + property)
  def -(property: Property): This = create(state - property)
}
