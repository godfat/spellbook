
package org.spbk

package object prelude{
  type   Maybe[+A] = Option[A]
  type    Just[+A] =   Some[A]
   val    Just     =   Some
   val Nothing     =   None

  import org.spbk.pure._
  import scala.collection.immutable.TreeMap

  object Footman extends Creature( "Footman",
                                   List(Fire.innate),
                                   State(Health(100), Mana(10), Energy(50), Vigor(50),
                                   Strength(40), Constitution(20), Imagination(10), Will(10),
                                   Agility(15)),
                                   List(AttackMelee()) )

  val CreatureMap: TreeMap[String, Creature] = TreeMap[String, Creature]() ++ List(
      Footman.name -> Footman
    )
}
