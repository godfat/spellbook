
package org.godfat.sb

case class Block(val terrain: Terrain, val creature: Creature) extends
  (Terrain, Creature)(terrain, creature)
