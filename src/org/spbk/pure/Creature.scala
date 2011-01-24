
package org.spbk.pure

object StateCreature extends State(Health(50), Mana(25), Energy(25), Vigor(25),
                                   Strength(25), Constitution(10), Imagination(25), Will(10),
                                   Agility(10))

case class Creature( val       name: String          = "Creature",
                     val   elements: List[Element]   = Nil,
                     val      state: State           = StateCreature,
                     val     skills: List[Skill]     = Nil,
                     // val features: List[Predicate] = Nil,
                     // val   abilites: List[Ability]   = Nil,
                     val      buffs: List[Buff]      = Nil  )
                     extends Unit//(features, abilites)
{
  type  This = Creature
  val create = Creature(name, elements, _: State, skills, buffs)
}
