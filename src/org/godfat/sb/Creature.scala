
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

object Footman extends Creature( "Footman",
                                 List(Fire.Large),
                                 State(Health(100), Mana(10), Energy(50), Vigor(50),
                                 Strength(40), Constitution(20), Imagination(10), Will(10),
                                 Agility(15)),
                                 List(MeleeAttack()) )
