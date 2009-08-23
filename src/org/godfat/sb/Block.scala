
package org.godfat.sb

case class Block(val index: Int, val terrain: Terrain, val creature: Creature) extends
  (Terrain, Creature)(terrain, creature)
