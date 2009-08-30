
package org.godfat.sb

object StateCreature extends State(Health(50), Mana(25), Energy(25), Vigor(25),
                                   Strength(25), Constitution(10), Imagination(25), Will(10),
                                   Agility(10))

case class Creature( override val      name: String        = "Creature",
                     override val  elements: List[Element] = Nil,
                     override val     state: State         = StateCreature,
                              val abilities: List[Ability] = Nil,
                              val     buffs: List[Buff]    = Nil ) extends
  Unit(name, elements, state)
{
  type  This = Creature
  val create = Creature(name, elements, _: State, abilities, buffs)
}
