
package org.godfat.sb

case class Block(val index: Int, val terrain: Terrain, val creature: Creature) extends
  (Terrain, Creature)(terrain, creature) with Ordered[Block]
{
  def compare(that: Block) = this.index.compare(that.index)
}
