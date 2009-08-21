
package org.godfat.sb

object UnitState extends State(Health(0), Mana(0), Energy(0), Vigor(0),
                               Strength(0), Constitution(0), Imagination(0), Will(0),
                               Agility(0))

abstract class Unit(     name: String        = "Unit",
                     elememts: List[Element] = Nil,
                        state: State         = UnitState )
{
  type This <: Unit
  val create: State => This

  def +(property: Property): This = create(state + property)
  def -(property: Property): This = create(state - property)
}
