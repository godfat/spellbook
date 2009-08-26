
package org.godfat.sb

import prelude_sb.Maybe

case class Block(val index: Int, val terrain: Terrain, val creature: Maybe[Creature]) extends
  (Terrain, Maybe[Creature])(terrain, creature) with Ordered[Block]
{
  def compare(that: Block) = this.index.compare(that.index)
}
