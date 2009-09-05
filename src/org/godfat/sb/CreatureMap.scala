
package org.godfat

import scala.collection.immutable.TreeMap

package object sb{
  object Footman extends Creature( "Footman",
                                   List(Fire.Innate),
                                   State(Health(100), Mana(10), Energy(50), Vigor(50),
                                   Strength(40), Constitution(20), Imagination(10), Will(10),
                                   Agility(15)),
                                   List(MeleeAttack()) )

  val CreatureMap: TreeMap[String, Creature] = TreeMap[String, Creature]() ++ List(
      Footman.name -> Footman
    )
}
