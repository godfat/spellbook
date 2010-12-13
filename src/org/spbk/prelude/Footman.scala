
package org.spbk.prelude
import org.spbk.pure._

object Footman extends Creature( "Footman",
                                 List(Fire.innate),
                                 State(Health(100), Mana(10), Energy(50), Vigor(50),
                                 Strength(40), Constitution(20), Imagination(10), Will(10),
                                 Agility(15)),
                                 List(AttackMelee) )
