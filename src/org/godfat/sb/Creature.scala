
package org.godfat.sb

case class Creature(      name: String,
                      elements: List[Element],
                         state: State,
                     abilities: List[Ability]) extends Unit(name, elements, state)
{
  type  This = Creature
  val create = Creature(name, elements, _: State, abilities)
}

object Footman extends Creature( "Footman",
                                 List(Fire.Large),
                                 State(Health(100), Mana(10), Energy(50), Vigor(50),
                                 Strength(40), Constitution(20), Imagination(10), Will(10),
                                 Agility(15)),
                                 List() )
