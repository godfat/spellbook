
package org.godfat.sb

import prelude_sb._

case class Block(val index: Int, val terrain: Terrain, val creature: Maybe[Creature]) extends
  (Terrain, Maybe[Creature])(terrain, creature) with Ordered[Block]
{
  def compare(that: Block) = this.index.compare(that.index)

  // def walk_over(creature: Creature): Creature
  def stay_here(foreigner: Creature): Block = creature match{
    case Nothing => {
      val (new_terrain, new_creature) = terrain.stay_here(foreigner)
      Block(index, new_terrain, Just(new_creature))
    }

    case Just(current_creature: Creature) =>
      error("already got a creature there: " + current_creature.toString)
  }
}
