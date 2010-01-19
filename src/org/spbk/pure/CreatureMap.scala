
package org.spbk

import scala.collection.immutable.TreeMap

package object pure{
  object Footman extends Creature( "Footman",
                                   List(Fire.Innate),
                                   State(Health(100), Mana(10), Energy(50), Vigor(50),
                                   Strength(40), Constitution(20), Imagination(10), Will(10),
                                   Agility(15)),
                                   List(AttackMelee()) )

  val CreatureMap: TreeMap[String, Creature] = TreeMap[String, Creature]() ++ List(
      Footman.name -> Footman
    )
}
