
package org.godfat.sb

case class Unit(val     name: String,
                val elememts: List[Element],
                val    state: State)
{
  def +(property: Property): Unit = Unit(name, elememts, state + property)
  def -(property: Property): Unit = Unit(name, elememts, state - property)
}

object Footman extends Unit( "Footman",
                             List(Fire.Large),
                             State(Health(100), Mana(10), Energy(50), Vigor(50),
                             Strength(40), Constitution(20), Imagination(10), Will(10),
                             Agility(15)))
