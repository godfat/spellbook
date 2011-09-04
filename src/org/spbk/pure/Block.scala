
package org.spbk.pure

import org.spbk.prelude._

case class Block(val index: Int, val terrain: Terrain, val creature: Maybe[Creature]) extends Ordered[Block]
{
  def compare(that: Block) = this.index.compare(that.index)

  // def walk_over(creature: Creature): Creature
  def put_on(foreigner: Creature): Block = creature match{
    case Nothing => {
      val (new_terrain, new_creature) = terrain.put_on(foreigner)
      Block(index, new_terrain, Just(new_creature))
    }

    case Just(current_creature: Creature) =>
      sys.error("already got a creature there: " + current_creature.toString)
  }
}
