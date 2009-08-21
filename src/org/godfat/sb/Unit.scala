
package org.godfat.sb

abstract class Unit(     name: String,
                     elememts: List[Element],
                        state: State)
{
  type This <: Unit
  val create: State => This

  def +(property: Property): This = create(state + property)
  def -(property: Property): This = create(state - property)
}
