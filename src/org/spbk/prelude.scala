
package org.spbk

package object prelude{
  type   Maybe[+A] = Option[A]
  type    Just[+A] =   Some[A]
   val    Just     =   Some
   val Nothing     =   None

  import org.spbk.pure._
  import scala.collection.immutable.TreeMap

  val CreatureMap: TreeMap[String, Creature] = TreeMap[String, Creature]() ++ List(
      Footman.name -> Footman
    )
}
